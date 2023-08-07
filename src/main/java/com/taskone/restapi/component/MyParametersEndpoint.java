package com.taskone.restapi.component;

import com.taskone.restapi.entity.Employee;
import com.taskone.restapi.service.EmployeeService;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "myParam")
public class MyParametersEndpoint {
//
//    private final EmployeeService employeeService;
//
//    public MyParametersEndpoint(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//    public List<Employee> searchEmployee(String keyword){
//        return employeeService.;
//    }
}
