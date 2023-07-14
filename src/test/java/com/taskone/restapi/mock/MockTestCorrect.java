package com.taskone.restapi.mock;

import com.taskone.restapi.controller.EmployeeController;
import com.taskone.restapi.model.Employee;
import com.taskone.restapi.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(EmployeeController.class)
@ActiveProfiles(value = "dev")
public class MockTestCorrect {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    void shouldReturnListOfEmployees() throws Exception {
        when(employeeService.list())
                .thenReturn(List.of(new Employee(1,"Leanne","Brett","Sincere@april.biz","CEO",30000)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees/list").header("List","Employee"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Leanne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("Sincere@april.biz"));
    }

//    @Test
//    void shuouldReturnEmpoloyee(){
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/list")
//                .param("id", String.valueOf(1))
//                .param("id",2))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }









//    private String uri;
//
//    @PostConstruct
//    public void init() {
//        uri = "http://localhost:8082";
//    }
//
//    @MockBean
//    EmployeeService employeeService;
//
//    @Test
//    public void givenEmployeeID_whenMakingGetRequestToEmployeeEndpoint_thenReturnEmpolyee() {
//
//        Employee employeeTest = new Employee(1, "Leanne Graham", "Bret", "Sincere@april.biz", "CEO", 30000);
//        when(employeeService.findById(1)).thenReturn(employeeTest);
//
//        get(uri + "/employees/" + employeeTest.getId()).then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .body("id", equalTo(employeeTest.getId()))
//                .body("name", equalTo(employeeTest.getName()))
//                .body("username", equalTo(employeeTest.getUsername()))
//                .body("email", equalTo(employeeTest.getEmail()))
//                .body("jobposition", equalTo(employeeTest.getJobposition()))
//                .body("salary", equalTo(employeeTest.getSalary()));
//
//
//        Employee result = get(uri + "/employees/" + employeeTest.getId()).then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .as(Employee.class);
//        assertThat(result).isEqualTo(employeeTest);
//
//        String responseString = get(uri + "/employees/" + employeeTest.getId()).then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .asString();
//        assertThat(responseString).isNotEmpty();
//    }


}
