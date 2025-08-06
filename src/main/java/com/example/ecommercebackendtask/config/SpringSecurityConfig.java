package com.example.ecommercebackendtask.config;


import com.example.ecommercebackendtask.filters.JWTFilter;
import com.example.ecommercebackendtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@CrossOrigin("*")
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{

    private final UserService userService;
    private final JWTFilter jwtFilter;

    @Autowired
    public SpringSecurityConfig(UserService userService, JWTFilter jwtFilter){
        this.userService = userService;
        this.jwtFilter = jwtFilter;
    }

    //no idea why cors is causing problems honestly (Invalid CORS request)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
//                .cors(cors -> cors.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(List.of("http://localhost:3000"));
//                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                    config.setAllowedHeaders(List.of("*"));
//                    config.setAllowCredentials(true);
//                    return config;
//                }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll().requestMatchers( "/swagger-ui/**").permitAll().requestMatchers( "/v3/api-docs/**").permitAll().requestMatchers("/api/products/**").permitAll()
                .anyRequest().authenticated()
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }



}
