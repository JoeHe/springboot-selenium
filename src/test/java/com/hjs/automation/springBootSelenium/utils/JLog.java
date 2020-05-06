package com.hjs.automation.springBootSelenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.Reporter;

@Slf4j
public class JLog {
    public static void info(String info){
        log.info(info);
        Reporter.log(info);
    }

    public static void warn(String warn){
        log.warn(warn);
        Reporter.log(warn);
    }

}
