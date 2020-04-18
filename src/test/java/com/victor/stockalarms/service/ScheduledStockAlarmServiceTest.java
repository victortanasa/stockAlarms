package com.victor.stockalarms.service;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.*;

import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.entity.User;
import org.junit.jupiter.api.Test;

class ScheduledStockAlarmServiceTest {

    private StockPriceService stockPriceService = mock(StockPriceService.class);
    private AlarmService alarmService = mock(AlarmService.class);
    private EmailService emailService = mock(EmailService.class);

    private ScheduledStockAlarmService scheduledStockAlarmService = new ScheduledStockAlarmService(stockPriceService, alarmService, emailService);

    private static final User DEFAULT_USER = new User("name", "pwd", "name@provider.com").withId(1);

    private static final Alarm ALARM_IBM = new Alarm("IBM", 20.2, 10D, 15D, DEFAULT_USER).withId(1);
    private static final Alarm ALARM_IBM_DISABLED = new Alarm("IBM", 20.2, 10D, 15D, DEFAULT_USER).withId(1).withEnabled(false);
    private static final Alarm ALARM_IBM_ONE_THRESHOLD = new Alarm("IBM", 20.2, 10D, null, DEFAULT_USER).withId(1);
    private static final Alarm ALARM_IBM_NO_THRESHOLDS = new Alarm("IBM", 20.2, null, null, DEFAULT_USER).withId(1);

    @Test
    void alarmIsTriggeredTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(30D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService).sendEmail(ALARM_IBM, 30D);
        verify(alarmService).disableAlarm(ALARM_IBM);
    }

    @Test
    void alarmWithOneThresholdIsTriggeredTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_ONE_THRESHOLD));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(30D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService).sendEmail(ALARM_IBM_ONE_THRESHOLD, 30D);
        verify(alarmService).disableAlarm(ALARM_IBM_ONE_THRESHOLD);
    }

    @Test
    void alarmIsNotTriggeredTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(21D);

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
        when(stockPriceService.getStockPrice("IBM")).thenReturn(30D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void alarmWithNoThresholdsIsNotTriggeredTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM_NO_THRESHOLDS));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(30D);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

    @Test
    void alarmIsNotTriggeredWhenStockPriceResponseIsNullTest() {
        //Given
        when(alarmService.getAllAlarms()).thenReturn(newArrayList(ALARM_IBM));
        when(stockPriceService.getStockPrice("IBM")).thenReturn(null);

        //When
        scheduledStockAlarmService.checkStocks();

        //Then
        verify(emailService, times(0)).sendEmail(any(Alarm.class), anyDouble());
        verify(alarmService, times(0)).disableAlarm(any(Alarm.class));
    }

}