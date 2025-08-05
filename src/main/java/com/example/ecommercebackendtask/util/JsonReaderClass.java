package com.example.ecommercebackendtask.util;

import com.example.ecommercebackendtask.model.Product;
import com.example.ecommercebackendtask.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//@Component
//@RequiredArgsConstructor
@Converter
public class JsonReaderClass implements AttributeConverter<List<String>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        try{
            return mapper.writeValueAsString(strings);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        try{
            return mapper.readValue(s, new TypeReference<>() {});
        } catch (IOException e){
            return new ArrayList<>();
        }
    }
}
