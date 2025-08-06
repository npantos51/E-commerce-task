package com.example.ecommercebackendtask;

import com.example.ecommercebackendtask.controller.CartController;
import com.example.ecommercebackendtask.model.Cart;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.requests.ItemRequest;
import com.example.ecommercebackendtask.services.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
public class CartControllerTests {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;

    @Test
    public void testAddToCart_shouldReturn201() throws Exception{
        ItemRequest request = new ItemRequest(1L, 2);
        Long userId = 5L;

        ResponseEntity<?> response = cartController.addToCart(request, userId);

        verify(cartService).addItemToCart(userId, request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testGetCart_shouldReturn200WithCart(){
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Cart cart = new Cart();
        cart.setUser(user);

        when(cartService.getCartByUserId(userId)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getCart(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testUpdateItem_shouldReturn204(){
        ResponseEntity<?> response = cartController.updateItemQuantity(5L, 2, 1L);

        verify(cartService).updateItemQuantity(1L, 5L, 2);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteItem_shouldReturn204(){
        ResponseEntity<?> response = cartController.removeItem(3L, 1L);

        verify(cartService).removeItemFromCart(1L, 3L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
