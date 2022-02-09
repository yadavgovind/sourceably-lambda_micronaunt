package com.oms.projectbuddy.model.request;



import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BusinessBasicInfoRequest {
	private Long id;
	private String userId;
	private String companyName;
	private String addressPerLocation;
	private String phoneNumber;
	private String alternateNumber;
	private String faxNumber;
	private String email;
	private String serviceName;
	private String tagLine;
	private String companyDescription;
	private String alternateEmail;
	private String pointOfContact;
	private String establishedDate;
	private String companyWebsite;
	private String yearsOfRegistered;
	private String companyVideoUrl;
	private String stockPrice;
	private String ceoName;
	private String founders;
	private String headquartersCountry;
	private String headquartersState;
	private String headquartersCity;
	private String products;
	private String subsidiary;
	private Boolean agreeToShare;
	private String lastUpdatedBy;
	private Long updatedEpochTime;

}
