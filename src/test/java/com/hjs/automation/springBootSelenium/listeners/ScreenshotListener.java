package com.hjs.automation.springBootSelenium.listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.hjs.automation.springBootSelenium.utils.ScreenShot;

public class ScreenshotListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println("执行onTestFailure");
        ScreenShot.saveScreenShot(tr);
        super.onTestFailure(tr);
    }
}

