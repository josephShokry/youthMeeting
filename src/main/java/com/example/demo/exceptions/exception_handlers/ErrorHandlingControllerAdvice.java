package com.example.demo.exceptions.exception_handlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
class ErrorHandlingControllerAdvice {
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, String> onConstraintValidationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        key -> "message",
                        fieldError -> messageSource.getMessage(fieldError.getMessage(), null, LocaleContextHolder.getLocale())
                ));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String,String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> messageSource.getMessage(Objects.requireNonNull(
                                fieldError.getDefaultMessage()), null, LocaleContextHolder.getLocale())));

    }

}