package com.taskone.restapi.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
