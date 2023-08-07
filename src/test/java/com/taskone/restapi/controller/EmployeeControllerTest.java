package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
class EmployeeControllerTest {

    @MockBean // @spy sprawdz
    private EmployeeService employeeService;

    @Autowired
    private EmployeeController employeeController = new EmployeeController(employeeService);

    @Test
    void shouldAddNewEmployee() {
        final var result = employeeController.createEmployee(
                new EmployeeRequest("name", "username", "email@a", "jobposition", 1000.0));
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    //        @Test
    //        void shouldUpdateExistingEmployee() {
    //            //given toDo mockitowhen
    //            final int id = 1;
    //            final String name = "Michal";
    //            final String username = "micrut";
    //            final String email = "micrut@gmail.com";
    //            final String jobpostion = "backend";
    //            final int salary = 10000;
    //
    //            final var employee = Employee.builder().id(id).name(name).username(username).build();
    //            //when
    //            final ResponseEntity<Employee> result = employeeController.updateEmployee(1, new
    // EmployeeRequest("name",
    //     "username", "email", "jobposition", 1000.0));
    //            //then
    //            Assertions.assertThat(result).isNotNull();
    //            final var resultEmployee = result.getBody();
    //            Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    //            Assertions.assertThat(resultEmployee).isNotNull();
    //            Assertions.assertThat(resultEmployee.getId()).isEqualTo(id);
    //            Assertions.assertThat(resultEmployee.getName()).isEqualTo(name);
    //            Assertions.assertThat(resultEmployee.getUsername()).isEqualTo(username);
    //        }
}
