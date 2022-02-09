package com.oms.projectbuddy.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oms.projectbuddy.model.response.CustomResponseMessage;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // global exceptions
    @ExceptionHandler(SourceablyCustomeException.class)
    public ResponseEntity<?> handleSourceablyCustomeException(SourceablyCustomeException customeException,
                                                               WebRequest webRequest){
        return new ResponseEntity<>(new CustomResponseMessage(customeException.getMessage(), -1,customeException.getHttpStatus(),customeException.getHttpStatus().value()), customeException.getHttpStatus());
    }

    
    // global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest webRequest){
        return new ResponseEntity<>(new CustomResponseMessage(exception.getMessage(), -1,HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
