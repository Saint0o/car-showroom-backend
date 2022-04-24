package com.carshowroom.project.carshowroomproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDto {
    private String jwt;

    public UserInfoDto (String jwt) {
        this.jwt = jwt;
    }
}
