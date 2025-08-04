package com.example.ecommercebackendtask.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ManyToAny;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "items", schema = "shop")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    @Column
    private int quantity;



}
