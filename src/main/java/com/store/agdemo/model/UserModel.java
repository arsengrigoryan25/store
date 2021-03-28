package com.store.agdemo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserModel {
    public Long id;

    @NotBlank(message = "Username should be filled  at least 4 characters, max 15")
    @Size(min = 4, max = 15)
    public String username;

    @NotBlank(message = "Password should be filled  at least 4 characters, max 32")
    @Size(min = 4, max = 32)
    public String password;

    public String role;
    public boolean isBlocked;

}