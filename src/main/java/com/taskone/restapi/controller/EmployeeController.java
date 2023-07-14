package com.taskone.restapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskone.restapi.model.Employee;
import com.taskone.restapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@RequiredArgsConstructor //tworzy konstruktory
@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private static final String JSON_FILE_PATH = "/json/empolyees.json";

    private final EmployeeService employeeService;



    @GetMapping("/")
    public String welcome(String name) {
        return employeeService.welcomeMessage(name);
    }


    @GetMapping("/list")
    public List<Employee> list() {
        return employeeService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getDetails(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        employeeService.deleteById(id);
        return "Employee #" + id + " is deleted!";
    }

    @GetMapping("/time")
    public String time() {
        return employeeService.dateFormat(LocalDate.now());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmpolyee(@RequestBody Employee employee) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(JSON_FILE_PATH);
            List<Employee> employees = new ArrayList<>();

            if (file.exists()) {
                employees = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
            }
            Employee employee1 = new Employee(employee.getId(), employee.getName(), employee.getUsername(), employee.email, employee.jobposition, employee.salary);



            employees.add(employee1);
            objectMapper.writeValue(file, employees); //zapisuje do pliku json
            return ResponseEntity.ok("Record added");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }


    }
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

















