//package com.uspanov.twitterclone.exceptions;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice
//public class AppExceptionsHandler {
//
//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<Object> handleUserServiceException(Exception ex, WebRequest request) {
//        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
//        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}
