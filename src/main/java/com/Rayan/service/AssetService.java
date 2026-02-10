package com.Rayan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rayan.model.Asset;
import com.Rayan.model.Coins;
import com.Rayan.model.User;
import com.Rayan.repo.AssetRepo;

@Service
public class AssetService implements IAsset {

	@Autowired
	private AssetRepo assetRepo;
	
	@Override
	public Asset createAsset(User user, Coins coin, double quantity) {
		Asset asset=new Asset();
		asset.setUser(user);
		asset.setCoin(coin);
		asset.setQuantity(quantity);
		asset.setBuyPrice(coin.getCurrentPrice());
		
		return assetRepo.save(asset);
		
	}

	@Override
	public Asset getAssetById(Long assetId) throws Exception {
		return assetRepo.findById(assetId).orElseThrow(()->new Exception("asset not found"));
	}

	@Override
	public Asset getAssetByUserId(Long userId, Long assetId) {
		return null;
	}

	@Override
	public List<Asset> getUsersAsset(Long userId) {
		return assetRepo.findByUserId(userId);
	}

	@Override
	public Asset updateAsset(Long assetId, double quantity) throws Exception {
		Asset oldAsset=getAssetById(assetId);
		oldAsset.setQuantity(quantity+oldAsset.getQuantity());
		return assetRepo.save(oldAsset);
		
	}

	@Override
	public Asset findAssetByUserIdAndCoinId(Long userId, String coinId) {
		return assetRepo.findByUserIdAndCoinId(userId, coinId);
	}

	@Override
	public void deleteAsset(Long assetId) {
		assetRepo.deleteById(assetId);

	}

}
