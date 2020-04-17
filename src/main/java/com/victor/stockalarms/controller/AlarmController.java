package com.victor.stockalarms.controller;

import com.victor.stockalarms.model.CreateAlarmRequest;
import com.victor.stockalarms.service.AlarmService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    public AlarmController(final AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @PostMapping("/create")
    public void createdAlarm(@RequestBody final CreateAlarmRequest createAlarmRequest) {
        alarmService.createAlarm(createAlarmRequest);
    }

}