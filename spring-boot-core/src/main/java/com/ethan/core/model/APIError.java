package com.ethan.core.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class APIError {

	private int statusCode;
	private HttpStatus status;
	private String message;
	private String path;
	private String timestamp;
	private String responseCode;
	private String responseMessage;

}
