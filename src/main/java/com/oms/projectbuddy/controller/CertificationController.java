package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.ProviderBCertificateData;
import com.oms.projectbuddy.model.request.FilterPublishRequest;
import com.oms.projectbuddy.model.request.ProviderCertificateDataDto;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.CyberScoreDto;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IBCertificateService;
import com.oms.projectbuddy.services.ICertificationService;
import com.oms.projectbuddy.utility.SmsEmailIntegration;
import com.oms.projectbuddy.utils.CertificationType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
//@Api(value = "Certification Controller", description = "Manage Certifications ", tags = {"Certification Manage"})
public class CertificationController {

    @Inject
    private ICertificationService iCertificationService;
    @Inject
    private IBCertificateService  ibCertificateService;

    @Post("/bulkUploadCertificationDetails")
    public Object bulkUploadCertificationDetails( String file) throws IOException {

            return new CustomResponseMessage(iCertificationService.bulkUploadCertifications(file), 0);

    }

    @Get("/getParentCertificationByType")
    public Object getParentCertificationByType(CertificationType certificationType) throws Exception {
        return new EntityResponse(iCertificationService.getParentCertificationByCertificateType(certificationType), 0);
    }

    @Get("/getCertificationByParentAndLevel")
    public Object getCertificationBYParentIdAndLeve( Long parentId,  String level) throws Exception {
        return new EntityResponse(iCertificationService.getCertificationByParentAndLevel(parentId, level), 0);
    }

    @Get("/getCertificationByParentAndLevelByUser")
    public Object getCertificationByParentAndLevelByUser( Long parentId,
                                                                     String level,
                                                                     String userId) throws Exception {
        return new EntityResponse(iCertificationService.getCertificationByParentAndLevelByUser(parentId, level, userId), 0);
    }

    @Post("/saveProviderCertificateData")
    public Object saveProviderCertificateData(@Body ProviderCertificateDataDto providerCertificateDataDto) {

            return new CustomResponseMessage(iCertificationService.saveProviderCertificateData(providerCertificateDataDto), 0);

    }

    @Get("/getProviderCertificationDetailsBYCertificationIDAndUserID")
    public Object getDetailsBYCertIDANDUserID( Long certificateID,  String userID) throws Exception {

            return new EntityResponse(iCertificationService.getCetificationDetailsByUserIdAndCertificationID(certificateID, userID), 0);

    }

    @Get("/getProviderCertificationDetailsByUserId")
    public Object getCertificationDetailsByUserId( String userID) throws Exception {

            return new EntityResponse(iCertificationService.getCetificationDetailsByUserId(userID), 0);

    }

    @Get("/getItCertificationDetailsForUserReport")
    public Object getItCertificationDetailsForUserReport( String userId) throws Exception {

            return new EntityResponse(iCertificationService.getItCertificationDetailsForUserReport(userId), 0);

    }

    @Get("/getProfileStrengthByUserId")
    public Object getProfileStrengthByUserId( String userId) {
        return new EntityResponse(iCertificationService.getProfileStrengthByUserID(userId), 0);
    }

    @Post("/bulkUploadBusinessCertificates")
    public Object uploadTheBusinessCertificates( String file) throws IOException {
        return new EntityResponse(iCertificationService.uploadBusinessCertificatedDetails(file), 0);
    }

    @Get("/getParentBusinessCertificates")
    public Object getParentBusinessCertificates() throws Exception {

            return new EntityResponse(iCertificationService.getParentBUsinessCertificatesDetails(), 0);

    }

    @Get("/getChildBusinessCertificates")
    public Object getChildBusinessCertificates( Long parentId) throws Exception {

            return new EntityResponse(iCertificationService.getChildBUsinessCertificatesDetails(parentId), 0);

    }

    @Post("/saveBCertificateData")
    public Object saveBusinessCertificateData(@Body ProviderBCertificateData providerBCertificateData) {

            return new EntityResponse(ibCertificateService.saveBCertificateData(providerBCertificateData), 0);


    }

    @Get("/getChildBusinessCertificatesByUser")
    public Object getChildBusinessCertificatesByUser( Long parentId,  String userId) throws Exception {

            return new EntityResponse(iCertificationService.getChildBusinessCertificatesByUser(parentId, userId), 0);

    }

    @Inject
    private SmsEmailIntegration smsEmailIntegration;

/*    @Get("/sendEmail")
    public String sendEmail( String email) {
        smsEmailIntegration.sendEmail(email, "SMS TEST", "Sms Test Success !");
        //"kiran.khemnar@omsoftware.net"
        return "sent";
    }*/

    //Fileter Search FOr the Details
    @Post("/filterTheDetailsPublishProject")
    public Object filterTheDetailsOfCompany(@Body FilterPublishRequest filterPublishRequest) {
        return null;

    }

    @Get("/calculatePercentage/{lavel}")
    public List<CyberScoreDto> getPercentage( String lavel,  String userId) {

        return iCertificationService.getPercentage(lavel, userId);

    }

}
