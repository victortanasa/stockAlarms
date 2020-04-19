package com.victor.stockalarms.repository;

import com.victor.stockalarms.entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {

}