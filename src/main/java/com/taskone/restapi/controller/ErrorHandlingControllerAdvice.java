package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeNotFoundException;
import com.taskone.restapi.model.ValidationErrorResponse;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        List<ValidationErrorResponse.Violation> violations = e.getConstraintViolations().stream()
                .map(constraintViolation -> ValidationErrorResponse.Violation.builder()
                        .fieldName(constraintViolation.getPropertyPath().toString())
                        .message(constraintViolation.getMessage())
                        .build())
                .toList();
        ValidationErrorResponse validationErrorResponse =
                ValidationErrorResponse.builder().violations(violations).build();
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST).getBody();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ValidationErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationErrorResponse.Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ValidationErrorResponse.Violation.builder()
                        .fieldName(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .toList();
        ValidationErrorResponse response =
                ValidationErrorResponse.builder().violations(violations).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
