package com.Rayan.service;

import java.util.List;

import com.Rayan.model.Coins;

public interface ICoins {

	List<Coins> getCoinList(int page) throws Exception;
	
	String getMarketChart(String coinId,int days) throws Exception;
	
	String getCoinDetails(String coinId) throws Exception;
	
	Coins findById(String coinId) throws Exception;
	
	String searchCoin(String keyword) throws Exception;
	
	String getTop50CoinByMarketCapRange() throws Exception;
	
	String getTradingCoins() throws Exception;
	
}
