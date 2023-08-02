package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.service.EmployeeService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
@Service
@Validated
public class EmployeeController extends RuntimeException {

    private final EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public List<EmployeeResponse> getAllEmployees(Pageable page) {
        List<EmployeeResponse> employeeResponses = employeeService.getAllEmployees(page);
        return employeeResponses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getDetails(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (NullPointerException nullPointerException) {
            nullPointerException.fillInStackTrace();
            System.out.println("Employee does not exist!");
            return null;
        }
    }

    @GetMapping("/salary-range")
    public List<EmployeeResponse> getEmployeesBySalaryRange(
            @RequestParam double minSalary, @RequestParam double maxSalary) {
        List<EmployeeResponse> employeeResponses = employeeService.getEmployeesBySalaryRange(minSalary, maxSalary);
        return employeeResponses;
    }

    @GetMapping("/search-jobposition")
    public List<EmployeeResponse> searchJobspositionByTitle(@RequestParam String title) {
        List<EmployeeResponse> employeeResponses = employeeService.getEmployeesByTitle(title);
        return employeeResponses;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        if (employeeResponse != null) {
            return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.deleteEmployeeById(id);
        if (employeeResponse != null) {
            return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    @Autowired
    //    private Environment environment;

    @GetMapping("/time")
    public ResponseEntity<String> time() {
        return ResponseEntity.ok(employeeService.dateFormat(LocalDate.now()));
    }

    @Value("${spring.mvc.format.date}")
    private String dateFormatt;

    @GetMapping("/time1")
    public ResponseEntity<String> time1() {

        return ResponseEntity.ok("Current date: " + dateFormatt);
    }

    //    @GetMapping("time2")
    //    public ResponseEntity<String> time2(Environment environment){
    //        return ResponseEntity.ok(environment.getProperty())
    //    }

}
