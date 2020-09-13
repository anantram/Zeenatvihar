package org.anantram.zeenat.repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.anantram.zeenat.domain.entities.Due;
import org.springframework.data.repository.CrudRepository;

public interface DueRepo extends CrudRepository<Due, Long> {

	Due findByDueMonthAndDueYear(Month dueMonth, Year dueYear);
}
