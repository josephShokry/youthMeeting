package com.angle.youthmeeting.exceptions.exception_handlers;

import com.angle.youthmeeting.exceptions.exceptions.DuplicateAttendanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DuplicateAttendanceExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(DuplicateAttendanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, String> onDuplicateAttendanceException(DuplicateAttendanceException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale()));
        return error;
    }
}
