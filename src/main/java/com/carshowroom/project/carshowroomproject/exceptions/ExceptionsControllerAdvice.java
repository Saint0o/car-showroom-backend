package com.carshowroom.project.carshowroomproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ShowroomError showroomError = new ShowroomError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(showroomError, HttpStatus.NOT_FOUND);
    }
}
