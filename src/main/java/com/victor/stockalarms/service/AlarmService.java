package com.victor.stockalarms.service;

import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.model.CreateAlarmRequest;
import com.victor.stockalarms.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;

    //TODO: remove
    @Autowired
    private UserService userService;

    public AlarmService(final AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    public void createAlarm(final CreateAlarmRequest request) {
        alarmRepository.save(new Alarm(request.getStockName(),
                request.getStockValue(),
                request.getPercentageIncrease(),
                request.getPercentageDecrease(),
                userService.findByUserName("victor")));
    }

}