package com.carshowroom.project.carshowroomproject.dto;

import com.carshowroom.project.carshowroomproject.entities.Brand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BrandDto {
    @ApiModelProperty(notes = "Unique identifier of the brand. No two products can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Brand's title.", example = "BMW", required = false, position = 1)
    private String title;

    @ApiModelProperty(notes = "Brand cars array", example = "3", required = false, position = 2)
    private List<CarDto> carsList;

    public BrandDto (Brand brand) {
        this.id = brand.getId();
        this.title = brand.getTitle();
    }
}
