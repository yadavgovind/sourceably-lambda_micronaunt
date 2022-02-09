package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.controller.SkillMatrixController;
import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.SkillMatrixCategory;
import com.oms.projectbuddy.model.SupplierSkillMatrix;
import com.oms.projectbuddy.model.request.SkillMatrixListRequest;
import com.oms.projectbuddy.model.request.SkillMatrixSaveRequest;
import com.oms.projectbuddy.model.request.SkillMatrixUpdateRequest;
import com.oms.projectbuddy.model.response.PageDto;
import com.oms.projectbuddy.repository.CompanyRepository;
import com.oms.projectbuddy.repository.SkillMatrixCategoryRepository;
import com.oms.projectbuddy.repository.SupplierSkillMatrixRepository;
import com.oms.projectbuddy.services.ISkillMatrixService;
import com.oms.projectbuddy.utils.ExceptionUtils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Service
public class SkillMatrixService implements ISkillMatrixService {
    private static final Logger LOG = LoggerFactory.getLogger(SkillMatrixService.class);

    @Autowired
    private SkillMatrixCategoryRepository skillMatrixCategoryRepository;

    @Autowired
    private SupplierSkillMatrixRepository supplierSkillMatrixRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void bukUploadSkillMatrixCategory(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 1010) {
                XSSFRow row = worksheet.getRow(index);
                try {
                    DataFormatter dataFormatter = new DataFormatter();
                    String level1 = "";
                    String level2 = "";
                    String level3 = "";
                    String level4 = "";
                    String level5 = "";
                    String level6 = "";
                    String level7 = "";

                    String id = row.getCell(0).getRawValue();
                    if (row.getCell(0) != null) {
                        level1 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(0))).orElse(null);
                    }
                    if (row.getCell(1) != null) {
                        level2 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(1))).orElse(null);
                    }
                    if (row.getCell(2) != null) {
                        level3 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(2))).orElse(null);
                    }
                    if (row.getCell(3) != null) {
                        level4 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(3))).orElse(null);
                    }
                    if (row.getCell(4) != null) {
                        level5 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(4))).orElse(null);
                    }
                    if (row.getCell(5) != null) {
                        level6 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(5))).orElse(null);
                    }
                    if (row.getCell(6) != null) {
                        level7 = Optional.ofNullable(dataFormatter.formatCellValue(row.getCell(6))).orElse(null);
                    }
                    if (!level1.equals("")) {
                        saveSkillMatrix(level1, false, "", "LEVEL-1");
                    }
                    if (!level2.equals("")) {
                        saveSkillMatrix(level2, true, level1, "LEVEL-2");
                    }
                    if (!level3.equals("")) {
                        saveSkillMatrix(level3, true, level2, "LEVEL-3");
                    }
                    if (!level4.equals("")) {
                        saveSkillMatrix(level4, true, level3, "LEVEL-4");
                    }
                    if (!level5.equals("")) {
                        saveSkillMatrix(level5, true, level4, "LEVEL-5");
                    }
                    if (!level6.equals("")) {
                        saveSkillMatrix(level6, true, level5, "LEVEL-6");
                    }
                    if (!level7.equals("")) {
                        saveSkillMatrix(level7, true, level6, "LEVEL-7");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object getParentCategoriesdetails(Pageable pageable) {
        Page<SkillMatrixCategory> skillMatrixCategories = skillMatrixCategoryRepository.findByIsSubCategory(false, pageable);
        return new PageDto(skillMatrixCategories.getContent(), skillMatrixCategories.getTotalPages(), skillMatrixCategories.getTotalElements(), skillMatrixCategories.getNumber());
    }

    @Override
    public PageDto getChildSkillCategoryBySkillId(String skillCategoryId, Pageable pageable) throws Exception {
        SkillMatrixCategory parent = getCategoryById(skillCategoryId);
        ExceptionUtils.verifyDataNotExistThenThrowException(parent);
        LOG.info("In Service For The Fetch The Details OF The Child Details By Parent ID " + skillCategoryId + " Level of parent  is" + parent.getLevel());

        Page<SkillMatrixCategory> skillMatrixCategories = skillMatrixCategoryRepository.findByParentAndIsSubCategory(parent, true, pageable);

        LOG.info("Data Is Retrived Success for the parent id " + skillCategoryId + " and Level is " + parent.getLevel());
        return new PageDto(skillMatrixCategories.getContent(), skillMatrixCategories.getTotalPages(), skillMatrixCategories.getTotalElements(), skillMatrixCategories.getNumber());
    }

    private List<SkillMatrixCategory> getChildSkillCategoryBySkillIdTwo(String skillCategoryId, Pageable pageable) throws Exception {
        SkillMatrixCategory parent = getCategoryById(skillCategoryId);
        Page<SkillMatrixCategory> skillMatrixCategories = skillMatrixCategoryRepository.findByParentAndIsSubCategory(parent, true, pageable);
        return skillMatrixCategories.getContent();
    }

    @Override
    public Object getAllLevelDetailsBySkillMatrixId(String skillMatrixId) throws Exception {
        Map<String, Object> allChildHearuchyDetails = new HashMap<>();
        SkillMatrixCategory skillMatrixCategory = getCategoryById(skillMatrixId);
        allChildHearuchyDetails.put("selectedCategoryId", skillMatrixId);
        allChildHearuchyDetails.put("selectedCategoryName", skillMatrixCategory.getSkillMatrixName());
        allChildHearuchyDetails.put("selectedLevel", skillMatrixCategory.getLevel());
        if (skillMatrixCategory.getLevel().equals("LEVEL-1")) {
            allChildHearuchyDetails.put("LEVEL-1", skillMatrixCategoryRepository.findByLevelAndLevel1("LEVEL-1", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-2", skillMatrixCategoryRepository.findByLevelAndLevel1("LEVEL-2", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-3", skillMatrixCategoryRepository.findByLevelAndLevel1("LEVEL-3", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-4", skillMatrixCategoryRepository.findByLevelAndLevel1("LEVEL-4", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-5", skillMatrixCategoryRepository.findByLevelAndLevel1("LEVEL-5", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-6", skillMatrixCategoryRepository.findByLevelAndLevel1("LEVEL-6", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-7", skillMatrixCategoryRepository.findByLevelAndLevel1("LEVEL-7", skillMatrixCategory.getSkillMatrixId()));
        }
        if (skillMatrixCategory.getLevel().equals("LEVEL-2")) {
            allChildHearuchyDetails.put("LEVEL-1", skillMatrixCategoryRepository.findByLevelAndLevel2("LEVEL-1", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-2", skillMatrixCategoryRepository.findByLevelAndLevel2("LEVEL-2", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-3", skillMatrixCategoryRepository.findByLevelAndLevel2("LEVEL-3", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-4", skillMatrixCategoryRepository.findByLevelAndLevel2("LEVEL-4", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-5", skillMatrixCategoryRepository.findByLevelAndLevel2("LEVEL-5", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-6", skillMatrixCategoryRepository.findByLevelAndLevel2("LEVEL-6", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-7", skillMatrixCategoryRepository.findByLevelAndLevel2("LEVEL-7", skillMatrixCategory.getSkillMatrixId()));
        }
        if (skillMatrixCategory.getLevel().equals("LEVEL-3")) {
            allChildHearuchyDetails.put("LEVEL-1", skillMatrixCategoryRepository.findByLevelAndLevel3("LEVEL-1", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-2", skillMatrixCategoryRepository.findByLevelAndLevel3("LEVEL-2", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-3", skillMatrixCategoryRepository.findByLevelAndLevel3("LEVEL-3", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-4", skillMatrixCategoryRepository.findByLevelAndLevel3("LEVEL-4", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-5", skillMatrixCategoryRepository.findByLevelAndLevel3("LEVEL-5", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-6", skillMatrixCategoryRepository.findByLevelAndLevel3("LEVEL-6", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-7", skillMatrixCategoryRepository.findByLevelAndLevel3("LEVEL-7", skillMatrixCategory.getSkillMatrixId()));
        }
        if (skillMatrixCategory.getLevel().equals("LEVEL-4")) {
            allChildHearuchyDetails.put("LEVEL-1", skillMatrixCategoryRepository.findByLevelAndLevel4("LEVEL-1", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-2", skillMatrixCategoryRepository.findByLevelAndLevel4("LEVEL-2", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-3", skillMatrixCategoryRepository.findByLevelAndLevel4("LEVEL-3", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-4", skillMatrixCategoryRepository.findByLevelAndLevel4("LEVEL-4", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-5", skillMatrixCategoryRepository.findByLevelAndLevel4("LEVEL-5", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-6", skillMatrixCategoryRepository.findByLevelAndLevel4("LEVEL-6", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-7", skillMatrixCategoryRepository.findByLevelAndLevel4("LEVEL-7", skillMatrixCategory.getSkillMatrixId()));
        }
        if (skillMatrixCategory.getLevel().equals("LEVEL-5")) {
            allChildHearuchyDetails.put("LEVEL-1", skillMatrixCategoryRepository.findByLevelAndLevel5("LEVEL-1", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-2", skillMatrixCategoryRepository.findByLevelAndLevel5("LEVEL-2", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-3", skillMatrixCategoryRepository.findByLevelAndLevel5("LEVEL-3", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-4", skillMatrixCategoryRepository.findByLevelAndLevel5("LEVEL-4", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-5", skillMatrixCategoryRepository.findByLevelAndLevel5("LEVEL-5", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-6", skillMatrixCategoryRepository.findByLevelAndLevel5("LEVEL-6", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-7", skillMatrixCategoryRepository.findByLevelAndLevel5("LEVEL-7", skillMatrixCategory.getSkillMatrixId()));
        }
        if (skillMatrixCategory.getLevel().equals("LEVEL-6")) {
            allChildHearuchyDetails.put("LEVEL-1", skillMatrixCategoryRepository.findByLevelAndLevel6("LEVEL-1", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-2", skillMatrixCategoryRepository.findByLevelAndLevel6("LEVEL-2", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-3", skillMatrixCategoryRepository.findByLevelAndLevel6("LEVEL-3", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-4", skillMatrixCategoryRepository.findByLevelAndLevel6("LEVEL-4", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-5", skillMatrixCategoryRepository.findByLevelAndLevel6("LEVEL-5", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-6", skillMatrixCategoryRepository.findByLevelAndLevel6("LEVEL-6", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-7", skillMatrixCategoryRepository.findByLevelAndLevel6("LEVEL-7", skillMatrixCategory.getSkillMatrixId()));
        }
        if (skillMatrixCategory.getLevel().equals("LEVEL-7")) {
            allChildHearuchyDetails.put("LEVEL-1", skillMatrixCategoryRepository.findByLevelAndLevel7("LEVEL-1", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-2", skillMatrixCategoryRepository.findByLevelAndLevel7("LEVEL-2", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-3", skillMatrixCategoryRepository.findByLevelAndLevel7("LEVEL-3", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-4", skillMatrixCategoryRepository.findByLevelAndLevel7("LEVEL-4", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-5", skillMatrixCategoryRepository.findByLevelAndLevel7("LEVEL-5", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-6", skillMatrixCategoryRepository.findByLevelAndLevel7("LEVEL-6", skillMatrixCategory.getSkillMatrixId()));
            allChildHearuchyDetails.put("LEVEL-7", skillMatrixCategoryRepository.findByLevelAndLevel7("LEVEL-7", skillMatrixCategory.getSkillMatrixId()));
        }
        return allChildHearuchyDetails;
    }

    @Override
    public void updateLevelsHearichy(String level) {
        List<SkillMatrixCategory> skillMatrixCategories = skillMatrixCategoryRepository.findByLevel(level);
        ExceptionUtils.verifyDataNotExistThenThrowException(skillMatrixCategories);
        List<SkillMatrixCategory> skillMatrixCategoryList = new ArrayList<>();
        for (SkillMatrixCategory skillMatrixCategory : skillMatrixCategories) {
            skillMatrixCategoryList.add(updateTheLevel(skillMatrixCategory));
        }
        skillMatrixCategoryRepository.saveAll(skillMatrixCategoryList);

    }


    private SkillMatrixCategory updateTheLevel(SkillMatrixCategory skillMatrixCategory) {
        if (skillMatrixCategory.getIsSubCategory()) {
            SkillMatrixCategory parentCategory = skillMatrixCategory.getParent();
            HashMap<String, String> hearichyDetails = new HashMap<>();
            hearichyDetails.put("LEVEL-1", "NA# ");
            hearichyDetails.put("LEVEL-2", "NA# ");
            hearichyDetails.put("LEVEL-3", "NA#");
            hearichyDetails.put("LEVEL-4", "NA#");
            hearichyDetails.put("LEVEL-5", "NA#");
            hearichyDetails.put("LEVEL-6", "NA#");
            hearichyDetails.put("LEVEL-7", "NA#");
            while (parentCategory.getIsSubCategory()) {
                hearichyDetails.put(parentCategory.getLevel(), parentCategory.getSkillMatrixId() + "#" + parentCategory.getSkillMatrixName());
                parentCategory = parentCategory.getParent();
                if (parentCategory.getLevel().equals("LEVEL-1")) {
                    break;
                }
            }
            hearichyDetails.put(skillMatrixCategory.getLevel(), skillMatrixCategory.getSkillMatrixId() + "#" + skillMatrixCategory.getSkillMatrixName());
            skillMatrixCategory.setLevel1(parentCategory.getSkillMatrixId());
            skillMatrixCategory.setLevel2(hearichyDetails.get("LEVEL-2").split("#")[0]);
            skillMatrixCategory.setLevel3(hearichyDetails.get("LEVEL-3").split("#")[0]);
            skillMatrixCategory.setLevel4(hearichyDetails.get("LEVEL-4").split("#")[0]);
            skillMatrixCategory.setLevel5(hearichyDetails.get("LEVEL-5").split("#")[0]);
            skillMatrixCategory.setLevel6(hearichyDetails.get("LEVEL-6").split("#")[0]);
            skillMatrixCategory.setLevel7(hearichyDetails.get("LEVEL-7").split("#")[0]);
            String hearichyName = parentCategory.getSkillMatrixName();
            if (!hearichyDetails.get("LEVEL-2").split("#")[0].equals("NA")) {
                hearichyName = hearichyName + " > " + hearichyDetails.get("LEVEL-2").split("#")[1];
            }
            if (!hearichyDetails.get("LEVEL-3").split("#")[0].equals("NA")) {
                hearichyName = hearichyName + " > " + hearichyDetails.get("LEVEL-3").split("#")[1];
            }
            if (!hearichyDetails.get("LEVEL-4").split("#")[0].equals("NA")) {
                hearichyName = hearichyName + " > " + hearichyDetails.get("LEVEL-4").split("#")[1];
            }
            if (!hearichyDetails.get("LEVEL-5").split("#")[0].equals("NA")) {
                hearichyName = hearichyName + " > " + hearichyDetails.get("LEVEL-5").split("#")[1];
            }
            if (!hearichyDetails.get("LEVEL-6").split("#")[0].equals("NA")) {
                hearichyName = hearichyName + " > " + hearichyDetails.get("LEVEL-6").split("#")[1];
            }
            if (!hearichyDetails.get("LEVEL-7").split("#")[0].equals("NA")) {
                hearichyName = hearichyName + " > " + hearichyDetails.get("LEVEL-7").split("#")[1];
            }
            skillMatrixCategory.setLevel_hearichy_name(hearichyName);
            return skillMatrixCategory;
        } else {
            skillMatrixCategory.setLevel1(skillMatrixCategory.getSkillMatrixId());
            skillMatrixCategory.setLevel2("NA");
            skillMatrixCategory.setLevel3("NA");
            skillMatrixCategory.setLevel4("NA");
            skillMatrixCategory.setLevel5("NA");
            skillMatrixCategory.setLevel6("NA");
            skillMatrixCategory.setLevel7("NA");
            skillMatrixCategory.setLevel_hearichy_name(skillMatrixCategory.getSkillMatrixName());
            return skillMatrixCategory;
        }
    }

    private SkillMatrixCategory getCategoryById(String skillCategoryId) throws Exception {
        if (skillMatrixCategoryRepository.existsById(skillCategoryId)) {
            return skillMatrixCategoryRepository.findById(skillCategoryId).get();
        } else {
        	throw new SourceablyCustomeException("No Skill Cateogry Found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void saveSkillMatrix(String categoryName, boolean isChild, String parentcategoryName, String level) {
        if (!skillMatrixCategoryRepository.existsBySkillMatrixName(categoryName)) {
            SkillMatrixCategory parent = skillMatrixCategoryRepository.findBySkillMatrixName(parentcategoryName);
            SkillMatrixCategory skillMatrixCategory = new SkillMatrixCategory();

            skillMatrixCategory.setIsSubCategory(isChild);
            skillMatrixCategory.setSkillMatrixName(categoryName);
            skillMatrixCategory.setIsDeleted(false);
            skillMatrixCategory.setStatus(true);
            skillMatrixCategory.setSkillMatricDescription(categoryName);
            skillMatrixCategory.setUpdatedBy("ADMIn");
            skillMatrixCategory.setCreatedBy("ADMIN");
            skillMatrixCategory.setCreatedEpochTime(Instant.now().toEpochMilli());
            skillMatrixCategory.setUpdatedEpochTime(Instant.now().toEpochMilli());
            skillMatrixCategory.setLevel(level);
            if (parent != null) {
                skillMatrixCategory.setParent(parent);
                skillMatrixCategoryRepository.save(skillMatrixCategory);
            } else {
                skillMatrixCategoryRepository.save(skillMatrixCategory);
                skillMatrixCategory.setParent(skillMatrixCategory);
                skillMatrixCategoryRepository.save(skillMatrixCategory);
            }
            skillMatrixCategory = updateTheLevel(skillMatrixCategory);
            skillMatrixCategoryRepository.save(skillMatrixCategory);

        }
    }

    @Override
    public String saveSupplierSkillMatrix(SkillMatrixListRequest skillMatrixListRequest, String userId) throws Exception {
        CompanyRegistration registration = companyRepository.findByUserId(userId);
        ExceptionUtils.verifyDataNotExistThenThrowException(registration,"User not found");
        for (SkillMatrixSaveRequest request : skillMatrixListRequest.getSkillMatrixList()) {
            SupplierSkillMatrix skillMatrix = new SupplierSkillMatrix();
            skillMatrix.setSkillMatrixId(request.getSkillMatrixId());
            skillMatrix.setLevelHearichyName(request.getLevel_hearichy_name());
            skillMatrix.setCreatedEpochTime(Instant.now().toEpochMilli());
            skillMatrix.setUserId(userId);
            skillMatrix.setIsActive(true);
            String name = registration.getFirstName();
            if (registration.getMiddleName() != null) {
                name = name + " " + registration.getMiddleName();
            }
            if (registration.getLastName() != null) {
                name = name + " " + registration.getLastName();
            }
            skillMatrix.setCreatedBy(name);
            supplierSkillMatrixRepository.save(skillMatrix);
        }
        return "saved Successfully";
    
    }

    @Override
    public Object getProviderSkillMatrix(String userId) throws Exception {

        List<SupplierSkillMatrix> skillMatrix = supplierSkillMatrixRepository.findByUserIdAndIsActive(userId,true);
        ExceptionUtils.verifyDataNotExistThenThrowException(skillMatrix, "User Id not found");
        List<SkillMatrixSaveRequest> matrixList = new ArrayList<SkillMatrixSaveRequest>();
        for (SupplierSkillMatrix matrix : skillMatrix) {
            SkillMatrixSaveRequest skillMatrixSave = new SkillMatrixSaveRequest();
            skillMatrixSave.setSkillMatrixId(matrix.getSkillMatrixId());
            skillMatrixSave.setLevel_hearichy_name(matrix.getLevelHearichyName());
            matrixList.add(skillMatrixSave);
        }
        return matrixList;
    
    }

    @Override
    public String updateSupplierSkillMatrix(SkillMatrixUpdateRequest matrixUpdateRequest) throws Exception {
        try {
            String[] skillId = matrixUpdateRequest.getSkillMatrixId().split(",");
            for (String id : skillId) {
                SupplierSkillMatrix skillMatrix = supplierSkillMatrixRepository.findByUserIdAndSkillMatrixId(matrixUpdateRequest.getUserId(), id);
                skillMatrix.setIsActive(false);
                supplierSkillMatrixRepository.save(skillMatrix);
            }
            return "updated";
        } catch (Exception e) {
        	throw new SourceablyCustomeException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}