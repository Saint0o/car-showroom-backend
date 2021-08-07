package com.carshowroom.project.carshowroomproject.controllers;

import com.carshowroom.project.carshowroomproject.dto.CarDto;
import com.carshowroom.project.carshowroomproject.entities.Car;
import com.carshowroom.project.carshowroomproject.exceptions.ResourceNotFoundException;
import com.carshowroom.project.carshowroomproject.exceptions.ShowroomError;
import com.carshowroom.project.carshowroomproject.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cars")
@Api("Set of endpoints for cars")
@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600)
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @ApiOperation("Returns all cars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars().stream().map(CarDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific car by their identifier. 404 if does not exist.")
    public CarDto getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to found car with id: %s", id)));
        return new CarDto(car);
    }

    @PostMapping
    @ApiOperation("Creates a new car. If id != null, then throw bad request response")
    public ResponseEntity<?> createNewCar(@RequestBody Car car) {
        if (car.getId() != null) {
            return new ResponseEntity<>(new ShowroomError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carService.save(car), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("Modify car info")
    public ResponseEntity<?> modifyCar(@RequestBody Car car) {
        if (car.getId() == null) {
            return new ResponseEntity<>(new ShowroomError(HttpStatus.BAD_REQUEST.value(), "Id must be not null modify entity "), HttpStatus.BAD_REQUEST);
        }
        if (!carService.existById(car.getId())) {
            return new ResponseEntity<>(new ShowroomError(HttpStatus.NOT_FOUND.value(), "Car with id " + car.getId() + " does not exist"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carService.save(car), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete car")
    public void deleteById(@PathVariable Long id) {
        carService.deleteById(id);    }
}
