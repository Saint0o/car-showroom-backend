package com.carshowroom.project.carshowroomproject.repositories;

import com.carshowroom.project.carshowroomproject.dto.CarDto;
import com.carshowroom.project.carshowroomproject.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByBrandId(Integer brandId);
}
