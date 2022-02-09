package com.oms.projectbuddy.model.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponseMessage {
    private String response;
    private int status;
    private HttpStatus httpStatus;
    private int statusCode;
	public CustomResponseMessage(String response, int status) {
		this.response = response;
		this.status = status;
	}
    
	public CustomResponseMessage(String response, int status,HttpStatus httpStatus,int statusCode) {
		this.response = response;
		this.status = status;
		this.httpStatus = httpStatus;
		this.statusCode= statusCode;
	}
    
}
