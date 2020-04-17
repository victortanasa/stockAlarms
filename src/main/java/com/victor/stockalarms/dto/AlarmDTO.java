package com.victor.stockalarms.dto;

public class AlarmDTO {

    private Long id;

    private String stockName;
    private Double stockValue;

    private Double percentageIncrease;
    private Double percentageDecrease;

    private Boolean enabled;

    public AlarmDTO() {
    }

    public AlarmDTO(final Long id, final String stockName, final Double stockValue, final Double percentageIncrease,
                    final Double percentageDecrease, boolean enabled) {
        this.percentageDecrease = percentageDecrease;
        this.percentageIncrease = percentageIncrease;
        this.stockValue = stockValue;
        this.stockName = stockName;
        this.enabled = enabled;
        this.id = id;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }
}