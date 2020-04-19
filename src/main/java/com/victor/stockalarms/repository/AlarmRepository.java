package com.victor.stockalarms.repository;

import com.victor.stockalarms.entity.Alarm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmRepository extends CrudRepository<Alarm, Long> {

    List<Alarm> findAllByUserId(final Long userId);

    Optional<Alarm> findByIdAndUserId(final Long alarmId, final Long userId);

}