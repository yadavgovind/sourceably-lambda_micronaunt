package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRegistrationRequest {

	//private Long companyId;
	private String userId;
	private String registrationType;
	private String companyName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String countryPincode;
	private String mobileNumber;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private String confirmPassword;
	private String extension;
	private String deskPhoneNumber;
	private String userRoleType;
	private String applyFor;
	private Boolean adminApproval;
	private String adminRemarks;
}
