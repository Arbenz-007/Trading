package com.Rayan.service;

import java.util.List;

import com.Rayan.model.Asset;
import com.Rayan.model.Coins;
import com.Rayan.model.User;

public interface IAsset {

	Asset createAsset(User user,Coins coin, double quantity);
	
	Asset getAssetById(Long assetId) throws Exception;
	
	Asset getAssetByUserId(Long userId,Long assetId);
	
	List<Asset> getUsersAsset(Long userId);
	
	Asset updateAsset(Long assetId,double quantity) throws Exception;
	
	Asset findAssetByUserIdAndCoinId(Long userId, String coinId);
	
	void deleteAsset(Long assetId);
}
