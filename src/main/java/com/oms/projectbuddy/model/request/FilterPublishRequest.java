package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterPublishRequest {
    private ProviderCriteraRequest providerCriteria;
    private ExperianceCriteraRequest experianceCriteraRequest;
    private String sourceblyMinRating;
    private ITCertFIlterRequest itCertFIlterRequest;
    private BusinessCertFilterRequest businessCertFilterRequest;
}
