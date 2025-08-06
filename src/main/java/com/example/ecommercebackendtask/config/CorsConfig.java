package com.example.ecommercebackendtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowedOrigins(List.of("http://localhost:3000"));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//        config.setAllowCredentials(true);
//        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
}
