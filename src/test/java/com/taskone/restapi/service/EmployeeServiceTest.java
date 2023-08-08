package com.taskone.restapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.taskone.restapi.model.EmployeeResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "dev")
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void isYourDateCorrect() {
        String dateString = employeeService.currentTime();
        assertEquals("2023-08-08", dateString);
    }

    @Test
    void shouldGetAllEmployees() {
        List<EmployeeResponse> allEmployees = employeeService.getAllEmployees(PageRequest.of(0, 10));
        assertNotNull(allEmployees);
        assertEquals(10, allEmployees.size());
    }

    @Test
    void shouldUpdateExistingEmployeeEmployeeService() {
        // given
        // when
        // then
    }
}
