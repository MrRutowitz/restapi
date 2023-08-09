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
    void shouldAddNewEmployee_IfCorrect() {
        // given
        EmployeeRequest employeeRequest = new EmployeeRequest("name", "username", "email@a", "jobposition", 1000.0);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldAddNewEmployee() {
        // given
        EmployeeResponse employeeResponse = new EmployeeResponse(1, "mich", "aaa", "post@post", "aaa", 1111);
        Mockito.when(employeeService.createEmployee(Mockito.any())).thenReturn(employeeResponse);
        EmployeeRequest employeeRequest = new EmployeeRequest("name", "username", "email@a", "jobposition", 1000.0);
        // when
        final var result = employeeController.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Mockito.verify(employeeService, Mockito.times(1)).createEmployee(employeeRequest);
    }

    //    @Test
    //    void shouldUpdateExistingEmployee() {
    //        // given
    //        EmployeeResponse employeeResponse = new EmployeeResponse(1L, "name", "username", "ema@il", "jobposition",
    // 1000.0);
    //        EmployeeRequest updatedEmployee = new EmployeeRequest("aaa", "aaa", "aa@a", "aaa", 2331.2);
    //        Mockito.when(employeeService.updateEmployee(1L, updatedEmployee)).thenReturn(employeeResponse);
    //        EmployeeRequest employeeRequest = new EmployeeRequest("aaa", "name", "userna@me", "email", 1000.0);
    //        // when
    //        final ResponseEntity<EmployeeResponse> result = employeeController.updateEmployee(1L, employeeRequest);
    //        // then
    //        Assertions.assertThat(result).isNotNull();
    //        final var resultEmployee = result.getBody();
    //        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    //        Assertions.assertThat(resultEmployee).isNotNull();
    //        Assertions.assertThat(resultEmployee.getId()).isEqualTo(employeeResponse.getId());
    //        Assertions.assertThat(resultEmployee.getName()).isEqualTo(employeeResponse.getName());
    //        Assertions.assertThat(resultEmployee.getUsername()).isEqualTo(employeeResponse.getUsername());
    //        Mockito.verify(employeeService, Mockito.times(1)).updateEmployee(1L, employeeRequest);
    //    }
}
