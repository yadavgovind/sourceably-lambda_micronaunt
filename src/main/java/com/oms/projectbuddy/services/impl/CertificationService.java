package com.oms.projectbuddy.services.impl;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.oms.projectbuddy.dto.CyberSecurityGraphDataDto;
import com.oms.projectbuddy.dto.GlobalMarketData;
import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.BusinessCertificates;
import com.oms.projectbuddy.model.CertificationDataFileDetails;
import com.oms.projectbuddy.model.CertificationMaster;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.ITCertificationUserHistory;
import com.oms.projectbuddy.model.ProviderBCertificateData;
import com.oms.projectbuddy.model.ProviderCertificateData;
import com.oms.projectbuddy.model.request.CertificationAttachmentDetails;
import com.oms.projectbuddy.model.request.ITCertificationUserHistoryDto;
import com.oms.projectbuddy.model.request.ProviderCertificateDataDto;
import com.oms.projectbuddy.model.response.CyberScoreDto;
import com.oms.projectbuddy.model.response.ITCertificationDetailsReport;
import com.oms.projectbuddy.repository.BusinessCertificatesRepository;
import com.oms.projectbuddy.repository.CertificationDataFileDetailsRepository;
import com.oms.projectbuddy.repository.CertificationMasterRepository;
import com.oms.projectbuddy.repository.CompanyRepository;
import com.oms.projectbuddy.repository.ITCertificationUserHistoryRepository;
import com.oms.projectbuddy.repository.ProviderBCertificateDataRepository;
import com.oms.projectbuddy.repository.ProviderCertificateDataRepository;
import com.oms.projectbuddy.services.ICertificationService;
import com.oms.projectbuddy.utils.CertificationType;
import com.oms.projectbuddy.utils.Constants;
import com.oms.projectbuddy.utils.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CertificationService implements ICertificationService {

	@Autowired
	private CertificationMasterRepository certificationMasterRepository;

	@Autowired
	private ProviderCertificateDataRepository providerCertificateDataRepository;

	@Autowired
	private CertificationDataFileDetailsRepository certificationDataFileDetailsRepository;

	@Autowired
	private BusinessCertificatesRepository businessCertificatesRepository;

	@Autowired
	private ProviderBCertificateDataRepository providerBCertificateDataRepository;

	@Autowired
	private ITCertificationUserHistoryRepository itCertificationUserHistoryRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public String bulkUploadCertifications(MultipartFile file) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 1) {
				XSSFRow row = worksheet.getRow(index);
				try {
					DataFormatter dataFormatter = new DataFormatter();
					String levelName1 = "";
					String levelDescription1 = "";
					String levelName2 = "";
					String levelDescription2 = "";
					String levelName3 = "";
					String levelDescription3 = "";
					String y = "";
					String n = "";
					String na = "";
					String evidence = "";
					String guidence = "";
					String id = row.getCell(0).getRawValue();
					if (row.getCell(0) != null) {
						levelName1 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null);
					}
					if (row.getCell(1) != null) {
						levelDescription1 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(1)))
								.orElse(null);
					}
					if (row.getCell(2) != null) {
						levelName2 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(2))).orElse(null);
					}
					if (row.getCell(3) != null) {
						levelDescription2 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(3)))
								.orElse(null);
					}
					if (row.getCell(4) != null) {
						levelName3 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(4))).orElse(null);
					}
					if (row.getCell(5) != null) {
						y = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(5))).orElse(null);
					}
					if (row.getCell(6) != null) {
						n = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(6))).orElse(null);
					}
					if (row.getCell(7) != null) {
						na = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(7))).orElse(null);
					}
					if (row.getCell(8) != null) {
						levelDescription3 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(8)))
								.orElse(null);
					}
					if (row.getCell(9) != null) {
						evidence = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(9))).orElse(null);
					}
					if (row.getCell(10) != null) {
						guidence = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(10))).orElse(null);
					}
					if (!levelName1.equals("")) {
						saveCertificationMasterDetails(levelName1, levelDescription1, "L1", guidence, evidence, y, n,
								na, false, levelName1);
					}
					if (!levelName2.equals("")) {
						saveCertificationMasterDetails(levelName2, levelDescription2, "L2", guidence, evidence, y, n,
								na, true, levelName1);
					}
					if (!levelName3.equals("")) {
						saveCertificationMasterDetails(levelName3, levelDescription3, "L3", guidence, evidence, y, n,
								na, true, levelName2);
					}
				} catch (Exception e) {
					throw new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.NO_CONTENT);
					// e.printStackTrace();
				}
			}
		}
		return "Upload Success !";
	}

	@Override
	public Object getParentCertificationByCertificateType(CertificationType certificationType)
			throws SourceablyCustomeException {
		List<CertificationMaster> allCertificationMaster = certificationMasterRepository
				.findByCertificationTypeAndIsSubCategory(certificationType, false);
		ExceptionUtils.verifyDataNotExistThenThrowException(allCertificationMaster);
		
		return allCertificationMaster;
	}

	private List<CertificationMaster> getParentCertificationByCertificateTypeOnly(CertificationType certificationType) {
		List<CertificationMaster> allCertificationMaster = certificationMasterRepository
				.findByCertificationType(certificationType);
		ExceptionUtils.verifyDataNotExistThenThrowException(allCertificationMaster);
		return allCertificationMaster;
	}

	@Override
	public Object getCertificationByParentAndLevel(Long parentId, String level) {
		CertificationMaster certificationMaster = getCertificationDetailsById(parentId);
		return certificationMasterRepository.findByParentIdAndAndLevel(certificationMaster.getCertificationId(), level);
	}

	@Override
	public Object getCertificationByParentAndLevelByUser(Long parentId, String level, String userId) throws SourceablyCustomeException{
		CertificationMaster certificationMaster = getCertificationDetailsById(parentId);
		List<CertificationMaster> certificationMasters = certificationMasterRepository
				.findByParentIdAndAndLevel(certificationMaster.getCertificationId(), level);
		ExceptionUtils.verifyDataNotExistThenThrowException(certificationMasters);
		
		for (CertificationMaster master : certificationMasters) {
			ProviderCertificateData providerCertificateData = providerCertificateDataRepository
					.findByCertificateIdAndUserId(master.getCertificationId(), userId);
			if (providerCertificateData != null) {
				master.setScoreByUser(providerCertificateData.getTotalScore());
				master.setUpdatedEpochTimeByUser(providerCertificateData.getLastUpdatedEpochTime()
						.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
				master.setUpdatedBy(providerCertificateData.getUpdatedBy());
				master.setUserName(itCertificationUserHistoryRepository.getUserNameById(userId));
			}
		}
		return certificationMasters;
	}

	@Override
	public Double getProfileStrengthByUserID(String userId) {
		Double score = providerCertificateDataRepository.getScoreByUser(userId);
		if (score != null && score > 0.0) {
			return (score / 760d) * 100;
		}
		return 0.0;

	}

	@Override
	public Object getItCertificationDetailsForUserReport(String userId) throws SourceablyCustomeException {
		List<ITCertificationDetailsReport> itCertificationDetailsReports = new ArrayList<>();
		List<CertificationMaster> certificationMasters = (List<CertificationMaster>) getParentCertificationByCertificateTypeOnly(
				CertificationType.IT_AND_SECURITY);

		List<ProviderCertificateData> certificateData = providerCertificateDataRepository.findByUserId(userId);
		ExceptionUtils.verifyDataNotExistThenThrowException(certificateData);

		for (CertificationMaster certificationMaster : certificationMasters) {
			List<CertificationMaster> childCertificationMasterL2 = certificationMasters.stream()
					.filter(certificateMaster -> certificateMaster.getParentId() != null
							&& certificateMaster.getParentId() == certificationMaster.getCertificationId()
							&& certificateMaster.getLevel().equals("L2"))
					.collect(Collectors.toList());
			Double socre = 0.0;
			Long attemptCountL3 = 0l;
			for (CertificationMaster masterL2 : childCertificationMasterL2) {
				List<CertificationMaster> childCertificationMasterL3 = certificationMasters.stream()
						.filter(certificateMaster -> certificateMaster.getParentId() != null
								&& certificateMaster.getParentId() == masterL2.getCertificationId()
								&& certificateMaster.getLevel().equals("L3"))
						.collect(Collectors.toList());

				for (CertificationMaster master : childCertificationMasterL3) {
					attemptCountL3 = certificateData.stream()
							.filter(predicate -> predicate.getCertificateId() == master.getParentId()
									&& master.getLevel().equals("L3"))
							.count();
					OptionalDouble max = certificateData.stream()
							.filter(predicate -> predicate.getCertificateId() == master.getCertificationId())
							.mapToDouble(certificateDataValue -> certificateDataValue.getCommentScore()
									+ certificateDataValue.getEvidenceFileScore()
									+ certificateDataValue.getProviderOptionScore())
							.max();

					socre = socre + (max.isPresent() ? max.getAsDouble() : 0.0d);
					// Optional.ofNullable(providerCertificateDataRepository.getScoreByUserAndCertificateID(master.getCertificationId(),
					// userId)).orElse(0.0);
				}
				socre = socre / childCertificationMasterL3.size();
				Long attemptCountL2 = certificateData.stream()
						.filter(predicate -> predicate.getCertificateId() == masterL2.getParentId()
								&& certificationMaster.getLevel().equals("L2"))
						.count();

				itCertificationDetailsReports.add(new ITCertificationDetailsReport(masterL2.getCertificationName(),
						masterL2.getCertificationDescription(), socre * 10, masterL2.getControlStatement(),
						masterL2.getPartiallyComplited(), masterL2.getFullyComplited(), masterL2.getNoDataAvaliable(),
						childCertificationMasterL2.size(), childCertificationMasterL3.size(), attemptCountL2,
						attemptCountL3));
			}
		}
		return itCertificationDetailsReports;
	}

	private void saveHistoryDetails(boolean isOptionHisotryCreated, boolean isattchmentHisotryCreated,
			boolean isCommentHisotryCreated, String attchmentString,
			ProviderCertificateDataDto providerCertificateDataDto, Long certificateDataId) {
		if (isattchmentHisotryCreated && isOptionHisotryCreated && isCommentHisotryCreated) {
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), certificateDataId, "ATTACHMENT,OPTION,COMMENT",
					providerCertificateDataDto.getUserId(), "ATTACHMENT,OPTION,COMMENT Added the Certificate Details",
					Instant.now().toEpochMilli()));
		}
		if (!isattchmentHisotryCreated && isOptionHisotryCreated && isCommentHisotryCreated) {
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), certificateDataId, "OPTION,COMMENT",
					providerCertificateDataDto.getUserId(), "OPTION,COMMENT Added the Certificate Details",
					Instant.now().toEpochMilli()));
		}
		if (isattchmentHisotryCreated && !isOptionHisotryCreated && isCommentHisotryCreated) {
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), certificateDataId, "ATTACHMENT,COMMENT",
					providerCertificateDataDto.getUserId(), "ATTACHMENT,COMMENT Added the Certificate Details",
					Instant.now().toEpochMilli()));
		}
		if (isattchmentHisotryCreated && isOptionHisotryCreated && !isCommentHisotryCreated) {
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), certificateDataId, "ATTACHMENT,OPTION",
					providerCertificateDataDto.getUserId(), "ATTACHMENT,OPTION Added the Certificate Details",
					Instant.now().toEpochMilli()));
		}
		if (!isattchmentHisotryCreated && !isOptionHisotryCreated && isCommentHisotryCreated) {
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), certificateDataId, "COMMENT",
					providerCertificateDataDto.getUserId(),
					"COMMENT Added the Certificate Details ->" + providerCertificateDataDto.getComment(),
					Instant.now().toEpochMilli()));
		}
		if (!isattchmentHisotryCreated && isOptionHisotryCreated && !isCommentHisotryCreated) {
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), certificateDataId, "OPTION",
					providerCertificateDataDto.getUserId(),
					"OPTION Added the Certificate Details ->" + providerCertificateDataDto.getProviderOptions(),
					Instant.now().toEpochMilli()));
		}
		if (isattchmentHisotryCreated && !isOptionHisotryCreated && !isCommentHisotryCreated) {
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), certificateDataId, "Attachment",
					providerCertificateDataDto.getUserId(),
					"Attachment Added the Certificate Details ->" + attchmentString, Instant.now().toEpochMilli()));
		}
	}

	private String saveCertificationHistoryDetails(ITCertificationUserHistoryDto itCertificationUserHistoryDto) {
		ITCertificationUserHistory itCertificationUserHistory = new ITCertificationUserHistory();
		BeanUtils.copyProperties(itCertificationUserHistoryDto, itCertificationUserHistory);
		itCertificationUserHistoryRepository.save(itCertificationUserHistory);
		return "Success !";
	}

	@Override
	public String saveProviderCertificateData(ProviderCertificateDataDto providerCertificateDataDto) {
		ProviderCertificateData providerCertificateData = providerCertificateDataRepository
				.findByCertificateIdAndUserId(providerCertificateDataDto.getCertificateId(),
						providerCertificateDataDto.getUserId());
		if (providerCertificateData != null) {

			boolean isattchmentHisotryCreated = false;
			boolean isCommentHisotryCreated = false;
			boolean isOptionHisotryCreated = false;
			String attchmentString = "";
			if (!providerCertificateDataDto.getProviderOptions().equals(providerCertificateData.getProviderOptions())) {
				isOptionHisotryCreated = true;
			}
			if (!providerCertificateDataDto.getComment().equals(providerCertificateData.getComment())) {
				isCommentHisotryCreated = true;
			}
			for (CertificationAttachmentDetails attachment : providerCertificateDataDto.getAttachments()) {
				if (!certificationDataFileDetailsRepository.existsByFilePath(attachment.getFilePath())) {
					isattchmentHisotryCreated = true;
					attchmentString = attachment.getServiceType();
				}
			}
			saveHistoryDetails(isOptionHisotryCreated, isattchmentHisotryCreated, isCommentHisotryCreated,
					attchmentString, providerCertificateDataDto, providerCertificateData.getProviderCertificateId());
		} else {
			providerCertificateData = new ProviderCertificateData();

			providerCertificateDataRepository.save(providerCertificateData);
			saveCertificationHistoryDetails(new ITCertificationUserHistoryDto(providerCertificateDataDto.getUserId(),
					providerCertificateDataDto.getCertificateId(), providerCertificateData.getProviderCertificateId(),
					"Initial Add", providerCertificateDataDto.getUserId(), "Initially Added the Certificate Details",
					Instant.now().toEpochMilli()));
		}
		providerCertificateData.setCertificateId(providerCertificateDataDto.getCertificateId());
		providerCertificateData.setUserId(providerCertificateDataDto.getUserId());
		providerCertificateData.setCertificationType(providerCertificateDataDto.getCertificationType());
		providerCertificateData.setProviderOptions(providerCertificateDataDto.getProviderOptions());
		providerCertificateData.setComment(providerCertificateDataDto.getComment());
		providerCertificateData.setCommentScore(StringUtils.isEmpty(providerCertificateDataDto.getComment()) ? 0 : 2);
		providerCertificateData
				.setProviderOptionScore(providerCertificateDataDto.getProviderOptions().equalsIgnoreCase("Y") ? 5 : 0);
		providerCertificateData.setCreatedBy(providerCertificateDataDto.getUserId());
		providerCertificateData.setCreatedEpochTime(LocalDate.now());
		providerCertificateData.setUpdatedBy(providerCertificateDataDto.getUserId());
		providerCertificateData.setLastUpdatedEpochTime(LocalDate.now());
		providerCertificateData.setStatus(true);
		if (providerCertificateDataDto.getAttachments() == null) {
			providerCertificateData.setEvidenceFileScore(
					Optional.ofNullable(providerCertificateData.getEvidenceFileScore()).orElse(0));
		} else {
			providerCertificateData.setEvidenceFileScore(providerCertificateDataDto.getAttachments() == null
					|| providerCertificateDataDto.getAttachments().isEmpty() ? 0 : 3);
		}
		providerCertificateDataRepository.save(providerCertificateData);
		if (providerCertificateDataDto.getAttachments() != null) {
			ProviderCertificateData finalProviderCertificateData = providerCertificateData;
			providerCertificateDataDto.getAttachments().forEach(certificationAttachmentDetails -> {
				saveAttachmentDetails(certificationAttachmentDetails, finalProviderCertificateData);
			});
		}
		return "Success !";
	}

	@Override
	public Object getCetificationDetailsByUserIdAndCertificationID(Long certificateID, String userID)
			throws SourceablyCustomeException {
		ProviderCertificateData providerCertificateData = providerCertificateDataRepository
				.findByCertificateIdAndUserId(certificateID, userID);
		
		ExceptionUtils.verifyDataNotExistThenThrowException(providerCertificateData);
		
		providerCertificateData.setAttachmentDetails(certificationDataFileDetailsRepository
				.findByProviderCertificationDataId(providerCertificateData.getProviderCertificateId()));
		List<ITCertificationUserHistory> history = itCertificationUserHistoryRepository
				.findByCertificateDataIdOrderByHistoryCreatedEpochTimeDesc(providerCertificateData.getProviderCertificateId());
		
		ExceptionUtils.verifyDataNotExistThenThrowException(history);
		
		for (ITCertificationUserHistory itCertificationUserHistory : history) {
			itCertificationUserHistory.setUserId(
					itCertificationUserHistoryRepository.getUserNameById(itCertificationUserHistory.getUserId()));
		}
		providerCertificateData.setHistory(history);
		providerCertificateData.setUserName(itCertificationUserHistoryRepository.getUserNameById(userID));
		return providerCertificateData;
	}

	@Override
	public Object getCetificationDetailsByUserId(String userID) throws Exception {
		List<ProviderCertificateData> providerCertificateData = providerCertificateDataRepository.findByUserId(userID);

		ExceptionUtils.verifyDataNotExistThenThrowException(providerCertificateData);

		for (ProviderCertificateData providerCertificateDatum : providerCertificateData) {
			providerCertificateDatum.setAttachmentDetails(certificationDataFileDetailsRepository
					.findByProviderCertificationDataId(providerCertificateDatum.getProviderCertificateId()));
			providerCertificateDatum.setCertificationName(
					certificationMasterRepository.getCertifcationNameById(providerCertificateDatum.getCertificateId()));
			providerCertificateDatum.setCertificationNameDetails(certificationMasterRepository
					.getCertifcationNameDetailsById(providerCertificateDatum.getCertificateId()));
		}
		return providerCertificateData;
	}

	@Override
	public Object uploadBusinessCertificatedDetails(MultipartFile file) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 6) {
				XSSFRow row = worksheet.getRow(index);
				try {
					DataFormatter dataFormatter = new DataFormatter();
					String country = "";
					String level1 = "";
					String level2 = "";

					String id = row.getCell(0).getRawValue();
					if (row.getCell(0) != null) {
						country = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null);
					}
					if (row.getCell(1) != null) {
						level1 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(1))).orElse(null);
					}
					if (row.getCell(2) != null) {
						level2 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(2))).orElse(null);
					}

					if (level1 != null || !level1.equals("")) {
						saveBusinessCertificate(level1, level1, country, false);
					}
					if (level2 != null || !level2.equals("")) {
						saveBusinessCertificate(level2, level1, country, true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "Upload Success !";
	}

	@Override
	public Object getParentBUsinessCertificatesDetails() throws SourceablyCustomeException {
		List<BusinessCertificates> businessCertificates = businessCertificatesRepository
				.findByIsSubCategoryOrderByBusinessCertificateIdAsc(false);
		ExceptionUtils.verifyDataNotExistThenThrowException(businessCertificates);
		return businessCertificates;
	}

	@Override
	public Object getChildBUsinessCertificatesDetails(Long parentId) throws SourceablyCustomeException {
		BusinessCertificates businessCertificates = businessCertificatesRepository.findById(parentId).orElse(null);
		ExceptionUtils.verifyDataNotExistThenThrowException(businessCertificates);
		return businessCertificatesRepository.findByIsSubCategoryAndParentOrderByBusinessCertificateIdAsc(true,
				businessCertificates);

	}
// Bussiness Certification logic from here

	@Override
	public Object getChildBusinessCertificatesByUser(Long parentId, String userId) throws SourceablyCustomeException {

		BusinessCertificates businessCertificates = businessCertificatesRepository.findById(parentId)
				.orElseThrow(() -> new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.NO_CONTENT));

		List<BusinessCertificates> businessCertificates1 = businessCertificatesRepository
				.findByIsSubCategoryAndParentOrderByBusinessCertificateIdAsc(true, businessCertificates);
		ExceptionUtils.verifyDataNotExistThenThrowException(businessCertificates1);
		
		for (BusinessCertificates certificates : businessCertificates1) {
			if (providerBCertificateDataRepository.existsByUserIdAndCertificateId(userId,
					certificates.getBusinessCertificateId())) {
				certificates.setProviderDataDetails(providerBCertificateDataRepository
						.findByUserIdAndCertificateId(userId, certificates.getBusinessCertificateId()));
			} else {
				certificates.setProviderDataDetails(new ProviderBCertificateData());
			}
		}
		return businessCertificates1;
	}

	private void saveBusinessCertificate(String certificateName, String parentName, String country,
			boolean isSubCategory) {
		if (!businessCertificatesRepository.existsByCertificateName(certificateName)) {
			BusinessCertificates businessCertificates = new BusinessCertificates();
			businessCertificates.setCertificateName(certificateName);
			businessCertificates.setStatus(true);
			businessCertificates.setIsSubCategory(isSubCategory);
			businessCertificates.setCountry(country);
			businessCertificates.setIsDeleted(false);
			if (isSubCategory) {
				businessCertificates.setParent(businessCertificatesRepository.findByCertificateName(parentName));
				businessCertificatesRepository.save(businessCertificates);
			} else {
				businessCertificatesRepository.save(businessCertificates);
				businessCertificates.setParent(businessCertificates);
				businessCertificatesRepository.save(businessCertificates);
			}
		}
	}

	private void saveAttachmentDetails(CertificationAttachmentDetails certificationAttachmentDetails,
			ProviderCertificateData providerCertificateData) {
		CertificationDataFileDetails certificationDataFileDetails = new CertificationDataFileDetails();
		certificationDataFileDetails.setCertificationType(providerCertificateData.getCertificationType());
		certificationDataFileDetails.setCertificateId(providerCertificateData.getCertificateId());
		certificationDataFileDetails.setProviderCertificationDataId(providerCertificateData.getProviderCertificateId());
		certificationDataFileDetails.setServiceType(certificationAttachmentDetails.getServiceType());
		certificationDataFileDetails.setFilePath(certificationAttachmentDetails.getFilePath());
		certificationDataFileDetails.setAccessBy(certificationAttachmentDetails.getAccessBy());
		certificationDataFileDetailsRepository.save(certificationDataFileDetails);
	}

	private CertificationMaster getCertificationDetailsById(Long parentId) {
		return certificationMasterRepository.findById(parentId)
				.orElseThrow(() -> new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.NO_CONTENT));
	}

	private void saveCertificationMasterDetails(String levelName, String levelDescription, String level,
			String guidence, String evidence, String y, String n, String na, Boolean isSubCategory,
			String parentLevelName) {
		CertificationMaster certificationMaster = new CertificationMaster();
		if (!certificationMasterRepository.existsByCertificationName(levelName)) {
			certificationMaster.setLevel(level);
			certificationMaster.setCertificateEvidenceDescription(evidence);
			certificationMaster.setCertificationGuidenceDescription(guidence);
			certificationMaster.setCertificationName(levelName);
			certificationMaster.setCertificationDescription(levelDescription);
			certificationMaster.setIsDeleted(false);
			certificationMaster.setIsSubCategory(isSubCategory);
			certificationMaster.setCertificationType(CertificationType.IT_AND_SECURITY);
			certificationMaster.setCreatedBy("Bulk Upload");
			certificationMaster.setCreatedEpochTime(Instant.now().toEpochMilli());
			certificationMaster.setLastUpdatedEpochTime(Instant.now().toEpochMilli());
			certificationMaster.setLastUpdatedBy("Bulk Upload");
			certificationMaster.setYesDescription(y);
			certificationMaster.setNoDescription(n);
			certificationMaster.setNaDescription(na);
			certificationMaster.setStatus(true);
			if (level.equals("L1")) {
				certificationMasterRepository.save(certificationMaster);
				certificationMaster.setParentId(certificationMaster.getCertificationId());
				certificationMasterRepository.save(certificationMaster);
			} else {
				certificationMaster.setParentId(
						certificationMasterRepository.findByCertificationName(parentLevelName).getCertificationId());
				certificationMasterRepository.save(certificationMaster);
			}
		}
	}

	public List<CyberScoreDto> getPercentage(String lavel, String userId) {
		List<CertificationMaster> certificationMasters = (List<CertificationMaster>) getParentCertificationByCertificateTypeOnly(
				CertificationType.IT_AND_SECURITY);
		Map<String, List<ProviderCertificateData>> certificateData = new HashMap<>();
		if (userId == null) {
			certificateData = providerCertificateDataRepository.findAll().stream()
					.collect(Collectors.groupingBy(ProviderCertificateData::getUserId));
		} else {
			certificateData.put(userId, providerCertificateDataRepository.findByUserId(userId));
		}

		return calculateL1Percentage(lavel, certificationMasters, certificateData);

	}

