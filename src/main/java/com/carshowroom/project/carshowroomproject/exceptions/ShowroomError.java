package com.carshowroom.project.carshowroomproject.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ShowroomError {
    private int status;
    private String message;
    private Date timestamp;

    public ShowroomError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
