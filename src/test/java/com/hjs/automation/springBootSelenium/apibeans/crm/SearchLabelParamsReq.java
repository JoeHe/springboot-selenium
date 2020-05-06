package com.hjs.automation.springBootSelenium.apibeans.crm;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchLabelParamsReq {
    private int paramsId;
    private List<String> paramsValue;
    private String paramsOperator;
}
