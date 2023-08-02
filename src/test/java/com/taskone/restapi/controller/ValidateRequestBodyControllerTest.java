package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.xmlunit.builder.Input;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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