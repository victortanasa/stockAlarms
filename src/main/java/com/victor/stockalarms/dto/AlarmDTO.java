package com.victor.stockalarms.dto;

import com.victor.stockalarms.AlarmType;

public class AlarmDTO {

    private Long id;

    private String stockName;
    private Double stockValue;

    private Double percentageThreshold;

    private AlarmType alarmType;

    private Boolean enabled;

    public AlarmDTO() {
    }

    public AlarmDTO(final Long id, final String stockName, final Double stockValue, final Double percentageThreshold,
                    final AlarmType alarmType, boolean enabled) {
        this.percentageThreshold = percentageThreshold;
        this.stockValue = stockValue;
        this.alarmType = alarmType;
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
}