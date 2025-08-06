package com.example.ecommercebackendtask.controller;


import com.example.ecommercebackendtask.filters.JWTUtil;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.requests.LoginRequest;
import com.example.ecommercebackendtask.requests.RegisterRequest;
import com.example.ecommercebackendtask.responses.AuthResponse;
import com.example.ecommercebackendtask.services.UserService;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(description = "registers a new user with the user role, with the provided username and password in the request")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }

    @PostMapping("/login")
    @Operation(description = "simple log in with username and password")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }




//
//    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(401).build();
//        }
//        return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(userService.loadUserByUsername(loginRequest.getUsername()).), userService.getPermissionsForUser(loginRequest.getEmail())));
//    }
}
