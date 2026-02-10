package com.Rayan.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Watchlist {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
	
	@ManyToMany
	private List<Coins> coins= new ArrayList<>();

	public Watchlist(Long id, User user, List<Coins> coins) {
		super();
		this.id = id;
		this.user = user;
		this.coins = coins;
	}

	public Watchlist() {
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

	public List<Coins> getCoins() {
		return coins;
	}

	public void setCoins(List<Coins> coins) {
		this.coins = coins;
	}
	
	
}