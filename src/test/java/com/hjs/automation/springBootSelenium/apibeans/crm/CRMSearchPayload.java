package com.hjs.automation.springBootSelenium.apibeans.crm;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CRMSearchPayload {
    private Integer labelId;
    private String labelWhere;
    private List<ParamsJson> paramsJson;
}



