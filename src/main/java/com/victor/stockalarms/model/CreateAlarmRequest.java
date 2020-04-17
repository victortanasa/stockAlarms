package com.victor.stockalarms.model;

public class CreateAlarmRequest {

    private String stockName;
    private Double stockValue;

    private Double percentageIncrease;
    private Double percentageDecrease;

    private Boolean enabled;

    public CreateAlarmRequest() {
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }
}