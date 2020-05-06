package com.hjs.automation.springBootSelenium.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class crmSelectComplexInfoModel {
    private Integer id;
    private String groupId;
    private String labelId;
    private String labelWhereValue;
    private String logicalCondition;
    private String groupLogicalCondition;
    private Integer paramsId;
    private String paramsValue;
    private String paramsOperator;
    private Integer active;

}
