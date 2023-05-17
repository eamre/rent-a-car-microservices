package com.example.filterservice.repository;

import com.example.filterservice.entity.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, String> {
    void deleteByCarId(UUID carId);

    void deleteAllByBrandId(UUID carId);

    Filter findByCarId(UUID carId);
}
