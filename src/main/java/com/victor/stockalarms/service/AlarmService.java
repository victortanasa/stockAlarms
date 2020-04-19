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

    private static final String ENTITY_NOT_FOUND_MESSAGE = "Could not find alarm with id [%s]";

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
                alarmDTO.getPercentageIncrease(),
                alarmDTO.getPercentageDecrease(),
                userService.getLoggedInUser()));
    }

    //TODO: check user
    public void updateAlarm(final Long id, final AlarmDTO alarmDTO) {
        final Alarm alarm = getAlarmById(id);

        updateAlarmFields(alarm, alarmDTO);

        alarmRepository.save(alarm);
    }

    public List<Alarm> getAllAlarms() {
        return Lists.newArrayList(alarmRepository.findAllByUserId(userService.getLoggedInUser().getId()));
    }

    //TODO: check user
    public void deleteAlarm(final Long id) {
        alarmRepository.deleteById(id);
    }

    void disableAlarm(final Alarm alarm) {
        alarmRepository.save(alarm.withEnabled(false));
    }

    private Alarm getAlarmById(final Long id) {
        return alarmRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id)));
    }

    private void updateAlarmFields(final Alarm alarm, final AlarmDTO alarmDTO) {
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