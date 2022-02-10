package com.oms.projectbuddy.services.impl;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.core.util.StringUtils;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.context.ServerRequestContext;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import com.oms.projectbuddy.dto.CountryRevenueDto;
import com.oms.projectbuddy.dto.DashBoardDto;
import com.oms.projectbuddy.dto.GlobalBussinessGraphData;
import com.oms.projectbuddy.dto.GlobalSearchFilter;
import com.oms.projectbuddy.dto.InternalBudgetProposalStatusDto;
import com.oms.projectbuddy.dto.PBcertificateScorePercentage;
import com.oms.projectbuddy.dto.ProjectMilestoneListResponse;
import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.BCertificatePercentage;
import com.oms.projectbuddy.model.BusinessBasicInfo;
import com.oms.projectbuddy.model.BusinessCertificates;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.ConsumerBusinessBasicInfo;
import com.oms.projectbuddy.model.ConsumerSales;
import com.oms.projectbuddy.model.ProjectBidDocuments;
import com.oms.projectbuddy.model.ProjectBidPost;
import com.oms.projectbuddy.model.ProjectCreation;
import com.oms.projectbuddy.model.ProjectDocumentUpload;
import com.oms.projectbuddy.model.ProjectInviteEmails;
import com.oms.projectbuddy.model.ProjectInviteList;
import com.oms.projectbuddy.model.ProjectMileStoneList;
import com.oms.projectbuddy.model.ProviderBCertificateData;
import com.oms.projectbuddy.model.SoftwareEvaluation;
import com.oms.projectbuddy.model.request.ProjectBidDocumentUploadRequest;
import com.oms.projectbuddy.model.request.ProjectBidPostRequest;
import com.oms.projectbuddy.model.request.ProjectCreationRequest;
import com.oms.projectbuddy.model.request.ProjectDocumentUploadRequest;
import com.oms.projectbuddy.model.request.ProjectInviteListRequest;
import com.oms.projectbuddy.model.request.ProviderNdaDocRequest;
import com.oms.projectbuddy.model.response.CyberScoreDto;
import com.oms.projectbuddy.model.response.PageDto;
import com.oms.projectbuddy.model.response.SupplierProjectBidResponse;
import com.oms.projectbuddy.repository.BusinessBasicInfoRepository;
import com.oms.projectbuddy.repository.BusinessCertificatesRepository;
import com.oms.projectbuddy.repository.CompanyLogoRepository;
import com.oms.projectbuddy.repository.CompanyRepository;
import com.oms.projectbuddy.repository.ConsumerBusinessBasicInfoRepository;
import com.oms.projectbuddy.repository.ConsumerSalesRepository;
import com.oms.projectbuddy.repository.ProjectBidDocumentsRepository;
import com.oms.projectbuddy.repository.ProjectBidPostRepository;
import com.oms.projectbuddy.repository.ProjectCreationRepository;
import com.oms.projectbuddy.repository.ProjectDocumentUploadRepository;
import com.oms.projectbuddy.repository.ProjectInviteEmailsRepository;
import com.oms.projectbuddy.repository.ProjectInviteListRepository;
import com.oms.projectbuddy.repository.ProjectMilestoneRepository;
import com.oms.projectbuddy.repository.ProviderBCertificateDataRepository;
import com.oms.projectbuddy.repository.SkillMatrixCategoryRepository;
import com.oms.projectbuddy.repository.SoftwareEvaluationRepository;
import com.oms.projectbuddy.services.IProjectCreationService;
import com.oms.projectbuddy.utility.EmailTemplates;
import com.oms.projectbuddy.utility.SmsEmailIntegration;
import com.oms.projectbuddy.utils.Constants;
import com.oms.projectbuddy.utils.DateTimeUtil;
import com.oms.projectbuddy.utils.ExceptionUtils;
import com.oms.projectbuddy.utils.NdaStatus;
import com.oms.projectbuddy.utils.ProjectStatus;
import com.oms.projectbuddy.utils.StatusEnum;

@Singleton
public class ProjectCreationService implements IProjectCreationService {

    private static final String GRANTED_PROJECTS = "Granted Projects";

    private static final String PUBLISHED = "Published";

    private static final String PENDING_PROJECTS = "Pending Projects";

    private static final String REJECTED = "Rejected";

    private static final String IN_PROGRESS = "In progress";

    private static final String TOTAL_PROJECTS = "TotalProjects";

    private static final String SUPPLIER_TYPE = "supplier";

    private static final String CUSTOMER_TYPE = "customer";

    @Inject
    private ProjectCreationRepository projectCreationRepository;
    @Inject
    private CompanyRepository companyRepository;
    @Inject
    private ProjectInviteListRepository projectInviteListRepository;
    //    @Inject
//    private FileOperation fileOperations;
    @Inject
    private ProjectDocumentUploadRepository projectDocumentUploadRepository;
    @Inject
    private ProjectInviteEmailsRepository projectInviteEmailsRepository;
    @Inject
    private ProjectMilestoneRepository projectMilestoneRepository;
    @Inject
    private ProjectBidPostRepository projectBidPostRepository;
    @Inject
    private ProjectBidDocumentsRepository projectBidDocumentsRepository;
    @Inject
    private ConsumerSalesRepository consumerSalesRepository;
    @Inject
    private SmsEmailIntegration smsEmailIntegration;
    @Inject
    private SkillMatrixCategoryRepository skillMatrixCategoryRepository;
    @Inject
    private ConsumerBusinessBasicInfoRepository consumerBusinessBasicInfoRepository;

    @Inject
    private CertificationService certificationService;
    @Inject
    private BusinessBasicInfoRepository basicInfoRepository;
    @Inject
    private CompanyLogoRepository companyLogoRepository;

//    @Inject
//    private JavaMailSender mailSender;
    @Inject
    private DateTimeUtil dateTimeUtil;

    @Inject
    private BusinessCertificatesRepository businessCertificatesRepository;

    @Inject
    private ProviderBCertificateDataRepository providerBCertificateDataRepository;

