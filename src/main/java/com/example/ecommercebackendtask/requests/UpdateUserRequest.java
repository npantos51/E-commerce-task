package com.example.ecommercebackendtask.requests;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private String role;

    public UpdateUserRequest(String role){
        this.role = role;
    }
}
