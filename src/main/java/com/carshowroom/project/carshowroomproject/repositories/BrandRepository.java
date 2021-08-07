package com.carshowroom.project.carshowroomproject.repositories;

import com.carshowroom.project.carshowroomproject.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByTitle(String title);
}
