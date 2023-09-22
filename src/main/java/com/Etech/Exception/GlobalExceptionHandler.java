package com.Etech.Exception;

import com.Etech.Exception.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceException ex){
        String message = ex.getMessage();
        HttpStatus status = ex.getStatus();
        ApiResponse response = ApiResponse.builder().success(false).status(status).message(message).build();
        return new ResponseEntity<ApiResponse>(response, status);
    }
}
