package com.victor.stockalarms.service;

import com.google.common.collect.Lists;
import com.victor.stockalarms.dto.AlarmDTO;
import com.victor.stockalarms.entity.Alarm;
import com.victor.stockalarms.repository.AlarmRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class AlarmService {

    private static final String ENTITY_NOT_FOUND_MESSAGE = "Could not find alarm with id [%s], for user id [%s].";

    private final AlarmRepository alarmRepository;
    private final UserService userService;

    public AlarmService(final AlarmRepository alarmRepository,
                        final UserService userService) {
        this.alarmRepository = alarmRepository;
        this.userService = userService;
    }

    public void createAlarm(final AlarmDTO alarmDTO) {
        alarmRepository.save(new Alarm(
                alarmDTO.getStockName(),
                alarmDTO.getStockValue(),
                alarmDTO.getPercentageThreshold(),
                alarmDTO.getAlarmType(),
                userService.getLoggedInUser()));
    }

    public void updateAlarm(final Long id, final AlarmDTO alarmDTO) {
        final Alarm alarm = getAlarmByIdAndUserId(id, userService.getLoggedInUser().getId());

        updateAlarmFields(alarm, alarmDTO);

        alarmRepository.save(alarm);
    }

    public List<Alarm> getAllAlarmsForUser() {
        return Lists.newArrayList(alarmRepository.findAllByUserId(userService.getLoggedInUser().getId()));
    }

    public void deleteAlarm(final Long id) {
        final Alarm alarm = getAlarmByIdAndUserId(id, userService.getLoggedInUser().getId());

        alarmRepository.delete(alarm);
    }

    List<Alarm> getAllAlarms() {
        return Lists.newArrayList(alarmRepository.findAll());
    }

    void disableAlarm(final Alarm alarm) {
        alarmRepository.save(alarm.withEnabled(false));
    }

    private Alarm getAlarmByIdAndUserId(final Long alarmId, final Long userId) {
        return alarmRepository
                .findByIdAndUserId(alarmId, userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, alarmId, userId)));
    }

    private void updateAlarmFields(final Alarm alarm, final AlarmDTO alarmDTO) {
        if (Objects.nonNull(alarmDTO.getPercentageThreshold())) {
            alarm.setPercentageThreshold(alarmDTO.getPercentageThreshold());
        }
        if (Objects.nonNull(alarmDTO.getAlarmType())) {
            alarm.setAlarmType(alarmDTO.getAlarmType());
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