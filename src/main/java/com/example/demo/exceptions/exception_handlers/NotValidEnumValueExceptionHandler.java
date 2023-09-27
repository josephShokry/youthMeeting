package com.example.demo.exceptions.exception_handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class NotValidEnumValueExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, String> onNotValidEnumValueException(HttpMessageNotReadableException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale()));
        return error;
    }
}
