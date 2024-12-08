package com.example.product_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException extends Exception {
    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<String> handleAlreadyExistException(AlreadyExist ex) {
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFoundException(NotFound ex) {
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
