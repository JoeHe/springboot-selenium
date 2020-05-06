package com.hjs.automation.springBootSelenium.apiServices.crm;

import com.alibaba.fastjson.JSON;
import com.hjs.automation.springBootSelenium.apiServices.ApiAbstractBase;
import com.hjs.automation.springBootSelenium.apibeans.crm.*;
import com.hjs.automation.springBootSelenium.model.crmSelectComplexInfoModel;
import com.hjs.automation.springBootSelenium.utils.HttpClientResult;
import com.hjs.automation.springBootSelenium.utils.RestClient;
import com.hjs.automation.springBootSelenium.utils.Utils;

import java.util.*;

public class SearchPeopleService extends ApiAbstractBase {
    private static final String url = baseUrl + Utils.getBIProperties().getPro("search.path");
    private static final String complexUrl = baseUrl + Utils.getBIProperties().getPro("searchComplex.path");

    public SearchPeopleService(RestClient restClient){
        this.restClient = restClient;
        this.headers = setDefaultHeaders();
        this.payload = setPayloadGenderBirthMar();

    }

    protected HashMap<String,String> setDefaultHeaders(){
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Region", Utils.getBIProperties().getPro("region"));
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Connection", "keep-alive");
        headers.put("User-Agent", "Mozilla/5.0");
        return headers;
    }

    public String setPayloadGenderBirthMar(){
        ParamsJson paramsJsonMale = ParamsJson.builder().id(5).paramsValue(Arrays.asList("Male")).paramsOperator("").build();
        ParamsJson paramsJson2 = ParamsJson.builder().id(6).paramsValue(Arrays.asList("2006-10-03")).paramsOperator("").build();
        ParamsJson paramsJson3 = ParamsJson.builder().id(7).paramsValue(Arrays.asList("Single","Married")).paramsOperator("").build();
        CRMSearchPayload searchPayload = CRMSearchPayload.builder().labelId(4).labelWhere("=").paramsJson(Collections.singletonList(paramsJsonMale)).build();
        CRMSearchPayload searchPayload2 = CRMSearchPayload.builder().labelId(5).labelWhere("<=").paramsJson(Collections.singletonList(paramsJson2)).build();
        CRMSearchPayload searchPayload3 = CRMSearchPayload.builder().labelId(6).labelWhere("in").paramsJson(Collections.singletonList(paramsJson3)).build();

        List<CRMSearchPayload> searchPayloadsList = Arrays.asList(searchPayload,searchPayload2,searchPayload3);

        Map<String, String> labelListJson = new HashMap<>();
        labelListJson.put("labelListJson", JSON.toJSONString(searchPayloadsList));

        return JSON.toJSONString(labelListJson);
    }

    public HttpClientResult searchPeopleGenderBirthMar() throws Exception {
        requestResult = restClient.post(url, payload, headers);
        return requestResult;
    }

    public String setPayloadSelectComplex(){
        SearchLabelParamsReq monthlyIncomeLabel = SearchLabelParamsReq.builder().paramsId(86).paramsValue(Arrays.asList("600000")).paramsOperator("").build();
        SearchLabelParamsReq latestRiskScoreLabel = SearchLabelParamsReq.builder().paramsId(118).paramsValue(Arrays.asList("500")).paramsOperator("").build();

        SearchLabelReq monthlyIncome = SearchLabelReq.builder().labelId("64").groupId("15").labelWhereValue(">").logicalCondition("and").searchLabelParamsReqList(Arrays.asList(monthlyIncomeLabel)).build();
        SearchLabelReq latestRiskScore = SearchLabelReq.builder().labelId("82").groupId("16").labelWhereValue(">").logicalCondition("and").searchLabelParamsReqList(Arrays.asList(latestRiskScoreLabel)).build();

        SearchLabelGroup searchGrp1 = SearchLabelGroup.builder().logicalCondition("and").searchLabelReqList(Arrays.asList(monthlyIncome)).build();
        SearchLabelGroup searchGrp2 = SearchLabelGroup.builder().logicalCondition("and").searchLabelReqList(Arrays.asList(latestRiskScore)).build();

        List<SearchLabelGroup> searchPayloadGrpList = Arrays.asList(searchGrp1,searchGrp2);
        Map<String, List<SearchLabelGroup>> searchLabelGroupReqList = new HashMap<>();
        searchLabelGroupReqList.put("searchLabelGroupReqList", searchPayloadGrpList);

        return JSON.toJSONString(searchLabelGroupReqList);
    }

