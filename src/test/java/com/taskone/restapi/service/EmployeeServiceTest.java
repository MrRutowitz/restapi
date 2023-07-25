package com.taskone.restapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.taskone.restapi.model.EmployeeResponse;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
    void shouldGetAllEmployees() {

        List<EmployeeResponse> allEmployees = employeeService.getAllEmployees(1,3);
        assertNotNull(allEmployees);
        assertEquals(3, allEmployees.size());
    }

    @Test
    void shouldUpdateExistingEmployeeEmployeeService() {
        // given
        // when
        // then

    }
}
