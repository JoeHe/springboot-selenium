package com.hjs.automation.springBootSelenium.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class cmsSubjectCategoryModel {
    private Integer id;
    private String name;
    private String icon;
    private Integer subject_count;
    private Integer show_status;
    private Integer sort;
}
