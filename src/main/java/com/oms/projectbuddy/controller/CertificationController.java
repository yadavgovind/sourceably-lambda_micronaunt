package com.oms.projectbuddy.controller;

import java.io.IOException;
import java.util.List;

import com.oms.projectbuddy.services.IBCertificateService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.oms.projectbuddy.model.ProviderBCertificateData;
import com.oms.projectbuddy.model.request.FilterPublishRequest;
import com.oms.projectbuddy.model.request.ProviderCertificateDataDto;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.CyberScoreDto;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.ICertificationService;
import com.oms.projectbuddy.utility.SmsEmailIntegration;
import com.oms.projectbuddy.utils.CertificationType;

import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
//@Api(value = "Certification Controller", description = "Manage Certifications ", tags = {"Certification Manage"})
public class CertificationController {

    @Autowired
    private ICertificationService iCertificationService;
    @Autowired
    private IBCertificateService  ibCertificateService;

    @PostMapping("/bulkUploadCertificationDetails")
    public ResponseEntity<?> bulkUploadCertificationDetails(@RequestParam MultipartFile file) throws IOException {

            return new ResponseEntity<>(new CustomResponseMessage(iCertificationService.bulkUploadCertifications(file), 0), HttpStatus.OK);

    }

    @GetMapping("/getParentCertificationByType")
    public ResponseEntity<?> getParentCertificationByType(@RequestParam CertificationType certificationType) throws Exception {
        return new ResponseEntity<>(new EntityResponse(iCertificationService.getParentCertificationByCertificateType(certificationType), 0), HttpStatus.OK);
    }

    @GetMapping("/getCertificationByParentAndLevel")
    public ResponseEntity<?> getCertificationBYParentIdAndLeve(@RequestParam Long parentId, @RequestParam String level) throws Exception {
        return new ResponseEntity<>(new EntityResponse(iCertificationService.getCertificationByParentAndLevel(parentId, level), 0), HttpStatus.OK);
    }

    @GetMapping("/getCertificationByParentAndLevelByUser")
    public ResponseEntity<?> getCertificationByParentAndLevelByUser(@RequestParam Long parentId,
                                                                    @RequestParam String level,
                                                                    @RequestParam String userId) throws Exception {
        return new ResponseEntity<>(new EntityResponse(iCertificationService.getCertificationByParentAndLevelByUser(parentId, level, userId), 0), HttpStatus.OK);
    }

    @PostMapping("/saveProviderCertificateData")
    public ResponseEntity<?> saveProviderCertificateData(@RequestBody ProviderCertificateDataDto providerCertificateDataDto) {

            return new ResponseEntity<>(new CustomResponseMessage(iCertificationService.saveProviderCertificateData(providerCertificateDataDto), 0), HttpStatus.OK);

    }

    @GetMapping("/getProviderCertificationDetailsBYCertificationIDAndUserID")
    public ResponseEntity<?> getDetailsBYCertIDANDUserID(@RequestParam Long certificateID, @RequestParam String userID) throws Exception {

            return new ResponseEntity<>(new EntityResponse(iCertificationService.getCetificationDetailsByUserIdAndCertificationID(certificateID, userID), 0), HttpStatus.OK);

    }

    @GetMapping("/getProviderCertificationDetailsByUserId")
    public ResponseEntity<?> getCertificationDetailsByUserId(@RequestParam String userID) throws Exception {

            return new ResponseEntity<>(new EntityResponse(iCertificationService.getCetificationDetailsByUserId(userID), 0), HttpStatus.OK);

    }

    @GetMapping("/getItCertificationDetailsForUserReport")
    public ResponseEntity<?> getItCertificationDetailsForUserReport(@RequestParam String userId) throws Exception {

            return new ResponseEntity<>(new EntityResponse(iCertificationService.getItCertificationDetailsForUserReport(userId), 0), HttpStatus.OK);

    }

    @GetMapping("/getProfileStrengthByUserId")
    public ResponseEntity<?> getProfileStrengthByUserId(@RequestParam String userId) {
        return new ResponseEntity<>(new EntityResponse(iCertificationService.getProfileStrengthByUserID(userId), 0), HttpStatus.OK);
    }

    @PostMapping("/bulkUploadBusinessCertificates")
    public ResponseEntity<?> uploadTheBusinessCertificates(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(new EntityResponse(iCertificationService.uploadBusinessCertificatedDetails(file), 0), HttpStatus.OK);
    }

    @GetMapping("/getParentBusinessCertificates")
    public ResponseEntity<?> getParentBusinessCertificates() throws Exception {

            return new ResponseEntity<>(new EntityResponse(iCertificationService.getParentBUsinessCertificatesDetails(), 0), HttpStatus.OK);

    }

    @GetMapping("/getChildBusinessCertificates")
    public ResponseEntity<?> getChildBusinessCertificates(@RequestParam Long parentId) throws Exception {

            return new ResponseEntity<>(new EntityResponse(iCertificationService.getChildBUsinessCertificatesDetails(parentId), 0), HttpStatus.OK);

    }

    @PostMapping("/saveBCertificateData")
    public ResponseEntity<?> saveBusinessCertificateData(@RequestBody ProviderBCertificateData providerBCertificateData) {

            return new ResponseEntity<>(new EntityResponse(ibCertificateService.saveBCertificateData(providerBCertificateData), 0), HttpStatus.OK);


    }

    @GetMapping("/getChildBusinessCertificatesByUser")
    public ResponseEntity<?> getChildBusinessCertificatesByUser(@RequestParam Long parentId, @RequestParam String userId) throws Exception {

            return new ResponseEntity<>(new EntityResponse(iCertificationService.getChildBusinessCertificatesByUser(parentId, userId), 0), HttpStatus.OK);

    }

    @Autowired
    private SmsEmailIntegration smsEmailIntegration;

/*    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String email) {
        smsEmailIntegration.sendEmail(email, "SMS TEST", "Sms Test Success !");
        //"kiran.khemnar@omsoftware.net"
        return "sent";
    }*/

    //Fileter Search FOr the Details
    @PostMapping("/filterTheDetailsPublishProject")
    public ResponseEntity<?> filterTheDetailsOfCompany(@RequestBody FilterPublishRequest filterPublishRequest) {
        return null;

    }

    @GetMapping("/calculatePercentage/{lavel}")
    public List<CyberScoreDto> getPercentage(@PathVariable("lavel") String lavel, @RequestParam(required = false) String userId) {

        return iCertificationService.getPercentage(lavel, userId);

    }

}
