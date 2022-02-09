package com.oms.projectbuddy.exception;

import io.micronaut.http.HttpStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SourceablyCustomeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3224656364473023330L;
	private String message;
	private HttpStatus httpStatus;
}
