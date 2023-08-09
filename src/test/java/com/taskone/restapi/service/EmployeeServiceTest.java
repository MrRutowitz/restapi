package com.taskone.restapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import com.taskone.restapi.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "dev")
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void isYourDateCorrect() {
        // given
        String expectedDate = String.valueOf(LocalDate.now());
        // when
        String dateString = employeeService.currentTime();
        // then
        assertEquals(expectedDate, dateString);
    }

    @Test
    void shouldGetEmployees() {
        // given
        int page = 0;
        int size = 9;
        PageRequest pageRequest = PageRequest.of(page, size);
        // when
        List<EmployeeResponse> result = employeeService.getEmployees(pageRequest);
        // then
        assertNotNull(result);
        assertEquals(size, result.size());
    }

    @Test
    void shouldCreateEmployee() {
        // given
        final var expectedName = "michal";
        final var expectedUsername = "xxx";
        final var expectedEmail = "xyz@abc";
        EmployeeRequest employeeRequest = new EmployeeRequest("michal", "xxx", "xyz@abc", "xxx", 1111.0);
        // when
        EmployeeResponse result = employeeService.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo(expectedName);
        Assertions.assertThat(result.getUsername()).isEqualTo(expectedUsername);
        Assertions.assertThat(result.getEmail())
                .isEqualTo(expectedEmail)
                .endsWith("c")
                .hasSize(7);
    }

    @Test
    void shouldGetEmployeeById() {
        // given
        long expectedId = 10;
        long id = 10;
        // when
        EmployeeResponse result = employeeService.getEmployeeById(id);
        // then
        assertNotNull(result);
        assertEquals(expectedId, result.getId());
        assertTrue(result.getId() == expectedId);
    }

    @Test
    void shouldGetEmployeesBySalaryRange() {
        // given
        double min = 1000;
        double max = 9000;
        // when
        List<EmployeeResponse> result = employeeService.getEmployeesBySalaryRange(min, max);
        // then
        Assertions.assertThat(result.size()).isEqualTo(4);
    }

    @Test
    void ShouldUpdateEmployee() {
        final int id = 1;
        final String name = "Michal";
        final String username = "micrut";
        final String email = "micrut@gmail.com";
        final String jobpostion = "backend";
        final int salary = 10000;
        EmployeeRequest updatedEmployee = new EmployeeRequest("Michal", "micrut", "dsd@sds", "sdd", 1000.0);
        // when

        EmployeeResponse result = employeeService.updateEmployee(1L, updatedEmployee);

        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(id);
        Assertions.assertThat(result.getName()).isEqualTo(name);
        Assertions.assertThat(result.getUsername()).isEqualTo(username);
    }
}
