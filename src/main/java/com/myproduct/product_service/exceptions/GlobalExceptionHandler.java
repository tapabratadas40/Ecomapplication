package com.myproduct.product_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException exception){
        ErrorResponce error = new ErrorResponce(LocalDateTime.now(),exception.getMessage(),"Product Not Found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<?> handleArrayIndexOutOfBoundException(ArrayIndexOutOfBoundsException exception){
        ErrorResponce error = new ErrorResponce(LocalDateTime.now(),exception.getMessage(),"ArrayIndexOutOfBoundException");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //ProductNotSavedInDbException
    @ExceptionHandler(ProductNotSavedInDbException.class)
    public ResponseEntity<?> handleProductNotSavedInDbException(ProductNotSavedInDbException exception){
        ErrorResponce error = new ErrorResponce(LocalDateTime.now(),exception.getMessage(),"Product Not Saved In the DB");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
