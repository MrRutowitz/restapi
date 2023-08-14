package com.taskone.restapi.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Data
public class EmployeeResponse {

    private long id;
    private String name;
    private String username;
    private String email;
    private String jobposition;
    private Double salary;

    //    @Override
    //    boolean equals(Object object){
    //
    //    }
}
