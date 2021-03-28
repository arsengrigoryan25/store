package com.store.agdemo.controller;

import com.store.agdemo.entity.User;
import com.store.agdemo.exception.StoreEntityConflictException;
import com.store.agdemo.exception.StoreEntityNotFoundException;
import com.store.agdemo.model.UserModel;
import com.store.agdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param userModel - the user which need create
     * @return -  the created user
     *
     * @throws com.store.agdemo.exception.StoreEntityConflictException - in case when the user is exist
     */
    @PostMapping
    public UserModel create(@Valid @RequestBody UserModel userModel) {
        userModel.role = "USER";
        try{
            User savedUser = userService.create(convertToEntity(userModel));
            return convertToModel(savedUser);
        } catch(DataIntegrityViolationException e){
            throw new StoreEntityConflictException(userModel.username);
        }
    }


    /**
     * @param id - the user id which need change status
     */
    @PutMapping("/auth/{id}/change/status")
    @PreAuthorize("hasRole('ADMIN')")
    public void changeUserRole(@PathVariable("id") Long id){
//        userService.getById(id).orElseThrow(() -> new StoreEntityNotFoundException(id, "User"));  // TODO Vorna aveli chisht
        try{
            userService.changeStatus(id);
        } catch(EntityNotFoundException e){
            throw new StoreEntityNotFoundException(id, "User");
        }
    }

    /**
     * @param page - the page which need to returned
     * @param size - the size of each pages
     * @return - page of users
     */
    @GetMapping("/auth/")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserModel> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<User> users = userService.getAll(pageable);
        return new PageImpl<>(users.getContent().stream().map(this::convertToModel).collect(Collectors.toList()), pageable, users.getTotalElements());
    }

    private User convertToEntity(UserModel userModel){
        User user = new User();
        user.setId(userModel.id);
        user.setAdmin(userModel.role.equals(User.Role.ADMIN.name()));
        user.setPassword(passwordEncoder.encode(userModel.password));
        user.setUsername(userModel.username);
        user.setBlocked(false);
        return user;
    }
    private UserModel convertToModel(User user){
        UserModel userModel = new UserModel();
        userModel.id = user.getId();
        userModel.username = user.getUsername();
        userModel.role = user.isAdmin() ? User.Role.ADMIN.name() : User.Role.USER.name();
        return  userModel;
    }
}
