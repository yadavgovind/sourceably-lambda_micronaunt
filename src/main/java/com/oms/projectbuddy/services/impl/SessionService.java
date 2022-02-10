package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.repository.CompanyRepository;
import com.oms.projectbuddy.services.ISessionService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.context.ServerRequestContext;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.security.Principal;
import java.util.Optional;


@Singleton
public class SessionService implements ISessionService {

    @Inject
    private CompanyRepository companyRepository;

    public CompanyRegistration getLoginCompanyRegistration() {
        String loginUserName="";
        Optional<HttpRequest<Object>> securityContext = ServerRequestContext.currentRequest();
        if(securityContext.isPresent()){
            Optional<Principal> principal = securityContext.get().getUserPrincipal();
            if(principal.isPresent()){
                loginUserName= principal.get().getName();
            }

        }

        return companyRepository.findByEmail(loginUserName);
    }
}
