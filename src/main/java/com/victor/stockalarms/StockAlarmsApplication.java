package com.victor.stockalarms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StockAlarmsApplication {

    //TODO: Let’s say that the current price for the stock A is 20 USD. The user could define as an alarm, the fact that the price of stock A is over +10% or is less than -20%.
    //TODO: for each Stock he can define ONE alarm(small restriction for first implementation)
    //TODO: stock - alarm relationship

    public static void main(final String[] args) {
        SpringApplication.run(StockAlarmsApplication.class, args);
    }

}