package com.taskone.restapi.controller;

import com.taskone.restapi.model.Employee;
import com.taskone.restapi.service.EmployeeService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    @Test
    void shouldWelcomeEmployee(){
        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
        when(employeeService.welcomeMessage("Michal")).thenReturn("Welcome Michal!");
        EmployeeController employeeController = new EmployeeController(employeeService);
        assertEquals("Welcome Michal!", employeeController.welcome("Michal"));
    }












}