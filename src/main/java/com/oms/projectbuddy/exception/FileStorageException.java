package com.oms.projectbuddy.exception;

import io.micronaut.http.HttpStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
public class FileStorageException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2316516837238704580L;
	private String message;
	private HttpStatus httpStatus;
	
}
