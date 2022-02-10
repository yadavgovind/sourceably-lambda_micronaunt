package com.oms.projectbuddy.services;

import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.request.ChangePassword;
import com.oms.projectbuddy.model.request.CompanyLogoRequest;
import com.oms.projectbuddy.model.request.CompanyRegistrationRequest;
import com.oms.projectbuddy.model.request.ResetPassword;


public interface ICompanyRegistrationService {
	
	///UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	Object signUp(CompanyRegistrationRequest companyRequest) throws Exception;
	
	CompanyRegistration getById(Long id) throws Exception;

	Object getAllCompany() throws Exception;

	CompanyRegistration findByEmail(String username)throws Exception;

	String logout(String token);

  //  Authentication authenticate(String username, String password) throws Exception;
	
	Object changePassword(ChangePassword changePassword) throws Exception ;

	void sendOtp(CompanyRegistration companyRegistration);

	Object verifyOtp(int mobileOtp, CompanyRegistration companyRegistration);

	String updateNewPassword(String email, String password) throws Exception;

	void resendMobOtp(String mobilenum);

	void resendEmailOtp(String email);

	boolean isMobileNumber(String mobilenum);

    Boolean sendMobOtp(String email, String mobileNumber);

	boolean isEmailId(String email);

	void modifyLogin(Long companyId);

	String forgotPassword(String email, String link) throws Exception;

	Object resetPassword(ResetPassword resetPassword) throws Exception;

    String saveCompanyLogo(CompanyLogoRequest fileRequest) throws Exception;

	String updateCompanyLogo(CompanyLogoRequest fileRequest) throws Exception;

	Object getCompanyLogoById(String userId) throws Exception;

	String deleteCompanyLogo(String userId) throws Exception;

    void resendMobOtpForUs(String mobileNumber);
}

