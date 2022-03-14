package com.carshowroom.project.carshowroomproject.controllers;

import com.carshowroom.project.carshowroomproject.dto.CarDto;
import com.carshowroom.project.carshowroomproject.entities.Car;
import com.carshowroom.project.carshowroomproject.exceptions.ExceptionsControllerAdvice;
import com.carshowroom.project.carshowroomproject.exceptions.ResourceNotFoundException;
import com.carshowroom.project.carshowroomproject.exceptions.ShowroomError;
import com.carshowroom.project.carshowroomproject.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/cars")
@Tag(name = "Set of endpoints for cars")
@CrossOrigin
@SecurityRequirement(name = "auth")
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @Operation(summary = "Returns all cars",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarDto.class))),
                    @ApiResponse(responseCode = "403", description = "Authorization failed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
    public List<CarDto> getAllCars() {
        return carService.getAllCars().stream().map(CarDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a specific car by their identifier. 404 if does not exist.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarDto.class))),
                    @ApiResponse(responseCode = "403", description = "Authorization failed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
    public CarDto getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to found car with id: %s", id)));
        return new CarDto(car);
    }

    @PostMapping
    @Operation(summary = "Creates a new car. If id != null, then throw bad request response",
            responses = {
                    @ApiResponse(responseCode = "201", description = "success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Car.class))),
                    @ApiResponse(responseCode = "403", description = "Authorization failed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
    public ResponseEntity<?> createNewCar(@RequestBody Car car) {
        if (car.getId() != null) {
            return new ResponseEntity<>(new ShowroomError(HttpStatus.BAD_REQUEST.value(), "Id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carService.save(car), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Modify car info",
            responses = {
                    @ApiResponse(responseCode = "201", description = "success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Car.class))),
                    @ApiResponse(responseCode = "403", description = "Authorization failed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
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
    @Operation(summary = "Delete car",
            responses = {
                    @ApiResponse(responseCode = "201", description = "success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Car.class))),
                    @ApiResponse(responseCode = "403", description = "Authorization failed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
    public void deleteById(@PathVariable Long id) {
        carService.deleteById(id);
    }
}
