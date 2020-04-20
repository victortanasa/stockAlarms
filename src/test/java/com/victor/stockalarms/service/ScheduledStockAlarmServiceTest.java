package com.victor.stockalarms.service;

import static com.google.common.collect.Lists.newArrayList;
import static com.victor.stockalarms.AlarmType.OVER_THRESHOLD;
import static org.mockito.Mockito.*;

import com.victor.stockalarms.AlarmType;
import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.entity.Stock;
import com.victor.stockalarms.entity.User;
import org.junit.jupiter.api.Test;

class ScheduledStockAlarmServiceTest {

    private StockPriceService stockPriceService = mock(StockPriceService.class);
    private AlarmService alarmService = mock(AlarmService.class);
    private EmailService emailService = mock(EmailService.class);
    private StockService stockService = mock(StockService.class);

    private ScheduledStockAlarmService scheduledStockAlarmService = new ScheduledStockAlarmService(stockPriceService, stockService, alarmService, emailService);

    private static final User DEFAULT_USER = new User("name", "pwd", "name@provider.com").withId(1);

    private static final Stock IBM_STOCK = new Stock("IBM", 20D).withId(1L);

    private static final Alarm ALARM_IBM_OVER_THRESHOLD = new Alarm(20D, 10D, OVER_THRESHOLD, DEFAULT_USER, IBM_STOCK).withId(1);
    private static final Alarm ALARM_IBM_UNDER_THRESHOLD = new Alarm(20D, 10D, AlarmType.UNDER_THRESHOLD, DEFAULT_USER, IBM_STOCK).withId(1);

    private static final Alarm ALARM_IBM_DISABLED = new Alarm(20D, 10D, OVER_THRESHOLD, DEFAULT_USER, IBM_STOCK).withId(1).withEnabled(false);
    private static final Alarm ALARM_IBM_NULL_THRESHOLD = new Alarm(20.2, null, OVER_THRESHOLD, DEFAULT_USER, IBM_STOCK).withId(1);

    @Test
    void overThresholdAlarmIsTriggeredByStockPriceIncreaseTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_OVER_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(30D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService).sendEmail(ALARM_IBM_OVER_THRESHOLD, 30D);
        verify(alarmService).disableAlarm(ALARM_IBM_OVER_THRESHOLD);
    }

    @Test
    void overThresholdAlarmIsNotTriggeredByStockPriceDecreaseTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_OVER_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(10D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void underThresholdAlarmIsTriggeredByStockPriceDecreaseTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_UNDER_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(10D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService).sendEmail(ALARM_IBM_UNDER_THRESHOLD, 10D);
        verify(alarmService).disableAlarm(ALARM_IBM_UNDER_THRESHOLD);
    }

    @Test
    void underThresholdAlarmIsNotTriggeredByStockPriceIncreaseTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_UNDER_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(30D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void overThresholdAlarmIsNotTriggeredBySmallIncreaseTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_OVER_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(20.1);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void underThresholdAlarmIsNotTriggeredBySmallDecreaseTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_UNDER_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(19.9);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void disabledAlarmIsNotTriggeredTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_DISABLED));

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void alarmWithNullThresholdIsNotTriggeredTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_NULL_THRESHOLD));

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void alarmIsNotTriggeredWhenStockPriceResponseIsNullTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_OVER_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(null);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

}