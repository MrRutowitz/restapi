package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.service.EmployeeService;
import com.taskone.restapi.service.TimeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
public class EmployeeController {

    private final EmployeeService employeeService;

    private final TimeService timeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public List<EmployeeResponse> listEmployees(Pageable page) {
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees(page);
        return employeeResponses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/salary-range")
    public List<EmployeeResponse> getEmployeesBySalaryRange(
            @Min(value = 1000, message = "Minimum salary 1000$")
                    @Max(value = 100000, message = "Maximum salary 100000$")
                    @RequestParam
                    double minSalary,
            @Min(value = 1000, message = "Minimum salary 1000$")
                    @Max(value = 100000, message = "Maximum 100000$")
                    @RequestParam
                    double maxSalary) {
        List<EmployeeResponse> employeeResponses = employeeService.getEmployeesBySalaryRange(minSalary, maxSalary);
        return employeeResponses;
    }

    @GetMapping("/search-by-jobposition")
    public List<EmployeeResponse> searchJobsPositionByTitle(
            @Pattern(regexp = "^[A-Za-z]*$", message = "Use only letters!") @RequestParam String title) {
        List<EmployeeResponse> employeeResponses = employeeService.getEmployeesByJobPosition(title);
        return employeeResponses;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
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

    @GetMapping("/time")
    public ResponseEntity time() {
        TimeService timeService = new TimeService();
        String time = timeService.currentTime().getTime();
        return new ResponseEntity<>(time, HttpStatus.OK);
    }
}
