package com.oms.projectbuddy.services;

import com.oms.projectbuddy.model.request.SkillMatrixListRequest;
import com.oms.projectbuddy.model.request.SkillMatrixUpdateRequest;
import io.micronaut.data.model.Pageable;


import java.io.IOException;

public interface ISkillMatrixService {

    void bukUploadSkillMatrixCategory(String file) throws IOException;

    Object getParentCategoriesdetails(Pageable pageable);

    Object getChildSkillCategoryBySkillId(String skillCategoryId, Pageable pageable) throws Exception;

    Object getAllLevelDetailsBySkillMatrixId(String skillMatrixId) throws Exception;

    void updateLevelsHearichy(String level);

    String saveSupplierSkillMatrix(SkillMatrixListRequest skillMatrixListRequest, String userId) throws Exception;

    Object getProviderSkillMatrix(String userId) throws Exception;

    String updateSupplierSkillMatrix(SkillMatrixUpdateRequest matrixUpdateRequest) throws Exception;
}
