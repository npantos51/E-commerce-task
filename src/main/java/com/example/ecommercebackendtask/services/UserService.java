package com.example.ecommercebackendtask.services;

import com.example.ecommercebackendtask.filters.JWTUtil;
import com.example.ecommercebackendtask.model.Cart;
import com.example.ecommercebackendtask.model.Role;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.repository.CartRepository;
import com.example.ecommercebackendtask.repository.UserRepository;
import com.example.ecommercebackendtask.requests.LoginRequest;
import com.example.ecommercebackendtask.requests.RegisterRequest;
import com.example.ecommercebackendtask.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest request){
        //create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setRole(Role.USER);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //set up the cart
        Cart cart = new Cart();
        cartRepository.save(cart);
        cart.setUser(user);
        user.setCart(cart);

        //save the user
        userRepository.save(user);

        return new AuthResponse(jwtUtil.generateToken(user));


    }

    public AuthResponse login(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername());
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Wrong password");
        }
        return new AuthResponse(jwtUtil.generateToken(user));

    }

    public User newUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();

    }
    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

}
