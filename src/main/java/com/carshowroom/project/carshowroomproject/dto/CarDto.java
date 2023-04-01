package com.carshowroom.project.carshowroomproject.dto;

import com.carshowroom.project.carshowroomproject.entities.Car;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {
    @ApiModelProperty(notes = "Unique identifier of the car. No two products can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Model of the car.", example = "m3", required = true, position = 1)
    private String model;

    @ApiModelProperty(notes = "Price of the car.", example = "1 000 000", required = true, position = 2)
    private Integer price;

    @ApiModelProperty(notes = "Power of the car.", example = "500", required = true, position = 3)
    private Integer power;

    @ApiModelProperty(notes = "Description of the car.", example = "example", required = false, position = 4)
    private String description;

    @ApiModelProperty(notes = "Brand id of the car.", example = "1", required = true, position = 5)
    private Integer brandId;

    public CarDto(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.price = car.getPrice();
        this.power = car.getPower();
        this.description = car.getDescription();
        this.brandId = car.getBrandId();
    }
}
