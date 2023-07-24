package com.taskone.restapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {

    private String name;
    private String username;
    private String email;
    private String jobposition;
    private double salary;

}
