package com.hjs.automation.springBootSelenium.page;

import com.hjs.automation.springBootSelenium.base.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DataGrid extends BasePage {

    @FindBy(css="div.el-table table.el-table__header th div")
    List<WebElement> headers;

    @FindBy(css = "div.el-table table.el-table__body")
    WebElement dataTable;

    @FindBy(css = "div.el-table table.el-table__body tr")
    List<WebElement> tableRows;

    @FindBy(css = "button")
    WebElement actionBtn; // the button in CRM, Rule Mgr->KycReason [Action] column

    @FindBy(css = " .bnt--primary")
    WebElement action_button_rule_mgr; //the button in Rule Manager [Action] column

    public List<WebElement> getDataGridRows(){
        log.info("get data rows from data grid.");
        log.info("got total [{}] row data records", tableRows.size());
        return tableRows;
    }

    public List<String> getDataGridHeaders(){
        log.info("get headers from data grid");
        List<String> headerList=new ArrayList<>();
        for( WebElement hd: headers){
            headerList.add(hd.getText());
        }
        return headerList;
    }

    public List<Map<String, String>> getDataGridData(){
        log.info("get the data grid data in view, save to a dict list.");
        List<Map<String, String>> dataMapList = new ArrayList<>();
        List<String> headers = getDataGridHeaders();
        for(WebElement row: tableRows){
            Map<String, String> tempMap = new HashMap<>();
            List<WebElement> tdsInRow = row.findElements(By.tagName("td"));
            for(int i=0; i<tdsInRow.size(); i++){
                tempMap.put(headers.get(i), tdsInRow.get(i).getText());
            }
            dataMapList.add(tempMap);
        }
        return dataMapList;
    }

    /***Click Action [View], [Edit] etc. in the specific data row.* **/
    public void clickActionInRow(String actionName, String rowUniqueKeyName, String rowUniqueKeyValue) throws InterruptedException {
        List<WebElement> actionBtnList=getActionBtnList(rowUniqueKeyName, rowUniqueKeyValue);
        for(WebElement actionBtn: actionBtnList){
            if(actionBtn.getText().equals(actionName)){
                log.info("click [{}] on row with [{}]={}", actionName, rowUniqueKeyName, rowUniqueKeyValue);
                Click(actionBtn);
                Thread.sleep(1000);
                return;
            }
        }
        log.warn("Not find record in data grid with [{}]={}, please check!", rowUniqueKeyName, rowUniqueKeyValue);
    }

    public List<WebElement> getActionBtnList(String row_unique_key_name, String row_unique_key_value){
        Map<Integer,WebElement> colIndexRowMap = getTargetRow(row_unique_key_name, row_unique_key_value);
        WebElement targetRow = null;
        Integer action_column_index = -1;
        for(Map.Entry<Integer,WebElement> entry: colIndexRowMap.entrySet()){
            action_column_index = entry.getKey();
            targetRow = entry.getValue();
        }
        List<WebElement> tds_in_row = targetRow.findElements(By.tagName("td"));
        return tds_in_row.get(action_column_index).findElements(By.tagName("button"));
    }

    public Map<Integer,WebElement> getTargetRow(String row_unique_key_name, String row_unique_key_value){
        Integer actionColIndex=-1;
        WebElement targetRow=null;
        List<String> headerList = getDataGridHeaders();
        for(int i=0;i<headerList.size();i++) {
            if (headerList.get(i).equals("Actions") || headerList.get(i).equals("Action")) {
                actionColIndex = i;
                break;
            }
        }
        for(WebElement row: tableRows){
            List<WebElement> tdsInRow = row.findElements(By.tagName("td"));
            String targetRowId = tdsInRow.get(0).getText(); //first column value, help find row on ui
            for(WebElement td: tdsInRow){
                if(td.getText().equals(row_unique_key_value)){
                    log.info("find the data record with {}={}", row_unique_key_name, row_unique_key_value);
                    targetRow = row;
                    break;
                }
            }

            if(targetRow!=null){ break; }
        }
        if(targetRow==null){
            log.warn("Not find any data record with {}={} !",row_unique_key_name, row_unique_key_value);
        }
        return Stream.of(new AbstractMap.SimpleEntry<>(actionColIndex, targetRow)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


}
