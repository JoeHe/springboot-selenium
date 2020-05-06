package com.hjs.automation.springBootSelenium.apiServices.crm;

import com.alibaba.fastjson.JSON;
import com.hjs.automation.springBootSelenium.apiServices.ApiAbstractBase;
import com.hjs.automation.springBootSelenium.apibeans.crm.LoginCredential;
import com.hjs.automation.springBootSelenium.utils.HttpClientResult;
import com.hjs.automation.springBootSelenium.utils.RestClient;
import com.hjs.automation.springBootSelenium.utils.Utils;


public class LoginOneService extends ApiAbstractBase {

    private final static String url = baseUrl + Utils.getBIProperties().getPro("login.path");

    public LoginOneService(RestClient restClient) {
        this.restClient = restClient;
        this.headers = setDefaultHeaders();
        this.payload = setLoginJsonString(Utils.getBIProperties().getPro("login.user1"), Utils.getBIProperties().getPro("login.pwd1"));
    }

//    protected HashMap<String, String> setDefaultHeaders() {
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        return headers;
//    }

    public String setLoginJsonString(String userName, String pwd) {
        LoginCredential loginPayload = LoginCredential.builder().email(userName).password(pwd).build();
        return JSON.toJSONString(loginPayload);
    }

    public HttpClientResult login() throws Exception {
        requestResult = restClient.post(url, payload, headers);
        return requestResult;
    }

}
