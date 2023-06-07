package com.springboot.httpResponse.ExceptionHandler;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestBodyMissingException extends RuntimeException{

    private HttpStatusCode httpStatusCode;
    private  String  message;

    @Serial
    private static final long serialVersionUID = 1L;

}
