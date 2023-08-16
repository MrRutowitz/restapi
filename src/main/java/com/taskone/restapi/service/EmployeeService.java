package com.taskone.restapi.service;

import com.taskone.restapi.entity.Employee;
import com.taskone.restapi.model.EmployeeNotFoundException;
import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(com.taskone.restapi.model.EmployeeRequest employeeRequest) {

        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .username(employeeRequest.getUsername())
                .email(employeeRequest.getEmail())
                .jobposition(employeeRequest.getJobposition())
                .salary(employeeRequest.getSalary())
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
            Employee savedEmployee = employeeRepository.save(exisitingEmployee);

            return new EmployeeResponse(
                    savedEmployee.getId(),
                    savedEmployee.getName(),
                    savedEmployee.getUsername(),
                    savedEmployee.getEmail(),
                    savedEmployee.getJobposition(),
                    savedEmployee.getSalary());
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteEmployeeById(Long id) {
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
    }
}
