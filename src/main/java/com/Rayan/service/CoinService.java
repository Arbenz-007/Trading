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
		String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=10&page=" + page;
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			List<Coins> coinList = objectMapper.readValue(res.getBody(), new TypeReference<List<Coins>>() {
			});

			return coinList;
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getMarketChart(String coinId, int days) throws Exception {
		String url = "https://api.coingecko.com/api/v3/coins/" + coinId + "/market_chart?vs_currency=usd&days=" + days;
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			return res.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getCoinDetails(String coinId) throws Exception {
		String url = "https://api.coingecko.com/api/v3/coins/" + coinId;
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			JsonNode jsonNode = objectMapper.readTree(res.getBody());
			Coins coin = new Coins();
			coin.setId(jsonNode.get("id").asText());
			coin.setName(jsonNode.get("name").asText());
			coin.setSymbol(jsonNode.get("symbol").asText());
			coin.setImage(jsonNode.get("image").get("large").asText());

			JsonNode marketData = jsonNode.path("market_data");

			coin.setCurrentPrice(marketData.path("current_price").path("usd").asDouble());

			coin.setMarketCap(marketData.path("market_cap").path("usd").asLong());

			coin.setMarketCapRank(marketData.path("market_cap_rank").asInt());

			coin.setTotalVolume(marketData.path("total_volume").path("usd").asLong());

			coin.setHigh24h(marketData.path("high_24h").path("usd").asDouble());

			coin.setLow24h(marketData.path("low_24h").path("usd").asDouble());

			coin.setPriceChange24h(marketData.path("price_change_24h").asDouble());

			coin.setPriceChangePercentage24h(marketData.path("price_change_percentage_24h").asDouble());

			coin.setMarketCapChange24h(marketData.path("market_cap_change_24h").asLong());

			coin.setMarketCapChangePercentage24h(marketData.path("market_cap_change_percentage_24h").asDouble());

			// ❗ total_supply DOES NOT contain "usd"
			coin.setTotalSupply(marketData.path("total_supply").asDouble());
			coinRepo.save(coin);

			return res.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			System.out.println("error---------------" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Coins findById(String coinId) throws Exception {
		Optional<Coins> opt = coinRepo.findById(coinId);
		if (opt.isEmpty())
			throw new Exception("coin not found");
		return opt.get();
	}

	@Override
	public String searchCoin(String keyword) throws Exception {
		String url = "https://api.coingecko.com/api/v3/search?query=" + keyword;
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			return res.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getTop50CoinByMarketCapRange() throws Exception {
		String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=50&page=1";
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			return res.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getTradingCoins() throws Exception {
		String url = "https://api.coingecko.com/api/v3/search/trending";
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			return res.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new Exception(e.getMessage());
		}
	}

}
