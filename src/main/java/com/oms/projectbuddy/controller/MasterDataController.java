package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.ContactUs;
import com.oms.projectbuddy.model.request.MailDto;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.repository.ContactUsRepository;
import com.oms.projectbuddy.services.IAddressService;
import com.oms.projectbuddy.utility.EmailTemplates;
import com.oms.projectbuddy.utility.SmsEmailIntegration;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;


import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/m")
public class MasterDataController {

    @Inject
    private IAddressService iAddressService;
    @Inject
    ContactUsRepository contactUsRepository;
    @Inject
    SmsEmailIntegration smsEmailIntegration;
    @Inject
    EmailTemplates emailTemplates;

    @Get("/getAllCountry")
    public Object getAllCountry() {
            return new EntityResponse(iAddressService.getAllCountry(), 0);
    }



    @Get("/getCountryByRegion")
    public Object getCountryByRegion( String region)   {
            return new EntityResponse(iAddressService.getCountryByRegion(region), 0);
    }


    @Get("/getState")
    public Object getState( Long countryId)   {
            return new EntityResponse(iAddressService.getStateByCountry(countryId), 0);
    }

    @Get("/getCityByStateId")
    public Object getCityByState( Long stateId)   {
            return new EntityResponse(iAddressService.getCityByStateId(stateId), 0);
    }

    @Post("/contactUs")
    public Object createContact(@Body ContactUs contactUs) {
        smsEmailIntegration.sendEmail("murali.osuraman@sourceably.com", contactUs.getQuery(), contactUs.getMessage());
        smsEmailIntegration.sendSms(contactUs.getMobileno(), "Hi," + contactUs.getName() + "thanks for reaching us our team will contact you soon");
        return contactUsRepository.save(contactUs);
    }

    @Get("/contactUs/{id}")
    public Object createContact(@PathVariable Long id) {

        return contactUsRepository.findById(id).get();
    }

    @Post("/sendEmail")
    public Object sendEmail(@Body List<MailDto> dtoList) {
        dtoList.forEach(item -> {
            String welcome = "";
            String template = emailTemplates.supplierTemplate(welcome);
            smsEmailIntegration.sendEmail(item.getTo(), item.getSubject(), template);
        });
        return "All send successfully";
    }

    @Get("/hello")
    public String hello() {
        return "hello Contatiner";
    }
}
