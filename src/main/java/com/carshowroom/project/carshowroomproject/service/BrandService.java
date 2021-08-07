package com.carshowroom.project.carshowroomproject.service;

import com.carshowroom.project.carshowroomproject.entities.Brand;
import com.carshowroom.project.carshowroomproject.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Optional<Brand> getOneById(Long id) {
        return brandRepository.findById(id);
    }

    public Optional<Brand> getByTitle(String title) {
        return brandRepository.findBrandByTitle(title);
    }
}
