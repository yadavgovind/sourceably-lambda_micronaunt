package com.oms.projectbuddy.model;

import com.oms.projectbuddy.utils.UserRoleType;

import javax.persistence.*;


@Entity
@Table(name = "company_registration", uniqueConstraints = {
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
    @Column(name = "applyFor")
    private String applyFor;
    @Column(name = "admin_approval")
    private Boolean adminApproval;
    @Column(name = "admin_remarks")
    private String adminRemarks;
    @Column(name = "is_subscription_active")
    private Boolean isSubscriptionActive;
    @Column(name = "is_first_login")
    private Boolean isFirstLogin;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountryIsocode() {
        return countryIsocode;
    }

    public void setCountryIsocode(String countryIsocode) {
        this.countryIsocode = countryIsocode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public UserRoleType getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(UserRoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getDeskPhoneNumber() {
        return deskPhoneNumber;
    }

    public void setDeskPhoneNumber(String deskPhoneNumber) {
        this.deskPhoneNumber = deskPhoneNumber;
    }

    public Long getCreatedEpochTime() {
        return createdEpochTime;
    }

    public void setCreatedEpochTime(Long createdEpochTime) {
        this.createdEpochTime = createdEpochTime;
    }

    public Long getLastUpdatedEpochTime() {
        return lastUpdatedEpochTime;
    }

    public void setLastUpdatedEpochTime(Long lastUpdatedEpochTime) {
        this.lastUpdatedEpochTime = lastUpdatedEpochTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getMobileVerified() {
        return isMobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        isMobileVerified = mobileVerified;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public String getApplyFor() {
        return applyFor;
    }

    public void setApplyFor(String applyFor) {
        this.applyFor = applyFor;
    }

    public Boolean getAdminApproval() {
        return adminApproval;
    }

    public void setAdminApproval(Boolean adminApproval) {
        this.adminApproval = adminApproval;
    }

    public String getAdminRemarks() {
        return adminRemarks;
    }

    public void setAdminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
    }

    public Boolean getSubscriptionActive() {
        return isSubscriptionActive;
    }

    public void setSubscriptionActive(Boolean subscriptionActive) {
        isSubscriptionActive = subscriptionActive;
    }

    public Boolean getFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        isFirstLogin = firstLogin;
    }
}
