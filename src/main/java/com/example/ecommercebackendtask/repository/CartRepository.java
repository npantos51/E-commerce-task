package com.example.ecommercebackendtask.repository;

import com.example.ecommercebackendtask.model.Cart;
import com.example.ecommercebackendtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
