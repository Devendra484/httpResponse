package com.springboot.httpResponse.ExceptionHandler;

import com.springboot.httpResponse.Model.ErrorResponseBody;
import com.springboot.httpResponse.Service.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @Autowired
    private TimeStamp timeStamp;

    @ExceptionHandler(MobileNotFoundException.class)
    public ResponseEntity<Object> MobilesNotFoundException(MobileNotFoundException exception){
        ErrorResponseBody errorResponseBody=new ErrorResponseBody(
                timeStamp.timeStamp,
                /*HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                "Mobile Not found",*/
                exception.getHttpStatusCode().value(),
                exception.getHttpStatusCode(),
                exception.getLocalizedMessage(),
                null

        );
        return  new ResponseEntity<>(errorResponseBody,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<Object> InternalServerError(InternalError error){
        ErrorResponseBody errorResponseBody=new ErrorResponseBody(
                timeStamp.timeStamp,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                null
        );
        return new ResponseEntity<>(errorResponseBody,HttpStatus.INTERNAL_SERVER_ERROR);
    }
/*
    @ExceptionHandler({RequestBodyMissingException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> RequestBodyMissing(Exception e){
        System.out.println("handled");
        ErrorResponseBody errorResponseBody=new ErrorResponseBody(
                timeStamp.timeStamp,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                e.getLocalizedMessage(),
                null
        );
        return new ResponseEntity<>(errorResponseBody,HttpStatus.BAD_REQUEST);
    }
*/
    @ExceptionHandler(RequestBodyMissingException.class)
    public ResponseEntity<Object> RequestBodyMissing(RequestBodyMissingException e){
        ErrorResponseBody errorResponseBody=new ErrorResponseBody(
          timeStamp.timeStamp,
          e.getHttpStatusCode().value(),
          e.getHttpStatusCode(),
          e.getLocalizedMessage(),
          null
        );
        return new ResponseEntity<>(errorResponseBody,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValid(MethodArgumentNotValidException e){
        BindingResult bindingResult=e.getBindingResult();
        System.out.println("Binding Result = "+bindingResult);
        System.out.println("---------------------"+bindingResult.getFieldError());
        String errorMessage=bindingResult.getFieldError().getDefaultMessage();
        ErrorResponseBody errorResponseBody=new ErrorResponseBody(
                timeStamp.timeStamp,
                e.getStatusCode().value(),
                e.getStatusCode(),
                e.getBindingResult().getFieldError().getDefaultMessage(),
                null
        );
        return new ResponseEntity<>(errorResponseBody,HttpStatus.BAD_REQUEST);
    }
}
