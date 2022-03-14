package com.carshowroom.project.carshowroomproject.controllers;

import com.carshowroom.project.carshowroomproject.dto.BrandDto;
import com.carshowroom.project.carshowroomproject.entities.Brand;
import com.carshowroom.project.carshowroomproject.entities.Car;
import com.carshowroom.project.carshowroomproject.exceptions.ExceptionsControllerAdvice;
import com.carshowroom.project.carshowroomproject.exceptions.ResourceNotFoundException;
import com.carshowroom.project.carshowroomproject.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/brands")
@Tag(name = "Set of endpoints for brands")
@SecurityRequirement(name = "auth")
public class BrandController {
    BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a specific brand by their identifier. 404 if does not exist.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Car.class))),
                    @ApiResponse(responseCode = "403", description = "Authorization failed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
    public BrandDto getBrandById(@PathVariable Long id) {
        Brand brand = brandService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find brand with id: " + id));
        return new BrandDto(brand);
    }

    @GetMapping
    @Operation(summary = "Returns brand by title",
            responses = {
                    @ApiResponse(responseCode = "200", description = "success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Car.class))),
                    @ApiResponse(responseCode = "403", description = "Authorization failed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
    public BrandDto getAuthorByName(@RequestParam String name) {
        Brand author = brandService.getByTitle(name).orElseThrow(() -> new ResourceNotFoundException("Unable to find brand with name: " + name));
        return new BrandDto(author);
    }
}
