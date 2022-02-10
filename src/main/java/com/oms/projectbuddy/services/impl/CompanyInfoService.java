package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.*;
import com.oms.projectbuddy.model.request.*;
import com.oms.projectbuddy.model.response.BusinessBasicInfoResponse;
import com.oms.projectbuddy.repository.*;
import com.oms.projectbuddy.utility.FileOperation;
import com.oms.projectbuddy.utils.Constants;
import com.oms.projectbuddy.utils.ExceptionUtils;

import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.oms.projectbuddy.services.ICompanyInfoService;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CompanyInfoService implements ICompanyInfoService {
	@Inject
	private BusinessBasicInfoRepository basicInfoRepository;
	@Inject
	private CompanyRepository registrationRepository;
	@Inject
	private BankingInfoRepository bankingInfoRepository;
	@Inject
	private CompanyDetailsRepository companyDetailsRepository;
	@Inject
	private CompanySalesRepository companySalesRepository;
	@Inject
	private ConsumerSalesRepository consumerSalesRepository;
	@Inject
	private CompanyHighlightsRepository companyHighlightsRepository;
	@Inject
	private CompanyFinancialsRepository companyFinancialsRepository;
	@Inject
	private ConsumerFinancialsRepository consumerFinancialsRepository;
	@Inject
	private FileOperation fileOperation;
	@Inject
	private AdditionalAttachmentRepository additionalAttachmentRepository;
	@Inject
	private FinancialImageRepository financialImageRepository;
	@Inject
	private ConsumerBusinessBasicInfoRepository consumerBusinessBasicInfoRepository;

	private static final Logger LOG = LoggerFactory.getLogger(CompanyInfoService.class);
	@Override
	public String saveBasicInfo(BusinessBasicInfoRequest basicInfoRequest) throws Exception {
			CompanyRegistration companyRegistration = registrationRepository.findByUserId(basicInfoRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(companyRegistration);
			BusinessBasicInfo basicInfo = new BusinessBasicInfo();
			basicInfo.setUserId(basicInfoRequest.getUserId());
			basicInfo.setAlternateNumber(basicInfoRequest.getAlternateNumber());
			basicInfo.setCeoName(basicInfoRequest.getCeoName());
			basicInfo.setEstablishedDate(basicInfoRequest.getEstablishedDate());
			basicInfo.setFaxNumber(basicInfoRequest.getFaxNumber());
			basicInfo.setFounders(basicInfoRequest.getFounders());
			basicInfo.setAlternateEmail(basicInfoRequest.getAlternateEmail());
			//basicInfo.setHeadquarters(basicInfoRequest.getHeadquarters());
			basicInfo.setHeadquartersCountry(basicInfoRequest.getHeadquartersCountry());
			basicInfo.setHeadquartersState(basicInfoRequest.getHeadquartersState());
			basicInfo.setHeadquartersCity(basicInfoRequest.getHeadquartersCity());
			basicInfo.setCompanyWebsite(basicInfoRequest.getCompanyWebsite());
			basicInfo.setPointOfContact(basicInfoRequest.getPointOfContact());
			basicInfo.setCompanyDescription(basicInfoRequest.getCompanyDescription());
			basicInfo.setTagLine(basicInfoRequest.getTagLine());
			basicInfo.setServiceName(basicInfoRequest.getServiceName());
			basicInfo.setCompanyVideo(basicInfoRequest.getCompanyVideoUrl());

			basicInfo.setProducts(basicInfoRequest.getProducts());
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
			basicInfoRepository.save(basicInfo);

			return "saved Successfully";
	}

	@Override
	public BusinessBasicInfoResponse getInfoByUserId(String userId) throws Exception {
		try {
			CompanyRegistration registration = registrationRepository.findByUserId(userId);
			ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			BusinessBasicInfoResponse infoResponse = new BusinessBasicInfoResponse();
			BusinessBasicInfo basicInfo = basicInfoRepository.findByUserId(userId);
			ExceptionUtils.verifyDataNotExistThenThrowException(basicInfo);
				infoResponse.setPhoneNumber(registration.getMobileNumber());
				infoResponse.setEmail(registration.getEmail());
				infoResponse.setCompanyName(registration.getCompanyName());
				String line1 = registration.getAddressLine1();
				String line2 = registration.getAddressLine2();
				String state = registration.getState();
				String city = registration.getCity();
				String country = registration.getCountry();
				String zipcode = registration.getZipcode();
				String address = line1 + ", " + line2 + ", " + state + ", " + city + ", " + country + ", " + zipcode;
				infoResponse.setAddressPerLocation(address);
				//infoRequest.setAddressPerLocation(registration.getAddressLine1());
				infoResponse.setCompanyVideo(basicInfo.getCompanyVideo());
				infoResponse.setId(basicInfo.getId());
				infoResponse.setUserId(basicInfo.getUserId());
				infoResponse.setCompanyDescription(basicInfo.getCompanyDescription());
				infoResponse.setTagLine(basicInfo.getTagLine());
				infoResponse.setServiceName(basicInfo.getServiceName());
				infoResponse.setAlternateEmail(basicInfo.getAlternateEmail());
				infoResponse.setAlternateNumber(basicInfo.getAlternateNumber());
				infoResponse.setCeoName(basicInfo.getCeoName());
				infoResponse.setEstablishedDate(basicInfo.getEstablishedDate());
				infoResponse.setFaxNumber(basicInfo.getFaxNumber());
				infoResponse.setFounders(basicInfo.getFounders());
				//infoResponse.setHeadquarters(basicInfo.getHeadquarters());
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
		} catch (Exception e) {
			throw new SourceablyCustomeException("id not found", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}


	@Override
	public String updateBasicInfo(BusinessBasicInfoRequest basicInfoRequest) throws Exception {

		CompanyRegistration companyRegistration = registrationRepository.findByUserId(basicInfoRequest.getUserId());
		ExceptionUtils.verifyDataNotExistThenThrowException(companyRegistration);
		BusinessBasicInfo basicInfo = basicInfoRepository.findByUserId(basicInfoRequest.getUserId());
		ExceptionUtils.verifyDataNotExistThenThrowException(basicInfo);
				basicInfo.setAlternateNumber(basicInfoRequest.getAlternateNumber());
				basicInfo.setCeoName(basicInfoRequest.getCeoName());
				basicInfo.setEstablishedDate(basicInfoRequest.getEstablishedDate());
				basicInfo.setFaxNumber(basicInfoRequest.getFaxNumber());
				basicInfo.setAlternateEmail(basicInfoRequest.getAlternateEmail());
				basicInfo.setFounders(basicInfoRequest.getFounders());
				//basicInfo.setHeadquarters(basicInfoRequest.getHeadquarters());
				basicInfo.setHeadquartersCountry(basicInfoRequest.getHeadquartersCountry());
				basicInfo.setHeadquartersState(basicInfoRequest.getHeadquartersState());
				basicInfo.setHeadquartersCity(basicInfoRequest.getHeadquartersCity());
				basicInfo.setCompanyDescription(basicInfoRequest.getCompanyDescription());
				basicInfo.setTagLine(basicInfoRequest.getTagLine());
				basicInfo.setServiceName(basicInfoRequest.getServiceName());
				basicInfo.setCompanyWebsite(basicInfoRequest.getCompanyWebsite());
				basicInfo.setPointOfContact(basicInfoRequest.getPointOfContact());
				basicInfo.setCompanyVideo(basicInfoRequest.getCompanyVideoUrl());

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
				basicInfoRepository.save(basicInfo);
				return "saved Successfully";
	}

	@Override
	public String saveBankingInformation(BankingInformationRequest bankingRequest,String userId) throws Exception {
		CompanyRegistration registration = registrationRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			for(BankingInformation bankInfo:bankingRequest.getSupplierBankingInfoList()) {
				BankingInformation bankingInformation = new BankingInformation();
				bankingInformation.setBankName(bankInfo.getBankName());
				bankingInformation.setAccountNumber(bankInfo.getAccountNumber());
				bankingInformation.setAccountType(bankInfo.getAccountType());
				bankingInformation.setBranchName(bankInfo.getBranchName());
				bankingInformation.setBeneficiaryName(bankInfo.getBeneficiaryName());
				bankingInformation.setBranchAddress(bankInfo.getBranchAddress());
				bankingInformation.setBranchCode(bankInfo.getBranchCode());
				bankingInformation.setMicrCode(bankInfo.getMicrCode());
				bankingInformation.setIfscCode(bankInfo.getIfscCode());
				bankingInformation.setUserId(userId);
				bankingInformation.setSwiftCode(bankInfo.getSwiftCode());
				if (bankInfo.getIsPrimaryAccount() != null) {
					bankingInformation.setIsPrimaryAccount(bankInfo.getIsPrimaryAccount());
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
				bankingInformation.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
				bankingInfoRepository.save(bankingInformation);
			}
			return "saved Succuessfully";
	}

	@Override
	public BankingInformationRequest getBankingInformation(String userId) throws Exception {
		List<BankingInformation> allBankingInformation = bankingInfoRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(allBankingInformation);
		BankingInformationRequest bankingInformationRequest=new BankingInformationRequest();
		bankingInformationRequest.setSupplierBankingInfoList(bankingInfoRepository.findByUserId(userId));
		return bankingInformationRequest;
	}

	@Override
	public String updateBankingInformation(BankingInformationRequest bankingRequest,String userId) throws Exception {
		List<BankingInformation> listOfBankInfo = bankingInfoRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(listOfBankInfo);
			bankingInfoRepository.deleteAllByUserId(userId);
			CompanyRegistration registration = registrationRepository.findByUserId(userId);
			ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			for(BankingInformation bankingInfo:bankingRequest.getSupplierBankingInfoList()) {
				BankingInformation bankingInformation = new BankingInformation();
				bankingInformation.setBankName(bankingInfo.getBankName());
				bankingInformation.setUserId(userId);
				bankingInformation.setBranchName(bankingInfo.getBranchName());
				bankingInformation.setAccountType(bankingInfo.getAccountType());
				bankingInformation.setAccountNumber(bankingInfo.getAccountNumber());
				bankingInformation.setBeneficiaryName(bankingInfo.getBeneficiaryName());
				bankingInformation.setBranchAddress(bankingInfo.getBranchAddress());
				bankingInformation.setBranchCode(bankingInfo.getBranchCode());
				bankingInformation.setIfscCode(bankingInfo.getIfscCode());
				bankingInformation.setMicrCode(bankingInfo.getMicrCode());
				bankingInformation.setSwiftCode(bankingInfo.getSwiftCode());
				bankingInformation.setIsPrimaryAccount(bankingInfo.getIsPrimaryAccount());
				String name=registration.getFirstName();
				if(registration.getMiddleName()!=null){
					name=name+" "+registration.getMiddleName();
				}
				if(registration.getLastName()!=null){
					name=name+" "+registration.getLastName();
				}
				bankingInformation.setLastUpdatedBy(name);
				bankingInformation.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
				bankingInfoRepository.save(bankingInformation);
			}
			return "Updated Successfully";
	}

	@Override
	public String saveCompanyDetails(CompanyDetailsRequest detailsRequest) throws Exception {
		CompanyRegistration registration = registrationRepository.findByUserId(detailsRequest.getUserId());
		ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			CompanyDetails companyDetails = new CompanyDetails();
			companyDetails.setUserId(detailsRequest.getUserId());
			companyDetails.setCompanyDescription(detailsRequest.getCompanyDescription());
			companyDetails.setAdditionalInfo(detailsRequest.getAdditionalInfo());
			companyDetails.setAdvertising(detailsRequest.getAdvertising());
			companyDetails.setBonded(detailsRequest.getBonded());
			companyDetails.setGeographicalServiceArea(detailsRequest.getGeographicalServiceArea());
			companyDetails.setHistory(detailsRequest.getHistory());
			companyDetails.setIndustryInfo(detailsRequest.getIndustryInfo());
			companyDetails.setAgreeToShare(detailsRequest.getAgreeToShare());
			companyDetails.setInsecured(detailsRequest.getInsecured());
			companyDetails.setLiscenceNo(detailsRequest.getLiscenceNo());
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
			companyDetailsRepository.save(companyDetails);
			return "saved successfully";
	}

	@Override
	public String updateCompanyDetails(CompanyDetailsRequest detailsRequest) throws Exception {
			CompanyDetails companyDetails = companyDetailsRepository.findByUserId(detailsRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(companyDetails);
			CompanyRegistration registration = registrationRepository.findByUserId(detailsRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			companyDetails.setCompanyDescription(detailsRequest.getCompanyDescription());
			companyDetails.setAdditionalInfo(detailsRequest.getAdditionalInfo());
			companyDetails.setAdvertising(detailsRequest.getAdvertising());
			companyDetails.setBonded(detailsRequest.getBonded());
			companyDetails.setGeographicalServiceArea(detailsRequest.getGeographicalServiceArea());
			companyDetails.setHistory(detailsRequest.getHistory());
			companyDetails.setIndustryInfo(detailsRequest.getIndustryInfo());
			companyDetails.setAgreeToShare(detailsRequest.getAgreeToShare());
			companyDetails.setInsecured(detailsRequest.getInsecured());
			companyDetails.setLiscenceNo(detailsRequest.getLiscenceNo());
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
			companyDetails.setInsuredAmount(detailsRequest.getInsuredAmount());
			companyDetailsRepository.save(companyDetails);
			return "updated Successfully";
	}

	@Override
	public CompanyDetails getCompanyDetails(String userId) throws Exception {
		CompanyDetails companyDetails = companyDetailsRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(companyDetails);
		return companyDetails;
	}

	@Override
	public String saveCompanySales(CompanySalesRequest salesRequest) throws Exception {
		CompanyRegistration registration = registrationRepository.findByUserId(salesRequest.getUserId());
		ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			CompanySales companySales = new CompanySales();
			companySales.setUserId(salesRequest.getUserId());
			companySales.setAdditionalInformation(salesRequest.getAdditionalInformation());
			if(salesRequest.getGeneralisedNdaDocumentUrl()!=null){
				companySales.setNdaFileName(salesRequest.getOrginalFileName());
				companySales.setGeneralisedNdaDocument(salesRequest.getGeneralisedNdaDocumentUrl());
				LOG.info("Supplier Generalised NDA uploaded with URL "+companySales.getGeneralisedNdaDocument());
			}
			String name=registration.getFirstName();
			if(registration.getMiddleName()!=null){
				name=name+" "+registration.getMiddleName();
			}
			if(registration.getLastName()!=null){
				name=name+" "+registration.getLastName();
			}
			companySales.setLastUpdatedBy(name);
			companySales.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
			companySalesRepository.save(companySales);
			if(salesRequest.getFilesUrls()!=null) {
				saveAdditionalAttachment(salesRequest.getUserId(), companySales.getId(), salesRequest.getFilesUrls());
			}
			return "saved Successfully";
		
	}
	private void saveAdditionalAttachment(String userId, Long salesId, String[] files){
		/*if(additionalAttachmentRepository.findBySalesIdAndUserId(salesId,userId)!=null){
			additionalAttachmentRepository.deleteAllBySalesIdAndUserId(salesId,userId);
		}*/
		try {
			for(String upload:files){
				String[] fileName= upload.split("^");
				AdditionalAttachment attachment=new AdditionalAttachment();
				attachment.setSalesId(salesId);
				attachment.setFileName(fileName[0]);
				attachment.setUserId(userId);
				attachment.setFiles(fileName[1]);
				additionalAttachmentRepository.save(attachment);
				LOG.info("Supplier additional attachement uploaded with URL "+attachment.getFiles());
			}
		}catch (Exception e){
			LOG.info("additinal attachment can not be uploaded "+e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public CompanySales getCompanySales(String userId) throws Exception {
		CompanySales companySales= companySalesRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(companySales);
		List<AdditionalAttachment> listOfAdditionalAttachement = additionalAttachmentRepository.findBySalesIdAndUserId(companySales.getId(),userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(listOfAdditionalAttachement);
		companySales.setAdditionalAttachments(listOfAdditionalAttachement);
		return companySales;
	}

	@Override
	public String updateCompanySales(CompanySalesRequest salesRequest) throws Exception {
			CompanyRegistration registration = registrationRepository.findByUserId(salesRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			CompanySales companySales = companySalesRepository.findByUserId(salesRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(companySales);
			companySales.setAdditionalInformation(salesRequest.getAdditionalInformation());
			if(salesRequest.getGeneralisedNdaDocumentUrl()!=null){
				companySales.setNdaFileName(salesRequest.getOrginalFileName());
				companySales.setGeneralisedNdaDocument(salesRequest.getGeneralisedNdaDocumentUrl());
				LOG.info("Supplier Generalised NDA doc updated with URL "+companySales.getGeneralisedNdaDocument());
			}
			String name=registration.getFirstName();
			if(registration.getMiddleName()!=null){
				name=name+" "+registration.getMiddleName();
			}
			if(registration.getLastName()!=null){
				name=name+" "+registration.getLastName();
			}
			companySales.setLastUpdatedBy(name);
			companySales.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
			companySalesRepository.save(companySales);
			if(salesRequest.getFilesUrls()!=null) {
				saveAdditionalAttachment(salesRequest.getUserId(), companySales.getId(), salesRequest.getFilesUrls());
			}
			return "updated successfully";
	}

	@Override
	public String saveCompanyHighlights(CompanyHighlightsRequest highlightsRequest) throws Exception {
			CompanyRegistration registration = registrationRepository.findByUserId(highlightsRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			CompanyHighlights companyHighlights = new CompanyHighlights();
			companyHighlights.setUserId(highlightsRequest.getUserId());;
			companyHighlights.setAwards(highlightsRequest.getAwards());
			companyHighlights.setNewsAndMedia(highlightsRequest.getNewsAndMedia());
			companyHighlights.setTestimonials(highlightsRequest.getTestimonials());
			companyHighlights.setSpecialProgramProjects(highlightsRequest.getSpecialProgramProjects());
			String name=registration.getFirstName();
			if(registration.getMiddleName()!=null){
				name=name+" "+registration.getMiddleName();
			}
			if(registration.getLastName()!=null){
				name=name+" "+registration.getLastName();
			}
			companyHighlights.setLastUpdatedBy(name);
			companyHighlights.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
			companyHighlightsRepository.save(companyHighlights);
			return "saved successfully";
	}

	@Override
	public CompanyHighlights getCompanyHighlights(String userId) throws Exception {
		CompanyHighlights companyHighlights = companyHighlightsRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(companyHighlights);
		return companyHighlights;
	}

	@Override
	public String updateCompanyHighlights(CompanyHighlightsRequest highlightsRequest) throws Exception {
			CompanyRegistration registration = registrationRepository.findByUserId(highlightsRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			CompanyHighlights companyHighlights = companyHighlightsRepository.findByUserId(highlightsRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(companyHighlights);
			companyHighlights.setAwards(highlightsRequest.getAwards());
			companyHighlights.setTestimonials(highlightsRequest.getTestimonials());
			companyHighlights.setSpecialProgramProjects(highlightsRequest.getSpecialProgramProjects());
			companyHighlights.setNewsAndMedia(highlightsRequest.getNewsAndMedia());
			String name=registration.getFirstName();
			if(registration.getMiddleName()!=null){
				name=name+" "+registration.getMiddleName();
			}
			if(registration.getLastName()!=null){
				name=name+" "+registration.getLastName();
			}
			companyHighlights.setLastUpdatedBy(name);
			companyHighlights.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
			companyHighlightsRepository.save(companyHighlights);
			return "updated Successfully";
		
	}

	@Override
	public String saveCompanyFinancials(CompanyFinancialsRequest financialsRequest) throws Exception {
		CompanyRegistration registration = registrationRepository.findByUserId(financialsRequest.getUserId());
		ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			CompanyFinancials financials = new CompanyFinancials();
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
			companyFinancialsRepository.save(financials);
			if(financialsRequest.getFilesUrls()!=null) {
				saveFinancialImage(financials.getUserId(), financials.getId(), financialsRequest.getFilesUrls());

			}
			return "saved successfully";
		}
	private void saveFinancialImage(String userId, Long financialId, String[] files){
		/*if(financialImageRepository.findByFinancialIdAndUserId(financialId,userId)!=null){
			financialImageRepository.deleteAllByFinancialIdAndUserId(financialId,userId);
		}*/
		try {
			for(String upload:files){
				String[] fileName= upload.split("\\^");
				FinancialImageUpload imageUpload=new FinancialImageUpload();
				imageUpload.setFinancialId(financialId);
				imageUpload.setUserId(userId);
				imageUpload.setFileName(fileName[0]);
				imageUpload.setFiles(fileName[1]);
				financialImageRepository.save(imageUpload);
				LOG.info("Company financial image saved with URL "+imageUpload.getFiles());
			}
		}catch (Exception e){
			LOG.info("Supplier financial image cannot be saved "+e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public CompanyFinancials getCompanyFinancials(String userId) throws Exception {
		CompanyFinancials companyFinancials= companyFinancialsRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(companyFinancials);
		List<FinancialImageUpload> allFinImagUpload = financialImageRepository.findByFinancialIdAndUserId(companyFinancials.getId(),userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(allFinImagUpload);
		companyFinancials.setFinancialImages(allFinImagUpload);
		return companyFinancials;
	}

	@Override
	public String updateCompanyfinancials(CompanyFinancialsRequest financialsRequest) throws Exception {
			CompanyRegistration registration = registrationRepository.findByUserId(financialsRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(registration);
			CompanyFinancials financials=companyFinancialsRepository.findByUserId(financialsRequest.getUserId());
			ExceptionUtils.verifyDataNotExistThenThrowException(financials);
			if(financials==null){
				financials= new CompanyFinancials();
				financials.setUserId(financialsRequest.getUserId());
			}
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
			companyFinancialsRepository.save(financials);
			if(financialsRequest.getFilesUrls()!=null) {
				saveFinancialImage(financials.getUserId(), financials.getId(), financialsRequest.getFilesUrls());
			}
			return "updated Successfully";
		
	}

	@Override
	public String removeCompanyVideo(String userId) throws Exception {
			if (basicInfoRepository.findByUserId(userId) != null) {
				BusinessBasicInfo businessBasicInfo = basicInfoRepository.findByUserId(userId);
				businessBasicInfo.setCompanyVideo(null);
				basicInfoRepository.save(businessBasicInfo);
				return "video removed";
			} else if (consumerBusinessBasicInfoRepository.findByUserId(userId) != null) {
				ConsumerBusinessBasicInfo businessBasicInfo = consumerBusinessBasicInfoRepository.findByUserId(userId);
				businessBasicInfo.setCompanyVideo(null);
				consumerBusinessBasicInfoRepository.save(businessBasicInfo);
				return "video removed";
			} else {
				throw new Exception("id not found");
			}
		}

	@Override
	public String deletedUploadedFiles(String userId, Long id, String type) throws Exception {
		if (type.equalsIgnoreCase("ADDITIONAL")) {
			if (companySalesRepository.existsByUserId(userId) || consumerSalesRepository.existsByUserId(userId)) {
				if (additionalAttachmentRepository.existsById(id)) {
					additionalAttachmentRepository.deleteById(id);
					return "Deleted";
				} else {
					throw new SourceablyCustomeException("Id not found", HttpStatus.UNPROCESSABLE_ENTITY);
				}

			}
			throw new Exception("not found");
		} else if (type.equalsIgnoreCase("FINANCIAL")) {
			if (companyFinancialsRepository.existsByUserId(userId) || consumerFinancialsRepository.existsByUserId(userId)) {
				if (financialImageRepository.existsById(id)) {
					financialImageRepository.deleteById(id);
					return "deleted";
				} else {
					throw new SourceablyCustomeException("id not found", HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		} else {
			throw new SourceablyCustomeException("Invalid type", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		throw new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}


