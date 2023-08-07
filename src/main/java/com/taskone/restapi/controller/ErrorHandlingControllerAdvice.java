package com.taskone.restapi.controller;

import com.taskone.restapi.model.EmployeeNotFoundException;
import com.taskone.restapi.model.ValidationErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations()
                    .add(new ValidationErrorResponse.Violation(
                            violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
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
        //        e.getBindingResult().getFieldErrors().stream().map(fieldError ->
        // ValidationErrorResponse.Violation.builder()
        //                .fieldName(fieldError.getField())
        //                .message(fieldError.getDefaultMessage())
        //                .build());
        //        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
        //            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        //        }
        //        return error;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
