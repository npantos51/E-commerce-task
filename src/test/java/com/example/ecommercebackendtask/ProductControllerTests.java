package com.example.ecommercebackendtask;

import com.example.ecommercebackendtask.controller.ProductController;
import com.example.ecommercebackendtask.model.Product;
import com.example.ecommercebackendtask.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void testGetAllProducts_shouldReturn200WithList(){
        List<Product> products = List.of(new Product(), new Product());
//        when(productService.getAll()).thenReturn()

    }

    @Test
    public void testGetProductById_shouldReturn200(){
        Product product = new Product();
        when(productService.get(1L)).thenReturn(product);
        ResponseEntity<Product> response = productController.getProduct(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }
}
