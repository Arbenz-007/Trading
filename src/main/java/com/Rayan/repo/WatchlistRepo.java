package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.Watchlist;

public interface WatchlistRepo extends JpaRepository<Watchlist, Long>{

	Watchlist findByUserId(Long userId);
}
