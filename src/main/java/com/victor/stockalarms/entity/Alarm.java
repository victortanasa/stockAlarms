package com.victor.stockalarms.entity;

import com.victor.stockalarms.AlarmType;
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

    @Column(name = "base_stock_price")
    private Double baseStockPrice;

    @Column(name = "percentage_threshold")
    private Double percentageThreshold;

    @Column(name = "alarm_type")
    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    private boolean enabled = true;

    //TODO: FetchType.LAZY
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Stock stock;

    public Alarm() {
    }

    public Alarm(final Double baseStockPrice, final Double percentageThreshold, final AlarmType alarmType, final User user, final Stock stock) {
        this.percentageThreshold = percentageThreshold;
        this.baseStockPrice = baseStockPrice;
        this.alarmType = alarmType;
        this.stock = stock;
        this.user = user;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(final Stock stock) {
        this.stock = stock;
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