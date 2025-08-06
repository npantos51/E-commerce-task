package com.example.ecommercebackendtask.services;

import com.example.ecommercebackendtask.model.Cart;
import com.example.ecommercebackendtask.model.Item;
import com.example.ecommercebackendtask.model.Product;
import com.example.ecommercebackendtask.model.User;
import com.example.ecommercebackendtask.repository.CartRepository;
import com.example.ecommercebackendtask.repository.ItemRepository;
import com.example.ecommercebackendtask.repository.ProductRepository;
import com.example.ecommercebackendtask.repository.UserRepository;
import com.example.ecommercebackendtask.requests.ItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public void addItemToCart(Long userId, ItemRequest request){
        //getting the car of the current user
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(request.getId()).get();
        Cart cart = user.getCart();
        if(cart == null){
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        //creating a new item, saving it in the repository and adding it to the cart
        Item item = new Item();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        itemRepository.save(item);
        cart.getItems().add(item);

        cartRepository.save(cart);
    }

    public void updateItemQuantity(Long userId, Long itemId, int quantity){
        Item item = itemRepository.findById(itemId).get();
        if(!item.getCart().getUser().getId().equals(userId)){
            return;
        }
        item.setQuantity(quantity);
        itemRepository.save(item);
    }

    public void removeItemFromCart(Long userId, Long itemId){
        Item item = itemRepository.findById(itemId).get();
        if(!item.getCart().getUser().getId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This item does not belong to your cart.");
        }
        itemRepository.delete(item);
    }

    public Cart getCartByUserId(Long userId){
        User user = userRepository.findById(userId).get();
        return user.getCart();
    }
}
