package com.victor.stockalarms.service;

import com.google.common.collect.Lists;
import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.model.CreateAlarmRequest;
import com.victor.stockalarms.model.UpdateAlarmRequest;
import com.victor.stockalarms.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

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

    public void updateAlarm(final UpdateAlarmRequest request) {
        throw new NotImplementedException();
    }

    public List<Alarm> getAllAlarms() {
        return Lists.newArrayList(alarmRepository.findAll());
    }

    public void deleteAlarm(final Long id) {
        alarmRepository.deleteById(id);
    }

}