package com.example.ecommercebackendtask.controller;


import com.example.ecommercebackendtask.model.Role;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listAll(@AuthenticationPrincipal Long requestId){
        User user = userService.getUserById(requestId).get();
        if(user.getRole() != Role.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @AuthenticationPrincipal Long requestId){
        User user = userService.getUserById(requestId).get();
        if(user.getRole() != Role.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUserToAdmin(@PathVariable Long id, @AuthenticationPrincipal Long requestId){
        User user = userService.getUserById(requestId).get();
        if(user.getRole() != Role.ADMIN){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.updateUserToAdmin(id);
        return ResponseEntity.noContent().build();
    }
}

