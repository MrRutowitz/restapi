package com.taskone.restapi.controller;

import com.taskone.restapi.model.Employee;
import com.taskone.restapi.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static java.nio.file.Paths.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeControllerTest {

    @MockBean //@spy sprawdz
    private EmployeeService employeeService;

    @Autowired
    private EmployeeController employeeController = new EmployeeController(employeeService);

    @Test
    void shouldWelcomeEmployee(){
        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
        when(employeeService.welcomeMessage("Michal")).thenReturn("Welcome Michal!");

        EmployeeController employeeController = new EmployeeController(employeeService);
        assertEquals("Welcome Michal!", employeeController.welcome("Michal"));
    }

//    @Test
//    void shouldAddNewEmployee(){
//        //given
//        final var employee = new Employee(13,"name","username","email","jobpostion",1000);
//        //when
//        final var result = employeeController.addEmpolyee(employee);
//        //then
//        Assertions.assertThat(result).isNotNull();
//        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//
//    }
//    @Test
//    void shouldUpdateExistingEmployee()
//    {
//        //given toDo mockitowhen
//        final int id = 1;
//        final String name = "Michal";
//        final String username = "micrut";
//
////        final String email = "micrut@gmail.com";
////        final String jobpostion = "backend";
////        final int salary = 10000;
//
//        final var employee = Employee.builder().id(id).name(name).username(username).build();
//        //when
//        final ResponseEntity<Employee> result = employeeController.updateEmployee(id,employee);
//        //then
//        Assertions.assertThat(result).isNotNull();
//        final var resultEmployee = result.getBody();
//        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(resultEmployee).isNotNull();
//        Assertions.assertThat(resultEmployee.getId()).isEqualTo(id);
//        Assertions.assertThat(resultEmployee.getName()).isEqualTo(name);
//        Assertions.assertThat(resultEmployee.getUsername()).isEqualTo(username);
//
//
//
//
//    }


}