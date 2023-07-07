package com.taskone.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskone.restapi.model.Employee;
import com.taskone.restapi.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {

//		ConfigurableApplicationContext configurableApplicationContext=
		SpringApplication.run(RestapiApplication.class, args);
//		EmployeeRepository employeeRepository =
//				configurableApplicationContext.getBean(EmployeeRepository.class);
//
//		Employee employee = new Employee("Michal", "Rutowicz");
//		employeeRepository.save(employee);
//
//
	}
		@Bean
		CommandLineRunner runner(EmployeeService employeeService){
			return args -> {

				ObjectMapper mapper = new ObjectMapper();
				TypeReference<List<Employee>> typeReference = new TypeReference<>(){};
				InputStream inputStream = TypeReference.class.getResourceAsStream("/json/employees.json"); //uzywam strumienia zeby odczytac JSONa
				try {
					List<Employee> employees = mapper.readValue(inputStream,typeReference);
					employeeService.save(employees);
					System.out.println("---------------------Employees saved-------------------------------");
				} catch (IOException e){
					System.out.println("Employees cannot be saved " + e.getMessage());
				}
			};
		}





	}


