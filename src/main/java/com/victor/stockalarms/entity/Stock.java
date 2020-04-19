package com.victor.stockalarms.entity;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double price;

    public Stock() {
    }

    public Stock(final String name, final Double price) {
        this.price = price;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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

    public Stock withId(final Long id) {
        this.id = id;
        return this;
    }

}