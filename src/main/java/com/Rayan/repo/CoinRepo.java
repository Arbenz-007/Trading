package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.Coins;

public interface CoinRepo extends JpaRepository<Coins, String> {

}
