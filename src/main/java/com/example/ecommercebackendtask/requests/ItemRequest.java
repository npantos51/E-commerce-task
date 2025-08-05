package com.example.ecommercebackendtask.requests;

import lombok.Data;

@Data
public class ItemRequest {
    private Long id;
    private Integer quantity;

    public ItemRequest(Long id, Integer quantity){
        this.id = id;
        this.quantity = quantity;
    }
}
