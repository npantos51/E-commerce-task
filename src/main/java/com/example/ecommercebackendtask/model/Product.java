package com.example.ecommercebackendtask.model;

import com.example.ecommercebackendtask.util.Converter;
import com.example.ecommercebackendtask.util.JsonReaderClass;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Product {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name field cannot be NULL")
    @Size(max = 200, message = "The input is too long")
    private String name;

    @NotNull(message = "price field cannot be NULL")
    private Double price;

    @Size(max = 1000, message = "The input is too long")
    private String shortDescription;

    @Size(max = 2000, message = "The input is too long")
    private String fullDescription;

    @ElementCollection
    private List<String> images;

    @Convert(converter = Converter.class)
    @Column(columnDefinition = "JSON")
    private Map<String, String> technicalSpecifications;


}
