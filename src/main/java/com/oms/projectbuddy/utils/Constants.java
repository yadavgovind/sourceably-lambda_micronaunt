package com.oms.projectbuddy.utils;

public class Constants {

	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 4 * 60 * 60;
	public static final String SIGNING_KEY = "projectbuddy";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";
	public static final String USD = "USD";
	public static final String ACTIVE = "ACTIVE";
	public static final String MONTHLY = "1month";
	public static final String YEARLY = "1year";
	public static final String BRAND_NAME = "PROJECTBUDDY";
	
	public static final String DATA_NOT_FOUND = "Data Not exist";
	
	public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	
}
