package com.Rayan.service;

import com.Rayan.model.Coins;
import com.Rayan.model.User;
import com.Rayan.model.Watchlist;

public interface IWatchlist {

	Watchlist findUserWatchlist(Long userId) throws Exception;
	Watchlist createWatchlist(User user);
	Watchlist findById(Long id) throws Exception;
	
	Coins addItemtoWatchlist(Coins coin,User user) throws Exception;
}
