package com.hjs.automation.springBootSelenium.apiServices;

import com.hjs.automation.springBootSelenium.utils.HttpClientResult;
import com.hjs.automation.springBootSelenium.utils.RestClient;
import com.hjs.automation.springBootSelenium.utils.Utils;

import java.util.HashMap;

public abstract class ApiAbstractBase {
    protected RestClient restClient=null;
    protected HashMap<String,String> headers=null;
    protected String payload=null;
    protected HttpClientResult requestResult=null;

    protected final static String baseUrl = Utils.getBIProperties().getPro("qa.baseUrl");

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    protected HashMap<String,String> setDefaultHeaders(){
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    public void AssertStatus200(){
        restClient.AssertStatusCode200(requestResult);
    }

}
