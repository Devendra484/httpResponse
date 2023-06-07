package com.springboot.httpResponse.Model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metadata {

	private int status;

	private String message;

	private String next;
}
