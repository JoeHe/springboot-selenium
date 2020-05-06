package com.hjs.automation.springBootSelenium.page;

import com.hjs.automation.springBootSelenium.base.BasePage;
import com.hjs.automation.springBootSelenium.configbeans.BIConfigs;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StaticsOverView extends BasePage {

    @Autowired
    BIConfigs biConfigs;

    @FindBy (css="div.el-tabs__content div.card-panel-text")
    List<WebElement> cardTexts;

    @FindBy (css="div.el-tabs__content span.card-panel-num")
    List<WebElement> cardValues;

    @FindBy (xpath="//a[@href='#/statisticsOverview/statisticsOverview']")
    WebElement statisticsOverview_link;


    public void clickStaticOverView(){
//        WaitElementVisible(statisticsOverview_link);
        WaitElementClickable(statisticsOverview_link);
        Click(statisticsOverview_link);
    }
}
