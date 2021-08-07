package com.carshowroom.project.carshowroomproject.controllers;

import com.carshowroom.project.carshowroomproject.dto.BrandDto;
import com.carshowroom.project.carshowroomproject.entities.Brand;
import com.carshowroom.project.carshowroomproject.exceptions.ResourceNotFoundException;
import com.carshowroom.project.carshowroomproject.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
@Api("Set of endpoints for brands")
public class BrandController {
    BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific brand by their identifier. 404 if does not exist.")
    public BrandDto getBrandById(@PathVariable Long id) {
        Brand brand = brandService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find brand with id: " + id));
        return new BrandDto(brand);
    }

    @GetMapping
    @ApiOperation("Returns brand by title")
    public BrandDto getAuthorByName(@RequestParam String name) {
        Brand author = brandService.getByTitle(name).orElseThrow(() -> new ResourceNotFoundException("Unable to find brand with name: " + name));
        return new BrandDto(author);
    }
}
