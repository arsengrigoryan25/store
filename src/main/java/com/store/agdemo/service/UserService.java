package com.store.agdemo.service;

import com.store.agdemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {
    String BEAN_NAME = "userService";

    User create(User user);
    Page<User> getAll(Pageable pageable);
    Optional<User> getById(Long userId);
    User getByUsername(String userName);
    void changeStatus(Long userId);
}
