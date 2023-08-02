package com.taskone.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskone.restapi.entity.Employee;
import com.taskone.restapi.repository.EmployeeRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

@SpringBootApplication
public class RestapiApplication {

    public static void main(String[] args) {


        SpringApplication.run(RestapiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(EmployeeRepository employeeRepository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<Employee>> typeReference = new TypeReference<>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream(
                    "/json/employees.json"); // uzywam strumienia zeby odczytac JSONa
            try {
                List<Employee> employees = mapper.readValue(inputStream, typeReference);
                employeeRepository.saveAll(employees);

                System.out.println("---------------------Employees saved-------------------------------");
            } catch (IOException e) {
                System.out.println("Employees cannot be saved " + e.getMessage());
            }
        };
    }
}
