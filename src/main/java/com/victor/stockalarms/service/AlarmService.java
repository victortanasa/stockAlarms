package com.victor.stockalarms.service;

import com.google.common.collect.Lists;
import com.victor.stockalarms.dto.AlarmDTO;
import com.victor.stockalarms.entity.Alarm;
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

    public void createAlarm(final AlarmDTO alarmDTO) {
        alarmRepository.save(new Alarm(alarmDTO.getStockName(),
                alarmDTO.getStockValue(),
                alarmDTO.getPercentageIncrease(),
                alarmDTO.getPercentageDecrease(),
                userService.findByUserName("victor")));
    }

    public void updateAlarm(final Long id, final AlarmDTO alarmDTO) {
        final Optional<Alarm> alarmFromDB = alarmRepository.findById(id);
        if (alarmFromDB.isPresent()) {
            final Alarm alarm = alarmFromDB.get();

            updateAlarmFields(alarmDTO, alarm);

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

    private void updateAlarmFields(final AlarmDTO alarmDTO, final Alarm alarm) {
        if (Objects.nonNull(alarmDTO.getPercentageIncrease())) {
            alarm.setPercentageIncrease(alarmDTO.getPercentageIncrease());
        }
        if (Objects.nonNull(alarmDTO.getPercentageDecrease())) {
            alarm.setPercentageDecrease(alarmDTO.getPercentageDecrease());
        }
        if (Objects.nonNull(alarmDTO.getStockName())) {
            alarm.setStockName(alarmDTO.getStockName());
        }
        if (Objects.nonNull(alarmDTO.getStockValue())) {
            alarm.setStockValue(alarmDTO.getStockValue());
        }
        if (Objects.nonNull(alarmDTO.getEnabled())) {
            alarm.setEnabled(alarmDTO.getEnabled());
        }
    }

}