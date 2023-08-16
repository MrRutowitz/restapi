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
//    public boolean equals(Object object) {
//        if (!(object instanceof EmployeeResponse)) {
//            return false;
//        }
//        EmployeeResponse employeeResponseTest = (EmployeeResponse) object;
//        if (employeeResponseTest.getName() == this.getName() && employeeResponseTest.getEmail() == this.getEmail()) {
//            return true;
//        }
//        return false;
//    }
}
