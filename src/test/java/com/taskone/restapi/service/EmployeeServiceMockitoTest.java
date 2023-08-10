package com.taskone.restapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.taskone.restapi.entity.Employee;
import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.model.TimeSupplier;
import com.taskone.restapi.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EmployeeServiceMockitoTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private TimeSupplier timeSupplier;

    @Test
    void currentTime_shouldReturnLocalDateNow() {
        // given
        String expectedDate = String.valueOf(LocalDate.now());
        Mockito.when(timeSupplier.getTime()).thenReturn(expectedDate);
        // when
        final var dateString = employeeService.currentTime();
        // then
        assertEquals(expectedDate, dateString);
        Mockito.verify(timeSupplier, Mockito.atMostOnce()).getTime();
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
        Mockito.verify(employeeRepository, Mockito.atMostOnce()).findById(employeeId);
        Mockito.verify(employeeRepository, Mockito.atMostOnce()).save(expectedEmployee);
    }
}
