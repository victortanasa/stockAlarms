package com.victor.stockalarms.service;

import static com.google.common.collect.Maps.newHashMap;

import com.victor.stockalarms.AlarmType;
import com.victor.stockalarms.entity.Alarm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class ScheduledStockAlarmService {

    private static final Logger LOG = LogManager.getLogger(ScheduledStockAlarmService.class.getName());

    private static final Map<String, Double> STOCK_PRICE_CACHE = newHashMap();

    private static final String COULD_NOT_DETERMINE_IF_SHOULD_TRIGGER_ALARM_MESSAGE = "Could not determine if alarm for stock [%s] and user id [%s] should be triggered.";

    private final StockPriceService stockPriceService;
    private final StockService stockService;
    private final AlarmService alarmService;
    private final EmailService emailService;

    public ScheduledStockAlarmService(final StockPriceService stockPriceService,
                                      final StockService stockService,
                                      final AlarmService alarmService,
                                      final EmailService emailService) {
        this.stockPriceService = stockPriceService;
        this.stockService = stockService;
        this.alarmService = alarmService;
        this.emailService = emailService;
    }

    @Scheduled(cron = "${stockAlarm.cron}")
    void checkStocks() {
        STOCK_PRICE_CACHE.clear();

        alarmService.getAllAlarms()
                .parallelStream()
                .filter(Alarm::isEnabled)
                .filter(this::hasConfiguredThreshold)
                .forEach(this::sendEmailIfAlarmIsTriggered);
    }

    private void sendEmailIfAlarmIsTriggered(final Alarm alarm) {
        final Double currentPrice = getStockPrice(alarm);
        if (alarmIsTriggered(alarm, currentPrice)) {
            emailService.sendEmail(alarm, currentPrice);
            alarmService.disableAlarm(alarm);
        }
    }

    private Double getStockPrice(final Alarm alarm) {
        final String stockName = alarm.getStock().getName();
        if (!STOCK_PRICE_CACHE.containsKey(stockName) || Objects.isNull(STOCK_PRICE_CACHE.get(stockName))) {
            final Double stockPrice = stockPriceService.getStockPrice(stockName);
            STOCK_PRICE_CACHE.put(stockName, stockPrice);
            stockService.updateStockPrice(alarm.getStock(), stockPrice);
        }
        return STOCK_PRICE_CACHE.get(stockName);
    }

    private boolean alarmIsTriggered(final Alarm alarm, final Double currentPrice) {
        if (Objects.isNull(currentPrice)) {
            LOG.error(String.format(COULD_NOT_DETERMINE_IF_SHOULD_TRIGGER_ALARM_MESSAGE, alarm.getStock().getName(), alarm.getUser().getId()));
            return false;
        }

        final double percentageDifference = ((currentPrice - alarm.getBaseStockPrice()) / currentPrice) * 100;

        return AlarmType.OVER_THRESHOLD.equals(alarm.getAlarmType()) && percentageDifference > alarm.getPercentageThreshold() ||
                AlarmType.UNDER_THRESHOLD.equals(alarm.getAlarmType()) && percentageDifference < -alarm.getPercentageThreshold();
    }

    private boolean hasConfiguredThreshold(final Alarm alarm) {
        return Objects.nonNull(alarm.getPercentageThreshold());
    }

}