package com.hjs.automation.springBootSelenium.apibeans.crm;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class SearchLabelGroup {
    private String logicalCondition;
    private List<SearchLabelReq> searchLabelReqList;
}
