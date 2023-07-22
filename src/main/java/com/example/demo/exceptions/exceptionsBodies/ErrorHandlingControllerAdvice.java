package com.example.demo.exceptions.exceptionsBodies;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, String> onConstraintValidationException(
            ConstraintViolationException e) {
        Map<String, String> error = new HashMap<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.put("message", violation.getMessage());
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String,String> onMethodArgumentNotValidException(
            //TODO change this shit
            MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        String errorMessage = "";
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage();
            errorMessage+=" & ";
        }
        errorMessage = errorMessage.substring(0,errorMessage.length()-2);
            error.put("message",errorMessage);
        return error;
    }

}