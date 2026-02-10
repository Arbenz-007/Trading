package com.Rayan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.Asset;

public interface AssetRepo extends JpaRepository<Asset, Long> {

	List<Asset> findByUserId(Long userId);
	
	Asset findByUserIdAndCoinId(Long userId,String coinId);
}
