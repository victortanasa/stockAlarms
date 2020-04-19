package com.victor.stockalarms.service;

import com.jayway.jsonpath.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Objects;

@Service
public class StockPriceService {

    private static final Logger LOG = LogManager.getLogger(StockPriceService.class.getName());

    private static final String COULD_NOT_GET_PRICE_MESSAGE = "Could not get price for [%s], API response was empty.";
    private static final String PRICE_PATH = "$.['Global Quote']['05. price']";

    private static final String STOCKS_BASE_URL = "https://www.alphavantage.co";
    private static final String GLOBAL_QUOTE_ENDPOINT = "/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s";

    private WebClient webClient;
    private String apiKey;
    private int timeout;

    public StockPriceService(@Value("${alphavantage.api.key}") final String apiKey,
                             @Value("${alphavantage.api.timeoutInSeconds}") final int timeout) {
        this.timeout = timeout;
        this.apiKey = apiKey;

        webClient = WebClient.builder()
                .baseUrl(STOCKS_BASE_URL)
                .build();
    }

    Double getStockPrice(final String symbol) {
        final ClientResponse clientResponse = webClient.get()
                .uri(String.format(GLOBAL_QUOTE_ENDPOINT, symbol, apiKey))
                .exchange()
                .timeout(Duration.ofSeconds(timeout)).block();

        if (Objects.nonNull(clientResponse)) {
            final String response = clientResponse.bodyToMono(String.class).block();
            return new Double(JsonPath.read(response, PRICE_PATH));
        } else {
            LOG.error(String.format(COULD_NOT_GET_PRICE_MESSAGE, symbol));
            return null;
        }
    }

}