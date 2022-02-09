package com.oms.projectbuddy.services;

import com.oms.projectbuddy.dto.GlobalBussinessGraphData;
import com.oms.projectbuddy.model.ProviderBCertificateData;

import java.time.LocalDate;
import java.util.List;

public interface IBCertificateService {
    Object saveBCertificateData(ProviderBCertificateData providerBCertificateData);

    //List<GlobalBussinessGraphData> globalCyberGraphData(LocalDate startDate, LocalDate endDate);

}
