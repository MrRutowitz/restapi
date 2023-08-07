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
    @Size(min = 2, max = 20, message = "Please use 2-20 characters in name")
    @Pattern(regexp = "^[A-Za-z]*$", message = "You can not use numbers and characters in name! ")
    private String name;

    @NotNull
    private String username;

    @Email(message = "Use @ in email, example xxx@gmail.com")
    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$", message = "Please use only letters!")
    private String jobposition;

    @Min(value = 1000, message = "Minimum salary 1000Euro")
    @Max(value = 100000, message = "Maksimum 100000")
    @NotNull
    private Double salary;
}
