package com.hjs.automation.springBootSelenium.test.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjs.automation.springBootSelenium.Mapper.crmSelectComplexInfoMapper;
import com.hjs.automation.springBootSelenium.apiServices.crm.LoginOneService;
import com.hjs.automation.springBootSelenium.apiServices.crm.SearchPeopleService;
import com.hjs.automation.springBootSelenium.apibeans.crm.*;
import com.hjs.automation.springBootSelenium.base.APIAbstractTest;
import com.hjs.automation.springBootSelenium.model.crmSelectComplexInfoModel;
import com.hjs.automation.springBootSelenium.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class TestOneAPI extends APIAbstractTest {

    private String host;
    private String url;
    private RestClient restClient;
    private PropertyUtil propertyUtil;

    List<crmSelectComplexInfoModel> crmLabelList;


    @BeforeClass
    public void setUp() throws IOException {
        propertyUtil=new PropertyUtil("src/test/resources/BIConfig.properties");
        host = propertyUtil.getPro("qa.baseUrl");
        url=host+propertyUtil.getPro("login.path");
        restClient = new RestClient();

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true);) {
            crmSelectComplexInfoMapper mapper = sqlSession.getMapper(crmSelectComplexInfoMapper.class);
            crmLabelList = mapper.getCrmPeopleInfoList(); //执行查询操作
        }
    }

    @Test
    public void testOneLogin() throws Exception {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        LoginCredential loginPayload = LoginCredential.builder().email("jinshan.he@oriente.com").password("welcome01").build();
        String userJsonString = JSON.toJSONString(loginPayload);
        log.info("verify login api...");
        HttpClientResult loginResult = restClient.post(url, userJsonString, headers);
        restClient.AssertStatusCode200(loginResult);
        JSONObject responseJson = loginResult.getResponseJson();
        log.info("verify login request success.");
        Assert.assertEquals(Utils.getValueByJPath(responseJson,"msg"), "success","login one success");


    }

    @Test(dependsOnMethods = "testOneLogin")
    public void testSearchPeople() throws Exception {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Region", propertyUtil.getPro("region"));
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Connection", "keep-alive");
        headers.put("User-Agent", "Mozilla/5.0");

        ParamsJson paramsJsonMale = ParamsJson.builder().id(5).paramsValue(Arrays.asList("Male")).paramsOperator("").build();
        ParamsJson paramsJson2 = ParamsJson.builder().id(6).paramsValue(Arrays.asList("2006-10-03")).paramsOperator("").build();
        ParamsJson paramsJson3 = ParamsJson.builder().id(7).paramsValue(Arrays.asList("Single","Married")).paramsOperator("").build();
        CRMSearchPayload searchPayload = CRMSearchPayload.builder().labelId(4).labelWhere("=").paramsJson(Collections.singletonList(paramsJsonMale)).build();
        CRMSearchPayload searchPayload2 = CRMSearchPayload.builder().labelId(5).labelWhere("<=").paramsJson(Collections.singletonList(paramsJson2)).build();
        CRMSearchPayload searchPayload3 = CRMSearchPayload.builder().labelId(6).labelWhere("in").paramsJson(Collections.singletonList(paramsJson3)).build();

        List<CRMSearchPayload> searchPayloadsList = Arrays.asList(searchPayload,searchPayload2,searchPayload3);
        Map<String, String> labelListJson = new HashMap<>();
        labelListJson.put("labelListJson", JSON.toJSONString(searchPayloadsList));
        String searchJsonString = JSON.toJSONString(labelListJson);

        log.info("verify search people api...");
        String searchUrl = host+"/api/peoplelibrary/search";
        HttpClientResult searchResult = restClient.post(searchUrl, searchJsonString, headers);

        restClient.AssertStatusCode200(searchResult);
        JSONObject responseJson = searchResult.getResponseJson();
        log.info("verify search people api request successfully.");
        Assert.assertEquals(Utils.getValueByJPath(responseJson,"msg"), "success","search people success");
        log.info("get select count: {}", Utils.getValueByJPath(responseJson, "data/selectCount"));
    }


//    public void testDelete() throws IOException {
//        HashMap<String,String> headers = new HashMap<String,String>();
//        headers.put("Content-Type", "application/json");
//        headers.put("Region", propertyUtil.getPro("region"));
//        headers.put("Accept", "application/json, text/plain, */*");
//        headers.put("Connection", "keep-alive");
//        headers.put("User-Agent", "Mozilla/5.0");
//
//        log.info("verify delete people api...");
////        restClient.SetCookie();
//        String deleteUrl = host+"/api/peoplelibrary/delete/";
//        CloseableHttpResponse searchResponse = restClient.post(deleteUrl, "", headers);
//        restClient.AssertStatusCode200(searchResponse);
//    }

    @Test
    public void testSearchPeopleComplex() throws Exception {
        LoginOneService loginService = new LoginOneService(restClient);
        loginService.login();

        SearchPeopleService searchPeopleService = new SearchPeopleService(restClient);
        List<Integer> pickLabelsIDs = Arrays.asList(32, 33);
        List<crmSelectComplexInfoModel> targetCrmLabels = crmLabelList.stream().filter(label->pickLabelsIDs.contains(label.getId())).collect(Collectors.toList());

        String customPayload = searchPeopleService.getSelectComplexPayloadString(targetCrmLabels);
        log.info("payload is: {}", customPayload);
        HttpClientResult monthlyIncome=searchPeopleService.setPayloadSearchPeopleComplex(customPayload);
        log.info("verify search people complex for label monthlyIncome and latestRiskScore works.");
        restClient.AssertStatusCode200(monthlyIncome);
        Assert.assertEquals(Utils.getValueByJPath(monthlyIncome.getResponseJson(),"msg"), "success","search people success");
        log.info("get select count: {}", Utils.getValueByJPath(monthlyIncome.getResponseJson(), "result/selectCount"));
    }

    @Test
    public void testSearchPeopleComplexUseGsonFormat() throws Exception {
        LoginOneService loginService = new LoginOneService(restClient);
        loginService.login();

        SearchPeopleService searchPeopleService = new SearchPeopleService(restClient);
        List<Integer> pickLabelsIDs = Arrays.asList(32, 33);
        List<crmSelectComplexInfoModel> targetCrmLabels = crmLabelList.stream().filter(label->pickLabelsIDs.contains(label.getId())).collect(Collectors.toList());

        String customPayload = searchPeopleService.getSelectComplexPayloadStringNew(targetCrmLabels);
        log.info("payload is: {}", customPayload);
        HttpClientResult monthlyIncome=searchPeopleService.setPayloadSearchPeopleComplex(customPayload);
        log.info("verify search people complex for label monthlyIncome and latestRiskScore works.");
        restClient.AssertStatusCode200(monthlyIncome);
        Assert.assertEquals(Utils.getValueByJPath(monthlyIncome.getResponseJson(),"msg"), "success","search people success");
        log.info("get select count: {}", Utils.getValueByJPath(monthlyIncome.getResponseJson(), "result/selectCount"));
    }


}
