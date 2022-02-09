package com.oms.projectbuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
public class FileStorageException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2316516837238704580L;
	private String message;
	private HttpStatus httpStatus;
	
}
