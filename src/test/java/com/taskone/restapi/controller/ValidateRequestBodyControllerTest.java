package com.taskone.restapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class ValidateRequestBodyControllerTest {

    //    @Autowired
    //    private MockMvc mvc;
    //
    //    @Autowired
    //    ObjectMapper objectMapper;
    //
    //    @Test
    //    void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
    //        EmployeeRequest employeeRequest =
    //        String body = objectMapper.writeValueAsString(input);
    //
    //        mvc.perform(post("/")
    //                        .contentType("json/employees.json")
    //                        .content(body))
    //                .andExpect(status().isBadRequest());
    //    }

}
