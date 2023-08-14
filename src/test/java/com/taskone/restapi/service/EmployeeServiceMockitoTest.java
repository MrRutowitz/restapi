package com.taskone.restapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.taskone.restapi.entity.Employee;
import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class EmployeeServiceMockitoTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private TimeService timeService;

    @Test
    public void shouldGetCurrentTime() {
        // given
        final var expectedDate = "2020-08-14";
        Mockito.when(timeService.currentTime()).thenReturn(() -> "2020-08-14");
        //        try (MockedStatic<LocalDate> utilities = Mockito.mockStatic(LocalDate.class)) {
        //        utilities.when(LocalDate::now).thenReturn(LocalDate.of(2000,8,29));
        // when
        String result = timeService.currentTime().getTime();
        // then
        Assertions.assertThat(result).isEqualTo(expectedDate);
        Mockito.verify(timeService, Mockito.times(1)).currentTime();
    }

    @Test
    void shouldGetEmployeesBySalaryRange() {
        // given
        final var min = 1000.0;
        final var max = 120000.0;
        final var expectedMin = 1000.0;
        final var expectedMax = 12000.0;
        Mockito.when(employeeRepository.findBySalaryBetween(1000.0, 150000.0)).thenReturn(employeeResponses());
        // when
        List<EmployeeResponse> result = employeeService.getEmployeesBySalaryRange(expectedMin, expectedMax);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(3);
        Mockito.verify(employeeRepository, Mockito.times(1)).findBySalaryBetween(min, max);
    }

    public List<Employee> employeeResponses() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "mich", "username1", "email@.com", "post", 8500.0));
        employees.add(new Employee(2, "oli", "username2", "xczs@gm.com", "post", 12000.0));
        employees.add(new Employee(3, "adnrzej", "username3", "asdf@gm.com", "post", 8900.0));
        return employees;
    }

    @Test
    void shouldGetEmployeesWithGivenSize() {
        // given
        int page = 0;
        int size = 3;
        Pageable pageable;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Employee> pageSubList = new PageImpl<>(employeeResponses());
        Mockito.when(employeeRepository.findAll(PageRequest.of(page, size))).thenReturn(pageSubList);
        // when
        List<EmployeeResponse> result = employeeService.getEmployees(pageRequest);
        // then
        assertNotNull(result);
        assertEquals(size, result.size());
    }

    @Test
    void shouldUpdateEmployeeMockito() {
        // given
        long employeeId = 1L;
        EmployeeRequest request = new EmployeeRequest("name", "suranem", "dsds", "sdad", 111.0);
        final var employee = new Employee(employeeId, "John", "Doe", "Software Engineer", "asa", 11.0);
        final var expectedEmployee = new Employee(1, "name", "suranem", "dsds", "sdad", 111.0);
        final var expectedResult = new EmployeeResponse(1, "name", "suranem", "dsds", "sdad", 111.0);
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.save(expectedEmployee)).thenReturn(expectedEmployee);
        // when
        final var result = employeeService.updateEmployee(employeeId, request);
        // then
        assertNotNull(result);
        assertEquals(expectedResult, result);
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeId);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(expectedEmployee);
    }
}
