package com.example.ecommercebackendtask.bootstrap;

import com.example.ecommercebackendtask.model.Cart;
import com.example.ecommercebackendtask.model.Product;
import com.example.ecommercebackendtask.model.Role;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.repository.CartRepository;
import com.example.ecommercebackendtask.repository.ProductRepository;
import com.example.ecommercebackendtask.repository.UserRepository;
import com.example.ecommercebackendtask.requests.RegisterRequest;
import com.example.ecommercebackendtask.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        File file = new File("C:\\Users\\nar_c\\Downloads\\products 1 1.json");
        try (InputStream is = new FileInputStream(file)) {
            List<Product> products = objectMapper.readValue(is, new TypeReference<>() {});
            productRepository.saveAll(products);
        }catch (TransactionSystemException e){
            Throwable cause = e.getRootCause();
            throw new RuntimeException("Transaction failed: " + (cause != null ? cause.getMessage() : "Unknown cause"), e);
        }


        User user1 = new User();
        user1.setUsername("npantos");
        user1.setEmail("npantos@yahoo.com");
        user1.setPassword("123");
        user1.setRole(Role.ADMIN);
        userRepository.save(user1);
        Cart cart1 = new Cart();

        cart1.setUser(user1);
        user1.setCart(cart1);
        cartRepository.save(cart1);

        User user2 = new User();
        user2.setUsername("marko");
        user2.setEmail("marko@yahoo.com");
        user2.setPassword("321");
        user2.setRole(Role.USER);
        userRepository.save(user2);
        Cart cart2 = new Cart();
        cart2.setUser(user2);
        user2.setCart(cart2);
        cartRepository.save(cart2);

        userService.newUser(user1);
        userService.newUser(user2);
    }
}
