package com.oms.projectbuddy.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oms.projectbuddy.dto.GlobalMarketData;
import com.oms.projectbuddy.model.request.ProviderCertificateDataDto;
import com.oms.projectbuddy.model.response.CyberScoreDto;
import com.oms.projectbuddy.utils.CertificationType;

public interface ICertificationService {
    String bulkUploadCertifications(MultipartFile file) throws IOException;

    Object getParentCertificationByCertificateType(CertificationType certificationType) throws Exception;

    Object getCertificationByParentAndLevel(Long parentId, String level) throws Exception;

    String saveProviderCertificateData(ProviderCertificateDataDto providerCertificateDataDto);

    Object getCetificationDetailsByUserIdAndCertificationID(Long certificateID, String userID) throws Exception;

    Object uploadBusinessCertificatedDetails(MultipartFile file) throws IOException;

    Object getParentBUsinessCertificatesDetails() throws Exception;

    Object getChildBUsinessCertificatesDetails(Long parentId) throws Exception;

    Object getChildBusinessCertificatesByUser(Long parentId, String userId) throws Exception;

    Object getCetificationDetailsByUserId(String userId) throws Exception;

    Object getItCertificationDetailsForUserReport(String userId) throws Exception;

    Object getCertificationByParentAndLevelByUser(Long parentId, String level, String userId) throws Exception;

    Object getProfileStrengthByUserID(String userId);

    List<CyberScoreDto> getPercentage(@PathVariable("lavel") String lavel, @RequestParam String userId);

    List<GlobalMarketData> getGlobalScoreBasedOnSupplier(LocalDate startDate, LocalDate endDate)throws Exception;

    List<?> globalCyberGraphData(LocalDate startDate, LocalDate endDate, String category, String supplierId);

}
