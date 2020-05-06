package com.hjs.automation.springBootSelenium.page;


import com.hjs.automation.springBootSelenium.configbeans.BIConfigs;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportSchedule extends DataGrid {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BIConfigs biConfigs;


    @FindBy(xpath = "//*[text()='Report Schedule']/parent::div//i")
    private WebElement reportToggle; //默认friendly访问级别，即包内可见

    @FindBy(xpath = "//a[@href='#/reportSchedule/reportBizList']")
    protected WebElement reportScheduleLink;

    @FindBy(xpath = "//*[text()='Report Schedule']/parent::div/..")
    WebElement reportMenuItemLi;

    @FindBy(xpath = "//*[@class='app-container']//*[text()='Add']/parent::Button")
    WebElement addBtn;

    @FindBy(css = "div.el-table table.el-table__body")
    WebElement report_table;

    @FindBy(css = "div.el-table table.el-table__body tr")
    List<WebElement> report_table_trs;

    // Edit Report View
    @FindBy(xpath = "//*[text()='Biz Type']/following-sibling::div//input")
    WebElement biz_type_input;

    @FindBy(xpath = "//*[text()='Email Title']/following-sibling::div//input")
    WebElement email_title_input;

    @FindBy(xpath = "//*[text()='Mail Title Timezone']/following-sibling::div//input")
    WebElement mail_title_timezone_input;

    @FindBy(xpath = "//*[text()='Email Date Format']/following-sibling::div//input")
    WebElement email_date_format_input;

    @FindBy(xpath = "//*[text()='Back Date']/following-sibling::div//input")
    WebElement back_date_input;

    @FindBy(xpath = "//*[text()='Schedule Time']/following-sibling::div//input")
    WebElement schedule_time_input;

    @FindBy(xpath = "//*[text()='Template']/following-sibling::div//input")
    WebElement template_input;

    @FindBy(xpath = "//*[text()='Return']/parent::button")
    WebElement return_btn;

    @FindBy(xpath = "//*[text()='Confirm']/parent::button")
    WebElement confirmBtn;

    //Add Report View
    @FindBy(xpath = "//*[text()='Biz Type']/following-sibling::div//input")
    WebElement add_biz_type_input;

    @FindBy(xpath = "//*[text()='Description']/following-sibling::div//textarea")
    WebElement add_description_area;

    //add_email_title_input, add_email_date_format_input,add_schedule_time_input, add_template_input,add_mail_title_timezone_input same as above related

    @FindBy(xpath = "//*[text()='Cancel']/parent::button")
    WebElement cancelBtn;

    @FindBy(xpath = "//*[text()='{}']/parent::div//i")
    WebElement drop_down_trigger;

    @FindBy(xpath = "//div[contains(@class, 'el-select-dropdown el-popper') and not(contains(@style, 'display: none;'))]//li")
    List<WebElement> drop_ul_li;


    //View Report page
    @FindBy(xpath = "//*[@class='app-container']//*[text()='Logs History']/parent::Button")
    WebElement view_logs_history;

    @FindBy(xpath = "//*[@class='app-container']//*[text()='Send Test Email']/parent::Button")
    WebElement view_send_test_email;

    @FindBy(xpath = "//*[text()='Report List']/../following-sibling::div/button")
    WebElement view_add_report_list;

    @FindBy(xpath = "//*[text()='Report List']/../../following-sibling::div//tr[@class='el-table__row']")
    WebElement view_report_row;

    @FindBy(xpath = "//*[text()='Email Recipients']/../following-sibling::div//button")
    WebElement view_add_email_recipients;

    @FindBy(xpath = "//*[text()='{}']/parent::div//input")
    WebElement view_input;

    @FindBy(xpath = "//*[text()='Mail Html']/following-sibling::div//textarea")
    WebElement view_mail_html;

    @FindBy(xpath = "//*[text()='Excel Report Sql']/following-sibling::div//textarea")
    WebElement view_excel_report_sql;

    @FindBy(xpath = "//*[text()='{}']/following-sibling::div//textarea")
    WebElement view_text_area;

    @FindBy(xpath = "//*[text()='Send Test Email']/../following-sibling::button")
    WebElement view_add_table_report_list;

    @FindBy(xpath = "//*[text()='{}']/parent::button")
    WebElement view_button;

    @FindBy(xpath = "//*[contains(@class, 'el-dialog')]//*[text()='{}']/following-sibling::div//input")
    WebElement view_email_input;

    @FindBy(xpath = "//*[contains(@class, 'el-dialog__footer')]//*[text()='Confirm']/parent::button")
    WebElement view_add_email_recipt_confirm;

    @FindBy(xpath = "//button[normalize-space()='Cancle']/following-sibling::button")
    WebElement confirm_publish_btn;

    public void ClickReportBizList() throws InterruptedException {
        logger.info("click [Report Biz List] tab.");
        if(!isReportMenuExpand()){
            logger.debug("expand menu [Report Schedule]");
            Click(reportToggle);
        }
        Thread.sleep(500);
        Click(reportScheduleLink);
        Thread.sleep(1000);
    }

    public boolean isReportMenuExpand(){
        logger.debug("check whether menu [Report Schedule] expanded.");
        String liAttribute = reportMenuItemLi.getAttribute("class");
        if(liAttribute.contains("is-opened")){
            return true;
        }
        return false;
    }



}
