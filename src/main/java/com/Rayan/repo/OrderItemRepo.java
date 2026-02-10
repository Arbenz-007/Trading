package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
