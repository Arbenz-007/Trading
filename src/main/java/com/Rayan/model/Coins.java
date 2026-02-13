package com.Rayan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Coins {

		@Id
	    @JsonProperty("id")
	    private String id;

	    @JsonProperty("symbol")
	    private String symbol;

	    @JsonProperty("name")
	    private String name;

	    @JsonProperty("image")
	    private String image;

	    @JsonProperty("current_price")
	    private double currentPrice;

	    @JsonProperty("market_cap")
	    private long marketCap;

	    @JsonProperty("market_cap_rank")
	    private int marketCapRank;

	    @JsonProperty("fully_diluted_valuation")
	    private long fullyDilutedValuation;

	    @JsonProperty("total_volume")
	    private long totalVolume;

	    @JsonProperty("high_24h")
	    private double high24h;

	    @JsonProperty("low_24h")
	    private double low24h;

	    @JsonProperty("price_change_24h")
	    private double priceChange24h;

	    @JsonProperty("price_change_percentage_24h")
	    private double priceChangePercentage24h;

	    @JsonProperty("market_cap_change_24h")
	    private long marketCapChange24h;

	    @JsonProperty("market_cap_change_percentage_24h")
	    private double marketCapChangePercentage24h;

	    @JsonProperty("circulating_supply")
	    private double circulatingSupply;

	    @JsonProperty("total_supply")
	    private double totalSupply;

	    @JsonProperty("max_supply")
	    private double maxSupply;

	    @JsonProperty("ath")
	    private double ath;

	    @JsonProperty("ath_change_percentage")
	    private double athChangePercentage;

	    @JsonProperty("ath_date")
	    private String athDate;

	    @JsonProperty("atl")
	    private double atl;

	    @JsonProperty("atl_change_percentage")
	    private double atlChangePercentage;

	    @JsonProperty("atl_date")
	    private String atlDate;

	    @JsonProperty("roi")
	    private Roi roi;

	    @JsonProperty("last_updated")
	    private String lastUpdated;

	    @JsonProperty("market_cap_rank_with_rehypothecated")
	    private int marketCapRankWithRehypothecated;

	    // ✅ No-args constructor (required by Jackson)
	    public Coins() {}

	    // ✅ All-args constructor
	    public Coins(
	            String id,
	            String symbol,
	            String name,
	            String image,
	            double currentPrice,
	            long marketCap,
	            int marketCapRank,
	            long fullyDilutedValuation,
	            long totalVolume,
	            double high24h,
	            double low24h,
	            double priceChange24h,
	            double priceChangePercentage24h,
	            long marketCapChange24h,
	            double marketCapChangePercentage24h,
	            double circulatingSupply,
	            double totalSupply,
	            double maxSupply,
	            double ath,
	            double athChangePercentage,
	            String athDate,
	            double atl,
	            double atlChangePercentage,
	            String atlDate,
	            Roi roi,
	            String lastUpdated,
	            int marketCapRankWithRehypothecated
	    ) {
	        this.id = id;
	        this.symbol = symbol;
	        this.name = name;
	        this.image = image;
	        this.currentPrice = currentPrice;
	        this.marketCap = marketCap;
	        this.marketCapRank = marketCapRank;
	        this.fullyDilutedValuation = fullyDilutedValuation;
	        this.totalVolume = totalVolume;
	        this.high24h = high24h;
	        this.low24h = low24h;
	        this.priceChange24h = priceChange24h;
	        this.priceChangePercentage24h = priceChangePercentage24h;
	        this.marketCapChange24h = marketCapChange24h;
	        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
	        this.circulatingSupply = circulatingSupply;
	        this.totalSupply = totalSupply;
	        this.maxSupply = maxSupply;
	        this.ath = ath;
	        this.athChangePercentage = athChangePercentage;
	        this.athDate = athDate;
	        this.atl = atl;
	        this.atlChangePercentage = atlChangePercentage;
	        this.atlDate = atlDate;
	        this.roi = roi;
	        this.lastUpdated = lastUpdated;
	        this.marketCapRankWithRehypothecated = marketCapRankWithRehypothecated;
	    }

	    // ✅ Getters and Setters

	    public String getId() { return id; }
	    public void setId(String id) { this.id = id; }

	    public String getSymbol() { return symbol; }
	    public void setSymbol(String symbol) { this.symbol = symbol; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getImage() { return image; }
	    public void setImage(String image) { this.image = image; }

	    public double getCurrentPrice() { return currentPrice; }
	    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

	    public long getMarketCap() { return marketCap; }
	    public void setMarketCap(long marketCap) { this.marketCap = marketCap; }

	    public int getMarketCapRank() { return marketCapRank; }
	    public void setMarketCapRank(int marketCapRank) { this.marketCapRank = marketCapRank; }

	    public long getFullyDilutedValuation() { return fullyDilutedValuation; }
	    public void setFullyDilutedValuation(long fullyDilutedValuation) {
	        this.fullyDilutedValuation = fullyDilutedValuation;
	    }

	    public long getTotalVolume() { return totalVolume; }
	    public void setTotalVolume(long totalVolume) { this.totalVolume = totalVolume; }

	    public double getHigh24h() { return high24h; }
	    public void setHigh24h(double high24h) { this.high24h = high24h; }

	    public double getLow24h() { return low24h; }
	    public void setLow24h(double low24h) { this.low24h = low24h; }

	    public double getPriceChange24h() { return priceChange24h; }
	    public void setPriceChange24h(double priceChange24h) {
	        this.priceChange24h = priceChange24h;
	    }

	    public double getPriceChangePercentage24h() {
	        return priceChangePercentage24h;
	    }

	    public void setPriceChangePercentage24h(double priceChangePercentage24h) {
	        this.priceChangePercentage24h = priceChangePercentage24h;
	    }

	    public long getMarketCapChange24h() { return marketCapChange24h; }
	    public void setMarketCapChange24h(long marketCapChange24h) {
	        this.marketCapChange24h = marketCapChange24h;
	    }

	    public double getMarketCapChangePercentage24h() {
	        return marketCapChangePercentage24h;
	    }

	    public void setMarketCapChangePercentage24h(double marketCapChangePercentage24h) {
	        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
	    }

	    public double getCirculatingSupply() { return circulatingSupply; }
	    public void setCirculatingSupply(double circulatingSupply) {
	        this.circulatingSupply = circulatingSupply;
	    }

	    public double getTotalSupply() { return totalSupply; }
	    public void setTotalSupply(double totalSupply) { this.totalSupply = totalSupply; }

	    public double getMaxSupply() { return maxSupply; }
	    public void setMaxSupply(double maxSupply) { this.maxSupply = maxSupply; }

	    public double getAth() { return ath; }
	    public void setAth(double ath) { this.ath = ath; }

	    public double getAthChangePercentage() { return athChangePercentage; }
	    public void setAthChangePercentage(double athChangePercentage) {
	        this.athChangePercentage = athChangePercentage;
	    }

	    public String getAthDate() { return athDate; }
	    public void setAthDate(String athDate) { this.athDate = athDate; }

	    public double getAtl() { return atl; }
	    public void setAtl(double atl) { this.atl = atl; }

	    public double getAtlChangePercentage() { return atlChangePercentage; }
	    public void setAtlChangePercentage(double atlChangePercentage) {
	        this.atlChangePercentage = atlChangePercentage;
	    }

	    public String getAtlDate() { return atlDate; }
	    public void setAtlDate(String atlDate) { this.atlDate = atlDate; }

	    public Roi getRoi() { return roi; }
	    public void setRoi(Roi roi) { this.roi = roi; }

	    public String getLastUpdated() { return lastUpdated; }
	    public void setLastUpdated(String lastUpdated) {
	        this.lastUpdated = lastUpdated;
	    }

	    public int getMarketCapRankWithRehypothecated() {
	        return marketCapRankWithRehypothecated;
	    }

	    public void setMarketCapRankWithRehypothecated(int marketCapRankWithRehypothecated) {
	        this.marketCapRankWithRehypothecated = marketCapRankWithRehypothecated;
	    }
	}

