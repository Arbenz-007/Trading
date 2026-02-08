package com.Rayan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.Rayan.model.Coins;
import com.Rayan.repo.CoinRepo;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class CoinService implements ICoins {

	@Autowired
	private CoinRepo coinRepo;
	
	@Autowired
	private ObjectMapper objectMapper;
	@Override
	public List<Coins> getCoinList(int page) throws Exception {
		String url="https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=10&page="+page;
		RestTemplate restTemplate= new RestTemplate();
		try {
			HttpHeaders headers= new HttpHeaders();
			
			HttpEntity<String> entity= new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			
			List<Coins> coinList= objectMapper.readValue(res.getBody(),new TypeReference<List<Coins>>() {});
			
			return coinList;
		}
		catch(HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getMarketChart(String coinId, int days) throws Exception {
		String url="https://api.coingecko.com/api/v3/coins/"+coinId+"market_chart?vs_currency=usd&days="+days;
		RestTemplate restTemplate= new RestTemplate();
		try {
			HttpHeaders headers= new HttpHeaders();
			
			HttpEntity<String> entity= new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			
			return res.getBody();
		}
		catch(HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getCoinDetails(String coinId) throws Exception {
		String url="https://api.coingecko.com/api/v3/coins/"+coinId;
		RestTemplate restTemplate= new RestTemplate();
		try {
			HttpHeaders headers= new HttpHeaders();
			
			HttpEntity<String> entity= new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			
			JsonNode jsonNode=objectMapper.readTree(res.getBody());
			Coins coin = new Coins();
			coin.setId(jsonNode.get("id").asText());
			coin.setName(jsonNode.get("name").asText());
			coin.setSymbol(jsonNode.get("symbol").asText());
			coin.setImage(jsonNode.get("image").get("large").asText());
			
			JsonNode marketData=jsonNode.get("market_data");
			
			coin.setCurrentPrice(marketData.get("current_price").get("usd").asDouble());
			coin.setMarketCap(marketData.get("market_cap").get("usd").asLong());
			coin.setMarketCapRank(marketData.get("market_cap_rank").asInt());
			coin.setTotalVolume(marketData.get("total_volume").get("usd").asLong());
			coin.setHigh24h(marketData.get("high_24h").get("usd").asDouble());
			coin.setLow24h(marketData.get("low_24h").get("usd").asDouble());
			coin.setPriceChange24h(marketData.get("price_change2_4h").get("usd").asDouble());
			coin.setPriceChangePercentage24h(marketData.get("price_change2_24h").get("usd").asDouble());
			coin.setMarketCapChange24h(marketData.get("market_cap_change_24h").asLong());
			coin.setMarketCapChangePercentage24h(marketData.get("market_cap_change_percentage_24h").asLong());
			
			coin.setTotalSupply(marketData.get("total_supply").get("usd").asLong());
			coinRepo.save(coin);
			
			
			return res.getBody();
		}
		catch(HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Coins findById(String coinId) throws Exception {
		Optional<Coins> opt=coinRepo.findById(coinId);
		if(opt.isEmpty()) throw new Exception("coin not found");
		return opt.get();
	}

	@Override
	public String searchCoin(String keyword) throws Exception {
		String url="https://api.coingecko.com/api/v3/search?query="+keyword;
		RestTemplate restTemplate= new RestTemplate();
		try {
			HttpHeaders headers= new HttpHeaders();
			
			HttpEntity<String> entity= new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			
			return res.getBody();
		}
		catch(HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getTop50CoinByMarketCapRange() throws Exception {
		String url="https://api.coingecko.com/api/v3/coins/markets/vs_currency=usd&per_page=50&page=1";
		RestTemplate restTemplate= new RestTemplate();
		try {
			HttpHeaders headers= new HttpHeaders();
			
			HttpEntity<String> entity= new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			
			return res.getBody();
		}
		catch(HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getTradingCoins() throws Exception {
		String url="https://api.coingecko.com/api/v3/search/trading";
		RestTemplate restTemplate= new RestTemplate();
		try {
			HttpHeaders headers= new HttpHeaders();
			
			HttpEntity<String> entity= new HttpEntity<String>("parameters",headers);
			
			ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			
			return res.getBody();
		}
		catch(HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

}
