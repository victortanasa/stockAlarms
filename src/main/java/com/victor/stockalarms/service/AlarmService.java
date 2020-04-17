package com.victor.stockalarms.service;

import com.google.common.collect.Lists;
import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.model.AlarmRequest;
import com.victor.stockalarms.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlarmService {

    private static final String ENTITY_NOT_FOUND_MESSAGE = "Could not find alarm with id [%s]";

    private final AlarmRepository alarmRepository;

    //TODO: remove
    @Autowired
    private UserService userService;

    public AlarmService(final AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    public void createAlarm(final AlarmRequest request) {
        alarmRepository.save(new Alarm(request.getStockName(),
                request.getStockValue(),
                request.getPercentageIncrease(),
                request.getPercentageDecrease(),
                userService.findByUserName("victor")));
    }

    public void updateAlarm(final Long id, final AlarmRequest request) {
        final Optional<Alarm> alarmFromDB = alarmRepository.findById(id);
        if (alarmFromDB.isPresent()) {
            final Alarm alarm = alarmFromDB.get();

            updateAlarmFields(request, alarm);

            alarmRepository.save(alarm);
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        }
    }

    public List<Alarm> getAllAlarms() {
        return Lists.newArrayList(alarmRepository.findAll());
    }

    public void deleteAlarm(final Long id) {
        alarmRepository.deleteById(id);
    }

    private void updateAlarmFields(final AlarmRequest request, final Alarm alarm) {
        if (Objects.nonNull(request.getPercentageIncrease())) {
            alarm.setPercentageIncrease(request.getPercentageIncrease());
        }
        if (Objects.nonNull(request.getPercentageDecrease())) {
            alarm.setPercentageDecrease(request.getPercentageDecrease());
        }
        if (Objects.nonNull(request.getStockName())) {
            alarm.setStockName(request.getStockName());
        }
        if (Objects.nonNull(request.getStockValue())) {
            alarm.setStockValue(request.getStockValue());
        }
        if (Objects.nonNull(request.getEnabled())) {
            alarm.setEnabled(request.getEnabled());
        }
    }

}