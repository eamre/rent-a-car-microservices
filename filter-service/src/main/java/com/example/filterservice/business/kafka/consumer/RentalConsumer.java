package com.example.filterservice.business.kafka.consumer;

import com.example.filterservice.business.abstracts.FilterService;
import com.kodlamaio.commonpackage.events.rental.RentalCreatedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "rental-created",
            groupId = "filter-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        var filter = service.getByCarId(event.getCarId());
        filter.setState("Rented");
        service.add(filter);
        log.info("Rental created event consumed {}", event);
    }
}
