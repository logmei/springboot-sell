package com.logmei.sell.repository;

import com.logmei.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Random;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveOrderMasterTest(){
        IntStream.range(0,10).mapToObj(i->{
            OrderMaster orderMaster = new OrderMaster();
            orderMaster.setOrderId((new Random().nextInt(99)+100)+"");
            orderMaster.setBuyerAddress("轮胎厂宿舍"+i);
            orderMaster.setBuyerName("logmei"+i);
            orderMaster.setBuyerOpenid("123"+i);
            orderMaster.setBuyerPhone("1234567"+i);
            orderMaster.setOrderAmount(new BigDecimal(20));
            return orderMaster;
        }).forEach(order->orderMasterRepository.save(order));
    }

    @Test
    public void findOrderMasterPageTest(){
        Page<OrderMaster> list = orderMasterRepository.findByBuyerOpenid("1236", PageRequest.of(0,3));
        Assert.assertNotEquals(0,list.getSize());
    }



}