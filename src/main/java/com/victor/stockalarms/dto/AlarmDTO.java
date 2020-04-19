package com.victor.stockalarms.dto;

import com.victor.stockalarms.AlarmType;

public class AlarmDTO {

    private Long id;

    private Double baseStockPrice;

    private Double percentageThreshold;

    private AlarmType alarmType;

    private Boolean enabled;

    private StockDTO stock;

    public AlarmDTO() {
    }

    public AlarmDTO(final Long id, final Double baseStockPrice, final Double percentageThreshold, final AlarmType alarmType, boolean enabled, final StockDTO stock) {
        this.percentageThreshold = percentageThreshold;
        this.baseStockPrice = baseStockPrice;
        this.alarmType = alarmType;
        this.enabled = enabled;
        this.stock = stock;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getBaseStockPrice() {
        return baseStockPrice;
    }

    public void setBaseStockPrice(final Double baseStockPrice) {
        this.baseStockPrice = baseStockPrice;
    }

    public Double getPercentageThreshold() {
        return percentageThreshold;
    }

    public void setPercentageThreshold(final Double percentageThreshold) {
        this.percentageThreshold = percentageThreshold;
    }

    public AlarmType getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(final AlarmType alarmType) {
        this.alarmType = alarmType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public StockDTO getStock() {
        return stock;
    }

    public void setStock(final StockDTO stock) {
        this.stock = stock;
    }
}