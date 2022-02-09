package com.oms.projectbuddy.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String details;
    
    private String response;
    private int status;

    public ErrorDetails(Date timestamp, String response, String details, int status) {
		this.timestamp = timestamp;
		this.response = response;
		this.details = details;
		this.status = status;
	}

}
