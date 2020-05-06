package com.hjs.automation.springBootSelenium.apibeans.crm;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class SearchLabelReq {
    private String labelId;
    private String groupId;
    private String labelWhereValue;
    private String logicalCondition;
    private List<SearchLabelParamsReq> searchLabelParamsReqList;
}
