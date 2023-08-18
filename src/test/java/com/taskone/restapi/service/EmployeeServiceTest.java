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
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class EmployeeServiceTest {

    @MockBean
    EmployeeRepository employeeRepositoryMock;

    @Autowired
    EmployeeService employeeService;

    @Test
    public void shouldGetCurrentTime() {
        // given
        final var expectedDate = "2023-08-15";
        final var timeService = Mockito.mock(TimeService.class);
        Mockito.when(timeService.currentTime()).thenReturn("2023-08-15");
        // when
        final var result = timeService.currentTime();
        // then
        Assertions.assertThat(result).isEqualTo(expectedDate);
        Mockito.verify(timeService, Mockito.times(1)).currentTime();
    }

    @Test
    void shouldGetEmployeesBySalaryRange() {
        // given
        final var min = 1000.0;
        final var max = 7000.0;
        Mockito.when(employeeRepositoryMock.findBySalaryBetween(min, max)).thenReturn(List.of());
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
        employees.add(new Employee(1, "Michal", "username1", "email@.com", "post", 8500.0));
        employees.add(new Employee(2, "Ola", "username2", "xczs@gm.com", "post", 12000.0));
        employees.add(new Employee(3, "Jan", "username3", "asdf@gm.com", "post", 8900.0));
        employees.add(new Employee(4, "Andrzej", "username3", "asdf@gm.com", "post", 7900.0));
        return employees;
    }

    @Test
    void shouldGetEmployeesWithGivenSize() {
        // given
        Random random = new Random();
        final var page = random.nextInt(10);
        final var size = employeeResponses().size();
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
        final var updateRequest = new EmployeeRequest("name", "dddd", "abc@xx", "jobposition", 111.0);
        final var employee = new Employee(employeeId, "John", "bbbb", "xyz@aa", "jobposition", 11.0);
        final var updatedEmployee = new Employee(1L, "name", "dddd", "abc@xx", "jobposition", 111.0);
        final var employeeResponse = new EmployeeResponse(1L, "name", "dddd", "abc@xx", "jobposition", 111.0);
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepositoryMock.save(updatedEmployee)).thenReturn(updatedEmployee);
        // when
        final var result = employeeService.updateEmployee(employeeId, updateRequest);
        // then
        assertNotNull(result);
        assertEquals(employeeResponse, result);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).save(updatedEmployee);
    }

    @Test
    public void shouldGetEmployeeById() {
        // given
        final var employeeId = 200L;
        final var expectedResult = new Employee(200L, "name", "suranem", "dsds", "sdad", 111.0);
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.of(expectedResult));
        // when
        final var result = employeeService.getEmployeeById(employeeId);
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
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
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.of(employee));
        // when
        employeeService.deleteEmployeeById(employeeId);
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
    }

    @Test
    void shouldThrowEmployeeNotFoundException() {
        // given
        final var employeeId = 1L;
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.empty());
        // when
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployeeById(employeeId));
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
    }
}
