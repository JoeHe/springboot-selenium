package com.hjs.automation.springBootSelenium.apibeans.crm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginCredential {
    private String email;
    private String password;

}
