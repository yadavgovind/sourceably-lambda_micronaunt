package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.oms.projectbuddy.utils.UserRoleType;



@Getter
@Setter
@Entity
@Table(name = "company_registration" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "mobile_number"}),})
public class CompanyRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;
	@Column(name = "registration_type")
	private String registrationType;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "address_line1")
	private String addressLine1;
	@Column(name = "address_line2")
	private String addressLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name = "zipcode")
	private String zipcode;
	@Column(name = "mobile_number")
	private String mobileNumber;
	@Column(name = "email")
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "Last_name")
	private String lastName;
	@Column(name = "country_isocode")
	private String countryIsocode;
	@Column(name = "password")
	private String password;
	@Column(name = "extension")
	private String extension;
	@Column(name = "user_role_type")
	@Enumerated(EnumType.STRING)
	private UserRoleType userRoleType;
	@Column(name = "desk_phone_number")
	private String deskPhoneNumber;
	@Column(name = "created_epoch_time")
	private Long createdEpochTime;
	@Column(name = "last_updated_epoch_time")
	private Long lastUpdatedEpochTime;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	@Column(name = "is_mobile_verified")
	private Boolean isMobileVerified;
	@Column(name = "is_email_verified")
	private Boolean isEmailVerified;
	@Column(name="applyFor")
	private String applyFor;
	@Column(name="admin_approval")
	private Boolean adminApproval;
	@Column(name = "admin_remarks")
	private String adminRemarks;
	@Column(name="is_subscription_active")
	private Boolean isSubscriptionActive;
	@Column(name="is_first_login")
	private Boolean isFirstLogin;


}