    @Inject
    private SoftwareEvaluationRepository softwareEvaluationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Object saveUpdateProject(ProjectCreationRequest request) throws Exception {
        try {
            CompanyRegistration registration = companyRepository.findByUserId(request.getCompanyId());
            ExceptionUtils.verifyDataNotExistThenThrowException("User not registered.");
                if (projectCreationRepository.existsByProjectCodeAndIsDeleted(request.getSystemGeneratedProjectId(),
                        false)) {
                    ProjectCreation projectCreation = projectCreationRepository
                            .findByProjectCodeAndIsDeleted(request.getSystemGeneratedProjectId(), false);
                    if (!projectCreation.getProjectNumber().equalsIgnoreCase(request.getProjectNumber())) {
                        if (projectCreationRepository.existsByProjectNumberAndCompanyId(request.getProjectNumber(),
                                request.getCompanyId())) {
                            throw new Exception("projectNumber must be unique");
                        }
                    }
                    projectCreation.setProjectNumber(request.getProjectNumber());
                    projectCreation.setProjectOwner(request.getProjectOwner());
                    projectCreation.setProjectEmail(request.getProjectEmail());
                    projectCreation.setProjectContactNum(request.getProjectContactNum());
                    projectCreation.setProjectTitle(request.getProjectTitle());
                    projectCreation.setProjectCategoryId(request.getCatgoryId());
                    projectCreation.setProjectSubCategoryId(request.getCatgorySubId());
                    projectCreation.setProjectSubCategoryLevel2Id(request.getSubCatLevel2Id());
                    projectCreation.setProjectSubCategoryLevel3Id(request.getSubCatLevel3Id());
                    projectCreation.setMarketplace(request.isMarketPlace());
                    validateStartEndExpiryDate(request, projectCreation.getCompanyId());

                    projectCreation.setStartDate(request.getStartDate());
                    projectCreation.setEndDate(request.getEndDate());
                    projectCreation.setExpiryDate(request.getExpiryDate());
                    projectCreation.setCurrency(request.getCurrency());
                    projectCreation.setNdaStatus(NdaStatus.PENDING);
                    projectCreation.setIsNDA(request.getIsNDA());
                    projectCreation.setShortDescription(request.getShortDescription());
                    projectCreation.setProjectBackground(request.getProjectBackground());
                    projectCreation.setProjectScopeGoal(request.getProjectScopeGoal());
                    projectCreation.setProjectScopeGoal2(request.getProjectScopeGoal2());
                    projectCreation.setLevelName(request.getLevelName());
                    projectCreation.setInternalBudget(request.getInternalBudget());
                    projectCreation.setBudgetRange(request.getBudgetRange());
                    projectCreation.setProjectBudgetVisibility(request.getProjectBudgetVisibility());
                    projectCreation.setProjectTeamSize(request.getProjectTeamSize());
                    projectCreation.setProjectTeamSizeVisibility(request.getProjectTeamSizeVisibility());
                    projectCreation.setIsActive(true);
                    // projectCreation.setIsDeleted(false);
                    projectCreation.setUpdatedEpochTime(LocalDate.now());
                    String name = registration.getFirstName();
                    if (registration.getMiddleName() != null) {
                        name = name + " " + registration.getMiddleName();
                    }
                    if (registration.getLastName() != null) {
                        name = name + " " + registration.getLastName();
                    }
                    projectCreation.setUpdatedBy(name);
                    if (request.getProjectStatus() == null) {
                        projectCreation.setProjectStatus(ProjectStatus.DRAFT);
                    } else if (request.getProjectStatus().equalsIgnoreCase(ProjectStatus.PUBLISHED.getValue())) {
                        projectCreation.setProjectStatus(ProjectStatus.PUBLISHED);
                    } else {
                        projectCreation.setProjectStatus(getProjectStatus(request.getMilestoneType()));
                    }

                    projectCreationRepository.save(projectCreation);
                    addupdateProjectMilestone(request, projectCreation);
                    EmailTemplates emailTemplates = new EmailTemplates();
                    String message = emailTemplates.updateProjectTemplate("");
                    smsEmailIntegration.sendEmail(registration.getEmail(), "About Project Details Updated", message);
                    if (request.getDocuments() != null) {
                        uploadProjectCreationDocument(request.getDocuments(), projectCreation.getProjectCode(),
                                request.getMilestoneType(), request.getMilestoneDate());
                    }
                    return "updated Successfully";

                } else {
                    ProjectCreation projectCreation = new ProjectCreation();
                    if (projectCreationRepository.existsByProjectNumberAndCompanyId(request.getProjectNumber(),
                            request.getCompanyId())) {
                        throw new Exception("projectNumber must be unique");
                    }
                    projectCreation.setProjectNumber(request.getProjectNumber());
                    projectCreation.setCompanyId(request.getCompanyId());
                    projectCreation.setProjectOwner(request.getProjectOwner());
                    projectCreation.setProjectEmail(request.getProjectEmail());
                    projectCreation.setCurrency(request.getCurrency());
                    projectCreation.setLevelName(request.getLevelName());
                    projectCreation.setProjectContactNum(request.getProjectContactNum());
                    projectCreation.setProjectTitle(request.getProjectTitle());
                    projectCreation.setShortDescription(request.getShortDescription());
                    projectCreation.setProjectCategoryId(request.getCatgoryId());
                    projectCreation.setNdaStatus(NdaStatus.PENDING);
                    projectCreation.setProjectSubCategoryId(request.getCatgorySubId());
                    projectCreation.setProjectSubCategoryLevel2Id(request.getSubCatLevel2Id());
                    projectCreation.setProjectSubCategoryLevel3Id(request.getSubCatLevel3Id());
                    projectCreation.setProjectBackground(request.getProjectBackground());
                    projectCreation.setProjectScopeGoal(request.getProjectScopeGoal());
                    projectCreation.setProjectScopeGoal2(request.getProjectScopeGoal2());
                    projectCreation.setInternalBudget(request.getInternalBudget());
                    projectCreation.setBudgetRange(request.getBudgetRange());
                    projectCreation.setProjectBudgetVisibility(request.getProjectBudgetVisibility());
                    projectCreation.setProjectTeamSize(request.getProjectTeamSize());
                    projectCreation.setProjectTeamSizeVisibility(request.getProjectTeamSizeVisibility());
                    projectCreation.setIsNDA(request.getIsNDA());
                    projectCreation.setIsActive(true);
                    projectCreation.setIsDeleted(false);
                    projectCreation.setProjectId(Instant.now().toEpochMilli());
                    projectCreation.setCreatedEpochTime(LocalDate.now());
                    validateStartEndExpiryDate(request, projectCreation.getCompanyId());
                    projectCreation.setStartDate(request.getStartDate());
                    projectCreation.setEndDate(request.getEndDate());
                    projectCreation.setExpiryDate(request.getExpiryDate());
                    // projectCreation.setUpdatedEpochTime(Instant.now().toEpochMilli());
                    String name = registration.getFirstName();
                    if (registration.getMiddleName() != null) {
                        name = name + " " + registration.getMiddleName();
                    }
                    if (registration.getLastName() != null) {
                        name = name + " " + registration.getLastName();
                    }
                    projectCreation.setCreatedBy(name);
                    if (request.getProjectStatus() == null) {
                        projectCreation.setProjectStatus(ProjectStatus.DRAFT);
                    } else if (request.getProjectStatus().equalsIgnoreCase(ProjectStatus.PUBLISHED.getValue())) {
                        projectCreation.setProjectStatus(ProjectStatus.PUBLISHED);
                    } else {
                        projectCreation.setProjectStatus(getProjectStatus(request.getMilestoneType()));
                    }

                    // projectCreation.setUpdatedBy(registration.getEmail());
                    projectCreationRepository.save(projectCreation);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yy");
                    String dateAppend = dateFormat.format(new Date());
                    String formatId = String.format("%05d", projectCreation.getId());
                    String projectCode = "PBM" + dateAppend + formatId;
                    projectCreation.setProjectCode(projectCode);
                    projectCreationRepository.save(projectCreation);

                    addupdateProjectMilestone(request, projectCreation);
                    if (request.getDocuments() != null) {
                        uploadProjectCreationDocument(request.getDocuments(), projectCreation.getProjectCode(),
                                request.getMilestoneType(), request.getMilestoneDate());
                    }
                    EmailTemplates emailTemplates = new EmailTemplates();
                    String message = emailTemplates.projectCreationTemplate(projectCreation.getProjectTitle(),
                            projectCreation.getProjectNumber(), projectCreation.getProjectOwner(),
                            projectCreation.getProjectEmail(), projectCreation.getProjectContactNum());

                    /*
                     * "CONGRATULATIONS!\n" +
                     * "You Have Successfully Posted A New Project On Market Place!!: "
                     * +" Project Name:"+ projectCreation.getProjectTitle()+ " Project Number:"
                     * +projectCreation.getProjectNumber()+ "Project Creation Date "
                     * +projectCreation.getStartDate()+ "Project Posted On Market Place : " +
                     * projectCreation.getCreatedEpochTime();
                     */
                    smsEmailIntegration.sendEmail(registration.getEmail(), "Create Project Confirmation", message);
                    /*
                     * long epoch = Instant.now().toEpochMilli(); System.out.println("Epoch Time:" +
                     * epoch); LocalDate date =
                     * Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
                     * System.out.println("Date:" + date);
                     */
                    String message1 = emailTemplates.emailMarketPlace(projectCreation.getProjectTitle(),
                            projectCreation.getProjectNumber(), LocalDate.now(), LocalDate.now(),
                            projectCreation.getExpiryDate(), projectCreation.getCreatedBy(),
                            projectCreation.getProjectEmail());
                    smsEmailIntegration.sendEmail(registration.getEmail(), "About Project Post in Market Place...!!",
                            message1);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("SystemGeneratedprojectId", projectCreation.getProjectCode());
                    return map;
                }

        } catch (Exception e) {
        	throw new SourceablyCustomeException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private ProjectStatus getProjectStatus(String milestoneType) {
        ProjectStatus defaultStatus = ProjectStatus.DRAFT;
        if (milestoneType == null) {
            return defaultStatus;
        }
        if (milestoneType.equalsIgnoreCase(StatusEnum.PROVIDERS_TO_SIGN_NDA.getCustomerValue())
                || milestoneType.equalsIgnoreCase(StatusEnum.DITRIBUTE_RFP_TO_PROVIDERS.getCustomerValue())
                || milestoneType.equalsIgnoreCase(StatusEnum.CONFIRMATION_TO_PARTICIPATE.getCustomerValue())) {
            return ProjectStatus.PUBLISHED;
        }
        if (milestoneType.equalsIgnoreCase(StatusEnum.QUESTIONS_DEADLINE.getCustomerValue())
                || milestoneType.equalsIgnoreCase(StatusEnum.RFP_DEADLINE.getCustomerValue())
                || milestoneType.equalsIgnoreCase(StatusEnum.INTERNAL_SHORTLISTING.getCustomerValue())
                || milestoneType.equalsIgnoreCase(StatusEnum.NOTIFY_SHORTLISTED_VENDORS.getCustomerValue())
                || milestoneType.equalsIgnoreCase(StatusEnum.REFERANCE_CHECK.getCustomerValue())
                || milestoneType.equalsIgnoreCase(StatusEnum.NEGOTIATED_BUDGET.getCustomerValue())) {
            return ProjectStatus.IN_PROGRESS;
        }
        if (milestoneType.equalsIgnoreCase(StatusEnum.COMMUNICATE_FINAL_DECISION.getCustomerValue())) {
            return ProjectStatus.GRANTED;
        }
        if (milestoneType.equalsIgnoreCase(StatusEnum.CANCEL_PROJECT.getCustomerValue())) {
            return ProjectStatus.CANCEL;
        }
        if (milestoneType.equalsIgnoreCase(StatusEnum.HOLD_PROJECT.getCustomerValue())) {
            return ProjectStatus.HOLD;
        }
        return defaultStatus;
    }

    private void validateStartEndExpiryDate(ProjectCreationRequest request, String companyId) throws Exception {
        LocalDate todayDate = dateTimeUtil.getTodayDate();
        LocalDate startDate = request.getStartDate();

        if (companyId == null) {
            if (startDate.isBefore(todayDate)) {
                throw new Exception("Invalid Start Date");
            }
        }

        LocalDate endDate = request.getEndDate();
        if (endDate.isBefore(todayDate) || endDate.isBefore(startDate)) {
            throw new Exception("Invalid End Date");
        }
        LocalDate expiryDate = request.getExpiryDate();
        if (expiryDate.isBefore(todayDate) || expiryDate.isBefore(startDate)) {
            throw new Exception("Invalid Expiry Date");
        }
    }

    @Override
    public String checkConsumerNDA(String userId) throws Exception {
        ConsumerSales sales = consumerSalesRepository.findByUserId(userId);
        ExceptionUtils.verifyDataNotExistThenThrowException(sales);
        if(sales.getGeneralisedNdaDocument()==null)
        {
        	throw new SourceablyCustomeException("NDA has not been uploaded by consumer.", HttpStatus.UNPROCESSABLE_ENTITY);	
        }
        return "NDA is available";
    }

    private void uploadProjectCreationDocument(List<ProjectDocumentUploadRequest> documents, String projectCode,
                                               String milestoneType, LocalDate milestoneDate) throws Exception {
        if (projectDocumentUploadRepository.existsByProjectCode(projectCode)) {
            projectDocumentUploadRepository.deleteByProjectCode(projectCode);
        }
        for (ProjectDocumentUploadRequest request : documents) {
            ProjectDocumentUpload upload = new ProjectDocumentUpload();
            upload.setDocumentId(Instant.now().toEpochMilli());
            upload.setProjectCode(projectCode);
            if (null == request.getFileName() || "".equals(request.getFileName())) {
                throw new Exception("Document Name is mandatory");
            }
            upload.setDocumentName(request.getFileName());
            upload.setDocumentType(request.getDocumentType());
            upload.setDocument(request.getDocument());

            if (milestoneType != null && milestoneType.equalsIgnoreCase(StatusEnum.RFP_DEADLINE.getCustomerValue())) {
                upload.setRequestedDate(milestoneDate);
            }

            projectDocumentUploadRepository.save(upload);
        }
    }

//    @Override
//    public Object projectDocS3upload(ProjectDocS3Request request) throws Exception {
//        try {
//            String filePath = fileOperation.storeDoc(request.getFile(), "document").getFilePath();
//            return filePath;
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

//    @Override
//    public String deleteS3Document(String fileName) throws Exception {
//        try {
//            String response = fileOperation.deleteDoc(fileName);
//            return response;
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }

    @Override
    public Page<ProjectCreation> getAllProject(Pageable pageable, Long sort) throws Exception {
        Page<ProjectCreation> projectCreation;
        if (sort == 2) {
            projectCreation = projectCreationRepository.findAllSortByEndDate(pageable);
        } else {
            projectCreation = projectCreationRepository
                    .findAllByIsDeletedAndIsActiveAndMarketplaceAndExpiryDateGreaterThanEqualOrderByCreatedEpochTimeDesc(
                            false, true, true, LocalDate.now(), pageable);
        }
       String loginUserName="";
        Optional<HttpRequest<Object>> securityContext = ServerRequestContext.currentRequest();
        if(securityContext.isPresent()){
            Optional<Principal> principal = securityContext.get().getUserPrincipal();
            if(principal.isPresent()){
                loginUserName= principal.get().getName();
            }

        }

        CompanyRegistration companyReg = companyRepository.findByEmail(loginUserName);
        ExceptionUtils.verifyDataNotExistThenThrowException(companyReg);

        for (ProjectCreation creation : projectCreation.getContent()) {
            creation.setTotalProjects(projectCreationRepository.countTotalProject(false));
            creation.setBidCount(projectBidPostRepository.countProjectBid(creation.getProjectCode()));
            if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectCategoryId())) {
                creation.setCategoryName(skillMatrixCategoryRepository
                        .findByskillMatrixId(creation.getProjectCategoryId()).getSkillMatrixName());
            }
            if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectSubCategoryId())) {
                creation.setSubCategoryName(skillMatrixCategoryRepository
                        .findByskillMatrixId(creation.getProjectSubCategoryId()).getSkillMatrixName());
            }
            if (creation.getSubCategoryLevel2Name() != null
                    && skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getSubCategoryLevel2Name())) {
                creation.setSubCategoryLevel2Name(skillMatrixCategoryRepository
                        .findByskillMatrixId(creation.getSubCategoryLevel2Name()).getSkillMatrixName());
            }
            if (creation.getSubCategoryLevel3Name() != null
                    && skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getSubCategoryLevel3Name())) {
                creation.setSubCategoryLevel3Name(skillMatrixCategoryRepository
                        .findByskillMatrixId(creation.getSubCategoryLevel3Name()).getSkillMatrixName());
            }
           
            ConsumerBusinessBasicInfo businessBasicInfo = consumerBusinessBasicInfoRepository
                    .findByUserId(creation.getCompanyId());
            ExceptionUtils.verifyDataNotExistThenThrowException(businessBasicInfo);
            String location = "";
            if (businessBasicInfo.getHeadquartersCity() != null) {
                location = location + businessBasicInfo.getHeadquartersCity() + ", ";
            }
            if (businessBasicInfo.getHeadquartersState() != null) {
                location = location + businessBasicInfo.getHeadquartersState() + ", ";
            }
            if (businessBasicInfo.getHeadquartersCountry() != null) {
                location = location + businessBasicInfo.getHeadquartersCountry();
            }
            creation.setLocation(location);
        
            if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {

                ProjectMilestoneListResponse dto = getValueByUserType(companyReg, creation);

                creation.setMileStoneList(dto);
            }
            if (projectDocumentUploadRepository.existsByProjectCode(creation.getProjectCode())) {
                creation.setDocuments(projectDocumentUploadRepository.findByProjectCode(creation.getProjectCode()));

            }
        }
        return projectCreation;
    }

    @Override
    public Object filterProject(Pageable pageable, String budgetRange, String teamSize, String providerId)
            throws Exception {
        Page<ProjectCreation> projectCreation;
        if (budgetRange != null && teamSize != null) {
            projectCreation = projectCreationRepository.findAllByBudgetAndTeamSize(teamSize, budgetRange, pageable);
        } else if (budgetRange != null) {
            projectCreation = projectCreationRepository.findAllByBudget(budgetRange, pageable);
        } else if (teamSize != null) {
            projectCreation = projectCreationRepository.findAllByTeamSize(teamSize, pageable);
        } else {
            projectCreation = projectCreationRepository
                    .findAllByIsDeletedAndIsActiveAndExpiryDateGreaterThanEqualOrderByCreatedEpochTimeDesc(false, true,
                            LocalDate.now(), pageable);

        }

        CompanyRegistration companyReg = getRegisterCompanyByLoggedInUser();
        ExceptionUtils.verifyDataNotExistThenThrowException(getRegisterCompanyByLoggedInUser());

        for (ProjectCreation creation : projectCreation.getContent()) {
            if (providerId.equalsIgnoreCase("0")) {
                creation.setStatus(0);
            } else {
            	ProjectBidPost bidPost = projectBidPostRepository
                        .getByProviderIdAndSystemGeneratedProjectId(providerId, creation.getProjectCode());
                if (bidPost!=null) {
                    if (bidPost.getProposalStatus() != null) {
                        creation.setStatus(1);
                    }
                    if (bidPost.getCoverLetter() != null) {
                        creation.setStatus(2);
                    }
                } else {
                    creation.setStatus(0);
                }
            }

            creation.setBidCount(projectBidPostRepository.countProjectBid(creation.getProjectCode()));
            creation.setTotalProjects(projectCreationRepository.countTotalProject(false));
            if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectCategoryId())) {
                creation.setCategoryName(skillMatrixCategoryRepository
                        .findByskillMatrixId(creation.getProjectCategoryId()).getSkillMatrixName());
            }
            if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectSubCategoryId())) {
                creation.setSubCategoryName(skillMatrixCategoryRepository
                        .findByskillMatrixId(creation.getProjectSubCategoryId()).getSkillMatrixName());
            }
            
            ConsumerBusinessBasicInfo businessBasicInfo = consumerBusinessBasicInfoRepository.findByUserId(creation.getCompanyId());
            if (businessBasicInfo!=null) {
                String location = "";
                if (businessBasicInfo.getHeadquartersCity() != null) {
                    location = location + businessBasicInfo.getHeadquartersCity() + ", ";
                }
                if (businessBasicInfo.getHeadquartersState() != null) {
                    location = location + businessBasicInfo.getHeadquartersState() + ", ";
                }
                if (businessBasicInfo.getHeadquartersCountry() != null) {
                    location = location + businessBasicInfo.getHeadquartersCountry();
                }
                creation.setLocation(location);
            }
            if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {
                ProjectMilestoneListResponse dto = getValueByUserType(companyReg, creation);
                creation.setMileStoneList(dto);

                // creation.setMileStoneList(projectMilestoneRepository.findByProjectId(creation.getProjectId()));
            }
            
            List<ProjectDocumentUpload> documents = projectDocumentUploadRepository.findByProjectCode(creation.getProjectCode());
            creation.setDocuments(documents);
        }
        return new PageDto(projectCreation.getContent(), projectCreation.getTotalPages(),
                projectCreation.getNumberOfElements(), projectCreation.getPageNumber());
    }

    @Override
    public Object getAllProjectsDetails(Pageable pageable, Long sort, String providerId) throws Exception {
        Page<ProjectCreation> projectCreation;
        if (sort == 2) {
            projectCreation = projectCreationRepository.findAllSortByEndDate(pageable);
        } else {
            projectCreation = projectCreationRepository
                    .findAllByIsDeletedAndIsActiveAndExpiryDateGreaterThanEqualOrderByCreatedEpochTimeDesc(false, true,
                            LocalDate.now(), pageable);
        }
        try {

            CompanyRegistration companyReg = getRegisterCompanyByLoggedInUser();

            ExceptionUtils.verifyDataNotExistThenThrowException(companyReg);
            for (ProjectCreation creation : projectCreation.getContent()) {
                if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(providerId,
                        creation.getProjectCode())) {
                    ProjectBidPost bidPost = projectBidPostRepository
                            .getByProviderIdAndSystemGeneratedProjectId(providerId, creation.getProjectCode());

                    if (bidPost.getProposalStatus() != null) {
                        creation.setStatus(1);
                    }
                    if (bidPost.getCoverLetter() != null) {
                        creation.setStatus(2);
                    }
                } else {
                    creation.setStatus(0);
                }

                creation.setTotalProjects(projectCreationRepository.countTotalProject(false));
                creation.setBidCount(projectBidPostRepository.countProjectBid(creation.getProjectCode()));
                if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectCategoryId())) {
                    creation.setCategoryName(skillMatrixCategoryRepository
                            .findByskillMatrixId(creation.getProjectCategoryId()).getSkillMatrixName());
                }
                if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectSubCategoryId())) {
                    creation.setSubCategoryName(skillMatrixCategoryRepository
                            .findByskillMatrixId(creation.getProjectSubCategoryId()).getSkillMatrixName());
                }
                if (consumerBusinessBasicInfoRepository.findByUserId(creation.getCompanyId()) != null) {
                    ConsumerBusinessBasicInfo businessBasicInfo = consumerBusinessBasicInfoRepository
                            .findByUserId(creation.getCompanyId());
                    String location = "";
                    if (businessBasicInfo.getHeadquartersCity() != null) {
                        location = location + businessBasicInfo.getHeadquartersCity() + ", ";
                    }
                    if (businessBasicInfo.getHeadquartersState() != null) {
                        location = location + businessBasicInfo.getHeadquartersState() + ", ";
                    }
                    if (businessBasicInfo.getHeadquartersCountry() != null) {
                        location = location + businessBasicInfo.getHeadquartersCountry();
                    }
                    creation.setLocation(location);
                }
                if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {
                    ProjectMilestoneListResponse dto = getValueByUserType(companyReg, creation);
                    creation.setMileStoneList(dto);

                    // creation.setMileStoneList(projectMilestoneRepository.findByProjectId(creation.getProjectId()));
                }
                if (projectDocumentUploadRepository.existsByProjectCode(creation.getProjectCode())) {
                    creation.setDocuments(projectDocumentUploadRepository.findByProjectCode(creation.getProjectCode()));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageDto(projectCreation.getContent(), projectCreation.getTotalPages(),
                projectCreation.getNumberOfElements(), projectCreation.getPageNumber());
    }

    @Override
    public Object searchProjectByText(Pageable pageable, String searchtext, String sort, String providerId)
            throws Exception {
    	ExceptionUtils.verifyDataNotExistThenThrowException(searchtext);
            try {
                Page<ProjectCreation> projectCreation = projectCreationRepository.searchByProjectTitle(searchtext,
                        pageable);
                CompanyRegistration companyReg = getRegisterCompanyByLoggedInUser();

                ExceptionUtils.verifyDataNotExistThenThrowException(companyReg);
                for (ProjectCreation creation : projectCreation.getContent()) {

                    if (providerId.equalsIgnoreCase("0")) {
                        creation.setStatus(0);
                    } else {
                    	ProjectBidPost bidPost = projectBidPostRepository
                                .getByProviderIdAndSystemGeneratedProjectId(providerId, creation.getProjectCode());
                        if (bidPost!=null) {
                            if (bidPost.getProposalStatus() != null) {
                                creation.setStatus(1);
                            }
                            if (bidPost.getCoverLetter() != null) {
                                creation.setStatus(2);
                            }
                        } else {
                            creation.setStatus(0);
                        }
                    }

                    creation.setBidCount(projectBidPostRepository.countProjectBid(creation.getProjectCode()) );
                    if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectCategoryId())) {
                        creation.setCategoryName(skillMatrixCategoryRepository
                                .findByskillMatrixId(creation.getProjectCategoryId()).getSkillMatrixName());
                    }

                    ConsumerBusinessBasicInfo businessBasicInfo = consumerBusinessBasicInfoRepository
                            .findByUserId(creation.getCompanyId());
                    ExceptionUtils.verifyDataNotExistThenThrowException(businessBasicInfo);
                    String location = "";
                    if (businessBasicInfo.getHeadquartersCity() != null) {
                        location = location + businessBasicInfo.getHeadquartersCity() + ", ";
                    }
                    if (businessBasicInfo.getHeadquartersState() != null) {
                        location = location + businessBasicInfo.getHeadquartersState() + ", ";
                    }
                    if (businessBasicInfo.getHeadquartersCountry() != null) {
                        location = location + businessBasicInfo.getHeadquartersCountry();
                    }
                    creation.setLocation(location);
                
                    
                    if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectSubCategoryId())) {
                        creation.setSubCategoryName(skillMatrixCategoryRepository
                                .findByskillMatrixId(creation.getProjectSubCategoryId()).getSkillMatrixName());
                    }
                    // added for subcategory level 3.
                    if (creation.getSubCategoryLevel2Name() != null && skillMatrixCategoryRepository
                            .existsBySkillMatrixId(creation.getSubCategoryLevel2Name())) {
                        creation.setSubCategoryLevel2Name(skillMatrixCategoryRepository
                                .findByskillMatrixId(creation.getSubCategoryLevel2Name()).getSkillMatrixName());
                    }
                    // added for subcategory level 3.
                    if (creation.getSubCategoryLevel3Name() != null && skillMatrixCategoryRepository
                            .existsBySkillMatrixId(creation.getSubCategoryLevel3Name())) {
                        creation.setSubCategoryLevel3Name(skillMatrixCategoryRepository
                                .findByskillMatrixId(creation.getSubCategoryLevel3Name()).getSkillMatrixName());
                    }
                    if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {

                        ProjectMilestoneListResponse dto = getValueByUserType(companyReg, creation);
                        creation.setMileStoneList(dto);
                        // creation.setMileStoneList(projectMilestoneRepository.findByProjectId(creation.getProjectId()));
                    }
                    if (projectDocumentUploadRepository.existsByProjectCode(creation.getProjectCode())) {
                        creation.setDocuments(
                                projectDocumentUploadRepository.findByProjectCode(creation.getProjectCode()));

                    }
                }
                return new PageDto(projectCreation.getContent(), projectCreation.getTotalPages(),
                        projectCreation.getNumberOfElements(), projectCreation.getPageNumber());

            } catch (Exception e) {
            	throw new SourceablyCustomeException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
    }

    private List<String> splitStringValue(String subCategory2Id) {
        List<String> values = new ArrayList<String>();
        if (!Objects.isNull(subCategory2Id)) {
            values = Arrays.asList(subCategory2Id.split(","));
        }
        return values;
    }

    @Override
    public Object searchProjectByCategory(Pageable pageable, String subCategoryId, String subCategory2Id,
                                          String subCategory3Id, String providerId) throws Exception {

        List<String> str = Arrays.asList(subCategoryId.split(","));
        List<String> listOfsubCat2 = splitStringValue(subCategory2Id);
        List<String> listOfsubCat3 = splitStringValue(subCategory3Id);
        try {

            Page<ProjectCreation> page;

            if (!StringUtils.isEmpty(subCategory2Id) && !StringUtils.isEmpty(subCategory3Id)) {
                page = projectCreationRepository.getProjectSubCategoryId(str, listOfsubCat2, listOfsubCat3, true,
                        pageable);
            } else if (!StringUtils.isEmpty(subCategory2Id)) {
                page = projectCreationRepository.getProjectSubCategoryId2(str, listOfsubCat2, true, pageable);
            } else if (!StringUtils.isEmpty(subCategory3Id)) {
                page = projectCreationRepository.getProjectSubCategoryId3(str, listOfsubCat3, true, pageable);
            } else {
                page = projectCreationRepository.getProjectSubCategoryId(str, true, pageable);
            }
              CompanyRegistration companyReg = getRegisterCompanyByLoggedInUser();

            for (ProjectCreation creation : page.getContent()) {
                if (providerId.equalsIgnoreCase("0")) {
                    creation.setStatus(0);
                } else {
                    if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(providerId,
                            creation.getProjectCode())) {
                        ProjectBidPost bidPost = projectBidPostRepository
                                .getByProviderIdAndSystemGeneratedProjectId(providerId, creation.getProjectCode());

                        if (bidPost.getProposalStatus() != null) {
                            creation.setStatus(1);
                        }
                        if (bidPost.getCoverLetter() != null) {
                            creation.setStatus(2);
                        }
                    } else {
                        creation.setStatus(0);
                    }
                }
                creation.setBidCount(projectBidPostRepository.countProjectBid(creation.getProjectCode()));
                if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectCategoryId())) {
                    creation.setCategoryName(skillMatrixCategoryRepository
                            .findByskillMatrixId(creation.getProjectCategoryId()).getSkillMatrixName());
                }
                if (consumerBusinessBasicInfoRepository.findByUserId(creation.getCompanyId()) != null) {
                    ConsumerBusinessBasicInfo businessBasicInfo = consumerBusinessBasicInfoRepository
                            .findByUserId(creation.getCompanyId());
                    String location = "";
                    if (businessBasicInfo.getHeadquartersCity() != null) {
                        location = location + businessBasicInfo.getHeadquartersCity() + ", ";
                    }
                    if (businessBasicInfo.getHeadquartersState() != null) {
                        location = location + businessBasicInfo.getHeadquartersState() + ", ";
                    }
                    if (businessBasicInfo.getHeadquartersCountry() != null) {
                        location = location + businessBasicInfo.getHeadquartersCountry();
                    }
                    creation.setLocation(location);
                }
                if (skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getProjectSubCategoryId())) {
                    creation.setSubCategoryName(skillMatrixCategoryRepository
                            .findByskillMatrixId(creation.getProjectSubCategoryId()).getSkillMatrixName());
                }
                // added for subcategory level 2.
                if (creation.getSubCategoryLevel2Name() != null
                        && skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getSubCategoryLevel2Name())) {
                    creation.setSubCategoryLevel2Name(skillMatrixCategoryRepository
                            .findByskillMatrixId(creation.getSubCategoryLevel2Name()).getSkillMatrixName());
                }
                // added for subcategory level 3.
                if (creation.getSubCategoryLevel3Name() != null
                        && skillMatrixCategoryRepository.existsBySkillMatrixId(creation.getSubCategoryLevel3Name())) {
                    creation.setSubCategoryLevel3Name(skillMatrixCategoryRepository
                            .findByskillMatrixId(creation.getSubCategoryLevel3Name()).getSkillMatrixName());
                }

                if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {
                    ProjectMilestoneListResponse dto = getValueByUserType(companyReg, creation);
                    creation.setMileStoneList(dto);

                    // creation.setMileStoneList(projectMilestoneRepository.findByProjectId(creation.getProjectId()));
                }
                if (projectDocumentUploadRepository.existsByProjectCode(creation.getProjectCode())) {
                    creation.setDocuments(projectDocumentUploadRepository.findByProjectCode(creation.getProjectCode()));

                }
            }

            return new PageDto(page.getContent(), page.getTotalPages(), page.getNumberOfElements(), page.getPageNumber());

        } catch (Exception e) {
        	throw new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Object getProjectByProjectCode(String systemGenerateProjectId, String userId, String type) throws Exception {
            ProjectCreation creation = projectCreationRepository.findByProjectCodeAndIsDeleted(systemGenerateProjectId,
                    false);
            ExceptionUtils.verifyDataNotExistThenThrowException(creation, "SystemGenerateProjectId not found");

            CompanyRegistration companyReg = getRegisterCompanyByLoggedInUser();

            try {
                if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {
                    ProjectMilestoneListResponse dto = getValueByUserType(companyReg, creation);
                    creation.setMileStoneList(dto);
                }
                creation.setBidCount(projectBidPostRepository.countProjectBid(creation.getProjectCode()));
                if (creation.getIsNDA()) {
                    if (consumerSalesRepository.existsByUserId(creation.getCompanyId())) {
                        creation.setConsumerNDA(consumerSalesRepository.findByUserId(creation.getCompanyId()));
                    }
                }

                if (projectDocumentUploadRepository.existsByProjectCode(creation.getProjectCode())) {
                    List<ProjectDocumentUpload> projectDocumentUploadList = projectDocumentUploadRepository
                            .findByProjectCode(creation.getProjectCode());
                    for (ProjectDocumentUpload documentUpload : projectDocumentUploadList) {
                        if (projectBidDocumentsRepository.existsByDocumentId(documentUpload.getId())) {
                            ProjectBidDocuments bidDocuments = projectBidDocumentsRepository
                                    .findByDocumentId(documentUpload.getId());
                            documentUpload.setUserUplodedDocumentUrl(bidDocuments.getDocument());
                            documentUpload.setUserUploadedDocumentName(bidDocuments.getFileName());
                        }
                    }
                    creation.setDocuments(projectDocumentUploadList);

                }
                if (type.equalsIgnoreCase("SS")) {
                    if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(userId,
                            systemGenerateProjectId)) {
                        ProjectBidPost projectBidPost = projectBidPostRepository
                                .findByProviderIdAndSystemGeneratedProjectId(userId, systemGenerateProjectId);
                        if (projectBidPost.getBidId() != null) {
                            projectBidPost
                                    .setBidDocuments(projectBidDocumentsRepository.findByBidId(projectBidPost.getId()));

                            creation.setProjectBidPost(projectBidPost);
                        }

                        creation.setNdaStatus(projectBidPost.getNdaApprovalStatus());
                        creation.setNdaURL(projectBidPost.getNdaFile());
                        creation.setNdaFileName(projectBidPost.getNdaFileName());
                        if (projectBidPost.getNdaApprovalDate() != null) {
                            creation.setNdaApprovedDate(projectBidPost.getNdaApprovalDate());
                        }

                    }

                }
            } catch (Exception e) {
            	throw new SourceablyCustomeException(Constants.DATA_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY);
            }
            return creation;
    }

    @Override
    public Object getProjectByCompanyId(String projectCompanyId, Pageable pageable) throws Exception {
        if (projectCreationRepository.existsByCompanyIdAndIsDeleted(projectCompanyId, false)) {
            Page<ProjectCreation> projectCreation = projectCreationRepository
                    .findByCompanyIdAndIsDeletedOrderByIdDesc(projectCompanyId, false, pageable);
            ExceptionUtils.verifyDataNotExistThenThrowException(projectCreation);

            CompanyRegistration companyReg = getRegisterCompanyByLoggedInUser();

            ExceptionUtils.verifyDataNotExistThenThrowException(companyReg);
            for (ProjectCreation creation : projectCreation.getContent()) {
                creation.setBidCount(projectBidPostRepository.countProjectBid(creation.getProjectCode()));
                if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {

                    ProjectMilestoneListResponse dto = getValueByUserType(companyReg, creation);
                    creation.setMileStoneList(dto);
                }
                if (projectDocumentUploadRepository.existsByProjectCode(creation.getProjectCode())) {
                    creation.setDocuments(projectDocumentUploadRepository.findByProjectCode(creation.getProjectCode()));

                }
            }
            return new PageDto(projectCreation.getContent(), projectCreation.getTotalPages(),
                    projectCreation.getNumberOfElements(), projectCreation.getPageNumber());
        } else {
        	throw new SourceablyCustomeException("ProjectCompanyId not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private ProjectMilestoneListResponse getValueByUserType(CompanyRegistration company, ProjectCreation creation) {
        ProjectMileStoneList entity = projectMilestoneRepository.findByProjectId(creation.getProjectCode());
        ExceptionUtils.verifyDataNotExistThenThrowException(entity);
        ProjectMilestoneListResponse dto = entity.convertToResponse();
        if (company != null && company.getRegistrationType().equalsIgnoreCase("supplier")) {
            dto.setMilestoneType(entity.getMilestoneType().getSupplierValue());
        } else {
            dto.setMilestoneType(entity.getMilestoneType().getCustomerValue());
        }
        return dto;
    }

    @Override
    public String deleteProjectByProjectCode(String projectCode) throws Exception {
        if (projectCreationRepository.existsByProjectCodeAndIsDeleted(projectCode, false)) {
            ProjectCreation projectCreation = projectCreationRepository.findByProjectCodeAndIsDeleted(projectCode,
                    false);
            projectCreation.setIsDeleted(true);
            projectCreation.setProjectStatus(ProjectStatus.REJECTED);
            projectCreationRepository.save(projectCreation);
            return "Deleted";
        } else {
        	throw new SourceablyCustomeException("Project companyId not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String changeProjectStatus(String projectCode) throws Exception {

        ProjectCreation projectCreation = projectCreationRepository.findByProjectCodeAndIsDeleted(projectCode,
                false);
        ExceptionUtils.verifyDataNotExistThenThrowException(projectCreation,"Project Id not found");
        if (projectCreation.getIsActive()) {
            projectCreation.setProjectStatus(ProjectStatus.REJECTED);
            projectCreation.setIsActive(false);
        } else {
            projectCreation.setIsActive(true);
        }
        projectCreationRepository.save(projectCreation);
        return "status changed";
    
    }

    @Override
    public String addUpdateProjectInvite(ProjectInviteListRequest inviteRequest) throws Exception {
        String status = "";
        if (projectCreationRepository.existsByCompanyIdAndIsDeleted(inviteRequest.getCompanyId(), false)) {
            if (projectInviteListRepository.existsByProjectCode(inviteRequest.getProjectCode())) {
                saveProjectEmail(inviteRequest);
                status = "updated";
            } else {
                ProjectInviteList projectInviteList = new ProjectInviteList();
                projectInviteList.setProjectCode(inviteRequest.getProjectCode());
                projectInviteList.setCompanyId(inviteRequest.getCompanyId());
                projectInviteListRepository.save(projectInviteList);
                saveProjectEmail(inviteRequest);
                status = "saved successfully";
            }
            inviteRequest.getEmailId().forEach(emailId -> {
                EmailTemplates emailTemplates = new EmailTemplates();
                String body = emailTemplates.supplierTemplate("");
                smsEmailIntegration.sendEmail(emailId.getEmail(), "Supplier invite for the Project", body);
            });
            return status;
        } else {
        	throw new SourceablyCustomeException("Project id not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void saveProjectEmail(ProjectInviteListRequest request) {
        if (projectInviteEmailsRepository.existsByProjectCode(request.getProjectCode())) {
            projectInviteEmailsRepository.deleteAllByProjectCode(request.getProjectCode());
        } else {
            for (ProjectInviteEmails email : request.getEmailId()) {
                ProjectInviteEmails projectInviteEmails = new ProjectInviteEmails();
                projectInviteEmails.setProjectCode(request.getProjectCode());
                projectInviteEmails.setEmail(email.getEmail());
                projectInviteEmails.setIsActive(true);
                projectInviteEmailsRepository.save(projectInviteEmails);
            }
        }
    }

    @Override
    public Object getAllProjectInvites() throws Exception{
        List<ProjectInviteList> projectInviteList = projectInviteListRepository.findAll();
        ExceptionUtils.verifyDataNotExistThenThrowException(projectInviteList);
        for (ProjectInviteList projectInvite : projectInviteList) {
            projectInvite.setEmails(projectInviteEmailsRepository.findByProjectCode(projectInvite.getProjectCode()));
        }
        return projectInviteList;
    }

    @Override
    public Object getProjectInvitesByProjectCode(String systemGenerateProjectId) throws Exception {
    	ProjectInviteList projectInviteList = projectInviteListRepository
                .findByProjectCode(systemGenerateProjectId);
        ExceptionUtils.verifyDataNotExistThenThrowException(projectInviteList, "ProjectCode not found");
        projectInviteList.setEmails(projectInviteEmailsRepository.findByProjectCode(systemGenerateProjectId));
        return projectInviteList;
    }

    @Override
    public Object changeProjectInviteListStatus(Long id) throws Exception {
        ProjectInviteEmails emailList = projectInviteEmailsRepository.findById(id).get();
        ExceptionUtils.verifyDataNotExistThenThrowException(emailList);
        emailList.setIsActive(!emailList.getIsActive());
        projectInviteEmailsRepository.save(emailList);
        return "status changed";
    
    }

    @Override
    public String deleteProjectDocument(Long documentId) throws Exception {
        if (projectDocumentUploadRepository.existsById(documentId)) {
            projectDocumentUploadRepository.deleteById(documentId);
            return "Deleted";
        } else {
            throw new SourceablyCustomeException("Project code not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void addupdateProjectMilestone(ProjectCreationRequest request, ProjectCreation creation) {
    	ProjectMileStoneList mileStoneList = projectMilestoneRepository.findByProjectId(creation.getProjectCode());
        if (mileStoneList!=null) {
            mileStoneList.setMilestoneType(StatusEnum.convertToEnum(request.getMilestoneType()));
            mileStoneList.setDate(request.getMilestoneDate());
            mileStoneList.setProjectId(creation.getProjectCode());
            mileStoneList.setUpdatedDate(LocalDate.now());
            mileStoneList.setUpdatedBy(creation.getUpdatedBy());
            projectMilestoneRepository.save(mileStoneList);
        } else {
            mileStoneList.setMilestoneId(Instant.now().toEpochMilli());
            mileStoneList.setProjectId(creation.getProjectCode());
            mileStoneList.setDate(request.getMilestoneDate());
            mileStoneList.setMilestoneType(StatusEnum.convertToEnum(request.getMilestoneType()));
            mileStoneList.setCreatedDate(LocalDate.now());
            mileStoneList.setCreatedBy(creation.getCreatedBy());
            projectMilestoneRepository.save(mileStoneList);
        }

    }

    @Override
    public ProjectMileStoneList getProjectMileStone(String projectId) throws Exception {
    	ProjectMileStoneList milestoneList = projectMilestoneRepository.findByProjectId(projectId);
    	ExceptionUtils.verifyDataNotExistThenThrowException(milestoneList,"ProjectId not found");
    	return milestoneList;
    }
    
    @Override
    public Object saveProjectBid(ProjectBidPostRequest request) throws Exception {
        if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(request.getProviderId(),
                request.getSystemGeneratedProjectId())) {
            ProjectBidPost projectBidPost = projectBidPostRepository.findByProviderIdAndSystemGeneratedProjectId(
                    request.getProviderId(), request.getSystemGeneratedProjectId());
            projectBidPost.setProjectCost(request.getProjectCost());
            projectBidPost.setCoverLetter(request.getCoverLetter());
            projectBidPost.setProposalStatus(StatusEnum.PROPOSAL_SUBMITTED);
            if (request.getBidDocument() != null) {
                projectBidPost.setBidId(Instant.now().toEpochMilli());
                projectBidPost.setProjectBidDate(LocalDate.now());
                CompanyRegistration registration = companyRepository.findByUserId(request.getProviderId());
                if (registration!=null) {
                    String name = registration.getFirstName();
                    if (registration.getMiddleName() != null) {
                        name = name + " " + registration.getMiddleName();
                    }
                    if (registration.getLastName() != null) {
                        name = name + " " + registration.getLastName();
                    }
                    projectBidPost.setBidPostedBy(name);
                }
            }
            projectBidPostRepository.save(projectBidPost);
            saveUpdateBidDocument(projectBidPost, request);
            return projectBidPost.getBidId();
        } else {
        	ProjectCreation creation = projectCreationRepository
                    .findByProjectCodeAndIsDeleted(request.getSystemGeneratedProjectId(), false);
        	ExceptionUtils.verifyDataNotExistThenThrowException(creation, "Project does not exist.");
                if (!creation.getIsNDA()) {
                    ProjectBidPost projectBidPost = new ProjectBidPost();
                    projectBidPost.setSystemGeneratedProjectId(request.getSystemGeneratedProjectId());
                    projectBidPost.setProviderId(request.getProviderId());
                    projectBidPost.setConsumerId(creation.getCompanyId());
                    projectBidPost.setProjectCost(request.getProjectCost());
                    projectBidPost.setNdaStatus(false);
                    projectBidPost.setProposalStatus(StatusEnum.PROPOSAL_SUBMITTED);
                    projectBidPost.setCoverLetter(request.getCoverLetter());
                    if (request.getBidDocument() != null) {
                        projectBidPost.setBidId(Instant.now().toEpochMilli());

                        projectBidPost.setProjectBidDate(LocalDate.now());
                        CompanyRegistration registration = companyRepository.findByUserId(request.getProviderId());
                        if (registration != null) {
                            String name = registration.getFirstName();
                            if (registration.getMiddleName() != null) {
                                name = name + " " + registration.getMiddleName();
                            }
                            if (registration.getLastName() != null) {
                                name = name + " " + registration.getLastName();
                            }
                            projectBidPost.setBidPostedBy(name);
                        }
                    }
                    projectBidPostRepository.save(projectBidPost);
                    saveUpdateBidDocument(projectBidPost, request);
                    return projectBidPost.getBidId();

                } else {
                	throw new SourceablyCustomeException("You need to upload nda document first.", HttpStatus.UNPROCESSABLE_ENTITY);
                }
        }

    }

    @Override
    public String updateProjectBid(ProjectBidPostRequest request) throws Exception {
        if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(request.getProviderId(),
                request.getSystemGeneratedProjectId())) {
            ProjectBidPost projectBidPost = projectBidPostRepository.findByProviderIdAndSystemGeneratedProjectId(
                    request.getProviderId(), request.getSystemGeneratedProjectId());
            projectBidPost.setProjectCost(request.getProjectCost());
            projectBidPost.setCoverLetter(request.getCoverLetter());
            if (request.getBidDocument() != null) {
                saveUpdateBidDocument(projectBidPost, request);
                projectBidPost.setProposalStatus(StatusEnum.PROPOSAL_SUBMITTED);
                CompanyRegistration registration = companyRepository.findByUserId(request.getProviderId());
                if (registration!=null) {
                    String name = registration.getFirstName();
                    if (registration.getMiddleName() != null) {
                        name = name + " " + registration.getMiddleName();
                    }
                    if (registration.getLastName() != null) {
                        name = name + " " + registration.getLastName();
                    }
                    projectBidPost.setBidPostedBy(name);

                }
            }
            projectBidPostRepository.save(projectBidPost);
            return "updated";
        } else {
            throw new SourceablyCustomeException("Bid not found.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Object getProjectsBidId(String providerId, Pageable pageable) throws Exception {

        List<SupplierProjectBidResponse> list = new ArrayList<>();
        Page<ProjectBidPost> projectBidPosts = projectBidPostRepository.findByProviderIdOrderByIdDesc(providerId,
                pageable);
        ExceptionUtils.verifyDataNotExistThenThrowException(projectBidPosts);

        for (ProjectBidPost bid : projectBidPosts) {
            SupplierProjectBidResponse response = new SupplierProjectBidResponse();

            if (projectCreationRepository.existsByProjectCodeAndIsDeleted(bid.getSystemGeneratedProjectId(),
                    false)) {
                ProjectCreation creation = projectCreationRepository
                        .findByProjectCodeAndIsDeleted(bid.getSystemGeneratedProjectId(), false);
                response.setProjectName(creation.getProjectTitle());
                response.setConsumerLastUpdate(creation.getUpdatedEpochTime());
                response.setCurrency(creation.getCurrency());
                response.setBudgetRange(creation.getBudgetRange());
                response.setTeamSize(creation.getProjectTeamSize());
                CompanyRegistration registration = companyRepository.findByUserId(creation.getCompanyId());
                response.setConsumerName(registration != null ? registration.getCompanyName() : " ");

                if (projectMilestoneRepository.existsByProjectId(creation.getProjectCode())) {
                    ProjectMileStoneList milestone = projectMilestoneRepository
                            .findByProjectId(creation.getProjectCode());
                    if (registration.getRegistrationType().equalsIgnoreCase("supplier")) {
                        response.setProjectMilestone(milestone.getMilestoneType().getSupplierValue());
                    } else {
                        response.setProjectMilestone(milestone.getMilestoneType().getCustomerValue());
                    }

                    response.setMilestoneDate(milestone.getDate());

                }
            }
            //
            response.setNdaSubmissionResponseDate(bid.getNdaUploadDate());
            response.setProjectId(bid.getSystemGeneratedProjectId());
            response.setProposalSubmissionResponseDate(bid.getProjectBidDate());

            response.setNdaApprovalDate(bid.getNdaApprovalDate());
            response.setNdaApprovalStatus(String.valueOf(bid.getNdaApprovalStatus()));
            response.setProposalStatus(
                    bid.getProposalStatus() != null ? bid.getProposalStatus().getCustomerValue() : "");
            // response.setProposalStatus(String.valueOf(bid.getProposalStatus()));
            response.setProjectCost(bid.getProjectCost());
            list.add(response);

        }

        return new PageDto(list, projectBidPosts.getTotalPages(), projectBidPosts.getNumberOfElements(),
                projectBidPosts.getPageNumber());

    
    }

    /*
     * if (projectBidPostRepository.existsByBidId(bidId)) { ProjectBidPost
     * projectBidPost = projectBidPostRepository.findByBidId(bidId); if
     * (projectBidDocumentsRepository.existsByBidId(bidId)) {
     * projectBidPost.setBidDocuments(projectBidDocumentsRepository.findByBidId(
     * bidId)); } if
     * (projectMilestoneRepository.existsByProjectId(creation.getProjectId())) {
     * creation.setMileStoneList(projectMilestoneRepository.findByProjectId(creation
     * .getProjectId())); } return projectBidPost; } else { throw new
     * Exception("bid id not found"); }
     *
     */

    @Override
    public Object saveProviderNdaDoc(ProviderNdaDocRequest ndaRequest) throws Exception {
    	ProjectCreation creation = projectCreationRepository
                .findByProjectCodeAndIsDeleted(ndaRequest.getProjectId(), false);
    	ExceptionUtils.verifyDataNotExistThenThrowException(creation, "Project not exist.");
            if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(ndaRequest.getProviderId(),
                    ndaRequest.getProjectId())) {
                ProjectBidPost projectBidPost = projectBidPostRepository.findByProviderIdAndSystemGeneratedProjectId(
                        ndaRequest.getProviderId(), ndaRequest.getProjectId());
                projectBidPost.setNdaFile(ndaRequest.getNdaFile());
                projectBidPost.setNdaFileName(ndaRequest.getNdaFileName());
                projectBidPost.setNdaApprovalStatus(NdaStatus.PENDING);
                projectBidPost.setProposalStatus(StatusEnum.PROPOSAL_SUBMITTED);
                if (ndaRequest.getNdaFile() != null) {
                    projectBidPost.setNdaStatus(true);
                    projectBidPost.setProposalStatus(StatusEnum.NDA_POSTED);
                }
                projectBidPost.setNdaUploadDate(LocalDate.now());
                projectBidPostRepository.save(projectBidPost);
                return "updated";
            }
            ProjectBidPost projectBidPost = new ProjectBidPost();
            projectBidPost.setSystemGeneratedProjectId(ndaRequest.getProjectId());
            projectBidPost.setProviderId(ndaRequest.getProviderId());
            projectBidPost.setConsumerId(creation.getCompanyId());
            projectBidPost.setNdaFile(ndaRequest.getNdaFile());
            projectBidPost.setNdaFileName(ndaRequest.getNdaFileName());
            projectBidPost.setNdaApprovalStatus(NdaStatus.PENDING);
            projectBidPost.setProposalStatus(StatusEnum.PROPOSAL_SUBMITTED);
            if (ndaRequest.getNdaFile() != null) {
                projectBidPost.setNdaStatus(true);
                projectBidPost.setProposalStatus(StatusEnum.NDA_POSTED);
            }
            projectBidPost.setNdaUploadDate(LocalDate.now());
            projectBidPostRepository.save(projectBidPost);
            return "saved";
    }

    private void saveUpdateBidDocument(ProjectBidPost projectBidPost, ProjectBidPostRequest request) {
        List<ProjectBidDocuments> bidDocumentList = projectBidDocumentsRepository.findByBidId(projectBidPost.getId());
        for (ProjectBidDocumentUploadRequest ndaUpload : request.getBidDocument()) {
            ProjectBidDocuments bidDocuments = new ProjectBidDocuments();
            bidDocuments.setDocument(ndaUpload.getDocument());
            if (bidDocumentList.stream().filter(item -> item.getDocumentId() == ndaUpload.getDocumentId()
                    && item.getBidId() == projectBidPost.getId()).findAny().isPresent()) {
                projectBidDocumentsRepository.deleteByBidIdAndDocumentId(projectBidPost.getId(),
                        ndaUpload.getDocumentId());
            }
            bidDocuments.setDocumentId(ndaUpload.getDocumentId());
            bidDocuments.setProjectId(projectBidPost.getSystemGeneratedProjectId());
            bidDocuments.setDocumentType(ndaUpload.getDocumentType());
            bidDocuments.setFileName(ndaUpload.getFileName());
            bidDocuments.setBidId(projectBidPost.getId());
            bidDocuments.setDocumentStatus(
                    ndaUpload.getDocumentStatus() == null ? "pending" : ndaUpload.getDocumentStatus());
            bidDocuments.setScore(ndaUpload.getScore() == null ? 0.0 : Double.valueOf(ndaUpload.getScore()));
            bidDocuments.setSubmittedOn(LocalDate.now());
            projectBidDocumentsRepository.save(bidDocuments);

        }
    }

    @Override
    public String saveUpdateBidDocument(Long id, String status, Double score) {
        Optional<ProjectBidDocuments> documents = projectBidDocumentsRepository.findById(id);
        if (documents.isPresent()) {
            ProjectBidDocuments document = documents.get();
            document.setDocumentStatus(status);
            document.setScore(score);
            projectBidDocumentsRepository.save(document);
            return "Score and status Updated successfully";
        }
        return "Document not found";
    }

    @Override
    public String deleteBidDocument(Long bidId, Long documentId) throws Exception {
        if (projectBidDocumentsRepository.existsByBidIdAndDocumentId(bidId, documentId)) {
            projectBidDocumentsRepository.deleteByBidIdAndDocumentId(bidId, documentId);
            return "deleted";
        } else {
        	throw new SourceablyCustomeException("Document not found.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String approveProjectNda(String systemGeneratedProjectId, String userId, String ndaComment)
            throws Exception {

        ProjectCreation creation = projectCreationRepository.findByProjectCodeAndIsDeleted(systemGeneratedProjectId,
                false);
        ExceptionUtils.verifyDataNotExistThenThrowException(creation, "Project not found");
        if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(userId,
                systemGeneratedProjectId)) {
            ProjectBidPost projectBidPost = projectBidPostRepository
                    .findByProviderIdAndSystemGeneratedProjectId(userId, systemGeneratedProjectId);
            projectBidPost.setNdaApprovalStatus(NdaStatus.APPROVED);
            projectBidPost.setNdaApprovalDate(LocalDate.now());
            projectBidPost.setProposalStatus(StatusEnum.NDA_APPROVED);
            projectBidPost.setNdaApprovedBy(creation.getCreatedBy());
            projectBidPost.setNdaComment(ndaComment);
            projectBidPostRepository.save(projectBidPost);
            return "nda approved";
        } else {
        	throw new SourceablyCustomeException("NDA not submitted.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    
    }

    @Override
    public String rejectProjectNda(String systemGeneratedProjectId, String userId, String ndaComment) throws Exception {

        ProjectCreation creation = projectCreationRepository.findByProjectCodeAndIsDeleted(systemGeneratedProjectId,
                false);
        ExceptionUtils.verifyDataNotExistThenThrowException(creation,"Project not found.");
        if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(userId,
                systemGeneratedProjectId)) {
            ProjectBidPost projectBidPost = projectBidPostRepository
                    .findByProviderIdAndSystemGeneratedProjectId(userId, systemGeneratedProjectId);
            projectBidPost.setNdaApprovalStatus(NdaStatus.REJECTED);
            projectBidPost.setProposalStatus(StatusEnum.NDA_REJECTED);
            projectBidPost.setNdaComment(ndaComment);
            projectBidPostRepository.save(projectBidPost);
            return "nda rejected";
        } else {
        	throw new SourceablyCustomeException("NDA not submitted.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Object getProjetBidsByProjectId(String systemGeneratedProjectId, Pageable pageable) throws Exception {
        ProjectCreation projectCreation = projectCreationRepository
                .findByProjectCodeAndIsDeleted(systemGeneratedProjectId, false);
        ExceptionUtils.verifyDataNotExistThenThrowException(projectCreation,"Project not found.");
        if (projectBidPostRepository.existsBySystemGeneratedProjectId(systemGeneratedProjectId)) {
            Page<ProjectBidPost> projectBidPostPage = projectBidPostRepository
                    .findBySystemGeneratedProjectId(systemGeneratedProjectId, pageable);

            CompanyRegistration companyReg =getRegisterCompanyByLoggedInUser();

            ExceptionUtils.verifyDataNotExistThenThrowException(companyReg);

            for (ProjectBidPost projectBidPost : projectBidPostPage.getContent()) {
                CompanyRegistration registration = companyRepository.findByUserId(projectBidPost.getProviderId());
                BusinessBasicInfo basicInfo = basicInfoRepository.findByUserId(registration.getUserId());

                projectBidPost.setPhoneNo(registration.getMobileNumber());
                projectBidPost.setCountryCode(registration.getCountryIsocode());
                if (companyReg.getRegistrationType().equalsIgnoreCase("supplier")) {
                    projectBidPost.setProposalStatusValue(
                            projectBidPost != null ? projectBidPost.getProposalStatus().getSupplierValue() : null);
                } else {
                    projectBidPost.setProposalStatusValue(
                            projectBidPost != null ? projectBidPost.getProposalStatus().getCustomerValue() : null);
                }
                try {
                    projectBidPost
                            .setSupplierCompanyaddress(registration.getCountry() + ", " + registration.getCity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                projectBidPost.setServicetagline(basicInfo.getTagLine());
                projectBidPost.setCompanydescription(basicInfo.getCompanyDescription());
                projectBidPost.setCurrency(projectCreation.getCurrency());
                if (companyLogoRepository.existsByUserIdAndIsDeleted(registration.getUserId(), false)) {
                    projectBidPost.setCompanyimage(Optional
                            .ofNullable(companyLogoRepository
                                    .findByUserIdAndIsDeleted(registration.getUserId(), false).getFile())
                            .orElse(null));
                }

                if (projectDocumentUploadRepository.existsByProjectCode(projectCreation.getProjectCode())) {
                    List<ProjectDocumentUpload> projectDocumentUploadList = projectDocumentUploadRepository
                            .findByProjectCode(projectCreation.getProjectCode());
                    for (ProjectDocumentUpload documentUpload : projectDocumentUploadList) {
                        if (projectBidDocumentsRepository.existsByDocumentId(documentUpload.getId())) {
                            ProjectBidDocuments bidDocuments = projectBidDocumentsRepository
                                    .findByDocumentId(documentUpload.getId());
                            documentUpload.setUserUplodedDocumentUrl(bidDocuments.getDocument());
                            documentUpload.setUserUploadedDocumentName(bidDocuments.getFileName());
                        }
                    }
                    projectBidPost.setDocuments(projectDocumentUploadList);

                }

                String name = registration.getCompanyName();
                projectBidPost.setSupplierName(name);
                projectBidPost.setIntendedCost(projectCreation.getInternalBudget());
                projectBidPost.setBidDocuments(projectBidDocumentsRepository.findByBidId(projectBidPost.getId()));

                projectBidPost.setRanking(calculateRanking(projectBidPost.getProviderId()));

            }
            return new PageDto(projectBidPostPage.getContent(), projectBidPostPage.getTotalPages(),
                    projectBidPostPage.getNumberOfElements(), projectBidPostPage.getPageNumber());

        } else {
        	throw new SourceablyCustomeException("Project bid not found.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    
    }

    private double calculateRanking(String supplierId) {
        List<SoftwareEvaluation> projects = softwareEvaluationRepository.findBySupplierId(supplierId);
        return projects.stream().mapToDouble(e -> e.getScore()).sum();
    }

    @Override
    @Transactional
    public String changeBidProposalStatus(String systemGeneratedProjectId, List<String> userIds, String proposalStatus)
            throws Exception {
        AtomicReference<String> status= new AtomicReference<>("");
        userIds.forEach(userId->{
        if (projectBidPostRepository.existsByProviderIdAndSystemGeneratedProjectId(userId, systemGeneratedProjectId)) {
            if (proposalStatus.matches("granted")) {
                List<ProjectBidPost> rejectBid = projectBidPostRepository
                        .findBySystemGeneratedProjectId(systemGeneratedProjectId);
                List<ProjectBidPost> allProjectBid = rejectBid.stream()
                        .filter(predicate -> predicate.getProviderId().equalsIgnoreCase(userId))
                        .map(projectBidReject -> {
                            if (projectBidReject.getProviderId().equalsIgnoreCase(userId)) {
                                projectBidReject.setProposalStatus(StatusEnum.GRANTED);
                                projectBidReject.setNdaApprovalStatus(NdaStatus.GRANTED);
                            } else {
                                projectBidReject.setNdaApprovalStatus(NdaStatus.REJECTED);
                                projectBidReject.setProposalStatus(StatusEnum.WITHDRAW);
                            }

                            return projectBidReject;
                        }).collect(Collectors.toList());

                projectBidPostRepository.saveAll(allProjectBid);
                projectCreationRepository.updateProjectStatus(systemGeneratedProjectId, "Granted");
                status.set("Project granted Successfully");
            }

            ProjectBidPost projectBidPost = projectBidPostRepository.findByProviderIdAndSystemGeneratedProjectId(userId,
                    systemGeneratedProjectId);
            projectBidPost.setProposalStatus(StatusEnum.convertToEnum(proposalStatus));

            projectBidPostRepository.save(projectBidPost);

            status.set("Proposal status changed");
        } else {
            throw new SourceablyCustomeException("Bid doesn't exists.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        });
        return status.get();
    }

    @Override
    public List<CompanyRegistration> getProjectByOptionalProperty(GlobalSearchFilter filter) throws Exception {

        Boolean category = Boolean.FALSE;
        List<CompanyRegistration> resultList;
        StringBuilder queryBuilder = new StringBuilder(" select cr.*  from company_registration cr ");
        if (filter == null) {
            resultList = entityManager.createNativeQuery(queryBuilder.toString(), CompanyRegistration.class)
                    .getResultList();
        }
        if (!StringUtils.isEmpty(filter.getCategory())) {
            category = Boolean.TRUE;
            queryBuilder = queryBuilder
                    .append(" left outer join  pb_provider_skills_matrix sm on cr.user_id=sm.user_id ");
        }
        if (!StringUtils.isEmpty(filter.getInsRequirement())) {
            queryBuilder = queryBuilder
                    .append(" left outer join pb_user_company_details pucd on cr.user_id = pucd.user_id ");
        }
        queryBuilder.append(" where UPPER(cr.registration_type)='SUPPLIER' ");

        if (!StringUtils.isEmpty(filter.getCountry())) {
            queryBuilder.append(" and  UPPER(cr.country)= '" + filter.getCountry().toUpperCase() + "' ");
        }
        if (!StringUtils.isEmpty(filter.getCity())) {
            queryBuilder.append(" and  UPPER(cr.city)= '" + filter.getCity().toUpperCase() + "' ");
        }
        if (!StringUtils.isEmpty(filter.getState())) {
            queryBuilder.append(" and  UPPER(cr.state)= '" + filter.getState().toUpperCase() + "' ");
        }

        queryBuilder
                .append(category
                        ? " and sm.skill_matrix_id in  "
                        + convertString(Arrays.asList(filter.getCategory(), filter.getSubCategory())) + " "
                        : "");

        resultList = entityManager.createNativeQuery(queryBuilder.toString(), CompanyRegistration.class)
                .getResultList();

        if (!CollectionUtils.isEmpty(filter.getProCyberCertificateIds())) {
            List<CyberScoreDto> cyberScore = certificationService.getPercentage("L1", null);
            for (String cyberFilter : filter.getProCyberCertificateIds()) {
                String[] KeyPercentage = cyberFilter.split("_");
                Optional<String> supplierId = cyberScore.stream().map(cyberScoreDto -> {
                    if (cyberScoreDto.getCertificateId() != Long.valueOf(KeyPercentage[0])
                            || cyberScoreDto.getCertificatePercentage() < Double.valueOf(KeyPercentage[1])) {
                        return cyberScoreDto.getSupplierId();
                    }
                    return "";
                }).findFirst();
                resultList.removeIf(result -> StringUtils.isEmpty(supplierId.get()));
            }
        }
        if (!CollectionUtils.isEmpty(filter.getProBusCertificateIds())) {
            for (CompanyRegistration registration : resultList) {
                List<PBcertificateScorePercentage> percentages = bcertificatePercentage(registration.getUserId());

            }

        }
        if (filter.getRating() != null) {
            resultList.removeIf(registration -> certificationService
                    .getProfileStrengthByUserID(registration.getUserId()) < filter.getRating());

        }
        return resultList;
    }

    @Override
    public List<CountryRevenueDto> getRevenuePerformance(String startDate, String endDate) throws Exception{
    	try
    	{
        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);

        List<ProjectBidPost> listOfBidPost = new ArrayList<>();
        CompanyRegistration registration = getRegisterCompanyByLoggedInUser();
        ExceptionUtils.verifyDataNotExistThenThrowException(registration);
        if (registration.getRegistrationType().equalsIgnoreCase("supplier")) {
            listOfBidPost = projectBidPostRepository.findAllByProjectBidDateBetween(sDate, eDate).stream()
                    .filter(e -> registration.getUserId().equalsIgnoreCase(e.getProviderId())
                            && e.getNdaApprovalStatus().name().equalsIgnoreCase(NdaStatus.GRANTED.name()))
                    .collect(Collectors.toList());
        } else {
//					 List<ProjectCreation> projectList = projectCreationRepository.findByCompanyIdAndStartDateBetween(registration.getUserId(),sDate,eDate)
//							 .stream().collect(Collectors.toList());

            listOfBidPost = projectBidPostRepository.findAllByProjectBidDateBetween(sDate, eDate).stream()
                    .filter(e -> registration.getUserId().equalsIgnoreCase(e.getConsumerId())
                            && e.getNdaApprovalStatus().name().equalsIgnoreCase(NdaStatus.GRANTED.name()))
                    .collect(Collectors.toList());
        }
        List<ProjectCreation> projectCreation = projectCreationRepository.findAllByProjectCodeIn(
                listOfBidPost.stream().map(ProjectBidPost::getSystemGeneratedProjectId).collect(Collectors.toList()));
        List<CompanyRegistration> allRegisterCompany = companyRepository.findAll();

        List<CountryRevenueDto> result = new ArrayList<>();
        for (ProjectCreation project : projectCreation) {
            CountryRevenueDto revenueDto = new CountryRevenueDto();
            Optional<ProjectBidPost> projectBid = listOfBidPost.stream()
                    .filter(bidpost -> bidpost.getSystemGeneratedProjectId().equalsIgnoreCase(project.getProjectCode()))
                    .findFirst();
            revenueDto.setProjectName(project.getProjectTitle());
            revenueDto.setRevenueValue(projectBid.isPresent() ? projectBid.get().getProjectCost() : 0);
            revenueDto.setProjectStartDate(project.getStartDate());
            if (registration.getRegistrationType().equalsIgnoreCase("supplier")) {
                Optional<CompanyRegistration> customer = allRegisterCompany.stream()
                        .filter(company -> company.getUserId().equalsIgnoreCase(projectBid.get().getConsumerId()))
                        .findFirst();
                if (customer.isPresent()) {

                    revenueDto.setCity(customer.get().getCity());
                    revenueDto.setState(customer.get().getState());
                    revenueDto.setCountry(customer.get().getCountry());
                    revenueDto.setName(customer.get().getFirstName() + " " + customer.get().getLastName());
                }
            } else {
                Optional<CompanyRegistration> supplier = allRegisterCompany.stream()
                        .filter(company -> company.getUserId().equalsIgnoreCase(projectBid.get().getProviderId()))
                        .findFirst();
                if (supplier.isPresent()) {
                    revenueDto.setCity(supplier.get().getCity());
                    revenueDto.setState(supplier.get().getState());
                    revenueDto.setCountry(supplier.get().getCountry());
                    revenueDto.setName(supplier.get().getFirstName() + " " + supplier.get().getLastName());
                }
            }
            result.add(revenueDto);
        }
        return result;
    	}
    	catch (Exception e) {
    		throw new SourceablyCustomeException("Revenue performance error", HttpStatus.UNPROCESSABLE_ENTITY);
		}
    }

    @Override
    public List<InternalBudgetProposalStatusDto> getInternalBudgetProposalStatus(String status, String startDate,
                                                                                 String endDate) throws Exception{

        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);
        List<ProjectCreation> totalProjects = getProjectCreationsByLoginUser(sDate, eDate);
        CompanyRegistration loginUser = getRegisterCompanyByLoggedInUser();
        List<ProjectMileStoneList> mileStoneLists = projectMilestoneRepository.findAllByProjectIdIn(
                totalProjects.stream().map(ProjectCreation::getProjectCode).collect(Collectors.toList()));
        ExceptionUtils.verifyDataNotExistThenThrowException(mileStoneLists);
        List<String> projectIds;
        if (status.equalsIgnoreCase("all")) {
            projectIds = mileStoneLists.stream().map(ProjectMileStoneList::getProjectId).collect(Collectors.toList());
        } else {
            projectIds = mileStoneLists.stream()
                    .filter(milestone -> milestone.getMilestoneType().equals(StatusEnum.convertToEnum(status)))
                    .map(ProjectMileStoneList::getProjectId).collect(Collectors.toList());
        }

        List<InternalBudgetProposalStatusDto> finalDTOs = new ArrayList<InternalBudgetProposalStatusDto>();
        totalProjects.stream().filter(projectCreation -> projectIds.contains(projectCreation.getProjectCode()))
                .forEach(project -> {

                    Optional<ProjectMileStoneList> ProjectMileStone = mileStoneLists.stream()
                            .filter(mileStone -> mileStone.getProjectId().equalsIgnoreCase(project.getProjectCode()))
                            .findFirst();
                    if (loginUser.getRegistrationType().equalsIgnoreCase("supplier")) {
                        String[] budgetRange = project.getBudgetRange().split("-");
                        finalDTOs.add(InternalBudgetProposalStatusDto.builder().projectName(project.getProjectTitle())
                                .internalBudget(Double.valueOf(project.getInternalBudget()))
                                .highProposedCost(budgetRange[1])
                                .proposalStatus(ProjectMileStone.isPresent()
                                        ? ProjectMileStone.get().getMilestoneType().getSupplierValue()
                                        : "")
                                .lowProposedCost(budgetRange[0]).build());
                    } else {
                        List<ProjectBidPost> allProjectBidPost = projectBidPostRepository
                                .findBySystemGeneratedProjectIdIn(projectIds);
                        List<ProjectBidPost> sorttedList = allProjectBidPost.parallelStream()
                                .filter(bPost -> bPost.getProposalStatus() != null
                                        && bPost.getSystemGeneratedProjectId().equals(project.getProjectCode()))
                                .sorted(Comparator.comparing(ProjectBidPost::getProjectCost))
                                .collect(Collectors.toList());

                        ProjectBidPost lowBidPost = CollectionUtils.isEmpty(sorttedList) ? null : sorttedList.get(0);
                        ProjectBidPost HighBidPost = CollectionUtils.isEmpty(sorttedList) ? null
                                : sorttedList.get(sorttedList.size() - 1);
                        finalDTOs.add(InternalBudgetProposalStatusDto.builder().projectName(project.getProjectTitle())
                                .internalBudget(Double.valueOf(project.getInternalBudget()))
                                .highProposedCost(
                                        HighBidPost != null ? String.valueOf(HighBidPost.getProjectCost()) : "N/A")
                                .proposalStatus(ProjectMileStone.isPresent()
                                        ? ProjectMileStone.get().getMilestoneType().getCustomerValue()
                                        : "")
                                .lowProposedCost(
                                        lowBidPost != null ? String.valueOf(lowBidPost.getProjectCost()) : "N/A")
                                .build());
                    }

                });

        return finalDTOs.stream().sorted(Comparator.comparing(InternalBudgetProposalStatusDto::getInternalBudget))
                .limit(10).collect(Collectors.toList());

        /*
         * Collections.sort(finalDTOs, (InternalBudgetProposalStatusDto o1,
         * InternalBudgetProposalStatusDto o2) -> o1.getInternalBudget().compareTo(
         * o2.getInternalBudget() )); return finalDTOs;
         */
    }

    @Override
    public List<DashBoardDto> getAllProjectCostByMonth(String startDate, String endDate) {

        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);

        List<DashBoardDto> dtos = new ArrayList<DashBoardDto>();

        List<ProjectCreation> totalProjects = getProjectCreationsByLoginUser(sDate, eDate);

        Integer totalProject = totalProjects.size();

        dtos.add(DashBoardDto.builder().projects(totalProject).label("Total Projects")
                .projectCost(projectCost(null, totalProjects))
                .countTrend(totalProject > 0 ? Double.valueOf(totalProject * 100 / totalProject) : 0).build());

        Map<ProjectStatus, List<ProjectCreation>> collect = totalProjects.stream()
                .filter(predicate -> predicate.getProjectStatus() != null)
                .collect(Collectors.groupingBy(ProjectCreation::getProjectStatus));
        Long totalCost = totalProjects.stream().filter(predicate -> predicate.getInternalBudget() != null)
                .mapToLong(ProjectCreation::getInternalBudget).sum();
        collect.entrySet().forEach(projectCreation -> {
            if (projectCreation.getKey().equals(ProjectStatus.IN_PROGRESS)) {
                Long projectCost = projectCreation.getValue().stream().mapToLong(ProjectCreation::getInternalBudget)
                        .sum();
                Double revenueTrend = Double.valueOf(projectCost) * 100 / Double.valueOf(totalCost);
                dtos.add(DashBoardDto.builder().projects(projectCreation.getValue().size()).label("In Progress")
                        .projectCost(projectCost).revenueTrend(revenueTrend)
                        .countTrend(projectCreation.getValue().size() > 0
                                ? (Double.valueOf(projectCreation.getValue().size()) * 100 / totalProject)
                                : 0)
                        .build());
            } else if (projectCreation.getKey().equals(ProjectStatus.CANCEL)
                    || projectCreation.getKey().equals(ProjectStatus.HOLD)
                    || projectCreation.getKey().equals(ProjectStatus.REJECTED)) {
                Long projectCost = projectCreation.getValue().stream().mapToLong(ProjectCreation::getInternalBudget)
                        .sum();
                Double revenueTrend = Double.valueOf(projectCost) * 100 / Double.valueOf(totalCost);
                dtos.add(DashBoardDto.builder().projects(projectCreation.getValue().size()).label("Rejected")
                        .projectCost(projectCost).revenueTrend(revenueTrend)
                        .countTrend(projectCreation.getValue().size() > 0
                                ? (Double.valueOf(projectCreation.getValue().size()) * 100 / totalProject)
                                : 0)
                        .build());
            } else if (projectCreation.getKey().equals(ProjectStatus.PENDING)
                    || projectCreation.getKey().equals(ProjectStatus.DRAFT)) {
                Long projectCost = projectCreation.getValue().stream().mapToLong(ProjectCreation::getInternalBudget)
                        .sum();
                Double revenueTrend = Double.valueOf(projectCost) * 100 / Double.valueOf(totalCost);
                dtos.add(DashBoardDto.builder().projects(projectCreation.getValue().size()).label("Pending")
                        .projectCost(projectCost).revenueTrend(revenueTrend)
                        .countTrend(projectCreation.getValue().size() > 0
                                ? (Double.valueOf(projectCreation.getValue().size()) * 100 / totalProject)
                                : 0)
                        .build());
            } else if (projectCreation.getKey().equals(ProjectStatus.PUBLISHED)) {
                Long projectCost = projectCreation.getValue().stream().mapToLong(ProjectCreation::getInternalBudget)
                        .sum();
                Double revenueTrend = Double.valueOf(projectCost) * 100 / Double.valueOf(totalCost);
                dtos.add(DashBoardDto.builder().projects(projectCreation.getValue().size()).label("Published")
                        .projectCost(projectCost).revenueTrend(revenueTrend)
                        .countTrend(projectCreation.getValue().size() > 0
                                ? (Double.valueOf(projectCreation.getValue().size()) * 100 / totalProject)
                                : 0)
                        .build());
            } else if (projectCreation.getKey().equals(ProjectStatus.GRANTED)) {
                Long projectCost = projectCreation.getValue().stream().mapToLong(ProjectCreation::getInternalBudget)
                        .sum();
                Double revenueTrend = Double.valueOf(projectCost) * 100 / Double.valueOf(totalCost);
                dtos.add(DashBoardDto.builder().projects(projectCreation.getValue().size()).label("Granted")
                        .projectCost(projectCost).revenueTrend(revenueTrend)
                        .countTrend(projectCreation.getValue().size() > 0
                                ? (Double.valueOf(projectCreation.getValue().size()) * 100 / totalProject)
                                : 0)
                        .build());
            }

        });

        return dtos;

    }

    private List<ProjectCreation> getProjectCreationsByLoginUser(LocalDate sDate, LocalDate eDate) {
        CompanyRegistration loginCus = getRegisterCompanyByLoggedInUser();
        ExceptionUtils.verifyDataNotExistThenThrowException(loginCus);
        List<ProjectCreation> totalProjects = new ArrayList<>();

        if (loginCus.getRegistrationType().equalsIgnoreCase("supplier")) {
            List<String> projectIds = projectBidPostRepository.getByProviderId(loginCus.getUserId()).stream()
                    .map(ProjectBidPost::getSystemGeneratedProjectId).collect(Collectors.toList());
            totalProjects = projectCreationRepository.findAllByProjectCodeIn(projectIds);
        } else {
            totalProjects = projectCreationRepository.findByCompanyId(loginCus.getUserId());
        }
        ExceptionUtils.verifyDataNotExistThenThrowException(totalProjects,"Projects not found");
        totalProjects = totalProjects.stream()
                .filter(predicate -> predicate.getCreatedEpochTime() != null
                        && predicate.getCreatedEpochTime().isAfter(sDate)
                        && predicate.getCreatedEpochTime().isBefore(eDate))
                .collect(Collectors.toList());
        return totalProjects;
    }

    @Override
    public List<PBcertificateScorePercentage> bcertificatePercentage(String supplierId) throws Exception{

        List<ProviderBCertificateData> allPBCertificationDatas = providerBCertificateDataRepository
                .findAllByUserId(supplierId);
        ExceptionUtils.verifyDataNotExistThenThrowException(allPBCertificationDatas,"Provider business certificate not found.");

        List<BusinessCertificates> businessCertificates = businessCertificatesRepository.findAll();
        ExceptionUtils.verifyDataNotExistThenThrowException(allPBCertificationDatas,"Business certificate not found.");
        List<PBcertificateScorePercentage> finalList = calculateBcertificateData(allPBCertificationDatas,
                businessCertificates);
        return finalList;
    }

    private List<PBcertificateScorePercentage> calculateBcertificateData(
            List<ProviderBCertificateData> allPBCertificationDatas, List<BusinessCertificates> businessCertificates) {
        Map<Long, String> mapOfParentCertificationName = businessCertificates.stream().collect(Collectors
                .toMap(BusinessCertificates::getBusinessCertificateId, BusinessCertificates::getCertificateName));

        Map<Long, List<BusinessCertificates>> mapOfParentWithChild = businessCertificates.parallelStream()
                .collect(Collectors.groupingBy(bcertificate -> bcertificate.getParent().getBusinessCertificateId()));

        List<PBcertificateScorePercentage> finalList = new ArrayList<PBcertificateScorePercentage>();
        // map of parent
        for (Map.Entry<Long, List<BusinessCertificates>> entry : mapOfParentWithChild.entrySet()) {

            Map<Long, String> mapOfCertificateIdName = entry.getValue().stream().collect(Collectors
                    .toMap(BusinessCertificates::getBusinessCertificateId, BusinessCertificates::getCertificateName));

            Set<Long> totalCert = mapOfCertificateIdName.keySet();
            List<ProviderBCertificateData> attemptCertificateData = allPBCertificationDatas.stream()
                    .filter(data -> totalCert.contains(data.getCertificateId())).collect(Collectors.toList());

            List<PBcertificateScorePercentage.ChildCertification> childCertificationList = new ArrayList<>();
            attemptCertificateData.forEach(certificateData -> {

                BCertificatePercentage bCertificatePercentage = certificateData.getbCertificatePercentage();
                long totalScoreEarn = 0l;
                if (bCertificatePercentage != null) {
                    totalScoreEarn = bCertificatePercentage.getCommentScore()
                            + bCertificatePercentage.getAttachedScore()
                            + bCertificatePercentage.getSourceablyVerifiedScore()
                            + bCertificatePercentage.getValidDocumentDateScore();
                }

                PBcertificateScorePercentage.ChildCertification dto = PBcertificateScorePercentage.ChildCertification
                        .builder().certificateId(certificateData.getCertificateId())
                        .certificateName(certificateData.getCertificateId() != null
                                ? mapOfCertificateIdName.get(certificateData.getCertificateId())
                                : "_")
                        .score(totalScoreEarn > 0 ? Double.valueOf(totalScoreEarn) : 0)
                        .userId(certificateData.getUserId()).build();

                childCertificationList.add(dto);

            });

            Double sumOfScore = childCertificationList.stream().mapToDouble(e -> e.getScore()).sum();
            PBcertificateScorePercentage pBcertificateScorePercentage = new PBcertificateScorePercentage();
            pBcertificateScorePercentage.setChildCertifications(childCertificationList);
            pBcertificateScorePercentage.setAverageScore(
                    childCertificationList.size() > 0 ? sumOfScore / Double.valueOf(childCertificationList.size())
                            : 0.0);
            pBcertificateScorePercentage.setCertificateId(entry.getKey());
            Double percentgate = 0.0;
            if (attemptCertificateData.size() > 0 && totalCert.size() > 0) {
                percentgate = Double.valueOf(attemptCertificateData.size()) / Double.valueOf(totalCert.size()) * 100;
            }
            pBcertificateScorePercentage.setCertificateName(mapOfParentCertificationName.get(entry.getKey()));
            pBcertificateScorePercentage.setTotalAttemptCert(Long.valueOf(attemptCertificateData.size()));
            pBcertificateScorePercentage.setCertificatePercentage(percentgate);
            pBcertificateScorePercentage.setTotalCert(Long.valueOf(totalCert.size()));

            finalList.add(pBcertificateScorePercentage);
        }
        return finalList;
    }

    private Long projectCost(Set<Long> ids, List<ProjectCreation> list) {
        Long totalCost = 0l;
        for (ProjectCreation projectCreation : list) {
            if (ids == null) {
                totalCost = totalCost + Long.valueOf(projectCreation.getInternalBudget());
            } else if (ids.contains(projectCreation.getProjectId())) {
                totalCost = totalCost + Long.valueOf(projectCreation.getInternalBudget());
            }
        }

        return totalCost;
    }

    private String convertString(List<String> list) {

        String res = String.join("','", list);
        return "('" + res + "')";

    }

    private LocalDate convertToLocalDate(Long epochTime) {
        return Instant.ofEpochMilli(epochTime).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public List<GlobalBussinessGraphData> globalCyberGraphData(String startDate, String endDate, String supplierId) {
    	try
    	{
        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);

        Map<String, List<ProviderBCertificateData>> allPBCertificationDatas = providerBCertificateDataRepository
                .findAll().stream()
                .filter(predicate -> predicate.getStartDate() != null && predicate.getEndDate() != null
                        && convertToLocalDate(predicate.getStartDate()).isAfter(sDate)
                        && convertToLocalDate(predicate.getEndDate()).isBefore(eDate))
                .collect(Collectors.groupingBy(ProviderBCertificateData::getUserId));
        List<BusinessCertificates> businessCertificates = businessCertificatesRepository.findAll();
        if (supplierId == null) {
            CompanyRegistration regCompany = getRegisterCompanyByLoggedInUser();
            supplierId = regCompany.getUserId();
        }

        List<PBcertificateScorePercentage> allPbCert = new ArrayList<PBcertificateScorePercentage>();
        List<PBcertificateScorePercentage> loginUserCert = new ArrayList<PBcertificateScorePercentage>();
        // collect detail of login User and all userDetails
        for (Map.Entry<String, List<ProviderBCertificateData>> keyValue : allPBCertificationDatas.entrySet()) {

            if (keyValue.getKey().equalsIgnoreCase(supplierId)) {
                loginUserCert.addAll(calculateBcertificateData(keyValue.getValue(), businessCertificates));
            } else {
                allPbCert.addAll(calculateBcertificateData(keyValue.getValue(), businessCertificates));
            }
        }

        Set<String> allUsers = allPBCertificationDatas.keySet();

        String finalSupplierId = supplierId;
        allUsers.removeIf(item -> item.equalsIgnoreCase(finalSupplierId));
//
        Map<String, List<PBcertificateScorePercentage>> mapOfCertificate = allPbCert.stream()
                .collect(Collectors.groupingBy(PBcertificateScorePercentage::getCertificateName));
        List<GlobalBussinessGraphData> finalValue = new ArrayList<>();
        for (Map.Entry<String, List<PBcertificateScorePercentage>> mapEntry : mapOfCertificate.entrySet()) {

            Double sumOfScoreall = mapEntry.getValue().stream().mapToDouble(e -> e.getAverageScore()).sum();
            Double sumOfScoreByUser = loginUserCert.stream()
                    .filter(e -> e.getCertificateName().equalsIgnoreCase(mapEntry.getKey()))
                    .mapToDouble(e -> e.getAverageScore()).sum();
            finalValue.add(GlobalBussinessGraphData.builder().certificateName(mapEntry.getKey())
                    .globalMarket(
                            Double.valueOf(sumOfScoreall + sumOfScoreByUser) / Double.valueOf(allUsers.size() + 1))
                    .aquirecreations(sumOfScoreByUser).build());
        }

        return finalValue;
    	}catch (Exception e) {
    		throw new SourceablyCustomeException("Graph data not exist", HttpStatus.UNPROCESSABLE_ENTITY);
		}
    }

    @Override
    public List<ProjectCreation> getProjectsByStatus(String projectSatus)throws Exception {
        CompanyRegistration loginCus = getRegisterCompanyByLoggedInUser();
        ExceptionUtils.verifyDataNotExistThenThrowException(loginCus);
        List<ProjectCreation> projectCreation = projectCreationRepository
                .findByProjectStatusAndCompanyId(ProjectStatus.convertToEnum(projectSatus), loginCus.getUserId());
        return projectCreation;
    }

    private CompanyRegistration getRegisterCompanyByLoggedInUser() {

        String loginUserName="";
        Optional<HttpRequest<Object>> securityContext = ServerRequestContext.currentRequest();
        if(securityContext.isPresent()){
            Optional<Principal> principal = securityContext.get().getUserPrincipal();
            if(principal.isPresent()){
                loginUserName= principal.get().getName();
            }

        }
        return companyRepository.findByEmail(loginUserName.toLowerCase());
    }

    @Override
    public String updateProjectstatus(String projectCode, String projectStatus)throws Exception {
        ProjectCreation project = projectCreationRepository.findByProjectCode(projectCode);
        ExceptionUtils.verifyDataNotExistThenThrowException(project,"Project not found.");
            projectCreationRepository.updateProjectStatus(projectCode, projectStatus);
            return "Status updated successfully";
    }

    @Override
    @Transactional
    public String marketUpdate(String projectCode, Boolean publish) {
        projectCreationRepository.updateMarket(projectCode, publish);
        if (publish) {
            return "project published to Market Place";
        } else {
            return "project unpublished to Market Place";
        }

    }


}