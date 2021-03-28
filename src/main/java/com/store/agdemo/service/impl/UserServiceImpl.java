package com.store.agdemo.service.impl;

import com.store.agdemo.entity.Users;
import com.store.agdemo.exception.IllegalUserStateException;
import com.store.agdemo.repository.UserRepository;
import com.store.agdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service(UserService.BEAN_NAME)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users create(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Page<Users> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<Users> getById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Users getByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void changeStatus(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.setBlocked(!user.isBlocked());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
        if(user.isBlocked()){
            throw new IllegalUserStateException(user.getUsername());
        }
        return new SecuredUser(user);
    }

    public static class SecuredUser implements UserDetails {
        private final Users user;

        SecuredUser(Users user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return resolveAuthorities(user);
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public Users getUser() {
            return this.user;
        }

        private List<? extends GrantedAuthority> resolveAuthorities(Users user) {
            return user.isAdmin() ? Arrays.asList(new GrantedAuthorityImpl(Users.Role.ADMIN.name()),
                    new GrantedAuthorityImpl(Users.Role.USER.name())):
                    Collections.singletonList(new GrantedAuthorityImpl(Users.Role.USER.name()));
        }
    }

    public static class GrantedAuthorityImpl implements GrantedAuthority {
        private final String role;

        GrantedAuthorityImpl(String role) {
            this.role = role;
        }

        @Override
        public String getAuthority() {
            return "ROLE_" + role;
        }
    }
    

}
