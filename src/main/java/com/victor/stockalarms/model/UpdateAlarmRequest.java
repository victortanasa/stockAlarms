package com.victor.stockalarms.model;

public class UpdateAlarmRequest extends CreateAlarmRequest {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}