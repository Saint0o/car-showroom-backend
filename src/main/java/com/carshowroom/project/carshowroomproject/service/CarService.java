package com.carshowroom.project.carshowroomproject.service;

import com.carshowroom.project.carshowroomproject.entities.Car;
import com.carshowroom.project.carshowroomproject.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getCarsByBrandId(Integer brandId) {
        return carRepository.findCarsByBrandId(brandId);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return carRepository.existsById(id);
    }
}
