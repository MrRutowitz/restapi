package com.taskone.restapi.service;

import com.taskone.restapi.repository.EmployeeRepository;
import com.taskone.restapi.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmployeeService {

    public EmployeeRepository employeeRepository;
    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> list(){
        return employeeRepository.findAll();
    }


    public void save(List<Employee> employees){ employeeRepository.saveAll(employees);}

    public Employee save(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Employee findById(Integer id){

        return employeeRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id){
        employeeRepository.deleteById(id);
    }

    public String dateFormat(LocalDate date){
        return date.format(dateTimeFormatter);
    }


    public String welcomeMessage(String name)
    {
        return String.format("Welcome", name);
    }









}
