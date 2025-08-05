package com.example.ecommercebackendtask.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String username;
    private String password;

    public RegisterRequest(String username, String password){
        this.username = username;
        this.password = password;
    }
}
