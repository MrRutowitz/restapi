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

    @MockBean
    TimeService timeService;

    @Test
    public void shouldGetCurrentTime() {
        // given
        final var expectedDate = "2023-08-15";
        Mockito.when(timeService.currentTime()).thenReturn(expectedDate);
        // when
        final var result = employeeService.currentTime();
        // then
        Assertions.assertThat(result).isEqualTo(expectedDate);
        Mockito.verify(timeService, Mockito.times(1)).currentTime();
    }

    @Test
    void shouldGetEmployeesBySalaryRange() {
        // given
        final var min = 1000.0;
        final var max = 9000.0;
        Mockito.when(employeeRepositoryMock.findBySalaryBetween(Mockito.eq(min), Mockito.eq(max)))
                .thenReturn(employeeResponses());
        // when
        final var result = employeeService.getEmployeesBySalaryRange(min, max);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(4);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findBySalaryBetween(Mockito.eq(min), Mockito.eq(max));
    }

    public List<Employee> employeeResponses() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Michal", "username1", "email@.com", "post1", 8500.0));
        employees.add(new Employee(2, "Ala", "username2", "asddf@gm.com", "post2", 8900.0));
        employees.add(new Employee(3, "Jan", "username3", "fasdf@gm.com", "post3", 8900.0));
        employees.add(new Employee(4, "Andrzej", "username4", "asdasf@gm.com", "post4", 7900.0));
        return employees;
    }

    @Test
    void shouldGetEmployeesWithGivenSize() {
        // given
        final var page = 2;
        final var size = 4;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Employee> pageSubList = new PageImpl<>(employeeResponses());
        Mockito.when(employeeRepositoryMock.findAll(pageRequest)).thenReturn(pageSubList);
        // when
        final var result = employeeService.getEmployees(pageRequest);
        // then
        assertNotNull(result);
        assertEquals(result.size(), size);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findAll(pageRequest);
    }

    @Test
    void shouldUpdateEmployeeMockito() {
        // given
        final var employeeId = 1L;
        final var updateRequest = new EmployeeRequest("name", "dddd", "abc@xx", "jobposition", 111.0);
        final var employee = new Employee(employeeId, "John", "bbbb", "xyz@aa", "jobposition", 11.0);
        final var updatedEmployee = new Employee(employeeId, "name", "dddd", "abc@xx", "jobposition", 111.0);
        final var employeeResponse = new EmployeeResponse(employeeId, "name", "dddd", "abc@xx", "jobposition", 111.0);
        Mockito.when(employeeRepositoryMock.findById(Mockito.eq(employeeId))).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepositoryMock.save(updatedEmployee)).thenReturn(updatedEmployee);
        // when
        final var result = employeeService.updateEmployee(employeeId, updateRequest);
        // then
        assertNotNull(result);
        assertEquals(employeeResponse, result);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(Mockito.eq(employeeId));
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).save(updatedEmployee);
    }

    @Test
    public void shouldGetEmployeeById() {
        // given
        final var employeeId = 200L;
        final var expectedResult = new Employee(employeeId, "name", "surname", "dsds", "sdad", 111.0);
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
        final var employee = new Employee(employeeId, "michal2", "xxx", "xyz@abc", "xxx", 10000.0);
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.of(employee));
        // when
        employeeService.deleteEmployeeById(employeeId);
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).deleteById(employeeId);
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenDeletingNonExistentEmployee() {
        // given
        final var employeeId = 1L;
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.empty());
        // when
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployeeById(employeeId));
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
        Mockito.verify(employeeRepositoryMock, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenUpdatingNonExistentEmployee() {
        // given
        final var employeeId = 20L;
        final var updatedEmployee = new EmployeeRequest("Michal", "Rutow", "micr@gmail.com", "junior", 4000.0);
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.empty());
        // when
        assertThrows(
                EmployeeNotFoundException.class, () -> employeeService.updateEmployee(employeeId, updatedEmployee));
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
        Mockito.verify(employeeRepositoryMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenGettingByIdNonExistentEmployee() {
        // given
        final var employeeId = 1L;
        Mockito.when(employeeRepositoryMock.findById(employeeId)).thenReturn(Optional.empty());
        // when
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(employeeId));
        // then
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(employeeId);
    }
}