//only for supplier Data as we dont have concept of the Certification for the Consumer so we dont have value there
	@Override
	public List<GlobalMarketData> getGlobalScoreBasedOnSupplier(LocalDate startDate, LocalDate endDate) {
		List<ProviderCertificateData> cyberScoreForAllSupplier = providerCertificateDataRepository
				.findByCreatedEpochTimeBetween(startDate, endDate);
		ExceptionUtils.verifyDataNotExistThenThrowException(cyberScoreForAllSupplier,"Provider certificates not found");
		CompanyRegistration registration = getRegisterCompanyByLoggedInUser();
		ExceptionUtils.verifyDataNotExistThenThrowException(registration);
		Map<String, List<ProviderCertificateData>> monthCertScore = cyberScoreForAllSupplier.stream()
				.collect(Collectors.groupingBy(certificateData -> certificateData.getCreatedEpochTime().getYear() + "-"
						+ certificateData.getCreatedEpochTime().getMonth().name()));
		List<GlobalMarketData> list = new ArrayList<>();
		for (Map.Entry<String, List<ProviderCertificateData>> monthScore : monthCertScore.entrySet()) {
			Long countOfSupplierCert = monthScore.getValue().stream()
					.filter(predicate -> predicate.getUserId().equalsIgnoreCase(registration.getUserId()))
					.map(ProviderCertificateData::getCertificateId).count();
			Long countOfAllCert = monthScore.getValue().stream().map(ProviderCertificateData::getCertificateId).count();
			Double supplierTotalScore = monthScore.getValue().stream()
					.filter(predicate -> predicate.getUserId().equalsIgnoreCase(registration.getUserId()))
					.mapToDouble(certificateData -> certificateData.getCommentScore()
							+ certificateData.getEvidenceFileScore() + certificateData.getProviderOptionScore())
					.sum();
			Double totalScore = monthScore.getValue().stream()
					.mapToDouble(certificateData -> certificateData.getCommentScore()
							+ certificateData.getEvidenceFileScore() + certificateData.getProviderOptionScore())
					.sum();
			list.add(GlobalMarketData.builder().month(monthScore.getKey())
					.globalMarket(countOfAllCert > 0 ? Double.valueOf(totalScore) / Double.valueOf(countOfAllCert) : 0)
					.aquirecreations(countOfSupplierCert > 0
							? Double.valueOf(supplierTotalScore) / Double.valueOf(countOfSupplierCert)
							: 0)
					.build());
		}
		return list;
	}

	@Override
	public List<CyberSecurityGraphDataDto.Response> globalCyberGraphData(LocalDate startDate, LocalDate endDate,
			String specificCategory, String supplierId) {

		Map<String, List<ProviderCertificateData>> certificateData = providerCertificateDataRepository
				.findByCreatedEpochTimeBetween(startDate, endDate).stream()
				.collect(Collectors.groupingBy(ProviderCertificateData::getUserId));
		List<CertificationMaster> certificationMasters = (List<CertificationMaster>) getParentCertificationByCertificateTypeOnly(
				CertificationType.IT_AND_SECURITY);
		List<CyberScoreDto> cyberScoreList = calculateL1Percentage("L2", certificationMasters, certificateData);
		Map<String, List<CyberScoreDto>> globalScoreDtosMap = cyberScoreList.stream()
				.collect(Collectors.groupingBy(CyberScoreDto::getCertificationTitle));
		// .sorted(Comparator.comparing(CyberScoreDto::getScore)).collect(Collectors.toList());

		Map<String, List<CyberScoreDto>> certificateMap = cyberScoreList.stream()
				.collect(Collectors.groupingBy(CyberScoreDto::getCertificationTitle));

		// Map<Long, List<CyberScoreDto>> map =
		// cyberScoreList.stream().collect(Collectors.groupingBy(CyberScoreDto::getCertificateId));
		List<CyberSecurityGraphDataDto.Response> responses = new ArrayList<>();
		for (Map.Entry<String, List<CyberScoreDto>> entry : certificateMap.entrySet()) {
			List<CyberScoreDto> specificScoreDtos = entry.getValue().stream()
					.sorted(Comparator.comparing(CyberScoreDto::getScore)).collect(Collectors.toList());
			List<CyberScoreDto> globalScoreDtos = globalScoreDtosMap.get(entry.getKey()).stream()
					.sorted(Comparator.comparing(CyberScoreDto::getScore)).collect(Collectors.toList());

			CyberScoreDto lowGlobalScore = globalScoreDtos.get(0);
			CyberScoreDto highGlobalScore = globalScoreDtos.get(globalScoreDtos.size() - 1);

			CyberScoreDto lowSpecificScore = specificScoreDtos.get(0);
			CyberScoreDto highSpecificScore = specificScoreDtos.get(specificScoreDtos.size() - 1);

			CyberSecurityGraphDataDto.Response response = new CyberSecurityGraphDataDto.Response();
			Optional<CyberScoreDto> first = entry.getValue().stream()
					.filter(predicate -> predicate.getSupplierId().equalsIgnoreCase(supplierId)).findFirst();
			if (first.isPresent()) {
				response.setSupplierGraph(new CyberSecurityGraphDataDto.GraphData(0, first.get().getScore()));

			}

			response.setGlobalGraph(new CyberSecurityGraphDataDto.GraphData(
					(highGlobalScore.getScore() - lowGlobalScore.getScore()) * 0.25,
					(highGlobalScore.getScore() - lowGlobalScore.getScore()) * 0.75));
			response.setSpecificGrapgh(new CyberSecurityGraphDataDto.GraphData(
					(highSpecificScore.getScore() - lowSpecificScore.getScore()) * 0.25,
					(highSpecificScore.getScore() - lowSpecificScore.getScore()) * 0.75));

			response.setCertificationId(highSpecificScore.getCertificateId());
			response.setCertificate(highSpecificScore.getCertificateName());
			response.setCertificationName(highSpecificScore.getCertificationTitle());
			responses.add(response);

		}
		Collections.sort(responses, Comparator.comparingLong(CyberSecurityGraphDataDto.Response::getCertificationId));
		return responses;
	}

	public List<CyberScoreDto> calculateL1Percentage(String lavel, List<CertificationMaster> certificationMasters,
			Map<String, List<ProviderCertificateData>> providerCertificationList) {

		Map<Long, List<CertificationMaster>> l1L3Map = new HashMap<>();

		Map<Long, List<CertificationMaster>> l1L2Map = certificationMasters.stream()
				.filter(predicate -> predicate.getParentId() != null && predicate.getLevel().equals(lavel))
				.collect(Collectors.groupingBy(CertificationMaster::getParentId));
		if (lavel.equals("L2")) {
			l1L2Map.entrySet().forEach(keyValue -> {
				List<CertificationMaster> L3List = new ArrayList<>();
				for (CertificationMaster certificationMaster : keyValue.getValue()) {
					log.info("parentId is " + certificationMaster.getParentId() + " certId"
							+ certificationMaster.getCertificationId());
					L3List.addAll(certificationMasters.stream()
							.filter(predicate -> predicate.getParentId() != null
									&& predicate.getParentId() == certificationMaster.getCertificationId())
							.collect(Collectors.toList()));
				}
				l1L3Map.put(keyValue.getKey(), L3List);
			});

			return mapCalculation(l1L3Map, certificationMasters, providerCertificationList);
		} else if (lavel.equals("L3")) {
			return mapCalculation(l1L2Map, certificationMasters, providerCertificationList);
		} else if (lavel.equals("L1")) {

		}
		return null;
	}

	private List<CyberScoreDto> mapCalculation(Map<Long, List<CertificationMaster>> map,
			List<CertificationMaster> certificationMasters,
			Map<String, List<ProviderCertificateData>> providerCertificationMap) {
		List<CyberScoreDto> cyberScoreDtos = new ArrayList<>();
		map.entrySet().forEach(keyValue -> {
			Optional<CertificationMaster> first = certificationMasters.stream()
					.filter(certificationMaster -> certificationMaster.getCertificationId() == keyValue.getKey())
					.findFirst();
			if (first.isPresent()) {
				List<Long> ids;
				ids = keyValue.getValue().stream().map(CertificationMaster::getCertificationId)
						.collect(Collectors.toList());
				log.info("KeyValue total size is {}  and ids {}", keyValue.getValue().size(), ids);
				providerCertificationMap.entrySet().forEach(certificationKeyValue -> {
					Integer totalScoreEarn = certificationKeyValue.getValue().stream()
							.filter(predicate -> ids.contains(predicate.getCertificateId()))
							.mapToInt(certiicateData -> certiicateData.getTotalScore()).sum();
					CertificationMaster certificationMaster = first.get();
					log.info("Total score of id {} and name is {}  and score value {}",
							certificationMaster.getCertificationId(), certificationMaster.getCertificationName(),
							totalScoreEarn);
					long totalAttemptCert = certificationKeyValue.getValue().stream().filter(
							predicate -> ids.contains(predicate.getCertificateId()) && predicate.getTotalScore() > 0)
							.count();
					Double percentage = ids.size() > 0
							? Double.valueOf(totalAttemptCert) / Double.valueOf(ids.size()) * 100
							: 0;
					cyberScoreDtos.add(CyberScoreDto.builder().certificateId(certificationMaster.getCertificationId())
							.certificateName(certificationMaster.getCertificationName())
							.certificationTitle(certificationMaster.getCertificationDescription()).totalCert(ids.size())
							.supplierId(certificationKeyValue.getKey())
							.score(percentage * (Double.valueOf(totalScoreEarn) / ids.size()) / 100)
							.averageScore(Double.valueOf(totalScoreEarn) / ids.size())
							.totalAttemptCert(totalAttemptCert).certificatePercentage(percentage).build());
				});
			}
		});
		return cyberScoreDtos;
	}

	private CompanyRegistration getRegisterCompanyByLoggedInUser() {
		String loginUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		return companyRepository.findByEmail(loginUserName.toLowerCase());
	}

}
