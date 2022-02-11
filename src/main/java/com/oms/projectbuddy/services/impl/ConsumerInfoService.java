package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.*;
import com.oms.projectbuddy.model.request.*;
import com.oms.projectbuddy.model.response.BusinessBasicInfoResponse;
import com.oms.projectbuddy.repository.*;
import com.oms.projectbuddy.services.IConsumerInfoService;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Collections;

@Singleton
public class ConsumerInfoService implements IConsumerInfoService {
    @Inject
    private CompanyRepository registrationRepository;
    @Inject
    private ConsumerBusinessBasicInfoRepository consumerBusinessBasicInfoRepository;
    @Inject
    private ConsumerBankingInfoRepository consumerBankingInfoRepository;
    @Inject
    private ConsumerCompanyDetailsRepository consumerCompanyDetailsRepository;
    @Inject
    private ConsumerSalesRepository consumerSalesRepository;
    @Inject
    private ConsumerHighlightsRepository consumerHighlightsRepository;
    @Inject
    private ConsumerFinancialsRepository consumerFinancialsRepository;
//    @Inject
//    private FileOperation fileOperation;
    @Inject
    private FinancialImageRepository financialImageRepository;
    @Inject
    private AdditionalAttachmentRepository additionalAttachmentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerInfoService.class);


    @Override
    public String saveConsumerBasicInfo(ConsumerBusinessBasicInfoRequest basicInfoRequest) throws Exception {
        if (registrationRepository.findByUserId(basicInfoRequest.getUserId()) != null) {
            CompanyRegistration companyRegistration = registrationRepository.findByUserId(basicInfoRequest.getUserId());
            ConsumerBusinessBasicInfo basicInfo = new ConsumerBusinessBasicInfo();
            basicInfo.setUserId(basicInfoRequest.getUserId());
            basicInfo.setAlternateNumber(basicInfoRequest.getAlternateNumber());
            basicInfo.setCeoName(basicInfoRequest.getCeoName());
            basicInfo.setEstablishedDate(basicInfoRequest.getEstablishedDate());
            basicInfo.setFaxNumber(basicInfoRequest.getFaxNumber());
            basicInfo.setFounders(basicInfoRequest.getFounders());
            //basicInfo.setHeadquarters(basicInfoRequest.getHeadquarters());
            basicInfo.setHeadquartersCountry(basicInfoRequest.getHeadquartersCountry());
            basicInfo.setHeadquartersState(basicInfoRequest.getHeadquartersState());
            basicInfo.setHeadquartersCity(basicInfoRequest.getHeadquartersCity());
            basicInfo.setCompanyWebsite(basicInfoRequest.getCompanyWebsite());
            basicInfo.setPointOfContact(basicInfoRequest.getPointOfContact());
            if(basicInfoRequest.getCompanyVideoUrl() != null) {
                basicInfo.setCompanyVideo(basicInfoRequest.getCompanyVideoUrl());
                LOG.info("Consumer video saved with URL"+ basicInfo.getCompanyVideo());
            }
            basicInfo.setProducts(basicInfoRequest.getProducts());
            basicInfo.setCompanyDescription(basicInfoRequest.getCompanyDescription());
            basicInfo.setTagLine(basicInfoRequest.getTagLine());
            basicInfo.setServiceName(basicInfoRequest.getServiceName());
            basicInfo.setSubsidiary(basicInfoRequest.getSubsidiary());
            basicInfo.setStockPrice(basicInfoRequest.getStockPrice());
            basicInfo.setYearsOfRegistered(basicInfoRequest.getYearsOfRegistered());
            if(basicInfoRequest.getAgreeToShare()!=null) {
                basicInfo.setAgreeToShare(basicInfoRequest.getAgreeToShare());
            }else {
                basicInfo.setAgreeToShare(true);
            }
            String name=companyRegistration.getFirstName();
            if(companyRegistration.getMiddleName()!=null){
                name=name+" "+companyRegistration.getMiddleName();
            }
            if(companyRegistration.getLastName()!=null){
                name=name+" "+companyRegistration.getLastName();
            }
            basicInfo.setLastUpdatedBy(name);
            basicInfo.setUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerBusinessBasicInfoRepository.save(basicInfo);

            return "saved Successfully";
        } else {
            return "Company not exist";
        }
    }

    @Override
    public BusinessBasicInfoResponse getConsumerBasicInfo(String userId) throws Exception {
        CompanyRegistration registration = registrationRepository.findByUserId(userId);
        BusinessBasicInfoResponse infoResponse = new BusinessBasicInfoResponse();
        System.out.println("userId:-"+userId);
        ConsumerBusinessBasicInfo basicInfo = consumerBusinessBasicInfoRepository.findByUserId(userId);
        System.out.println("basic info:-"+ basicInfo.getUserId()+"id:"+ basicInfo.getId());
        if (basicInfo != null) {
            infoResponse.setPhoneNumber(registration.getMobileNumber());
            infoResponse.setEmail(registration.getEmail());
            infoResponse.setCompanyName(registration.getCompanyName());
            String line1=registration.getAddressLine1();
            String line2=registration.getAddressLine2();
            String state=registration.getState();
            String city=registration.getCity();
            String country=registration.getCountry();
            String zipcode=registration.getZipcode();
            String address= line1+", "+line2+", "+city+", "+state+", "+country+", "+zipcode;
            infoResponse.setAddressPerLocation(address);
            //infoRequest.setAddressPerLocation(registration.getAddressLine1());
            infoResponse.setId(basicInfo.getId());
            infoResponse.setAlternateEmail(basicInfo.getAlternateEmail());
            infoResponse.setUserId(basicInfo.getUserId());
            infoResponse.setAlternateNumber(basicInfo.getAlternateNumber());
            infoResponse.setCeoName(basicInfo.getCeoName());
            infoResponse.setEstablishedDate(basicInfo.getEstablishedDate());
            infoResponse.setFaxNumber(basicInfo.getFaxNumber());
            infoResponse.setCompanyDescription(basicInfo.getCompanyDescription());
            infoResponse.setTagLine(basicInfo.getTagLine());
            infoResponse.setServiceName(basicInfo.getServiceName());
            infoResponse.setFounders(basicInfo.getFounders());
            infoResponse.setCompanyVideo(basicInfo.getCompanyVideo());
           // infoResponse.setHeadquarters(basicInfo.getHeadquarters());
            infoResponse.setHeadquartersCountry(basicInfo.getHeadquartersCountry());
            infoResponse.setHeadquartersState(basicInfo.getHeadquartersState());
            infoResponse.setHeadquartersCity(basicInfo.getHeadquartersCity());
            infoResponse.setCompanyWebsite(basicInfo.getCompanyWebsite());
            infoResponse.setPointOfContact(basicInfo.getPointOfContact());
            infoResponse.setProducts(basicInfo.getProducts());
            infoResponse.setSubsidiary(basicInfo.getSubsidiary());
            infoResponse.setStockPrice(basicInfo.getStockPrice());
            infoResponse.setYearsOfRegistered(basicInfo.getYearsOfRegistered());
            infoResponse.setAgreeToShare(basicInfo.getAgreeToShare());
            infoResponse.setLastUpdatedBy(basicInfo.getLastUpdatedBy());
            infoResponse.setUpdatedEpochTime(basicInfo.getUpdatedEpochTime());
            return infoResponse;
        } else {
            throw new SourceablyCustomeException("UserId not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String updateConsumerBasicInfo(ConsumerBusinessBasicInfoRequest basicInfoRequest) throws Exception {
        if (registrationRepository.findByUserId(basicInfoRequest.getUserId()) != null) {
            if (consumerBusinessBasicInfoRepository.findByUserId(basicInfoRequest.getUserId()) != null) {
                ConsumerBusinessBasicInfo basicInfo = consumerBusinessBasicInfoRepository.findByUserId(basicInfoRequest.getUserId());
                CompanyRegistration companyRegistration = registrationRepository.findByUserId(basicInfoRequest.getUserId());
                basicInfo.setAlternateNumber(basicInfoRequest.getAlternateNumber());
                basicInfo.setCeoName(basicInfoRequest.getCeoName());
                basicInfo.setAlternateEmail(basicInfoRequest.getAlternateEmail());
                basicInfo.setEstablishedDate(basicInfoRequest.getEstablishedDate());
                basicInfo.setFaxNumber(basicInfoRequest.getFaxNumber());
                basicInfo.setFounders(basicInfoRequest.getFounders());
                //basicInfo.setHeadquarters(basicInfoRequest.getHeadquarters());
                basicInfo.setHeadquartersCountry(basicInfoRequest.getHeadquartersCountry());
                basicInfo.setHeadquartersState(basicInfoRequest.getHeadquartersState());
                basicInfo.setHeadquartersCity(basicInfoRequest.getHeadquartersCity());
                basicInfo.setCompanyWebsite(basicInfoRequest.getCompanyWebsite());
                basicInfo.setCompanyDescription(basicInfoRequest.getCompanyDescription());
                basicInfo.setTagLine(basicInfoRequest.getTagLine());
                basicInfo.setServiceName(basicInfoRequest.getServiceName());
                basicInfo.setPointOfContact(basicInfoRequest.getPointOfContact());
                basicInfo.setCompanyVideo(basicInfoRequest.getCompanyVideoUrl());
                //if(basicInfoRequest.getCompanyVideo() != null) {
                basicInfo.setProducts(basicInfoRequest.getProducts());
                basicInfo.setSubsidiary(basicInfoRequest.getSubsidiary());
                basicInfo.setStockPrice(basicInfoRequest.getStockPrice());
                basicInfo.setYearsOfRegistered(basicInfoRequest.getYearsOfRegistered());
                if (basicInfoRequest.getAgreeToShare() != null) {
                    basicInfo.setAgreeToShare(basicInfoRequest.getAgreeToShare());
                }
                String name=companyRegistration.getFirstName();
                if(companyRegistration.getMiddleName()!=null){
                    name=name+" "+companyRegistration.getMiddleName();
                }
                if(companyRegistration.getLastName()!=null){
                    name=name+" "+companyRegistration.getLastName();
                }
                basicInfo.setLastUpdatedBy(name);
                basicInfo.setUpdatedEpochTime(Instant.now().toEpochMilli());
                consumerBusinessBasicInfoRepository.save(basicInfo);
                return "saved Successfully";
            } else {
                throw new SourceablyCustomeException("user id not found",HttpStatus.NOT_FOUND);
            }
        } else {
            throw new SourceablyCustomeException("user id not found",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String saveConsumerBankingInformation(ConsumerBankingInfoRequest bankingRequest,String userId) throws Exception {
        CompanyRegistration registration = registrationRepository.findByUserId(userId);
        if (registration != null) {
            for(ConsumerBankingInformation bankingInfo : bankingRequest.getConsumerBankingInfoList()) {
                ConsumerBankingInformation bankingInformation = new ConsumerBankingInformation();
                bankingInformation.setBankName(bankingInfo.getBankName());
                bankingInformation.setBranchName(bankingInfo.getBranchName());
                bankingInformation.setMicrCode(bankingInfo.getMicrCode());
                bankingInformation.setAccountType(bankingInfo.getAccountType());
                bankingInformation.setAccountNumber(bankingInfo.getAccountNumber());
                bankingInformation.setBeneficiaryName(bankingInfo.getBeneficiaryName());
                bankingInformation.setBranchAddress(bankingInfo.getBranchAddress());
                bankingInformation.setBranchCode(bankingInfo.getBranchCode());
                bankingInformation.setIfscCode(bankingInfo.getIfscCode());
                bankingInformation.setUserId(userId);
                bankingInformation.setSwiftCode(bankingInfo.getSwiftCode());
                if (bankingInfo.getIsPrimaryAccount() != null) {
                    bankingInformation.setIsPrimaryAccount(bankingInfo.getIsPrimaryAccount());
                } else {
                    bankingInformation.setIsPrimaryAccount(true);
                }

                String name=registration.getFirstName();
                if(registration.getMiddleName()!=null){
                    name=name+" "+registration.getMiddleName();
                }
                if(registration.getLastName()!=null){
                    name=name+" "+registration.getLastName();
                }
                bankingInformation.setLastUpdatedBy(name);
                bankingInformation.setLastUpdatedBy(registration.getEmail());
                bankingInformation.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
                consumerBankingInfoRepository.save(bankingInformation);
            }
            return "saved Succuessfully";
        } else {
            throw new SourceablyCustomeException("user id not found",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ConsumerBankingInfoRequest getConsumerBankingInformation(String userId) throws Exception {
        if (consumerBankingInfoRepository.findByUserId(userId) != null) {
            ConsumerBankingInfoRequest bankingInfoRequest=new ConsumerBankingInfoRequest();
        bankingInfoRequest.setConsumerBankingInfoList(consumerBankingInfoRepository.findByUserId(userId));
        return bankingInfoRequest;
        } else {
            throw new SourceablyCustomeException("user id not found",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String updateConsumerBankingInformation(ConsumerBankingInfoRequest bankingRequest,String userId) throws Exception {
          if (consumerBankingInfoRepository.findByUserId(userId) != null) {
              consumerBankingInfoRepository.deleteAllByUserId(userId);
              CompanyRegistration registration=registrationRepository.findByUserId(userId);
              for(ConsumerBankingInformation bankInfo: bankingRequest.getConsumerBankingInfoList()) {
                 // ConsumerBankingInformation bankingInformation = consumerBankingInfoRepository.findById(bankInfo.getId()).get();
                  ConsumerBankingInformation bankingInformation = new ConsumerBankingInformation();
                  bankingInformation.setBankName(bankInfo.getBankName());
                  bankingInformation.setUserId(userId);
                  bankingInformation.setAccountType(bankInfo.getAccountType());
                  bankingInformation.setAccountNumber(bankInfo.getAccountNumber());
                  bankingInformation.setBeneficiaryName(bankInfo.getBeneficiaryName());
                  bankingInformation.setBranchName(bankInfo.getBranchName());
                  bankingInformation.setMicrCode(bankInfo.getMicrCode());
                  bankingInformation.setBranchAddress(bankInfo.getBranchAddress());
                  bankingInformation.setBranchCode(bankInfo.getBranchCode());
                  bankingInformation.setIfscCode(bankInfo.getIfscCode());
                  bankingInformation.setSwiftCode(bankInfo.getSwiftCode());
                  bankingInformation.setIsPrimaryAccount(bankInfo.getIsPrimaryAccount());
                  String name=registration.getFirstName();
                  if(registration.getMiddleName()!=null){
                      name=name+" "+registration.getMiddleName();
                  }
                  if(registration.getLastName()!=null){
                      name=name+" "+registration.getLastName();
                  }
                  bankingInformation.setLastUpdatedBy(name);
                  bankingInformation.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
                  consumerBankingInfoRepository.save(bankingInformation);
              }
              return "Updated Successfully";

          } else {
              return "user id not found";
          }
      }


    @Override
    public String saveConsumerDetails(ConsumerDetailsRequest detailsRequest) throws Exception {
        CompanyRegistration registration = registrationRepository.findByUserId(detailsRequest.getUserId());
        if (registration != null) {
            ConsumerDetails consumerDetails = new ConsumerDetails();
            consumerDetails.setUserId(detailsRequest.getUserId());
            consumerDetails.setCompanyDescription(detailsRequest.getCompanyDescription());
            consumerDetails.setAdditionalInfo(detailsRequest.getAdditionalInfo());
            consumerDetails.setAdvertising(detailsRequest.getAdvertising());
            consumerDetails.setBonded(detailsRequest.getBonded());
            consumerDetails.setGeographicalServiceArea(detailsRequest.getGeographicalServiceArea());
            consumerDetails.setHistory(detailsRequest.getHistory());
            consumerDetails.setIndustryInfo(detailsRequest.getIndustryInfo());
            consumerDetails.setAgreeToShare(detailsRequest.getAgreeToShare());
            consumerDetails.setLiscenceNo(detailsRequest.getLiscenceNo());
            consumerDetails.setInsecured(detailsRequest.getInsecured());
            consumerDetails.setGrossAnnualSale(detailsRequest.getGrossAnnualSale());
            consumerDetails.setLegalStructure(detailsRequest.getLegalStructure());
            consumerDetails.setProductDescription(detailsRequest.getProductDescription());
            consumerDetails.setServiceDescription(detailsRequest.getServiceDescription());
            consumerDetails.setPublicRelation(detailsRequest.getPublicRelation());
            consumerDetails.setSafetyPolicy(detailsRequest.getSafetyPolicy());
            consumerDetails.setLiscensed(detailsRequest.getLiscensed());
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            consumerDetails.setLastUpdatedBy(name);
            consumerDetails.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerCompanyDetailsRepository.save(consumerDetails);
            return "saved successfully";
        } else {
            return "user id not found";
        }
    }

    @Override
    public String updateConsumerDetails(ConsumerDetailsRequest detailsRequest) throws Exception {
        if (consumerCompanyDetailsRepository.findByUserId(detailsRequest.getUserId()) != null) {
            ConsumerDetails companyDetails = consumerCompanyDetailsRepository.findByUserId(detailsRequest.getUserId());
            CompanyRegistration registration = registrationRepository.findByUserId(detailsRequest.getUserId());
            companyDetails.setCompanyDescription(detailsRequest.getCompanyDescription());
            companyDetails.setAdditionalInfo(detailsRequest.getAdditionalInfo());
            companyDetails.setAdvertising(detailsRequest.getAdvertising());
            companyDetails.setBonded(detailsRequest.getBonded());
            companyDetails.setGeographicalServiceArea(detailsRequest.getGeographicalServiceArea());
            companyDetails.setHistory(detailsRequest.getHistory());
            companyDetails.setIndustryInfo(detailsRequest.getIndustryInfo());
            companyDetails.setAgreeToShare(detailsRequest.getAgreeToShare());
            companyDetails.setLiscenceNo(detailsRequest.getLiscenceNo());
            companyDetails.setInsecured(detailsRequest.getInsecured());
            companyDetails.setGrossAnnualSale(detailsRequest.getGrossAnnualSale());
            companyDetails.setLegalStructure(detailsRequest.getLegalStructure());
            companyDetails.setProductDescription(detailsRequest.getProductDescription());
            companyDetails.setServiceDescription(detailsRequest.getServiceDescription());
            companyDetails.setPublicRelation(detailsRequest.getPublicRelation());
            companyDetails.setSafetyPolicy(detailsRequest.getSafetyPolicy());
            companyDetails.setLiscensed(detailsRequest.getLiscensed());
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            companyDetails.setLastUpdatedBy(name);
            companyDetails.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerCompanyDetailsRepository.save(companyDetails);
            return "updated Successfully";
        } else {
            return "user not found";
        }
    }

    @Override
    public ConsumerDetails getConsumerDetails(String userId) throws Exception {
        if (consumerCompanyDetailsRepository.findByUserId(userId) != null) {
            return consumerCompanyDetailsRepository.findByUserId(userId);
        } else {
            throw new Exception("user id not found");
        }
    }

    @Override
    public String saveConsumerSales(ConsumerSalesRequest salesRequest) throws Exception {
        CompanyRegistration registration = registrationRepository.findByUserId(salesRequest.getUserId());
        if (registration != null) {
            ConsumerSales consumerSales = new ConsumerSales();
            consumerSales.setUserId(salesRequest.getUserId());
            consumerSales.setAdditionalInformation(salesRequest.getAdditionalInformation());
            if(salesRequest.getGeneralisedNdaDocumentUrl()!=null){
                consumerSales.setNdaFileName(salesRequest.getOrginalFileName());
                consumerSales.setGeneralisedNdaDocument(salesRequest.getGeneralisedNdaDocumentUrl());
                LOG.info("Generalised NDA uploaded with URL"+consumerSales.getGeneralisedNdaDocument());
            }
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            consumerSales.setLastUpdatedBy(name);
            consumerSales.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerSalesRepository.save(consumerSales);
            if(salesRequest.getFiles()!=null) {
                saveAdditionalAttachment(salesRequest.getUserId(), consumerSales.getId(), salesRequest.getFiles());
            }
            return "saved Successfully";
        } else {
            return "user id not found";
        }
    }
    private void saveAdditionalAttachment(String userId, Long salesId, String[] files) {
       /* if (additionalAttachmentRepository.findBySalesIdAndUserId(salesId, userId) != null) {
            additionalAttachmentRepository.deleteAllBySalesIdAndUserId(salesId, userId);
        }*/
        try {
            for (String upload : files) {
                String[] uploadfile= upload.split("\\^");
                AdditionalAttachment attachment = new AdditionalAttachment();
                attachment.setSalesId(salesId);
                attachment.setUserId(userId);
                attachment.setFileName(uploadfile[0]);
                attachment.setFiles(uploadfile[1]);
                additionalAttachmentRepository.save(attachment);
                LOG.info("additional doc uploaded with URL "+attachment.getFiles() +"for userId"+userId);

            }
        } catch (Exception e) {
            LOG.info("document can not be uploaded "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ConsumerSales getConsumerSales(String userId) throws Exception {
        try {
            ConsumerSales consumerSales= consumerSalesRepository.findByUserId(userId);
            consumerSales.setAdditionalAttachments(additionalAttachmentRepository.findBySalesIdAndUserId(consumerSales.getId(),userId));
            return consumerSales;
        } catch (Exception e) {
            throw new Exception("user not found"+e.getMessage());
        }

    }

    @Override
    public String updateConsumerSales(ConsumerSalesRequest salesRequest) throws Exception {
        try {
            CompanyRegistration registration = registrationRepository.findByUserId(salesRequest.getUserId());
            ConsumerSales consumerSales = consumerSalesRepository.findByUserId(salesRequest.getUserId());
            if(consumerSales==null){
                consumerSales= new ConsumerSales();
                consumerSales.setUserId(salesRequest.getUserId());
            }
            consumerSales.setAdditionalInformation(salesRequest.getAdditionalInformation());
            if(salesRequest.getGeneralisedNdaDocumentUrl()!=null){
                consumerSales.setNdaFileName(salesRequest.getOrginalFileName());
                consumerSales.setGeneralisedNdaDocument(salesRequest.getGeneralisedNdaDocumentUrl());
                LOG.info("Consumer NDA doc updated with URL "+consumerSales.getGeneralisedNdaDocument());
            }
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            consumerSales.setLastUpdatedBy(name);
            consumerSales.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerSalesRepository.save(consumerSales);
            if(salesRequest.getFiles()!=null) {
                saveAdditionalAttachment(salesRequest.getUserId(), consumerSales.getId(), salesRequest.getFiles());
                LOG.info("updated consumer additional attachment");
            }
            return "updated successfully";
        } catch (Exception e) {
            throw new Exception("user id not found"+e.getMessage());
        }
    }

    @Override
    public String saveConsumerHighlights(ConsumerHighlightsRequest highlightsRequest) throws Exception {
        if (registrationRepository.findByUserId(highlightsRequest.getUserId()) != null) {
            CompanyRegistration registration = registrationRepository.findByUserId(highlightsRequest.getUserId());
            ConsumerHighlights consumerHighlights = new ConsumerHighlights();
            consumerHighlights.setUserId(highlightsRequest.getUserId());;
            consumerHighlights.setAwards(highlightsRequest.getAwards());
            consumerHighlights.setNewsAndMedia(highlightsRequest.getNewsAndMedia());
            consumerHighlights.setTestimonials(highlightsRequest.getTestimonials());
            consumerHighlights.setSpecialProgramProjects(highlightsRequest.getSpecialProgramProjects());
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            consumerHighlights.setLastUpdatedBy(name);
            consumerHighlights.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerHighlightsRepository.save(consumerHighlights);
            return "saved successfully";
        } else {
            return "user id not found";
        }
    }

    @Override
    public ConsumerHighlights getConsumerHighlights(String userId) throws Exception {
        try {
            return consumerHighlightsRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new Exception("user Id not found"+e.getMessage());
        }
    }

    @Override
    public String updateConsumerHighlights(ConsumerHighlightsRequest highlightsRequest) throws Exception {
        try {
            CompanyRegistration registration = registrationRepository.findByUserId(highlightsRequest.getUserId());
            ConsumerHighlights consumerHighlights = consumerHighlightsRepository.findByUserId(highlightsRequest.getUserId());
            consumerHighlights.setAwards(highlightsRequest.getAwards());
            consumerHighlights.setTestimonials(highlightsRequest.getTestimonials());
            consumerHighlights.setSpecialProgramProjects(highlightsRequest.getSpecialProgramProjects());
            consumerHighlights.setNewsAndMedia(highlightsRequest.getNewsAndMedia());
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            consumerHighlights.setLastUpdatedBy(name);
            consumerHighlights.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerHighlightsRepository.save(consumerHighlights);
            return "updated Successfully";
        } catch (Exception e) {
            throw new Exception("User id not found"+e.getMessage());
        }
    }

    @Override
    public String saveConsumerFinancials(ConsumerFinancialsRequest financialsRequest) throws Exception {
        if(registrationRepository.findByUserId(financialsRequest.getUserId()) != null) {
            CompanyRegistration registration = registrationRepository.findByUserId(financialsRequest.getUserId());
            ConsumerFinancials financials = new ConsumerFinancials();
            financials.setUserId(financialsRequest.getUserId());
            financials.setFinancialTargets(financialsRequest.getFinancialTargets());
            financials.setPartners(financialsRequest.getPartners());
            financials.setLastYearRevenue(financialsRequest.getLastYearRevenue());
            financials.setNumberOfEmployees(financialsRequest.getNumberOfEmployees());
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            financials.setLastUpdatedBy(name);
            financials.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerFinancialsRepository.save(financials);
            if(financialsRequest.getFiles()!=null) {
                saveFinancialImage(financials.getUserId(), financials.getId(), financialsRequest.getFiles());
            }
            return "saved successfully";
        }else{
            return "user id not found";
        }
    }
    private void saveFinancialImage(String userId, Long financialId, String[] files){
        /*if(financialImageRepository.findByFinancialIdAndUserId(financialId,userId)!=null){
            financialImageRepository.deleteAllByFinancialIdAndUserId(financialId,userId);
        }*/
        try {
            for(String upload:files){
                String[] fileName= upload.split("^");
                FinancialImageUpload imageUpload=new FinancialImageUpload();
                imageUpload.setFinancialId(financialId);
                imageUpload.setUserId(userId);
                imageUpload.setFileName(fileName[0]);
                imageUpload.setFiles(fileName[1]);
                financialImageRepository.save(imageUpload);
                LOG.info("consumer financial image uploaded with URL "+imageUpload.getFiles());
            }
        }catch (Exception e){
            LOG.info("Unable to upload "+e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public ConsumerFinancials getConsumerFinancials(String userId) throws Exception {
        try {
            ConsumerFinancials consumerFinancials= consumerFinancialsRepository.findByUserId(userId);
            consumerFinancials.setFinancialImages(financialImageRepository.findByFinancialIdAndUserId(consumerFinancials.getId(),userId));
            return consumerFinancials;
        } catch (Exception e) {
            throw new Exception("user id not found"+e.getMessage());
        }
    }

    @Override
    public String updateConsumerfinancials(ConsumerFinancialsRequest financialsRequest) throws Exception {
        try{
            CompanyRegistration registration = registrationRepository.findByUserId(financialsRequest.getUserId());
            ConsumerFinancials financials=consumerFinancialsRepository.findByUserId(financialsRequest.getUserId());
            financials.setPartners(financialsRequest.getPartners());
            financials.setFinancialTargets(financialsRequest.getFinancialTargets());
            financials.setNumberOfEmployees(financialsRequest.getNumberOfEmployees());
            financials.setLastYearRevenue(financialsRequest.getLastYearRevenue());
            String name=registration.getFirstName();
            if(registration.getMiddleName()!=null){
                name=name+" "+registration.getMiddleName();
            }
            if(registration.getLastName()!=null){
                name=name+" "+registration.getLastName();
            }
            financials.setLastUpdatedBy(name);
            financials.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
            consumerFinancialsRepository.save(financials);
            if(financialsRequest.getFiles()!=null) {
                saveFinancialImage(financials.getUserId(), financials.getId(), financialsRequest.getFiles());
            }
            return "updated Successfully";
        }catch (Exception e){
            throw new Exception("user id not found"+e.getMessage());

        }
    }

    @Override
    public Object deleteNdaDocument(String userId) throws Exception {

        if (consumerSalesRepository.existsByUserId(userId)){
            ConsumerSales consumerSales= consumerSalesRepository.findByUserId(userId);
            consumerSales.setNdaFileName(null);
            consumerSales.setGeneralisedNdaDocument(null);
            consumerSalesRepository.save(consumerSales);
            return "Deleted";
        }else {
            throw new SourceablyCustomeException("Not Exist",HttpStatus.NOT_FOUND);
        }
    }
}
