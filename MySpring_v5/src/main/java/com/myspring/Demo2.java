package com.myspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//使用配置文件管理jdbc模板
//创建测试环境
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")

public class Demo2<ContextConfiguration> {

    //使用注解注入属性
    //Resource 是java中的注释，可以通过名称注入属性
    //Spring中也有提供，一个是根据类型自动注入，还得和另外一个强制使用名称匹配的注解一起使用才行，比较麻烦
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @Test
    public void run2(){
        jdbcTemplate.update("insert into t_account values (null,?,?)", "测试5", 700);

    }

}
