package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
@Service
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.getAllEmployees();
        return employeeResponses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getDetails(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest){
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id,employeeRequest);
        if(employeeResponse != null){
            return new ResponseEntity<>(employeeResponse,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Integer id) {
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




































