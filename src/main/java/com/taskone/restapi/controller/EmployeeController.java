package com.taskone.restapi.controller;


import com.taskone.restapi.model.Employee;
import com.taskone.restapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor //tworzy konstruktory
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;




    @GetMapping("/list")
    public List<Employee> list(){

        return employeeService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getDetails(@PathVariable("id") Integer id)
    {
        return  ResponseEntity.ok(employeeService.details(id));
    }




//
//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    @GetMapping("/employee")
//    public ResponseEntity<List<Employee>> listEmployess() //konterner na moj response
//    {
//        return ResponseEntity.ok(List.of(Employee.builder().id(1).firstName("Michal").lastName("Rutowicz").build()));
//    }
//
//    @GetMapping("/employees")
//    public List<Employee> listAll(){
//        return employeeRepository.findAll();
//    }
//
//    @GetMapping("/addemployee")
//    public List<Employee> addEmployee(){
//         employeeRepository.save(employee);
//        return null;
//    }
















//    @GetMapping("/addemployee")
//    public ResponseEntity<List<Employee>> addEmployee(){
//        Employee employee = new Employee("Michal", "Rutowicz");
//        ResponseEntity.ok(List.of(E))








    }








