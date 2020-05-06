package com.hjs.automation.springBootSelenium.apibeans.crm;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParamsJson {
    private Integer id;
    private String paramsOperator;
    private List<String> paramsValue;
}
