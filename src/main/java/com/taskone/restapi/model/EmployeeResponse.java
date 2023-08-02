package com.taskone.restapi.model;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeResponse {

    private long id;
    private String name;
    private String username;
    private String email;
    private String jobposition;
    private double salary;
}
