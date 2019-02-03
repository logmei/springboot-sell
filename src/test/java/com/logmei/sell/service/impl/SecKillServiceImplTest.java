package com.logmei.sell.service.impl;

import com.logmei.sell.service.ISecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecKillServiceImplTest {

    @Autowired
    private  ISecKillService secKillService;

    @Test
   public  void secKill(){
        //secKillService.orderProductMockDiffUser("12345");
       //创建线程池
//        LinkedBlockingQueue workQuen = new LinkedBlockingQueue(100);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,50,100, TimeUnit.SECONDS,workQuen);
       IntStream.range(0,1000).forEach(i->{
           new Thread(()->{
               secKillService.orderProductMockDiffUser("12345");
           }).start();
       });
   }
}