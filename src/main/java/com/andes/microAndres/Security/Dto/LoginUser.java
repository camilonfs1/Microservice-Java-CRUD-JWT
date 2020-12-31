package com.andes.microAndres.Security.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginUser {
    @NotBlank
    private String UserName;
    @NotBlank
    private String password;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
