package com.taskone.restapi.service;

import com.taskone.restapi.repository.EmployeeRepository;
import com.taskone.restapi.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    public EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> list(){
        return employeeRepository.findAll();
    }


    public void save(List<Employee> employees){
        employeeRepository.saveAll(employees);
    }
//    public void save(Employee)
//    {
//
//    }

    public Employee details(Integer id){

        return employeeRepository.findById(id).orElse(null);
    }


}
