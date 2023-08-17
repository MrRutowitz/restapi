package com.taskone.restapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.taskone.restapi.entity.Employee;
import com.taskone.restapi.model.EmployeeNotFoundException;
import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class EmployeeServiceTest {

    @Test
    public void shouldGetCurrentTime() {
        // given
        final var expectedDate = "2023-08-15";
        final var timeService = Mockito.mock(TimeService.class);
        Mockito.when(timeService.currentTime()).thenReturn(() -> "2023-08-15");
        // when
        final var result = timeService.currentTime().getTime();
        // then
        Assertions.assertThat(result).isEqualTo(expectedDate);
        Mockito.verify(timeService, Mockito.times(1)).currentTime();
    }

    @Test
    void shouldGetEmployeesBySalaryRange() {
        // given
        final var min = 1000.0;
        final var max = 7000.0;
        final var employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        final var employeeService = new EmployeeService(employeeRepositoryMock);
        Mockito.when(employeeRepositoryMock.findBySalaryBetween(Mockito.eq(min), Mockito.eq(max)))
                .thenReturn(List.of());
        // when
        final var result = employeeService.getEmployeesBySalaryRange(min, max);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(0);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1))
                .findBySalaryBetween(Mockito.anyDouble(), Mockito.anyDouble());
    }

    public List<Employee> employeeResponses() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "mich", "username1", "email@.com", "post", 8500.0));
        employees.add(new Employee(2, "oli", "username2", "xczs@gm.com", "post", 12000.0));
        employees.add(new Employee(3, "adnrzej", "username3", "asdf@gm.com", "post", 8900.0));
        employees.add(new Employee(4, "adnrzej", "username3", "asdf@gm.com", "post", 7900.0));
        return employees;
    }

    @Test
    void shouldGetEmployeesWithGivenSize() {
        // given
        final var page = 2;
        final var size = 4;
        final var employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        final var employeeService = new EmployeeService(employeeRepositoryMock);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Employee> pageSubList = new PageImpl<>(employeeResponses());
        Mockito.when(employeeRepositoryMock.findAll(pageRequest)).thenReturn(pageSubList);
        // when
        final var result = employeeService.getEmployees(pageRequest);
        // then
        assertNotNull(result);
        assertEquals(result.size(), size);
    }

    @Test
    void shouldUpdateEmployeeMockito() {
        // given
        final var employeeId = 1L;
        final var request = new EmployeeRequest("name", "suranem", "dsds", "sdad", 111.0);
        final var employee = new Employee(employeeId, "John", "Doe", "Software Engineer", "asa", 11.0);
        final var expectedEmployee = new Employee(1L, "name", "suranem", "dsds", "sdad", 111.0);
        final var expectedResult = new EmployeeResponse(1L, "name", "suranem", "dsds", "sdad", 111.0);
        final var employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        final var employeeService = new EmployeeService(employeeRepositoryMock);
        Mockito.when(employeeRepositoryMock.findById(Mockito.eq(employeeId))).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepositoryMock.save(expectedEmployee)).thenReturn(expectedEmployee);
        // when
        final var result = employeeService.updateEmployee(employeeId, request);
        // then
        assertNotNull(result);
        assertEquals(expectedResult, result);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(Mockito.eq(employeeId));
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).save(expectedEmployee);
    }

    @Test
    public void shouldGetEmployeeById() {
        // given
        final var employeeId = 200L;
        final var expectedResult = new Employee(200L, "name", "suranem", "dsds", "sdad", 111.0);
        final var employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        final var employeeService = new EmployeeService(employeeRepositoryMock);
        Mockito.when(employeeRepositoryMock.findById(Mockito.eq(employeeId))).thenReturn(Optional.of(expectedResult));
        // when
        final var result = employeeService.getEmployeeById(employeeId);
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(Mockito.eq(employeeId));
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getEmail(), result.getEmail());
    }

    @Test
    void shouldCreateNewEmployee() {
        // given
        final var employee = new Employee(1, "michal2", "xxx", "xyz@abc", "xxx", 10000.0);
        final var expectedResult = new EmployeeResponse(1, "michal2", "xxx", "xyz@abc", "xxx", 10000.0);
        final var employeeRequest = new EmployeeRequest("aaa", "xxx", "xyz@abc", "xxx", 10000.0);
        final var employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        final var employeeService = new EmployeeService(employeeRepositoryMock);
        Mockito.when(employeeRepositoryMock.save(Mockito.any())).thenReturn(employee);
        // when
        final var result = employeeService.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(expectedResult).isEqualTo(result);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void shouldDeleteEmployeeById() {
        // given
        final var employeeId = 1L;
        final var employee = new Employee(1, "michal2", "xxx", "xyz@abc", "xxx", 10000.0);
        final var employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        final var employeeService = new EmployeeService(employeeRepositoryMock);
        Mockito.when(employeeRepositoryMock.findById(Mockito.eq(employeeId))).thenReturn(Optional.of(employee));
        Mockito.doNothing().when(employeeRepositoryMock).deleteById(Mockito.eq(employeeId));
        // when
        employeeService.deleteEmployeeById(employeeId);
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(Mockito.eq(employeeId));
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).deleteById(Mockito.eq(employeeId));
        Mockito.verifyNoMoreInteractions(employeeRepositoryMock);
    }

    @Test
    void shouldThrowEmployeeNotFoundException() {
        // given
        final var employeeId = 1L;
        final var employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        final var employeeService = new EmployeeService(employeeRepositoryMock);
        Mockito.when(employeeRepositoryMock.findById(Mockito.eq(employeeId))).thenReturn(Optional.empty());
        Mockito.doNothing().when(employeeRepositoryMock).deleteById(Mockito.eq(employeeId));
        // when
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployeeById(employeeId));
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(Mockito.eq(employeeId));
        Mockito.verify(employeeRepositoryMock, Mockito.never()).deleteById(Mockito.any());
        Mockito.verifyNoMoreInteractions(employeeRepositoryMock);
    }
}
