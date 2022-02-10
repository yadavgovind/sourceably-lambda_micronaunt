//package com.oms.projectbuddy.exception;
//
//
//import com.oms.projectbuddy.model.response.CustomResponseMessage;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    // global exceptions
//    @ExceptionHandler(SourceablyCustomeException.class)
//    public Object handleSourceablyCustomeException(SourceablyCustomeException customeException,
//                                                               WebRequest webRequest){
//        return new CustomResponseMessage(customeException.getMessage(), -1,customeException.getHttpStatus(),customeException.getHttpStatus().value()), customeException.getHttpStatus());
//    }
//
//
//    // global exceptions
//    @ExceptionHandler(Exception.class)
//    public Object handleGlobalException(Exception exception,WebRequest webRequest){
//        return new CustomResponseMessage(exception.getMessage(), -1,HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value());
//    }
//}
