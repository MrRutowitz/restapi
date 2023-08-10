package com.taskone.restapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import java.util.List;
import org.assertj.core.api.Assertions;
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
    void shouldGetEmployeesWithGivenSize() {
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
        final var expectedResult = new EmployeeResponse(11, "michal", "xxx", "xyz@abc", "xxx", 10000.0);
        final var employeeRequest = new EmployeeRequest("michal", "xxx", "xyz@abc", "xxx", 10000.0);
        // when
        final var result = employeeService.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    void shouldGetEmployeeById() {
        // given
        long id = 1;
        final var expectedResult = new EmployeeResponse(1, "Michal", "micrut", "micrut@gmail.com", "Backend", 10000.0);
        // when
        final var result = employeeService.getEmployeeById(id);
        // then
        assertNotNull(result);
        assertEquals(expectedResult, result);
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
        // given
        final var expectedResult = new EmployeeResponse(1, "Michal", "micrut", "dsd@sds", "sdd", 1000.0);
        final var updatedEmployee = new EmployeeRequest("Michal", "micrut", "dsd@sds", "sdd", 1000.0);
        // when

        final var result = employeeService.updateEmployee(1L, updatedEmployee);

        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(expectedResult).isEqualTo(result);
    }
}
