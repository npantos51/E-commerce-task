package com.example.ecommercebackendtask.controller;

import com.example.ecommercebackendtask.model.Cart;
import com.example.ecommercebackendtask.requests.ItemRequest;
import com.example.ecommercebackendtask.services.CartService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;

    }
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody ItemRequest request, @AuthenticationPrincipal Long userId){
        cartService.addItemToCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal Long userId){
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }
    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItemQuantity(@PathVariable Long id, @RequestParam int quantity, @AuthenticationPrincipal Long userId){
        cartService.updateItemQuantity(userId, id, quantity);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> removeItem(@PathVariable Long id, @AuthenticationPrincipal Long userId){
        cartService.removeItemFromCart(userId, id);
        return ResponseEntity.noContent().build();
    }

}
