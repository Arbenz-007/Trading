package com.Rayan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rayan.model.Coins;
import com.Rayan.model.User;
import com.Rayan.model.Watchlist;
import com.Rayan.service.CoinService;
import com.Rayan.service.UserService;
import com.Rayan.service.WatchlistService;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

	
	@Autowired
	private WatchlistService watchlistService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CoinService coinService;
	
	@GetMapping("/user")
	public ResponseEntity<Watchlist> getUserWatchList(
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserProfileByJwt(jwt);
		Watchlist watchlist=watchlistService.findUserWatchlist(user.getId());
		return ResponseEntity.ok(watchlist);
	}

	@GetMapping("/{watchlistId}")
	public ResponseEntity<Watchlist> getWatchlistById(
			@PathVariable Long watchlistId) throws Exception{
		Watchlist watchlist=watchlistService.findById(watchlistId);
		return ResponseEntity.ok(watchlist);
	}
	
	public ResponseEntity<Coins> addItemToWatchlist(@RequestHeader("Authorization") String jwt,
	@PathVariable String coinId) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Coins coin = coinService.findById(coinId);
		
		Coins addedCoin=watchlistService.addItemtoWatchlist(coin, user);
		return ResponseEntity.ok(addedCoin);
	}
}
