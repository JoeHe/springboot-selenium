package com.hjs.automation.springBootSelenium.Mapper;

import com.hjs.automation.springBootSelenium.model.cmsSubjectCategoryModel;

import java.util.List;

public interface cmsSubjectCategoryMapper {

    int insertCategory(cmsSubjectCategoryModel model);

    int updateCategory(cmsSubjectCategoryModel model);

    int deleteCategory(Integer categoryId);

    List<cmsSubjectCategoryModel> getCategoryList();
}
