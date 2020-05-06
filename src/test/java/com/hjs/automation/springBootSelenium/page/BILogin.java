package com.hjs.automation.springBootSelenium.page;

import com.hjs.automation.springBootSelenium.base.BasePage;
import com.hjs.automation.springBootSelenium.configbeans.BIConfigs;
import com.hjs.automation.springBootSelenium.constants.constants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BILogin extends BasePage {

    @Autowired BIConfigs biConfigs;

    @FindBy (xpath="//*[@id='pane-loginByEmailPwd']//*[@placeholder='Email']")
    WebElement usernameInput;

    @FindBy (xpath="//*[@id='pane-loginByEmailPwd']//*[@placeholder='Password']")
    WebElement passwordInput;

    @FindBy (css="div#pane-loginByEmailPwd button.loginBnt")
    WebElement loginBtn;

    @FindBy(xpath = "//img[contains(@class,'user-avatar')]")
    WebElement userAvatar;

    @FindBy(css=".el-menu--horizontal .right-menu span.el-dropdown-selfdefine  .el-icon-caret-bottom")
    WebElement countryTrigger;

    @FindBy(css=".el-menu--horizontal .right-menu span.el-dropdown-selfdefine")
    WebElement countryLabel;

    @FindBy(xpath="//*[contains(@class, 'el-dropdown-menu--medium') and not(contains(@style,'display: none;'))]/li")
    @CacheLookup
    List<WebElement> countryList;


    public void GoToOneHome() {
        log.info("go to one page.");
        driver.get(biConfigs.getBaseUrl());
    }

    public void InputUserName(String user){
        log.info("input username: {}", user);
        Input(usernameInput, user);
    }

    public void InputPwd(String pwd){
        log.info("input pwd");
        Input(passwordInput, pwd);
    }

    public void ClickLogin(){
        log.info("click login button.");
        loginBtn.click();
    }

    public void Login(String username, String password) throws Exception {
        GoToOneHome();
        InputUserName(username);
        InputPwd(password);
        ClickLogin();
        SwitchRegion(constants.Regions.IDN);
    }

    public String getRegion(){
        return countryLabel.getText();
    }

    public void SwitchRegion(String region) throws Exception {
        log.info("Switch Region to: {}", region);
        if(getRegion().equalsIgnoreCase(region)){
            log.info("current country is as expected, skip switch.");
            return;
        }
        WaitElementClickable(countryTrigger).click();
        Thread.sleep(500);
        for(int i=0;i<countryList.size();i++){
            if(countryList.get(i).getText().equalsIgnoreCase(region)){
                log.info("got target country element: {}", region);
                Click(countryList.get(i));
                Thread.sleep(500);
                break;
            }
        }
        String actualRegion=getRegion();
        if(!actualRegion.equals(region)){
            throw new Exception(String.format("failed switch country to:[%s], actual is:[%s]", region, actualRegion));
        }
        log.info("switch region successfully!");
    }

}
