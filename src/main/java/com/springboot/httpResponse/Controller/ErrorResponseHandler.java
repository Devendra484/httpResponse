package com.springboot.httpResponse.Controller;

import com.springboot.httpResponse.Model.ErrorResponseBody;
import com.springboot.httpResponse.Service.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ErrorResponseHandler {
    @Autowired
    private TimeStamp timeStamp;
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> methodNotSupportedHandler(HttpRequestMethodNotSupportedException ex){
        ErrorResponseBody errorResponseBody=new ErrorResponseBody(timeStamp.timeStamp,ex.getStatusCode().value(),ex.getStatusCode(),ex.getMethod(),"/mobiles");
        return new ResponseEntity<>(errorResponseBody,ex.getStatusCode());
    }

}
