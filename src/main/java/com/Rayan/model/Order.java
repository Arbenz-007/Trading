package com.Rayan.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.Rayan.domain.OrderStatus;
import com.Rayan.domain.OrderType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User user;
	
	@Column(nullable=false)
	private OrderType orderType;
	
	@Column(nullable=false)
	private OrderStatus status;
	
	@Column(nullable=false)
	private BigDecimal price;
	
	private LocalDateTime timeStamp=LocalDateTime.now();
	
	@OneToOne(mappedBy="order", cascade=CascadeType.ALL)
	private OrderItem orderItem;

	public Order(Long id, User user, OrderType order, OrderStatus status, BigDecimal price, LocalDateTime timeStamp,
			OrderItem orderItem) {
		super();
		this.id = id;
		this.user = user;
		this.orderType = order;
		this.status = status;
		this.price = price;
		this.timeStamp = timeStamp;
		this.orderItem = orderItem;
	}

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType order) {
		this.orderType = order;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	
	
	
}
