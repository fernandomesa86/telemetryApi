package com.example.kafka.controller;

import com.example.kafka.exception.VehicleNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VehicleExceptionController {
    @ExceptionHandler(value = VehicleNotfoundException.class)
    public ResponseEntity<Object> exception(VehicleNotfoundException exception) {
        return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
    }
}
