package com.taskone.restapi.controller;


import com.taskone.restapi.model.Employee;
import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//1. Rozdziel model bazodanowy od modelu HTTP:
//        - przygotuj modele request/response dla danych entity
//        - nie korzystaj z konwersji do stringa przy użyciu objectMapper
//        2. Dodaj kilka customowych metod do JPA repository w ramach praktyki:
//        - dodaj wyszukiwanie po zakresie salary <min, max>
//  - dodaj wyszukiwanie po polu tekstowym z użyciem query LIKE (użyj native query)
//        3. Dodaj paginację do endpointa z pobieraniem listy
//        4. Dodać obsługę błędów + walidacje na API
//        5. Spróbuj wykorzystać adnotację @RequiredArgsConstructor zamiast tworzyć konstruktory z polami
//        6. Dodaj odczytywanie parametrów z application.properties (np. format czasu)
//        7. Dodaj aktuator springa dla monitoringu aplikacji
//        8. Dodaj integration unit-test dla aplikacji
//        9. Dodaj swaggera
//        10. Zdockeryzuj aplikację


@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
@Service
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<String> welcome(String name) {
        return ResponseEntity.ok(employeeService.welcomeMessage(name));
    }


    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse,HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        ResponseEntity<List<EmployeeResponse>> responseEntity = employeeService.getAllEmployees();
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getDetails(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest){
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id,employeeRequest);
        if(employeeResponse != null){
            return new ResponseEntity<>(employeeResponse,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<EmployeeResponse> delete(@PathVariable Integer id) {
        EmployeeResponse employeeResponse = employeeService.deleteEmployeeById(id);
        if(employeeResponse != null){
            return new ResponseEntity<>(employeeResponse,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/time")
    public ResponseEntity<String> time(){
        return ResponseEntity.ok(employeeService.dateFormat(LocalDate.now()));
    }
}




































