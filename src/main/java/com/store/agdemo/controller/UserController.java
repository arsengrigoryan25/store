package com.store.agdemo.controller;

import com.store.agdemo.entity.Users;
import com.store.agdemo.exception.StoreEntityConflictException;
import com.store.agdemo.exception.StoreEntityNotFoundException;
import com.store.agdemo.model.UserModel;
import com.store.agdemo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    static final Logger log = Logger.getLogger(UserController.class);

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
        log.info("Start to creat user: " + userModel.toString());
        try{
            userService.getByUsername(userModel.username);
            throw new StoreEntityConflictException(userModel.username);
        } catch(EntityNotFoundException e){
            Users savedUser = userService.create(convertToEntity(userModel));
            UserModel model = convertToModel(savedUser);
            log.info("End to creat user: " + model.toString());
            return model;
        }
    }


    /**
     * @param id - the user id which need change status
     */
    @PutMapping("/auth/{id}/change/status")
    @PreAuthorize("hasRole('ADMIN')")
    public void changeUserRole(@PathVariable("id") Long id){
        try{
            log.info("Start change status of user which id = " + id);
            userService.changeStatus(id);
        } catch(EntityNotFoundException e){
            throw new StoreEntityNotFoundException(id, "User");
        }
        log.info("End change status of user which id = " + id);
    }

    /**
     * @param page - the page which need to returned
     * @param size - the size of each pages
     * @return - page of users
     */
    @GetMapping("/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserModel> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Users> users = userService.getAll(pageable);
        return new PageImpl<>(users.getContent().stream().map(this::convertToModel).collect(Collectors.toList()), pageable, users.getTotalElements());
    }

    private Users convertToEntity(UserModel userModel){
        Users user = new Users();
        user.setId(userModel.id);
        user.setAdmin(userModel.role.equals(Users.Role.ADMIN.name()));
        user.setPassword(passwordEncoder.encode(userModel.password));
        user.setUsername(userModel.username);
        user.setBlocked(userModel.isBlocked);
        return user;
    }
    private UserModel convertToModel(Users user){
        UserModel userModel = new UserModel();
        userModel.id = user.getId();
        userModel.username = user.getUsername();
        userModel.role = user.isAdmin() ? Users.Role.ADMIN.name() : Users.Role.USER.name();
        userModel.isBlocked = user.isBlocked();
        return  userModel;
    }
}
