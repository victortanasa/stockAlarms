package com.victor.stockalarms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StockAlarmsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(StockAlarmsApplication.class, args);
    }

}