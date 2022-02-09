package com.oms.projectbuddy.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.projectbuddy.dto.GlobalBussinessGraphData;
import com.oms.projectbuddy.dto.PBcertificateScorePercentage;
import com.oms.projectbuddy.model.BCertificatePercentage;
import com.oms.projectbuddy.model.ProviderBCertificateData;
import com.oms.projectbuddy.repository.ProviderBCertificateDataRepository;
import com.oms.projectbuddy.services.IBCertificateService;

@Service
public class BCertificationService implements IBCertificateService {
    @Autowired
    private ProjectCreationService projectCreationService;

    @Autowired
    private ProviderBCertificateDataRepository providerBCertificateDataRepository;



    @Override
    public Object saveBCertificateData(ProviderBCertificateData requestData) {
        ProviderBCertificateData providerBCertificateData  = providerBCertificateDataRepository.findByUserIdAndCertificateId(requestData.getUserId(), requestData.getCertificateId());
        if (providerBCertificateData!=null) {
            requestData.setBCertificateDataId(providerBCertificateData.getBCertificateDataId());
        }

        BCertificatePercentage bCertificatePercentage= new BCertificatePercentage(requestData);
        bCertificatePercentage.setAttachedScore(requestData.getFilePath()!=null?3l:0);
        bCertificatePercentage.setCommentScore(requestData.getUserComment()!=null?1l:0);

        Boolean validDate = new Date(requestData.getStartDate() * 1000).before(new Date(requestData.getEndDate() * 1000));
        bCertificatePercentage.setSourceablyVerifiedScore(requestData.getSelfAttested()?3l:0);
        bCertificatePercentage.setValidDocumentDateScore(validDate?3l:0);
        requestData.setBCertificatePercentage(bCertificatePercentage);
        providerBCertificateDataRepository.save(requestData);

        return "Data Saved Success !";
    }

	/*
	 * @Override public List<GlobalBussinessGraphData>
	 * globalCyberGraphData(LocalDate startDate, LocalDate endDate) {
	 * //certificateName, globalMarket, aquireCreation return Arrays.asList(new
	 * GlobalBussinessGraphData("Dummy",5.4,8.5)); }
	 */
    

}
