package com.springboot.httpResponse.Model;

import lombok.*;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseBody {
	
	private String timestamp;

	private int status;

	private HttpStatusCode error;

	private String message;

	private String path;
}
