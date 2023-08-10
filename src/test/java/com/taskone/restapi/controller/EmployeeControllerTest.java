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
    void shouldAddNewEmployee() {
        // given
        final var expectedResult = new EmployeeResponse(1, "m11", "aaa", "post@post", "aaa", 111133.0);
        final var employeeRequest = new EmployeeRequest("name", "username", "aa@ss", "jobposition", 1000.0);
        Mockito.when(employeeService.createEmployee(employeeRequest)).thenReturn(expectedResult);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(expectedResult).isEqualTo(result.getBody());
        Assertions.assertThat(result.getBody().getName()).isEqualTo(expectedResult.getName());
        Assertions.assertThat(result.getBody().getEmail()).isEqualTo(expectedResult.getEmail());
        Assertions.assertThat(result.getBody().getId()).isEqualTo(expectedResult.getId());
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Mockito.verify(employeeService, Mockito.times(1)).createEmployee(employeeRequest);
    }
}
