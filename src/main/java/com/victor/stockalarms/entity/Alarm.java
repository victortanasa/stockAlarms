package com.victor.stockalarms.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "alarm")
public class Alarm {

    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "stock_name")
    private String stockName;
    @Column(name = "stock_value")
    private Double stockValue;

    @Column(name = "percentage_increase")
    private Double percentageIncrease;
    @Column(name = "percentage_decrease")
    private Double percentageDecrease;

    private boolean enabled = true;

    //TODO: FetchType.LAZY
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Alarm() {
    }

    public Alarm(final String stockName, final Double stockValue, final Double percentageIncrease, final Double percentageDecrease, final User user) {
        this.percentageIncrease = percentageIncrease;
        this.percentageDecrease = percentageDecrease;
        this.stockValue = stockValue;
        this.stockName = stockName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(final String stockName) {
        this.stockName = stockName;
    }

    public Double getStockValue() {
        return stockValue;
    }

    public void setStockValue(final Double stockValue) {
        this.stockValue = stockValue;
    }

    public Double getPercentageIncrease() {
        return percentageIncrease;
    }

    public void setPercentageIncrease(final Double percentageIncrease) {
        this.percentageIncrease = percentageIncrease;
    }

    public Double getPercentageDecrease() {
        return percentageDecrease;
    }

    public void setPercentageDecrease(final Double percentageDecrease) {
        this.percentageDecrease = percentageDecrease;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Alarm withId(final long id) {
        this.id = id;
        return this;
    }

    public Alarm withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}