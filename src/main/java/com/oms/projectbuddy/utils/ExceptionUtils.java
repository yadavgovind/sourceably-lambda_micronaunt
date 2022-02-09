package com.oms.projectbuddy.utils;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import com.oms.projectbuddy.exception.SourceablyCustomeException;

public class ExceptionUtils {
	
	public static void verifyDataNotExistThenThrowException(Collection<?> records) {
		if (CollectionUtils.isEmpty(records)) {
			throw new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	public static void verifyDataNotExistThenThrowException(Object record) {
		if (record == null) {
			throw new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	public static void verifyDataNotExistThenThrowException(Object record,String message) {
		if (record == null) {
			throw new SourceablyCustomeException(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	public static void throwException(String message) {
			throw new SourceablyCustomeException(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	public static void throwException(String message,HttpStatus status) {
		throw new SourceablyCustomeException(message, status);
}
	
}
