package com.hjs.automation.springBootSelenium.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hjs.automation.springBootSelenium.base.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;


public class ScreenShot {
    static Logger logger = LoggerFactory.getLogger(ScreenShot.class);

    public static void saveScreenShot(ITestResult tr) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String mDateTime = formatter.format(new Date());
        String fileName = mDateTime + "_" + tr.getName();//图片名，时间加用例名
        String filePath = "";//保存位置
        try {
            File screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
//            filePath = "test-output/screenshot/" + fileName + ".jpg";
            filePath = "test-output/screenshot/" +mDateTime.substring(0,8) +"/"+ fileName + ".jpg";
            File destFile = new File(filePath);
            FileUtils.copyFile(screenshot, destFile);
            logger.info("截图成功，保存在：" + "[ " + filePath + " ]");

        } catch (Exception e) {
            filePath = "截图失败" + e.getMessage();
            logger.error(filePath);
        }

        if (!"".equals(filePath)) {
            Reporter.setCurrentTestResult(tr);
//            Reporter.log(filePath);
            // 把截图写入到Html报告中方便查看
//            Reporter.log("<img src=\"../../" + filePath + "\"/>");
            Reporter.log("<img src=\"./screenshot/" + mDateTime.substring(0,8) +"/"+ fileName + ".jpg" + "\"/>");

        }
    }

}
