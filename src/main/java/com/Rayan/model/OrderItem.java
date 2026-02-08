package com.Rayan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private double quantity;
	
	@ManyToOne
	private Coins coin;
	
	private double sellPrice;
	
	private double buyPrice;
	
	@JsonIgnore
	@OneToOne
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Coins getCoin() {
		return coin;
	}

	public void setCoin(Coins coin) {
		this.coin = coin;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderItem(Long id, double quantity, Coins coin, double sellPrice, double buyPrice, Order order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.coin = coin;
		this.sellPrice = sellPrice;
		this.buyPrice = buyPrice;
		this.order = order;
	}

	public OrderItem() {
		super();
	}
	
	
	
}
