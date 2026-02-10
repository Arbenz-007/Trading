package com.Rayan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
	List<Order> findByUserId(Long userId);
}
