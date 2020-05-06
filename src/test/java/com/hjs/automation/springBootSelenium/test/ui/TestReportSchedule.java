package com.hjs.automation.springBootSelenium.test.ui;

import com.hjs.automation.springBootSelenium.base.LoginBaseTest;
import com.hjs.automation.springBootSelenium.page.ReportSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TestReportSchedule extends LoginBaseTest {

    @Autowired
    private ReportSchedule reportSchedule;


    @Test(groups = {"smokeTest"})
//    @Test
    public void testGetReportDataGridData() throws Exception {
        logs("Test get report data grid data.");
        reportSchedule.ClickReportBizList();
        List<Map<String, String>> reportDatas = reportSchedule.getDataGridData();
        logs("got report schedule data:\n"+reportDatas);
        Assert.assertTrue(reportDatas.size()>0, "there are no data in report schedule table!");
    }

    @Test
    public void test_excel_report(){
        logs("test create excel type report schedule.");
        Assert.assertTrue(1==2,"test test test");
    }

    @DataProvider(name = "params")
    public Object [][] dataProviderSample1(){
        return new Object[][]{
                {"A", 65},
                {"B", 66},
                {"C", 67}
        };
    }

    @Test(dataProvider = "params", groups = {"smokeTest"})
    public void testPrintParam(String str, int i){
        System.out.println("strParam = " + str + " ,i = " + i);
    }


    private void logs(String s){
        log.info(s);
        Reporter.log(s);
    }
}
