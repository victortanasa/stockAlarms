package com.victor.stockalarms.service;

import static com.google.common.collect.Lists.newArrayList;

import com.victor.stockalarms.dto.StockDTO;
import com.victor.stockalarms.entity.Stock;
import com.victor.stockalarms.repository.StockRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StockService {

    private static final String STOCK_NOT_FOUND_MESSAGE = "Could not find stock with id [%s].";

    private final StockRepository stockRepository;

    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(final StockDTO stockDTO) {
        stockRepository.save(new Stock(stockDTO.getName(), stockDTO.getPrice()));
    }

    Stock getStockById(final Long stockId) {
        return stockRepository
                .findById(stockId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(STOCK_NOT_FOUND_MESSAGE, stockId)));
    }

    void updateStockPrice(final Stock stock, final Double newPrice) {
        stockRepository.save(stock.withPrice(newPrice));
    }

    public List<Stock> getAllStocks() {
        return newArrayList(stockRepository.findAll());
    }

}