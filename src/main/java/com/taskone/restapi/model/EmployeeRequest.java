package com.taskone.restapi.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {

    //@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @NotNull
    @Size(min = 2, max = 20, message = "Please use 2-20 characters in name")
    public String name;
    @NotNull
    private String username;
    @Email(message = "Use @ in email, example xxx@gmail.com")
    @NotNull
    private String email;
    @NotNull
    private String jobposition;
    @Min(value = 1000, message = "Minimum salary 1000Euro")
    @NotNull
    private Double salary;



}
