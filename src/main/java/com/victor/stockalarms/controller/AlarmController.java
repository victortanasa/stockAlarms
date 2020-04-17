package com.victor.stockalarms.controller;

import static java.util.stream.Collectors.toList;

import com.victor.stockalarms.dto.AlarmDTO;
import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.model.CreateAlarmRequest;
import com.victor.stockalarms.model.UpdateAlarmRequest;
import com.victor.stockalarms.service.AlarmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public List<AlarmDTO> listAlarms() {
        return alarmService.getAllAlarms().stream()
                .map(this::toAlarmDTO)
                .collect(toList());
    }

    @PutMapping("/update")
    public void updateAlarm(@RequestBody final UpdateAlarmRequest updateAlarmRequest) {
        alarmService.updateAlarm(updateAlarmRequest);
    }

    @DeleteMapping("delete/{id}")
    public void deleteAlarm(@PathVariable final Long id) {
        alarmService.deleteAlarm(id);
    }

    private AlarmDTO toAlarmDTO(final Alarm alarm) {
        return new AlarmDTO(alarm.getId(),
                alarm.getStockName(),
                alarm.getStockValue(),
                alarm.getPercentageIncrease(),
                alarm.getPercentageDecrease(),
                alarm.isEnabled());
    }

}