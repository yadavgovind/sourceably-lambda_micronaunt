package com.oms.projectbuddy.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	
	private String token;
	private Long id;
	private String userId;
	private Boolean isEmailVerified;
	private Boolean isMobileVerified;
	private String name;
	private String registrationType;
	private Boolean isFirstLogin;


}
