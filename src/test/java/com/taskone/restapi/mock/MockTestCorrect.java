package com.taskone.restapi.mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.taskone.restapi.controller.EmployeeController;
import com.taskone.restapi.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    //    @Test
    //    void shouldReturnListOfEmployees() throws Exception {
    //        when(employeeService.list())
    //                .thenReturn(List.of(new Employee(1,"Leanne","Brett","Sincere@april.biz","CEO",30000)));
    //
    //        this.mockMvc
    //                .perform(MockMvcRequestBuilders.get("/employees/list").header("List","Employee"))
    //                .andExpect(status().isOk())
    //                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
    //                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Leanne"))
    //                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("Sincere@april.biz"));
    //    }

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
