package com.example.ecommercebackendtask.controller;


import com.example.ecommercebackendtask.model.Role;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(description = "views all users if the current user is an admin")
    public ResponseEntity<List<User>> listAll(@AuthenticationPrincipal Long requestId){
        User user = userService.getUserById(requestId).get();
        if(user.getRole() != Role.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("{id}")
    @Operation(description = "based on the provided id, deletes the user if the current user is an admin")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @AuthenticationPrincipal Long requestId){
        User user = userService.getUserById(requestId).get();
        if(user.getRole() != Role.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Operation(description = "if the current user is an admin, upgrades a user to the admin role based on the provided user id")
    public ResponseEntity<?> updateUserToAdmin(@PathVariable Long id, @AuthenticationPrincipal Long requestId){
        User user = userService.getUserById(requestId).get();
        if(user.getRole() != Role.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.updateUserToAdmin(id);
        return ResponseEntity.noContent().build();
    }
}

