package com.hjs.automation.springBootSelenium.base;

import com.hjs.automation.springBootSelenium.SpringBootSeleniumApplication;
import com.hjs.automation.springBootSelenium.configbeans.BIConfigs;
import com.hjs.automation.springBootSelenium.listeners.ScreenshotListener;
import com.hjs.automation.springBootSelenium.page.BILogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({ScreenshotListener.class})
@SpringBootTest(classes = SpringBootSeleniumApplication.class)
public class LoginBaseTest extends AbstractTestNGSpringContextTests {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BIConfigs biConfigs;

    @Autowired
    BILogin biLogin;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        logger.info("Setup Test class :: Login one site.");
        biLogin.Login(biConfigs.getTestUser(), biConfigs.getPassword());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass(){
        logger.info("Teardown Test Class :: Trying to close all web driver instances");
        DriverFactory.getInstance().removeDriver();
    }
}
