package com.taskone.restapi.model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {

    @NotNull
    @Size(min = 2, max = 20, message = "Please use 2-20 letters in name")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Use only letters!")
    private String name;

    @NotNull
    private String username;

    @Email(message = "Invalid email address!")
    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please use only letters!")
    private String jobposition;

    @Min(value = 1000, message = "Minimum salary 1000$")
    @Max(value = 100000, message = "Maximum salary 100000$")
    @NotNull
    private Double salary;
}
