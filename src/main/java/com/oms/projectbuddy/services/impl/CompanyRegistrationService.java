//package com.oms.projectbuddy.services.impl;
//
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.transaction.Transactional;
//
//import jakarta.inject.Inject;
//import jakarta.inject.Singleton;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.oms.projectbuddy.config.TokenProvider;
//import com.oms.projectbuddy.exception.SourceablyCustomeException;
//import com.oms.projectbuddy.model.BusinessBasicInfo;
//import com.oms.projectbuddy.model.CompanyLogo;
//import com.oms.projectbuddy.model.CompanyRegistration;
//import com.oms.projectbuddy.model.ConsumerBusinessBasicInfo;
//import com.oms.projectbuddy.model.Country;
//import com.oms.projectbuddy.model.request.ChangePassword;
//import com.oms.projectbuddy.model.request.CompanyLogoRequest;
//import com.oms.projectbuddy.model.request.CompanyRegistrationRequest;
//import com.oms.projectbuddy.model.request.ResetPassword;
//import com.oms.projectbuddy.repository.BusinessBasicInfoRepository;
//import com.oms.projectbuddy.repository.CompanyLogoRepository;
//import com.oms.projectbuddy.repository.CompanyRepository;
//import com.oms.projectbuddy.repository.ConsumerBusinessBasicInfoRepository;
//import com.oms.projectbuddy.repository.CountryRepository;
//import com.oms.projectbuddy.services.ICompanyRegistrationService;
//import com.oms.projectbuddy.utility.EmailTemplates;
//import com.oms.projectbuddy.utility.RegexUtil;
//import com.oms.projectbuddy.utility.SmsEmailIntegration;
//import com.oms.projectbuddy.utils.Constants;
//import com.oms.projectbuddy.utils.ExceptionUtils;
//import com.oms.projectbuddy.utils.UserRoleType;
//
//@Singleton
//public class CompanyRegistrationService implements  ICompanyRegistrationService {
//
////	@Inject
////    private BCryptPasswordEncoder bcryptEncoder;
//
//	@Inject
//	private SmsEmailIntegration smsEmailIntegration;
//
//	@Inject
//	private CompanyRepository companyRepository;
//
////	@Inject
////	private TokenProvider tokenProvider;
//
//	@Inject
//	private CountryRepository countryRepository;
//
//	@Inject
//	private OtpService otpService;
//
////	 @Inject
////	 private FileOperation fileOperation;
//
//	@Inject
//	private BusinessBasicInfoRepository basicInfoRepository;
//
//	@Inject
//	private CompanyLogoRepository companyLogoRepository;
//
//	@Lazy
//	@Inject
//	private AuthenticationManager authenticationManager;
//
//	@Inject
//	private ConsumerBusinessBasicInfoRepository consumerInfoRepository;
//
//	@Inject
//	RegexUtil regexUtil;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        CompanyRegistration user = companyRepository.findByEmail(username.toLowerCase());
//        if (user == null) {
//            throw new SourceablyCustomeException("Invalid username or password.", HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                getAuthority(user));
//
//	}
//
//	@Override
//	public Object signUp(CompanyRegistrationRequest companyRequest) throws Exception {
//    	CompanyRegistration companyRegistration = new CompanyRegistration();
//        if (!companyRepository.existsByEmail(companyRequest.getEmail().toLowerCase()) && !companyRepository.existsByMobileNumber(companyRequest.getMobileNumber())) {
//           //this.sendMobOtp(companyRequest.getemail(), companyRequest.getMobileNumber());
//			String email=companyRequest.getEmail().toLowerCase();
//			String domain= email.substring(email.lastIndexOf("@") + 1).toLowerCase();
//			Boolean match=validateEmail(domain);
//			if(match){
//				throw new SourceablyCustomeException("Use your company business email id not public domain email id", HttpStatus.UNPROCESSABLE_ENTITY);
//			}
//			if(!regexUtil.isInputSatisfyRegex(companyRequest.getConfirmPassword(), Constants.PASSWORD_PATTERN)) {
//				throw new SourceablyCustomeException("Invalid Password!", HttpStatus.UNPROCESSABLE_ENTITY);
//			}
//    	companyRegistration.setRegistrationType(companyRequest.getRegistrationType());
//    	companyRegistration.setCompanyName(companyRequest.getCompanyName());
//    	companyRegistration.setAddressLine1(companyRequest.getAddressLine1());
//    	companyRegistration.setAddressLine2(companyRequest.getAddressLine2());
//    	companyRegistration.setCity(companyRequest.getCity());
//    	companyRegistration.setState(companyRequest.getState());
//    	companyRegistration.setCountry(companyRequest.getCountry());
//    	companyRegistration.setZipcode(companyRequest.getZipcode());
//    	companyRegistration.setMobileNumber(companyRequest.getMobileNumber());
//    	companyRegistration.setEmail(companyRequest.getEmail().toLowerCase());
//    	companyRegistration.setFirstName(companyRequest.getFirstName());
//    	companyRegistration.setMiddleName(companyRequest.getMiddleName());
//    	companyRegistration.setLastName(companyRequest.getLastName());
//        Country countrylist=countryRepository.findByCountryName(companyRequest.getCountry());
//    	companyRegistration.setCountryIsocode(countrylist.getPhoneCode());
//    	companyRegistration.setPassword(getEncodedPassword(companyRequest.getConfirmPassword()));
//    	companyRegistration.setExtension(companyRequest.getExtension());
//    	companyRegistration.setDeskPhoneNumber(companyRequest.getDeskPhoneNumber());
//    	companyRegistration.setIsFirstLogin(Boolean.FALSE);
//    	if(companyRequest.getAdminApproval()!=null) {
//			companyRegistration.setAdminApproval(companyRequest.getAdminApproval());
//		}else {
//    		companyRegistration.setAdminApproval(true);
//		}
//    	companyRegistration.setAdminRemarks(companyRequest.getAdminRemarks());
//
//    	companyRegistration.setIsActive(true);
//    	companyRegistration.setIsSubscriptionActive(false);
//    	companyRegistration.setIsDeleted(false);
//    	companyRegistration.setIsEmailVerified(false);
//    	companyRegistration.setIsMobileVerified(false);
//    	companyRegistration.setCreatedEpochTime(Instant.now().toEpochMilli());
//    	companyRegistration.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
//    	companyRegistration.setApplyFor(companyRequest.getApplyFor());
//    	 if (companyRequest.getUserRoleType().equalsIgnoreCase("Admin")) {
//    		 companyRegistration.setUserRoleType(UserRoleType.ADMIN);
//         } else if (companyRequest.getUserRoleType().equalsIgnoreCase("User")) {
//        	 companyRegistration.setUserRoleType(UserRoleType.USER);
//         } else {
//             throw new SourceablyCustomeException("No role specified", HttpStatus.UNPROCESSABLE_ENTITY);
//         }
//        CompanyRegistration company=companyRepository.save(companyRegistration);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yy");
//			String dateAppend  = dateFormat.format(new Date());
//			String formatId=String.format("%05d", company.getCompanyId());
//			if(company.getRegistrationType().equalsIgnoreCase("Supplier")) {
//				String id = "PBS" + dateAppend + "-" + formatId;
//				company.setUserId(id);
//
//				EmailTemplates emailTemplates=new EmailTemplates();
//				String message=emailTemplates.supplierTemplate("");
//				BusinessBasicInfo basicInfo=new BusinessBasicInfo();
//				basicInfo.setAgreeToShare(true);
//				basicInfo.setUserId(id);
//				basicInfo.setLastUpdatedBy(company.getEmail().toLowerCase());
//				basicInfo.setUpdatedEpochTime(Instant.now().toEpochMilli());
//				basicInfoRepository.save(basicInfo);
//				smsEmailIntegration.sendEmail(companyRequest.getEmail().toLowerCase(),"About Profile Creation",message);
//			}
//			else if(company.getRegistrationType().equalsIgnoreCase("Customer")){
//				String id = "PBC" + dateAppend + "-" + formatId;
//				company.setUserId(id);
//				ConsumerBusinessBasicInfo consumerBasicInfo=new ConsumerBusinessBasicInfo();
//				consumerBasicInfo.setUserId(id);
//				consumerBasicInfo.setAgreeToShare(true);
//				consumerBasicInfo.setLastUpdatedBy(company.getEmail().toLowerCase());
//				consumerBasicInfo.setUpdatedEpochTime(Instant.now().toEpochMilli());
//				consumerInfoRepository.save(consumerBasicInfo);
//			}
//			CompanyRegistration registration=companyRepository.save(company);
////			if(company.getRegistrationType().equalsIgnoreCase("Supplier")){
////				BusinessBasicInfo basicInfo=new BusinessBasicInfo();
////				basicInfo.setAgreeToShare(true);
////				basicInfo.setUserId(registration.getUserId());
////				basicInfo.setLastUpdatedBy(registration.getEmail());
////				basicInfo.setUpdatedEpochTime(Instant.now().toEpochMilli());
////				basicInfoRepository.save(basicInfo);
////			}else if(company.getRegistrationType().equalsIgnoreCase("Customer")){
////
////			}
//			sendMobOtp(company.getEmail().toLowerCase(),company.getMobileNumber());
//			String token=userLogIn(registration.getEmail(),companyRequest.getConfirmPassword());
//			long epoch=Instant.now().toEpochMilli();
//			System.out.println("Epoch Time:"+epoch);
//			LocalDate date=Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
//			System.out.println("Date:"+date);
//			EmailTemplates emailTemplates=new EmailTemplates();
//			String message=emailTemplates.welcomeTemplate(companyRegistration.getUserId(),date);
//			smsEmailIntegration.sendEmail(companyRequest.getEmail().toLowerCase(),"About Company Registration",message);
//    	Map<String,String> map=new HashMap<String,String>();
//    	map.put("userId",registration.getUserId());
//    	map.put("token",token);
//    	map.put("registrationType",registration.getRegistrationType());
//    	return map;
//        }
//    	else {
//    		if((companyRepository.existsByEmail(companyRequest.getEmail().toLowerCase()) && companyRepository.existsByMobileNumber(companyRequest.getMobileNumber()))){
//				throw new SourceablyCustomeException("Email id and mobile no already exists", HttpStatus.UNPROCESSABLE_ENTITY);
//			}
//    		else if(companyRepository.existsByEmail(companyRequest.getEmail().toLowerCase())){
//    			throw new SourceablyCustomeException("Email id already exists", HttpStatus.UNPROCESSABLE_ENTITY);
//			}else if(companyRepository.existsByMobileNumber(companyRequest.getMobileNumber())){
//				throw new SourceablyCustomeException("Mobile no already exist", HttpStatus.UNPROCESSABLE_ENTITY);
//			}else{
//    			throw new SourceablyCustomeException("Failed to register", HttpStatus.UNPROCESSABLE_ENTITY);
//			}
//    	}
//	}
//
//	@Override
//	public CompanyRegistration getById(Long id) throws Exception{
//		return companyRepository.findById(id)
//				.orElseThrow(() -> new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY));
//	}
//
//	@Override
//	public Object getAllCompany() throws Exception{
//		List<CompanyRegistration> allCompnay = companyRepository.findAllByIsDeletedOrderByCreatedEpochTimeDesc(false);
//		ExceptionUtils.verifyDataNotExistThenThrowException(allCompnay);
//		return allCompnay;
//	}
//
//	@Override
//	 public CompanyRegistration findByEmail(String username) {
//		CompanyRegistration registerCompany = companyRepository.findByEmail(username.toLowerCase());
//		ExceptionUtils.verifyDataNotExistThenThrowException(registerCompany);
//	    return registerCompany;
//	  }
//
//
//
//	   public String getEncodedPassword(String password) {
//        return bcryptEncoder.encode(password);
//    }
//	   private Set<SimpleGrantedAuthority> getAuthority(CompanyRegistration user) {
//	        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//	        authorities.add(new SimpleGrantedAuthority("ROLE_"+ user.getUserRoleType().toString()));
//	        return authorities;
//	    }
//
//	@Override
//	public String logout(String token) {
//		  Boolean flag = tokenProvider.isTokenExpired(token);
//	        if (flag) {
//	            return "Logout success";
//	        } else {
//	            return "Logout Failed";
//	        }
//	}
//	private String userLogIn(String username,String password) throws Exception {
//		try {
//			final Authentication authentication = authenticate(username, password);
//
//			final UserDetails userDetails = loadUserByUsername(username);
//			CompanyRegistration userFromAuth = findByEmail(userDetails.getUsername().toLowerCase());
//			if (!userFromAuth.getIsActive() || userFromAuth.getIsDeleted()) {
//				throw new SourceablyCustomeException("User not active", HttpStatus.UNPROCESSABLE_ENTITY);
//			}
//			final String token = tokenProvider.generateToken(authentication,userFromAuth.getRegistrationType(),userFromAuth.getUserId());
//			return token;
//		}catch (Exception e){
//			throw new SourceablyCustomeException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//	public Authentication authenticate(String username, String password) throws Exception {
//		try {
//			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (Exception e) {
//			throw new SourceablyCustomeException("INVALID_CREDENTIALS", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//	@Override
//	public Object changePassword(ChangePassword changePassword) throws Exception {
//		if (companyRepository.findByUserId(changePassword.getUserId()) != null) {
//			if (companyRepository.existsByEmail(changePassword.getEmail().toLowerCase())) {
//				CompanyRegistration companyRegistration = companyRepository.findByEmail(changePassword.getEmail().toLowerCase());
//				if (bcryptEncoder.matches(changePassword.getOldPassword(), companyRegistration.getPassword())) {
//					companyRegistration.setPassword(getEncodedPassword(changePassword.getNewPassword()));
//					companyRepository.save(companyRegistration);
//					sendEmailForPasswordChange(companyRegistration);
//				} else {
//					throw new SourceablyCustomeException("Old password is incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
//				}
//			} else {
//				throw new SourceablyCustomeException("Please enter correct email", HttpStatus.UNPROCESSABLE_ENTITY);
//			}
//
//			return "Update Successfully";
//		}else {
//			throw new SourceablyCustomeException("User not exist", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//
//	public void sendOtp(CompanyRegistration companyRegistration) {
//		sendMobOtp(companyRegistration.getEmail().toLowerCase(), companyRegistration.getMobileNumber());
//
//	}
//
//	@Override
//	public boolean isEmailId(String email) {
//		return companyRepository.existsByEmail(email.toLowerCase());
//	}
//
//	@Override
//	@Transactional
//	public void modifyLogin(Long companyId) {
//		companyRepository.updateFirstLogin(companyId);
//	}
//
//	@Override
//	public String forgotPassword(String email,String link) throws Exception{
//		try {
//			//if (companyRepository.existsByEmail(email)) {
//				CompanyRegistration user = companyRepository.findByEmail(email.toLowerCase());
//				String companyId = String.valueOf(user.getCompanyId());
//				int otp=otpService.generateOTPForGetPassWord(email);
//				//	String link1 = "http://pb-api.omsdeven.com/demohtml/Project_buddy/forget_password.html/" + Base64.getEncoder().encodeToString(companyId.getBytes());
//				String emailLink = link+"/"+Base64.getEncoder().encodeToString(companyId.getBytes());
//
//				String message = "Please click on this link for change your password " + emailLink + " your unique code is"+otp;
//				smsEmailIntegration.sendEmail(email.toLowerCase(), "Change Your Password", message);
//				return "Reset Password link send on your mail";
//
//		} catch (Exception e) {
//			throw new Exception( "This Email is not Exist, Please Try with Registered Email");
//		}
//	}
//
//	@Override
//	public Object resetPassword(ResetPassword resetPassword) throws Exception{
//		try{
//			int storedOtp=otpService.getOtpForGetPassWord(resetPassword.getEmail().toLowerCase());
//			if(resetPassword.getCode()==storedOtp) {
//				otpService.clearOTPForGetPassWord(resetPassword.getEmail());
//				CompanyRegistration companyRegistration = companyRepository.findByEmail(resetPassword.getEmail().toLowerCase());
//				companyRegistration.setPassword(getEncodedPassword(resetPassword.getNewPassword()));
//				companyRepository.save(companyRegistration);
//				return "password changed successfully";
//			}else{
//				throw new Exception("code doesn't match");
//				}
//		}catch (Exception e){
//			throw new Exception("code Expired");
//		}
//	}
//
//	@Override
//	public String saveCompanyLogo(CompanyLogoRequest fileRequest) throws Exception {
//		try {
//			if (companyRepository.findByUserId(fileRequest.getUserId()) != null) {
//				if (companyLogoRepository.existsByUserIdAndIsDeleted(fileRequest.getUserId(),false)) {
//					CompanyLogo companyLogo = companyLogoRepository.findByUserIdAndIsDeleted(fileRequest.getUserId(),false);
//					companyLogo.setFile(fileRequest.getFileUrl());
//					companyLogoRepository.save(companyLogo);
//					return "updated successfully";
//				}else{
//				CompanyLogo companyLogo = new CompanyLogo();
//					companyLogo.setUserId(fileRequest.getUserId());
//					companyLogo.setRegistrationType(fileRequest.getRegistrationType());
//					companyLogo.setFile(fileRequest.getFileUrl());
//					companyLogo.setDeleted(false);
//				companyLogoRepository.save(companyLogo);
//
//				return "saved successfully";
//			}}else {
//				throw new Exception("id not found");
//			}
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//	}
//
//	@Override
//	public String updateCompanyLogo(CompanyLogoRequest fileRequest) throws Exception {
//		try {
//			if (companyLogoRepository.existsByUserIdAndIsDeleted(fileRequest.getUserId(),false)) {
//				CompanyLogo companyLogo = companyLogoRepository.findByUserIdAndIsDeleted(fileRequest.getUserId(),false);
//				companyLogo.setFile(fileRequest.getFileUrl());
//				companyLogoRepository.save(companyLogo);
//				return "updated successfully";
//			} else {
//				throw new Exception("id not found");
//			}
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//	}
//
//	@Override
//	public Object getCompanyLogoById(String userId) throws Exception {
//		if (companyLogoRepository.existsByUserIdAndIsDeleted(userId,false)){
//			return companyLogoRepository.findByUserIdAndIsDeleted(userId,false);
//		}else {
//		throw new Exception("id not found");
//		}
//	}
//
//	@Override
//	public String deleteCompanyLogo(String userId) throws Exception {
//		if(companyLogoRepository.existsByUserIdAndIsDeleted(userId,false)){
//			CompanyLogo companyLogo=companyLogoRepository.findByUserIdAndIsDeleted(userId,false);
//			companyLogo.setDeleted(true);
//			companyLogoRepository.save(companyLogo);
//			return "deleted successfully";
//
//		}else {
//			throw new Exception("id not found");
//		}
//	}
//
//
//
//	@Override
//	public Object verifyOtp(int mobileOtp, CompanyRegistration companyRegistration) {
//		boolean isVerified= false;
//
//		//int storedEmailOtp = 1234;//otpService.getOtp(companyRegistration.getEmail());
//		 int storedMobileOtp = 1234;
//		//int storedMobileOtp =otpService.getOtp(companyRegistration.getMobileNumber());
//
//		//if ( emailOtp == storedEmailOtp && mobileOtp == storedMobileOtp ) {
//			if (  mobileOtp == storedMobileOtp) {
//			otpService.clearOTP(companyRegistration.getEmail().toLowerCase());
//			otpService.clearOTP(companyRegistration.getMobileNumber());
//			Map<Object,Object> map=new HashMap<>();
//			if (companyRepository.existsByEmail(companyRegistration.getEmail().toLowerCase())) {
//				CompanyRegistration companyRegistration1 = companyRepository.findByEmail(companyRegistration.getEmail().toLowerCase());
//				companyRegistration1.setIsMobileVerified(true);
//				companyRegistration1.setIsEmailVerified(true);
//				companyRepository.save(companyRegistration1);
//				isVerified= true;
//				map.put("isoCode",companyRegistration1.getCountryIsocode());
//				map.put("isVerified",true);
//				return map;
//			}
//
//			return isVerified;
//		} else {
//			return isVerified;
//		}
//	}
//
//	@Override
//	public String updateNewPassword(String email, String password) throws Exception {
//		if(companyRepository.existsByEmail(email.toLowerCase())){
//			CompanyRegistration registration=companyRepository.findByEmail(email.toLowerCase());
//			registration.setPassword(getEncodedPassword(password));
//			companyRepository.save(registration);
//			return "password updated";
//		}else{
//			return "email doesn't exist";
//		}
//	}
//
//	@Override
//	public void resendMobOtp(String mobilenum) {
//		try{
//			otpService.clearOTP(mobilenum);
//			int mobileOtp=otpService.generateOTP(mobilenum);
//			String messageForMobile =  "Welcome to Farm Flyer App Powered by OMSOFTWARE - Your OTP is "+mobileOtp;
//			String subject="OTP";
//			smsEmailIntegration.sendSms(mobilenum,messageForMobile);
//		}catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public void resendMobOtpForUs(String mobileNumber) {
//		try{
//			otpService.clearOTP(mobileNumber);
//			int mobileOtp=otpService.generateOTP(mobileNumber);
//			String messageForMobile =  "Welcome to Farm Flyer App Powered by OMSOFTWARE - Your OTP is "+mobileOtp;
//			String subject="OTP";
//			smsEmailIntegration.sendSmsUS(mobileNumber,messageForMobile);
//		}catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void resendEmailOtp(String email) {
//		try {
//			otpService.clearOTP(email.toLowerCase());
//			int emailOtp = otpService.generateOTP(email.toLowerCase());
//			String messageForEmail =  "Welcome to Farm Flyer App Powered by OMSOFTWARE - Your OTP is " + emailOtp;
//			String subject = "OTP";
//			smsEmailIntegration.sendEmail(email.toLowerCase(), subject, messageForEmail);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public boolean isMobileNumber(String mobilenum) {
//	if(companyRepository.existsByMobileNumber(mobilenum)){
//		return true;
//	}else{
//		return false;
//	}
//	}
//
//	public Boolean sendMobOtp(String email, String mobileNumber) {
//		try {
//			otpService.clearOTP(email.toLowerCase());
//			otpService.clearOTP(mobileNumber);
//			//int mobileOtp=otpService.generateOTP(mobileNumber);
//			int mobileOtp=1234;
//			int emailOtp = mobileOtp;//otpService.generateOTP(email);
//			// String message = "Welcome to Farm Flyer App Powered by OMSOFTWARE - Your OTP is "+ otp + " valid for 5 minutes";
//			String messageForEmail =  "Welcome to Sourceably.com \n\n\n\n\n" +
//					"\n\n\n" +
//					"Please complete your email verification by entering this code on Website Registration process.\n" +
//					"\n\n" +
//					"Your One Time Password to verify email is :"+mobileOtp+"\n" +
//					"\n" +
//					"\n ";
//			String messageForMobile =  "Welcome to Sourceably.com App Powered by OMSOFTWARE - Your OTP is "+emailOtp;
//			//smsEmailIntegration.sendSms(mobileNumber, message);
//			String subject="OTP Verification";
//			smsEmailIntegration.sendSms(mobileNumber,messageForMobile);
//			smsEmailIntegration.sendEmail(email.toLowerCase(),subject,messageForEmail);
//			return true;
//		} catch (Exception e) {
//			//e.printStackTrace();
//			return false;
//		}
//	}
//
//
//	private void sendEmailForPasswordChange(CompanyRegistration companyRegistration) {
//			try {
//
//				String message = "Your Password changed Successfully ";
//				smsEmailIntegration.sendEmail(companyRegistration.getEmail().toLowerCase(), "Password Change", message);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		public Boolean validateEmail(String domain){
//			List<String> list=new ArrayList<String>();
//			list.add("gmail.com");
//			list.add("yahoo.com");
//			list.add("hotmail.com");
//			list.add("aol.com");
//			list.add("hotmail.co.uk");
//			list.add("hotmail.fr");
//			list.add("msn.com");
//			list.add("yahoo.fr");
//			list.add("wanadoo.fr");
//			list.add("orange.fr");
//			list.add("comcast.net");
//			list.add("yahoo.co.uk");
//			list.add("yahoo.com.br");
//			list.add("yahoo.co.in");
//			list.add("live.com");
//			list.add("rediffmail.com");
//			list.add("free.fr");
//			list.add("gmx.de");
//			list.add("web.de");
//			list.add("yandex.ru");
//			list.add("ymail.com");
//			list.add("libero.it");
//			list.add("outlook.com");
//			list.add("uol.com.br");
//			list.add("bol.com.br");
//			list.add("mail.ru");
//			list.add("cox.net");
//			list.add("hotmail.it");
//			list.add("sbcglobal.net");
//			list.add("sfr.fr");
//			list.add("live.fr");
//			list.add("verizon.net");
//			list.add("live.co.uk");
//			list.add("googlemail.com");
//			list.add("yahoo.es");
//			list.add("ig.com.br");
//			list.add("live.nl");
//			list.add("bigpond.com");
//			list.add("terra.com.br");
//			list.add("yahoo.it");
//			list.add("neuf.fr");
//			list.add("yahoo.de");
//			list.add("alice.it");
//			list.add("rocketmail.com");
//			list.add("att.net");
//			list.add("laposte.net");
//			list.add("facebook.com");
//			list.add("bellsouth.net");
//			list.add("yahoo.in");
//			list.add("hotmail.es");
//			list.add("charter.net");
//			list.add("yahoo.ca");
//			list.add("yahoo.com.au");
//			list.add("rambler.ru");
//			list.add("hotmail.de");
//			list.add("tiscali.it");
//			list.add("shaw.ca");
//			list.add("yahoo.co.jp");
//			list.add("sky.com");
//			list.add("earthlink.net");
//			list.add("optonline.net");
//			list.add("freenet.de");
//			list.add("t-online.de");
//			list.add("aliceadsl.fr");
//			list.add("virgilio.it");
//			list.add("home.nl");
//			list.add("qq.com");
//			list.add("telenet.be");
//			list.add("me.com");
//			list.add("yahoo.com.ar");
//			list.add("tiscali.co.uk");
//			list.add("yahoo.com.mx");
//			list.add("voila.fr");
//			list.add("gmx.net");
//			list.add("mail.com");
//			list.add("planet.nl");
//			list.add("tin.it");
//			list.add("live.it");
//			list.add("ntlworld.com");
//			list.add("arcor.de");
//			list.add("yahoo.co.id");
//			list.add("frontiernet.net");
//			list.add("hetnet.nl");
//			list.add("live.com.au");
//			list.add("yahoo.com.sg");
//			list.add("zonnet.nl");
//			list.add("club-internet.fr");
//			list.add("juno.com");
//			list.add("optusnet.com.au");
//			list.add("blueyonder.co.uk");
//			list.add("bluewin.ch");
//			list.add("skynet.be");
//			list.add("sympatico.ca");
//			list.add("windstream.net");
//			list.add("mac.com");
//			list.add("centurytel.net");
//			list.add("chello.nl");
//			list.add("live.ca");
//			list.add("aim.com");
//			list.add("bigpond.net.au");
//			Boolean b=list.contains(domain);
//			return b;
//		}
//	}
//
//
//
//
//
//
//
//
