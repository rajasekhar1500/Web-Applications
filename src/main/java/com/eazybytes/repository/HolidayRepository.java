package com.eazybytes.repository;

import com.eazybytes.model.Holiday;
import org.springframework.data.repository.CrudRepository;

public interface HolidayRepository extends CrudRepository<Holiday, Integer> {
}
