package com.taskone.restapi.model;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.Test;

class EmployeeRequestTest {

    @Test
    void emailIsInvalid() {
        EmployeeRequest employeeRequest = new EmployeeRequest("mich", "michal2", "email", "xyzabc", 10000.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmployeeRequest>> constraintViolations = validator.validate(employeeRequest);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Invalid email address!", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void nameIsToShortOrToLong() {
        EmployeeRequest employeeRequest = new EmployeeRequest("m", "michal2", "ema@il", "xyzabc", 10000.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmployeeRequest>> constraintViolations = validator.validate(employeeRequest);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Please use 2-20 letters in name",
                constraintViolations.iterator().next().getMessage());
    }
}
