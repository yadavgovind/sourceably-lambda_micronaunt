package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.ContactUs;
import com.oms.projectbuddy.model.request.MailDto;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.repository.ContactUsRepository;
import com.oms.projectbuddy.services.IAddressService;
import com.oms.projectbuddy.utility.EmailTemplates;
import com.oms.projectbuddy.utility.SmsEmailIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/m")
public class MasterDataController {

    @Autowired
    private IAddressService iAddressService;
    @Autowired
    ContactUsRepository contactUsRepository;
    @Autowired
    SmsEmailIntegration smsEmailIntegration;
    @Autowired
    EmailTemplates emailTemplates;

    @GetMapping("/getAllCountry")
    public ResponseEntity<?> getAllCountry() {
            return new ResponseEntity<>(new EntityResponse(iAddressService.getAllCountry(), 0), HttpStatus.OK);
    }



    @GetMapping("/getCountryByRegion")
    public ResponseEntity<?> getCountryByRegion(@RequestParam String region)   {
            return new ResponseEntity<>(new EntityResponse(iAddressService.getCountryByRegion(region), 0), HttpStatus.OK);
    }


    @GetMapping("/getState")
    public ResponseEntity<?> getState(@RequestParam Long countryId)   {
            return new ResponseEntity<>(new EntityResponse(iAddressService.getStateByCountry(countryId), 0), HttpStatus.OK);
    }

    @GetMapping("/getCityByStateId")
    public ResponseEntity<?> getCityByState(@RequestParam Long stateId)   {
            return new ResponseEntity<>(new EntityResponse(iAddressService.getCityByStateId(stateId), 0), HttpStatus.OK);
    }

    @PostMapping("/contactUs")
    public ResponseEntity<ContactUs> createContact(@RequestBody ContactUs contactUs) {
        smsEmailIntegration.sendEmail("murali.osuraman@sourceably.com", contactUs.getQuery(), contactUs.getMessage());
        smsEmailIntegration.sendSms(contactUs.getMobileno(), "Hi," + contactUs.getName() + "thanks for reaching us our team will contact you soon");
        return ResponseEntity.ok(contactUsRepository.save(contactUs));
    }

    @GetMapping("/contactUs/{id}")
    public ResponseEntity<ContactUs> createContact(@PathVariable Long id) {

        return ResponseEntity.ok(contactUsRepository.getOne(id));
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody List<MailDto> dtoList) {
        dtoList.forEach(item -> {
            String welcome = "";
            String template = emailTemplates.supplierTemplate(welcome);
            smsEmailIntegration.sendEmail(item.getTo(), item.getSubject(), template);
        });
        return ResponseEntity.ok("All send successfully");
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello Contatiner";
    }
}
