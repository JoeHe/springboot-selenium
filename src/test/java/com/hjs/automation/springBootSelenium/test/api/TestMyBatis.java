package com.hjs.automation.springBootSelenium.test.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.ibatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;

import com.hjs.automation.springBootSelenium.Mapper.cmsSubjectCategoryMapper;
import com.hjs.automation.springBootSelenium.base.APIAbstractTest;
import com.hjs.automation.springBootSelenium.model.cmsSubjectCategoryModel;



@Slf4j
public class TestMyBatis extends APIAbstractTest {

    private SqlSessionFactory sqlSessionFactory;

    private  List<cmsSubjectCategoryModel> userModelList;


    @BeforeClass
    public void beforeClass() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            cmsSubjectCategoryMapper mapper = sqlSession.getMapper(cmsSubjectCategoryMapper.class);
            userModelList = mapper.getCategoryList(); //执行查询操作
        }
    }


    @Test
    public void test_mybatis_get_mall_subject_category() {
        logs("test mybatis query.");
        userModelList.forEach(item -> {
            log.info("{}", item);
            Reporter.log(item.toString());

        });
    }

    @Test(enabled = false)
    public void test_mybatis_insert_subject_category() throws IOException {
        logs("test mybatis insert subject_category.");
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            cmsSubjectCategoryMapper mapper = sqlSession.getMapper(cmsSubjectCategoryMapper.class);
            cmsSubjectCategoryModel userModel = cmsSubjectCategoryModel.builder().name("春节专题").icon("icon.springfest").subject_count(6).show_status(9).sort(0).build();
            int insert = mapper.insertCategory(userModel); //执行插入操作
            logs("影响行数："+insert);
        }
    }

    @Test
    public void test_mybatis_update_subject_category() throws IOException {
        logs("test mybatis update subject_category.");
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            cmsSubjectCategoryMapper mapper = sqlSession.getMapper(cmsSubjectCategoryMapper.class);
            cmsSubjectCategoryModel userModel = cmsSubjectCategoryModel.builder().id(7).name("旅游专题3").icon("icon.travel6").subject_count(7).show_status(1).sort(0).build();
            int insert = mapper.updateCategory(userModel); //执行更新操作
            logs("影响行数："+insert);
        }
    }

    @Test
    public void test_mybatis_delete_subject_category() throws IOException {
        logs("test mybatis delete subject_category.");
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            cmsSubjectCategoryMapper mapper = sqlSession.getMapper(cmsSubjectCategoryMapper.class);
            int insert = mapper.deleteCategory(6); //执行删除操作
            logs("影响行数："+insert);
        }
    }

    private void logs(String s){
        log.info(s);
        Reporter.log(s);
    }

}
