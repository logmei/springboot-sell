package com.logmei.sell.repository;

import com.logmei.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void saveOrderDetailTest(){
        IntStream.range(0,10).mapToObj(i->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId((new Random().nextInt(99)+100)+"");
            orderDetail.setOrderId(123+"");
            orderDetail.setProductIcon("/ico/path"+i);
            orderDetail.setProductId("s0001");
            orderDetail.setProductName("虾仁炒鸡蛋"+i);
            orderDetail.setProductPrice(new BigDecimal(12.34));
            orderDetail.setProductQuantity(new Random(10).nextInt(10));
            return orderDetail;
        }).forEach(d->{
           OrderDetail orderDetail = orderDetailRepository.save(d);
            Assert.assertNotNull(orderDetail);
        });
    }

    @Test
    public void findOrderDetailListTest(){
        List<OrderDetail> list = orderDetailRepository.findByOrderId("123");
        Assert.assertNotEquals(0,list.size());
    }

}