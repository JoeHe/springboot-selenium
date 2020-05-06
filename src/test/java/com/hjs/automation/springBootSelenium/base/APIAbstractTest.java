package com.hjs.automation.springBootSelenium.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
public class APIAbstractTest extends AbstractTestNGSpringContextTests {

//    protected SqlSessionFactory sqlSessionFactory;
//
//    @BeforeClass
//    public void before() throws IOException {
//        //指定mybatis全局配置文件
//        String resource = "mybatis-config.xml";
//        //读取全局配置文件
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        //构建SqlSessionFactory对象
////        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
////        this.sqlSessionFactory = sqlSessionFactory;
//        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//    }

}
