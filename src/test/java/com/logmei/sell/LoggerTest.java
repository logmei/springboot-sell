package com.logmei.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
    Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name = "logmei";
        String pwd = "12345";
        logger.debug("--------------------debug");
        logger.info("--------name={};password={}----------------info",name,pwd);
        logger.error("-------------------error");

    }

    public static void main(String[] args){

    }
}
