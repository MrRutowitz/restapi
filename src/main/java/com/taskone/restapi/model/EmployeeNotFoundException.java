package com.taskone.restapi.model;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long message) {

        super("Employee with ID " + message + " not found");
    }
}
