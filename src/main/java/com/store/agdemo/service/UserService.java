package com.store.agdemo.service;

import com.store.agdemo.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {
    String BEAN_NAME = "userService";

    Users create(Users user);
    Page<Users> getAll(Pageable pageable);
    Optional<Users> getById(Long userId);
    Users getByUsername(String userName);
    void changeStatus(Long userId) ;
}
