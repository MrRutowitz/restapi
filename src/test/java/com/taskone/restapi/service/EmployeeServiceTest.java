package com.taskone.restapi.service;

import com.taskone.restapi.model.EmployeeRequest;
import com.taskone.restapi.model.EmployeeResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "dev")
class EmployeeServiceTest {

    @MockBean
    private EmployeeService employeeService;

    @Test
    void shouldCreateEmployee() {
        // given
        final var expectedResult = new EmployeeResponse(11, "michal2", "xxxfs", "xyz@abc", "xxx", 10000.0);
        final var employeeRequest = new EmployeeRequest("michal", "xxx", "xyz@abc", "xxx", 10000.0);
        // when
        final var result = employeeService.createEmployee(employeeRequest);
        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(expectedResult).isEqualTo(result);
    }
    //
    //    @Test
    //    void shouldGetEmployeeById() {
    //        // given
    //        long id = 1;
    //        final var expectedResult = new EmployeeResponse(1, "Michal", "micrut", "micrut@gmail.com", "Backend",
    // 10000.0);
    //        // when
    //        final var result = employeeService.getEmployeeById(id);
    //        // then
    //        assertNotNull(result);
    //        assertEquals(expectedResult, result);
    //    }

}
