package com.oms.projectbuddy.services;

import com.oms.projectbuddy.model.request.SkillMatrixListRequest;
import com.oms.projectbuddy.model.request.SkillMatrixUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

public interface ISkillMatrixService {

    void bukUploadSkillMatrixCategory(MultipartFile file) throws IOException;

    Object getParentCategoriesdetails(Pageable pageable);

    Object getChildSkillCategoryBySkillId(String skillCategoryId, Pageable pageable) throws Exception;

    Object getAllLevelDetailsBySkillMatrixId(String skillMatrixId) throws Exception;

    void updateLevelsHearichy(String level);

    String saveSupplierSkillMatrix(SkillMatrixListRequest skillMatrixListRequest, String userId) throws Exception;

    Object getProviderSkillMatrix(String userId) throws Exception;

    String updateSupplierSkillMatrix(SkillMatrixUpdateRequest matrixUpdateRequest) throws Exception;
}
