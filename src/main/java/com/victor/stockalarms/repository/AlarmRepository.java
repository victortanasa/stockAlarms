package com.victor.stockalarms.repository;

import com.victor.stockalarms.entity.Alarm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends CrudRepository<Alarm, Long> {

    List<Alarm> findAllByUserId(final Long userId);

}