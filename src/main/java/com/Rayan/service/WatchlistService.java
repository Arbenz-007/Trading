package com.Rayan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rayan.model.Coins;
import com.Rayan.model.User;
import com.Rayan.model.Watchlist;
import com.Rayan.repo.WatchlistRepo;

@Service
public class WatchlistService implements IWatchlist {

	@Autowired
	private WatchlistRepo watchlistRepo;
	
	@Override
	public Watchlist findUserWatchlist(Long userId) throws Exception {
		Watchlist watchlist= watchlistRepo.findByUserId(userId);
		if(watchlist==null) {
			throw new Exception("watchList not found");
		}
		return watchlist;
	}

	@Override
	public Watchlist createWatchlist(User user) {
		Watchlist watchlist=new Watchlist();
		watchlist.setUser(user);
		
		return watchlistRepo.save(watchlist);
	}

	@Override
	public Watchlist findById(Long id) throws Exception {
		Optional<Watchlist> opt=watchlistRepo.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("WatchList not found");
		}
		
		return opt.get();
	}

	@Override
	public Coins addItemtoWatchlist(Coins coin, User user) throws Exception {
		Watchlist watchlist=findUserWatchlist(user.getId());
		
		if(watchlist.getCoins().contains(coin)) {
			watchlist.getCoins().remove(coin);
		}
		else {
			watchlist.getCoins().add(coin);
		}
		
		watchlistRepo.save(watchlist);
		return coin;
	}

	
}
