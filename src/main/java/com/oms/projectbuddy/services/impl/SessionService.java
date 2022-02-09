package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.repository.CompanyRepository;
import com.oms.projectbuddy.services.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SessionService implements ISessionService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyRegistration getLoginCompanyRegistration() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return companyRepository.findByEmail(loggedInUser.getUsername());
    }
}
