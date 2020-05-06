package com.hjs.automation.springBootSelenium.test.ui;

import com.hjs.automation.springBootSelenium.base.AbstractTest;
import com.hjs.automation.springBootSelenium.configbeans.BIConfigs;
import com.hjs.automation.springBootSelenium.page.BILogin;
import com.hjs.automation.springBootSelenium.page.ReportSchedule;
import com.hjs.automation.springBootSelenium.page.StaticsOverView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestBILogin extends AbstractTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    BIConfigs biConfigs;

    @Autowired
    BILogin biLogin;

    @Autowired
    StaticsOverView staticsOverView;

    @Autowired
    ReportSchedule reportSchedule;

    @Test
    public void testLogin() throws Exception {
        logger.info("Test one login");
        biLogin.Login(biConfigs.getTestUser(), biConfigs.getPassword());
    }



}
