package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
	
        private String oldPassword;
	    private String newPassword;
	    private String token;
	    private String email;
	   private String userId;

}
