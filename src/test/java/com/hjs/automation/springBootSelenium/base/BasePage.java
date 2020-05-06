package com.hjs.automation.springBootSelenium.base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {

    private final int TIMEOUT=5;

    protected WebDriver driver= DriverFactory.getInstance().getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 5, 60);


    public  BasePage() {
        driver.manage().window().maximize();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    public BasePage(final String title) {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        try {
            boolean flag = wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver arg0) {
                String acttitle = arg0.getTitle();
                return acttitle.equals(title);
                }
        });
        } catch (TimeoutException te) {
        throw new IllegalStateException("Current page is Not as expected，current page title is：" + driver.getTitle());
        }
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected WebElement WaitElementVisible(WebElement element){
        return wait.until(visibilityOf(element));
    }

    protected void Input(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    protected void Click(WebElement element){
        element.click();
    }

    protected WebElement WaitElementClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
