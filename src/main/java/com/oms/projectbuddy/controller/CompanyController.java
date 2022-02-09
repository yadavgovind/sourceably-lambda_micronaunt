package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.request.*;


import com.oms.projectbuddy.config.TokenProvider;
import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.model.response.LoginResponse;
import com.oms.projectbuddy.services.ICompanyRegistrationService;
import com.oms.projectbuddy.utils.ExceptionUtils;
import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
//@Api(value = "Registration", description = "company Registration", tags = {"Company Signup"})
public class CompanyController {
    @Autowired
    private final ICompanyRegistrationService iCompanyRegistrationService;
    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody CompanyRegistrationRequest registrationRequest) {
        try {
            return new ResponseEntity<>(
                    new EntityResponse(iCompanyRegistrationService.signUp(registrationRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCompanyById")
    public ResponseEntity<?> getCompanyById(@RequestParam Long id) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.getById(id), 0), HttpStatus.OK);
    }

    @GetMapping("/getAllCompany")
    public ResponseEntity<?> getAllCompany() throws Exception{
    	return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.getAllCompany(), 0), HttpStatus.OK);
    }

    @PostMapping("/companyLogin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            final Authentication authentication = authenticate(loginRequest.getEmail().toLowerCase(), loginRequest.getPassword());

            final UserDetails userDetails = iCompanyRegistrationService.loadUserByUsername(loginRequest.getEmail().toLowerCase());
            CompanyRegistration userFromAuth = iCompanyRegistrationService.findByEmail(userDetails.getUsername().toLowerCase());
            if (!userFromAuth.getIsActive() || userFromAuth.getIsDeleted()) {
            	ExceptionUtils.throwException("User Not Active");
            }
            if (userFromAuth.getIsFirstLogin()==null || !userFromAuth.getIsFirstLogin()) {
                iCompanyRegistrationService.modifyLogin(userFromAuth.getCompanyId());
            }
            final String token = jwtTokenUtil.generateToken(authentication,userFromAuth.getRegistrationType(),userFromAuth.getUserId());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setId(userFromAuth.getCompanyId());
            loginResponse.setIsEmailVerified(userFromAuth.getIsEmailVerified());
            loginResponse.setIsMobileVerified(userFromAuth.getIsMobileVerified());
            loginResponse.setName(userFromAuth.getFirstName());
            loginResponse.setRegistrationType(userFromAuth.getRegistrationType());
            loginResponse.setUserId(userFromAuth.getUserId());
            loginResponse.setIsFirstLogin(userFromAuth.getIsFirstLogin());
            return new ResponseEntity<>(new EntityResponse(loginResponse, 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EntityResponse("Please Check Username and Password", -1),
                    HttpStatus.UNAUTHORIZED);

        } 
    }

    @PostMapping("/companyLogout")
    public ResponseEntity<?> logout(@RequestParam String token) {
        try {
            iCompanyRegistrationService.logout(token);
            return new ResponseEntity<>(new CustomResponseMessage("Logout", 0), HttpStatus.OK);

        } catch (Exception e) {
        	throw new SourceablyCustomeException("Not Saved", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
        try {
            return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.changePassword(changePassword), 0), HttpStatus.OK);
        } catch (Exception e) {
        	throw new SourceablyCustomeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
        	throw new SourceablyCustomeException("INVALID_CREDENTIALS", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String email, @RequestParam String link) {
        try {
            boolean exist = iCompanyRegistrationService.isEmailId(email);
            if (exist) {
               // String companyId = iCompanyRegistrationService.forgotPassword(email, link);
                return new ResponseEntity<>(new EntityResponse("Reset link send on Registered Email", 0), HttpStatus.OK);
            } else {
            	throw new SourceablyCustomeException("Email does not exist", HttpStatus.UNPROCESSABLE_ENTITY);
            }	
        } catch (Exception e) {
            throw new SourceablyCustomeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassword resetPassword) {
        try {
            boolean exist = iCompanyRegistrationService.isEmailId(resetPassword.getEmail());
            if (exist) {
                return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.resetPassword(resetPassword), 0), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CustomResponseMessage("Email does not exist", -1), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new SourceablyCustomeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestParam int mobileOtp,
                                       @RequestParam String mobileNumber, @RequestParam String email) {
        try {
            CompanyRegistration companyRegistration = getUserFromToken(email);
            companyRegistration.setMobileNumber(mobileNumber);
            return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.verifyOtp(mobileOtp, companyRegistration), 0), HttpStatus.OK);
        } catch (Exception e) {
            throw new SourceablyCustomeException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/resendEmailOtp")
    public ResponseEntity<?> resendEmailOtp(@RequestParam String email) {
        try {
            if (iCompanyRegistrationService.findByEmail(email) != null) {
                iCompanyRegistrationService.resendEmailOtp(email);
                return new ResponseEntity<>(new EntityResponse("OTP Sent on Registered Email", 0), HttpStatus.OK);
            } else
            	throw new SourceablyCustomeException("Email does not exist", HttpStatus.OK);
        } catch (Exception e) {
        	throw new SourceablyCustomeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/resendMobileOtp")
    public ResponseEntity<?> resendMobileOtp(@RequestParam String mobileNumber) {
        try {
            boolean exist = iCompanyRegistrationService.isMobileNumber(mobileNumber);
            if (exist) {
                iCompanyRegistrationService.resendMobOtp(mobileNumber);
                return new ResponseEntity<>(new EntityResponse("OTP Sent on Registered Mobile", 0), HttpStatus.OK);
            } else
            throw new SourceablyCustomeException("Mobile number does not exist", HttpStatus.OK);
        } catch (Exception e) {
            throw new SourceablyCustomeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/resendMobileOtpForUs")
    public ResponseEntity<?> resendMobileOtpForUs(@RequestParam String mobileNumber) {
        try {
            iCompanyRegistrationService.resendMobOtpForUs(mobileNumber);
            return new ResponseEntity<>(new EntityResponse("OTP Sent on Registered Mobile", 0), HttpStatus.OK);
        } catch (Exception e) {
        	throw new SourceablyCustomeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    private CompanyRegistration getUserFromToken(String email) throws Exception {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();
            return iCompanyRegistrationService.findByEmail(username);
        } catch (Exception e) {
            return iCompanyRegistrationService.findByEmail(email);
        }

    }

    @PostMapping("/sendMobEmailOtp")
    public ResponseEntity<?> sendEmailMobOtp(@RequestParam String mobileNumber, @RequestParam String email) {
        try {
            return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.sendMobOtp(email, mobileNumber), 0), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveLogo")
    public ResponseEntity<?> saveLogo(@RequestBody CompanyLogoRequest fileRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iCompanyRegistrationService.saveCompanyLogo(fileRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateLogo")
    public ResponseEntity<?> updateLogo(@RequestBody CompanyLogoRequest fileRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iCompanyRegistrationService.updateCompanyLogo(fileRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getLogoById")
    public ResponseEntity<?> getLogoById(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.getCompanyLogoById(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteLogo")
    public ResponseEntity<?> deleteLogo(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iCompanyRegistrationService.deleteCompanyLogo(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
