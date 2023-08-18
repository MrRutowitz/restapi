package com.taskone.restapi.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EmployeeRequestTest {

    static Stream<Arguments> provideFieldAndInvalidValue() {
        return Stream.of(
                Arguments.of("name", null),
                Arguments.of("username", null),
                Arguments.of("email", null),
                Arguments.of("jobposition", null),
                Arguments.of("salary", null));
    }

    @ParameterizedTest
    @MethodSource("provideFieldAndInvalidValue")
    void testInvalidEmployeeRequest(String fieldName, Object invalidValue)
            throws NoSuchFieldException, IllegalAccessException {
        EmployeeRequest employeeRequest = getValidEmployeeRequest();

        Field field = EmployeeRequest.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(employeeRequest, invalidValue);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmployeeRequest>> constraintViolations = validator.validate(employeeRequest);

        assertThat(constraintViolations.size()).isOne();
    }

    private EmployeeRequest getValidEmployeeRequest() {
        return new EmployeeRequest("mi", "michal", "ema@il", "xyzabc", 10000.0);
    }
}
