package com.victor.stockalarms.dto;

public class StockDTO {

    private Long id;
    private String name;
    private Double price;

    public StockDTO() {
    }

    public StockDTO(final Long id, final String name, final Double price) {
        this.price = price;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}