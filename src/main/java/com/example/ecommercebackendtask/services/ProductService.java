package com.example.ecommercebackendtask.services;

import com.example.ecommercebackendtask.model.Product;
import com.example.ecommercebackendtask.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> getAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Product get(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

}
