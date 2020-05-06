package com.hjs.automation.springBootSelenium.base;

import com.hjs.automation.springBootSelenium.SpringBootSeleniumApplication;
import com.hjs.automation.springBootSelenium.listeners.ScreenshotListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;


@Listeners({ScreenshotListener.class})
@SpringBootTest(classes = SpringBootSeleniumApplication.class)
public class AbstractTest extends AbstractTestNGSpringContextTests{
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @AfterMethod
    public void close(){
        logger.info("After Method :: Trying to close all web driver instances");
        DriverFactory.getInstance().removeDriver();
    }



}
