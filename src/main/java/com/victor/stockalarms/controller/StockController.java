package com.victor.stockalarms.controller;

import com.victor.stockalarms.dto.StockDTO;
import com.victor.stockalarms.entity.Stock;
import com.victor.stockalarms.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(final StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/create")
    public void createStock(@RequestBody final StockDTO stockDTO) {
        stockService.createStock(stockDTO);
    }

    @GetMapping("/list")
    public List<Stock> listStocks() {
        return stockService.getAllStocks();
    }

}