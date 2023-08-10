package com.taskone.restapi.service;

import com.taskone.restapi.entity.Employee;
import com.taskone.restapi.model.EmployeeNotFoundException;
import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.model.TimeSupplier;
import com.taskone.restapi.repository.EmployeeRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${date.time.format:yyyy-MM-dd}")
    private String timeFormat;

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {

        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .username(employeeRequest.getUsername())
                .email(employeeRequest.getEmail())
                .jobposition(employeeRequest.getJobposition())
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeResponse(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getUsername(),
                savedEmployee.getEmail(),
                savedEmployee.getJobposition(),
                savedEmployee.getSalary());
    }

    public List<EmployeeResponse> getEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getUsername(),
                        employee.getEmail(),
                        employee.getJobposition(),
                        employee.getSalary()))
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee findEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return new EmployeeResponse(
                findEmployee.getId(),
                findEmployee.getName(),
                findEmployee.getUsername(),
                findEmployee.getEmail(),
                findEmployee.getJobposition(),
                findEmployee.getSalary());
    }

    public List<EmployeeResponse> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
        List<Employee> employees = employeeRepository.findBySalaryBetween(minSalary, maxSalary);
        return employees.stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getUsername(),
                        employee.getEmail(),
                        employee.getJobposition(),
                        employee.getSalary()))
                .collect(Collectors.toList());
    }

    public List<EmployeeResponse> getEmployeesByJobPosition(String title) {
        List<Employee> employees = employeeRepository.findByTitleOfJobPosition(title);
        return employees.stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getUsername(),
                        employee.getEmail(),
                        employee.getJobposition(),
                        employee.getSalary()))
                .collect(Collectors.toList());
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest updatedEmployee) {
        Employee exisitingEmployee =
                employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        if (exisitingEmployee != null) {
            exisitingEmployee.setName(updatedEmployee.getName());
            exisitingEmployee.setUsername(updatedEmployee.getUsername());
            exisitingEmployee.setEmail(updatedEmployee.getEmail());
            exisitingEmployee.setJobposition(updatedEmployee.getJobposition());
            exisitingEmployee.setSalary(updatedEmployee.getSalary());
            exisitingEmployee = employeeRepository.save(exisitingEmployee);

            return new EmployeeResponse(
                    exisitingEmployee.getId(),
                    exisitingEmployee.getName(),
                    exisitingEmployee.getUsername(),
                    exisitingEmployee.getEmail(),
                    exisitingEmployee.getJobposition(),
                    exisitingEmployee.getSalary());
        } else {
            return null;
        }
    }

    public EmployeeResponse deleteEmployeeById(Long id) {
        Employee findEmployee = employeeRepository.findById(id).orElse(null);
        if (findEmployee != null) {
            employeeRepository.delete(findEmployee);
            return new EmployeeResponse(
                    findEmployee.getId(),
                    findEmployee.getName(),
                    findEmployee.getUsername(),
                    findEmployee.getEmail(),
                    findEmployee.getJobposition(),
                    findEmployee.getSalary());
        } else {
            return null;
        }
    }

    public String currentTime() {

        TimeSupplier<String> supplier = () -> dateTimeFormatter.format(LocalDateTime.now());
        return supplier.getTime();
    }

    public String welcomeMessage(String name) {
        return String.format("Welcome", name);
    }
}
