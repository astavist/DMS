package com.astavist.DMS.utils.ExceptionHandlers;

import org.modelmapper.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class RestExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ExceptionResults<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ExceptionResults<>(ExceptionTypes.Exception.Validation, validationErrors);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
    public ExceptionResults<Object> handleValidationException(ValidationException exception) {
        return new ExceptionResults<>(ExceptionTypes.Exception.Validation, exception.getMessage());
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
//    public ExceptionResults<Object> handleBusinessException(RuntimeException exception) {
//        return new ExceptionResults<>(ExceptionTypes.Exception.Business, exception.getMessage());
//    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public ExceptionResults<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        return new ExceptionResults<>(ExceptionTypes.Exception.DataIntegrityViolation, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ExceptionResults<Object> handleRuntimeException(RuntimeException exception) {
        return new ExceptionResults<>(ExceptionTypes.Exception.Runtime, exception.getMessage());
    }
}
