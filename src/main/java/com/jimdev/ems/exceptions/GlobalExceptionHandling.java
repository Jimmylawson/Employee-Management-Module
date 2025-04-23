package com.jimdev.ems.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleUserNotFoundException(EmployeeNotFoundException exception) {
        return exception.getMessage();
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return exception.getMessage();
    }
}
