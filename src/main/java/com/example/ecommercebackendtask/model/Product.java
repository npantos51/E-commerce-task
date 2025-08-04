package com.example.ecommercebackendtask.model;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "products", schema = "shop")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name field cannot be NULL")
    @Size(max = 50, message = "The input is too long")
    private String name;

    @NotNull(message = "price field cannot be NULL")
    private Double price;

    @Size(max = 200, message = "The input is too long")
    private String shortDescription;

    @Size(max = 500, message = "The input is too long")
    private String fullDescription;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    @CollectionTable(name = "technical_specifications")
    private Map<String, String> technicalSpecifications;



}
