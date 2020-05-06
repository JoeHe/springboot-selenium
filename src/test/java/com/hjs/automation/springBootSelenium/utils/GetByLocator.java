//package com.hjs.automation.springBootSelenium.utils;
//
//import org.openqa.selenium.By;
//
//public class GetByLocator {
//
//    public static By getLocator(String locator) {
////        PropertyUtil proUtil=new PropertyUtil("element.properties");
////        String locator=proUtil.getPro(key);
//
//        if(!locator.contains("=>")){
//            if(locator.startsWith("/")){
//                return By.xpath(locator);
//            }else {
//                return  By.cssSelector(locator);
//            }
//        }else{
//            String locatorType=locator.split("=>")[0];
//            String locatorValue=locator.split("=>")[1];
//            if(locatorType.equals("id")) {
//                return By.id(locatorValue);
//            }else if(locatorType.equals("name"))
//            {
//                return By.name(locatorValue);
//            }else if(locatorType.equals("className"))
//            {
//                return By.className(locatorValue);
//            }else if(locatorType.equals("linkText"))
//            {
//                return By.linkText(locatorValue);
//            }else{
//                return By.tagName(locatorValue);
//            }
//        }
//    }
//}
