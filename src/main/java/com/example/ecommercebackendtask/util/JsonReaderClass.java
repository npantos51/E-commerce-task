package com.example.ecommercebackendtask.util;

import com.example.ecommercebackendtask.model.Product;
import com.example.ecommercebackendtask.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonReaderClass {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

//    @PostConstruct
//    public void init(){
//        try(InputStream input = getClass().getResourceAsStream("/resources/products 1 1.json")){
//            List<Product> products = objectMapper.readValue(input, new TypeReference<>() {});
//            productRepository.saveAll(products);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
}