    public HttpClientResult searchPeopleComplex() throws Exception {
        setPayload(setPayloadSelectComplex());
        requestResult = restClient.post(complexUrl, payload, headers);
        return requestResult;
    }

//    public HttpClientResult searchPeopleComplexPure() throws Exception {
//        requestResult = restClient.post(complexUrl, payload, headers);
//        return requestResult;
//    }

    public HttpClientResult setPayloadSearchPeopleComplex(String customPayload) throws Exception {
        setPayload(customPayload);
        requestResult = restClient.post(complexUrl, payload, headers);
        return requestResult;
    }

    /**
     * generate select people complex search payload string
     * @param crmLabelList
     * @return
     */
    public String getSelectComplexPayloadString(List<crmSelectComplexInfoModel> crmLabelList){
        List<SearchLabelGroup> searchPayloadGrpList = new ArrayList<>();
        for(crmSelectComplexInfoModel crmLabel: crmLabelList){
            searchPayloadGrpList.add(getSearchLabelGroup(crmLabel));
        }
        Map<String, List<SearchLabelGroup>> searchLabelGroupReqList = new HashMap<>();
        searchLabelGroupReqList.put("searchLabelGroupReqList", searchPayloadGrpList);
        return JSON.toJSONString(searchLabelGroupReqList);
    }

    private SearchLabelGroup getSearchLabelGroup(crmSelectComplexInfoModel selectInfo){
        SearchLabelParamsReq searchLabelParams = SearchLabelParamsReq.builder().paramsId(selectInfo.getParamsId()).paramsValue(Arrays.asList(selectInfo.getParamsValue())).paramsOperator(selectInfo.getParamsOperator()).build();
        SearchLabelReq searchLabel = SearchLabelReq.builder().labelId(selectInfo.getLabelId()).groupId(selectInfo.getGroupId()).labelWhereValue(selectInfo.getLabelWhereValue()).logicalCondition(selectInfo.getLogicalCondition()).searchLabelParamsReqList(Arrays.asList(searchLabelParams)).build();
        SearchLabelGroup searchGrp = SearchLabelGroup.builder().logicalCondition(selectInfo.getLogicalCondition()).searchLabelReqList(Arrays.asList(searchLabel)).build();
        return searchGrp;
    }



    //get direct from CRMComplexSearchBean bean
    public String getSelectComplexPayloadStringNew(List<crmSelectComplexInfoModel> crmLabelList){

        List<CRMComplexSearchBean.SearchLabelGroupReqListBean> searchLabelGroupReqList = new ArrayList<>();
        for(crmSelectComplexInfoModel selectInfo: crmLabelList){
            CRMComplexSearchBean.SearchLabelGroupReqListBean.SearchLabelReqListBean.SearchLabelParamsReqListBean searchLabelParams =
                    CRMComplexSearchBean.SearchLabelGroupReqListBean.SearchLabelReqListBean.SearchLabelParamsReqListBean.builder().paramsId(selectInfo.getParamsId()).paramsValue(Arrays.asList(selectInfo.getParamsValue())).paramsOperator(selectInfo.getParamsOperator()).build();
            CRMComplexSearchBean.SearchLabelGroupReqListBean.SearchLabelReqListBean searchLabel =
                    CRMComplexSearchBean.SearchLabelGroupReqListBean.SearchLabelReqListBean.builder().labelId(selectInfo.getLabelId()).groupId(selectInfo.getGroupId()).labelWhereValue(selectInfo.getLabelWhereValue()).logicalCondition(selectInfo.getLogicalCondition()).searchLabelParamsReqList(Arrays.asList(searchLabelParams)).build();
            CRMComplexSearchBean.SearchLabelGroupReqListBean searchGrp = CRMComplexSearchBean.SearchLabelGroupReqListBean.builder().logicalCondition(selectInfo.getLogicalCondition()).searchLabelReqList(Arrays.asList(searchLabel)).build();

            searchLabelGroupReqList.add(searchGrp);
        }

        CRMComplexSearchBean crmComplexSearchBean = CRMComplexSearchBean.builder().searchLabelGroupReqList(searchLabelGroupReqList).build();

        return JSON.toJSONString(crmComplexSearchBean);

    }
}
