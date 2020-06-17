package com.example.demo.controller.exeption;

import org.springframework.http.HttpStatus;

public class CustomExeption extends RuntimeException {
    HttpStatus httpStatus;
    public CustomExeption(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;


    }
}
