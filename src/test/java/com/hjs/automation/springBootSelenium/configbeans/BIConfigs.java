package com.hjs.automation.springBootSelenium.configbeans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:BIConfig.properties")
@ConfigurationProperties(prefix = "qa")
public class BIConfigs {
    private String BaseUrl;
    private String TestUser;
    private String Password;

    public String getBaseUrl() {
        return BaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    public String getTestUser() {
        return TestUser;
    }

    public void setTestUser(String testUser) {
        TestUser = testUser;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
