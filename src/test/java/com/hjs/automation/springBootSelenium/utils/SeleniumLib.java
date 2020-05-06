//package com.hjs.automation.springBootSelenium.utils;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class SeleniumLib {
//
//    private WebDriver driver;
//    private int timeout;
//
//    public SeleniumLib(WebDriver driver) {
//        this.driver = driver;
//        this.timeout = 5;
//    }
//
//    public boolean waitElement(By by){
//        try{
//            (new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(by));
//        }catch (TimeoutException | NoSuchElementException e){
//            return false;
//        }
//        return true;
//    }
//
//    protected void waitForElementDisappear(By by){
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
//    }
//
//    public WebElement getElement(String locator){
//        return getElement(locator, 5);
//    }
//
//    public WebElement getElement(String locator, int timeout){
//        WebElement el = (new WebDriverWait(driver, timeout)).until(
//                ExpectedConditions.presenceOfElementLocated(GetByLocator.getLocator(locator)));
//        return el;
//    }
//
//    public boolean click(String locator) {
//        WebElement webElement = getElement(locator);
//        try {
//            webElement.click();
//        } catch (Exception ignored) {
//            try {
//                SeleniumLib.executeScript(this.driver, webElement, "$(arguments[0]).click()");
//            } catch (Exception e) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void visit(String url) {
//        this.driver.get(url);
//    }
//
//    public void maxWindow(){
//        this.driver.manage().window().maximize();
//    }
//
//    public String getAttribute(WebElement el, String attribute){
//        return el.getAttribute(attribute);
//    }
//
//    public String getText(String locator){
//        WebElement el = getElement(locator);
//        return el.getText();
//    }
//
//    public void clear(String locator){
//        WebElement el = getElement(locator);
//        el.clear();
//    }
//
//    public void type(String locator, String text){
//        WebElement el = getElement(locator);
////        if(getValue(el)!=""){
////            cleanBySendKeys(el);
////        }
////        el.sendKeys(text);
////
////        if(!getValue(el).equals(text)){
////            SeleniumLib.executeScript(this.driver, el, "arguments[0].value=\"" + text + "\"");
////        }
//        SeleniumLib.executeScript(this.driver, el, "arguments[0].value=\"" + text + "\"");
//    }
//
//    public String getValue(WebElement el){
//        return getAttribute(el, "value");
//    }
//
//    public void cleanBySendKeys(WebElement el){
//        while (getValue(el) !=""){
//            el.sendKeys(Keys.BACK_SPACE);
//        }
//    }
//
//    public void rightCLick(String locator){
//
//    }
//
//    public void setText(WebElement element, String s) {
//        SeleniumLib.executeScript(this.driver, element, "arguments[0].value=\"" + s + "\"");
//    }
//
////    public  WebElement getElement(String locator){
////        WebElement el = driver.findElement(GetByLocator.getLocator(locator));
////        return el;
////    }
////below need to update
//
//    /**
//     * Search for the element continuously until it is found.
//     *
//     * @param by the way to search the element
//     * @return the web element
//     */
//    private WebElement findContinuously(By by) {
//        WebElement result;
//        while (true) {
//            try {
//                result = driver.findElement(by);
//                return result;
//            } catch (Exception ignored) {
////                System.err.println("Find element fail, try again");
//            }
//        }
//    }
//
//    /**
//     * Search for the elements continuously until they are found.
//     *
//     * @param by the way to search the element
//     * @return A list of elements
//     */
//    private List<WebElement> findListContinuously(By by) {
//        List<WebElement> result;
//        while (true) {
//            try {
//                result = driver.findElements(by);
//                return result;
//            } catch (Exception ignored) {
//            }
//        }
//    }
//
//    public void fill(WebElement webElement, String s) {
//        webElement.sendKeys(s);
//    }
//
//    public WebElement findElementByXpath(String xpath, boolean continuous) {
//        if (continuous) {
//            return findContinuously(By.xpath(xpath));
//        } else return this.driver.findElement(By.xpath(xpath));
//    }
//
//    public List<WebElement> findElementsByXpath(String xpath, boolean continuous) {
//        if (continuous)
//            return findListContinuously(By.xpath(xpath));
//        else return driver.findElements(By.xpath(xpath));
//    }
//
//    public WebElement findElementById(String id) {
//        return findElementByXpath("//*[@id='" + id + "']", true);
//    }
//
//    public void addCookie(Map<String, String> cookieMap) {
//        Set<Map.Entry<String, String>> entries = cookieMap.entrySet();
//        for (Map.Entry<String, String> entry : entries) {
//            Cookie cookie = new Cookie(entry.getKey(), cookieMap.get(entry.getValue()));
//            driver.manage().addCookie(cookie);
//        }
//    }
//
//    public void addCookies(Cookie[] cookies) {
//        for (Cookie cookie : cookies)
//            driver.manage().addCookie(cookie);
//    }
//
//    public void addCookie(Cookie cookie) {
//        driver.manage().addCookie(cookie);
//    }
//
//    public void refresh() {
//        driver.navigate().refresh();
//    }
//
//    public void quit() {
//        driver.quit();
//    }
//
//    public void acceptAlert() {
//        Alert alert = driver.switchTo().alert();
//        System.out.println(alert.getText());
//        alert.accept();
//    }
//
//    /**
//     * switch to an another arbitrary window
//     */
//    public void switchToAnotherWindow() {
//        Set<String> handles = driver.getWindowHandles();
//        String curr = driver.getWindowHandle();
//        for (String k : handles) {
//            if (!k.equals(curr)) {
//                WebDriver webDriver = driver.switchTo().window(k);
//                this.driver = (ChromeDriver) webDriver;
//                System.out.println("switch to " + webDriver.getTitle());
//                break;
//            }
//        }
//    }
//
//    public WebDriver getDriverByPartialTitle(String title) {
//        Set<String> handles = driver.getWindowHandles();
//        String curr = driver.getWindowHandle();
//        for (String k : handles) {
//            if (k.equals(curr))
//                continue;
//            WebDriver webDriver = driver.switchTo().window(k);
//            String currTitle = webDriver.getTitle();
//            if (currTitle.contains(title)) {
//                return webDriver;
//            }
//        }
//        return null;
//    }
//
//    public void swithToByPartialText(String title) {
//        WebDriver foo = getDriverByPartialTitle(title);
//        if (foo != null)
//            this.driver = (ChromeDriver) foo;
//        else System.out.println("No such page");
//    }
//
//    public void close() {
//        if (driver != null)
//            driver.close();
//
//    }
//
//    public static String attributeToXpath(String attr, String value) {
//        return "//*[@" + attr + "='" + value + "']";
//    }
//
//    public static void setAttribute(WebDriver driver, WebElement element, String attributename, String value) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",
//                element, attributename, value);
//    }
//
//    public static void executeScript(WebDriver driver, WebElement element, String script) {
//        ((JavascriptExecutor) driver).executeScript(script, element);
//    }
//
//    public static void removeAttribute(WebDriver driver, WebElement element, String attributename) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute(arguments[1],arguments[2])", element, attributename);
//    }
//
//    static Cookie[] resolveCookies(String s) {
//        ArrayList<Cookie> cookies = new ArrayList<Cookie>();
//        while (s.length() != 0) {
//            int index = s.indexOf('=');
//            if (index == -1)
//                break;
//            String cookieName = s.substring(0, index).trim();
//            int endIndex = s.indexOf(';');
//            if (endIndex == -1)
//                endIndex = s.length();
//            String cookieValue = s.substring(index + 1, endIndex).trim();
//            cookies.add(new Cookie(cookieName, cookieValue));
//            s = s.substring(endIndex + 1);
//        }
//
//        return cookies.toArray(new Cookie[0]);
//    }
//}
