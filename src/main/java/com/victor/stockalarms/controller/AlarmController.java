package com.victor.stockalarms.controller;

import static java.util.stream.Collectors.toList;

import com.victor.stockalarms.dto.AlarmDTO;
import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.service.AlarmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    //TODO: validate input? @Valid
    public AlarmController(final AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @PostMapping("/create")
    public void createdAlarm(@RequestBody final AlarmDTO alarmDTO) {
        alarmService.createAlarm(alarmDTO);
    }

    @GetMapping("/list")
    public List<AlarmDTO> listAlarms() {
        return alarmService.getAllAlarmsForUser().stream()
                .map(this::toAlarmDTO)
                .collect(toList());
    }

    @PutMapping("/{id}")
    public void updateAlarm(@PathVariable final Long id, @RequestBody final AlarmDTO alarmDTO) {
        alarmService.updateAlarm(id, alarmDTO);
    }

    @DeleteMapping("/{id}")
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