package com.taskone.restapi.service;

import com.taskone.restapi.controller.EmployeeController;
import com.taskone.restapi.model.Employee;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.text.DateFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "dev")
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;


    @Test
    void isYourDataCorrect() {
        LocalDate date = LocalDate.of(2023, 07, 11);

        String dateString = employeeService.dateFormat(date);

        assertEquals("2023-07-11", dateString);
    }

    @Test
    void shouldGetAllEmployees(){

        List<Employee> allEmployees = employeeService.list();
        assertNotNull(allEmployees);
        assertEquals(11,allEmployees.size());
    }

    @Test
    void checkEmpolyeById(){

        ResponseEntity<Employee> employeeResponseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);


    }








//    @Test
//    void shouldActivEmployees(){
//        List<Employee> employees =
//    }



}