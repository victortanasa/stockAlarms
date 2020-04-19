package com.victor.stockalarms.service;

import static com.google.common.collect.Lists.newArrayList;

import com.victor.stockalarms.dto.StockDTO;
import com.victor.stockalarms.entity.Stock;
import com.victor.stockalarms.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(final StockDTO stockDTO) {
        stockRepository.save(new Stock(stockDTO.getName(), stockDTO.getPrice()));
    }

    public List<Stock> getAllStocks() {
        return newArrayList(stockRepository.findAll());
    }

}