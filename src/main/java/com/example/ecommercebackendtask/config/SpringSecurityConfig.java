package com.example.ecommercebackendtask.config;


import com.example.ecommercebackendtask.filters.JWTFilter;
import com.example.ecommercebackendtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SpringSecurityConfig  {

    private final UserService userService;
    private final JWTFilter jwtFilter;

    @Autowired
    public SpringSecurityConfig(UserService userService, JWTFilter jwtFilter){
        this.userService = userService;
        this.jwtFilter = jwtFilter;
    }



}
