package com.example.ecommercebackendtask.filters;

import com.example.ecommercebackendtask.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    public JWTFilter(UserService userService, JWTUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwt = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);

            try{
                Long userId = jwtUtil.extractId(jwt);

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(token);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        filterChain.doFilter(request, response);



    }
}
