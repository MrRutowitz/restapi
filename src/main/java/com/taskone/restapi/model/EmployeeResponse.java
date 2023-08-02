package com.taskone.restapi.model;

import lombok.*;

import jakarta.validation.constraints.*;


@AllArgsConstructor
@Getter
@Setter
public class EmployeeResponse {

    private long id;
    public String name;
    private String username;
    private String email;
    private String jobposition;
    private double salary;

}
