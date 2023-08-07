package com.taskone.restapi.model;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {

        super(message);
    }
}
