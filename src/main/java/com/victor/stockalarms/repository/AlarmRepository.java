package com.victor.stockalarms.repository;

import com.victor.stockalarms.entity.Alarm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends CrudRepository<Alarm, Long> {

}