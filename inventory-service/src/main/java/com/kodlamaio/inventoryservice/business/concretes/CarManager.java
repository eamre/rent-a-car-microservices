package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.commonpackage.events.inventory.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.inventory.CarDeletedEvent;
import com.kodlamaio.commonpackage.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.GetCarResponse;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryservice.business.rules.CarBusinessRules;
import com.kodlamaio.inventoryservice.entities.Car;
import com.kodlamaio.inventoryservice.entities.enums.State;
import com.kodlamaio.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final ModelMapperService mapper;
    private final CarRepository repository;
    private final KafkaProducer producer;
    private final CarBusinessRules rules;

    @Override
    public List<GetAllCarsResponse> getAll() {
        var cars = repository.findAll();
        var response = cars
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        var car = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID());
        car.setState(State.Available);

        var createdCar = repository.save(car);
        sendKafkaCarCreatedEvent(createdCar);
        // var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);

        var response = mapper.forResponse().map(createdCar, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        repository.save(car);

        var response = mapper.forResponse().map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
        sendKafkaCarDeletedEvent(id);
    }

    @Override
    public void changeStateByCarId(State state, UUID id) {
        repository.changeStateByCarId(state, id);
    }

    private void sendKafkaCarCreatedEvent(Car createdCar) {
        var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        producer.sendMessage(event, "car-created");
    }

    private void sendKafkaCarDeletedEvent(UUID id) {
        producer.sendMessage(new CarDeletedEvent(id), "car-deleted");
    }

    @Override
    public ClientResponse checkIfCarAvailable(UUID id) {
        var response = new ClientResponse();
        validateCarAvailability(id, response);
        return response;
    }

    private void validateCarAvailability(UUID id, ClientResponse response) {
        try {
            rules.checkIfCarExists(id);
            rules.checkCarAvailability(id);
            response.setSuccess(true);
        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
    }
}
