package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private EmployeeController employeeController;

    @Test
    void shouldCreateNewEmployee() {
        // given
        EmployeeRequest employeeRequest = new EmployeeRequest("naaa", "username", "emai@la", "jobposition", 10000.0);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldNotCreateNewEmployeeWithInvalidEmail() {
        // given
        String expectedEmail = "xyz@abc.com";
        EmployeeRequest employeeRequest =
                new EmployeeRequest("naaa", "username", expectedEmail, "jobposition", 10000.0);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldNotCreateNewEmployeeWithInvalidName() {
        // given
        String expectedName = "Ki";
        EmployeeRequest employeeRequest =
                new EmployeeRequest(expectedName, "username", "emai@la", "jobposition", 10000.0);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldNotCreateNewEmployeeWithInvalidSalary() {
        // given
        Double expextedSalary = 1000.0;
        EmployeeRequest employeeRequest =
                new EmployeeRequest("name", "username", "emai@la", "jobposition", expextedSalary);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldCreateNewEmployeeMockito() {
        // given
        final var expectedResult = new EmployeeResponse(1, "m11", "aaa", "post@post", "aaa", 111133.0);
        final var employeeRequest = new EmployeeRequest("name", "username", "aa@ss", "jobposition", 1000.0);
        Mockito.when(employeeService.createEmployee(employeeRequest)).thenReturn(expectedResult);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(expectedResult).isEqualTo(result.getBody());
        Assertions.assertThat(expectedResult.getName())
                .isEqualTo(result.getBody().getName());
        Assertions.assertThat(expectedResult.getEmail())
                .isEqualTo(result.getBody().getEmail());
        Assertions.assertThat(expectedResult.getId()).isEqualTo(result.getBody().getId());
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Mockito.verify(employeeService, Mockito.times(1)).createEmployee(employeeRequest);
    }
}
