package com.example.filterservice.business.abstracts;

import com.example.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.example.filterservice.business.dto.responses.GetFilterResponse;
import com.example.filterservice.entity.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();

    GetFilterResponse getById(String id);

    void add(Filter filter);

    void delete(String id);

    void deleteAllByBrandId(UUID brandId);

    void deleteAllByModelId(UUID modelId);

    void deleteByCarId(UUID carId);

    Filter getByCarId(UUID carId);

}
