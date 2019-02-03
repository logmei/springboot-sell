package com.logmei.sell.service.impl;

import com.logmei.sell.DataTranslateObject.OrderDTO;
import com.logmei.sell.dataobject.OrderDetail;
import com.logmei.sell.enums.OrderStatusEnum;
import com.logmei.sell.service.IOrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("liulei9867");
        orderDTO.setBuyerAddress("北京");
        orderDTO.setBuyerPhone("987655444");
        orderDTO.setBuyerOpenid("4321");
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setProductId("s0004");
        detail.setProductQuantity(2);
        list.add(detail);
        OrderDetail detail1 = new OrderDetail();
        detail1.setProductId("s0002");
        detail1.setProductQuantity(2);
        list.add(detail1);
        orderDTO.setOrderDetails(list);
        orderService.create(orderDTO);
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1547088994161438282");
        orderDTO.setOrderStatus(OrderStatusEnum.NEWDOWN.getCode());
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setProductId("s0002");
        detail.setProductQuantity(2);
        list.add(detail);
        orderDTO.setOrderDetails(list);
        orderService.cancel(orderDTO);

    }

    @Test
    public void finish() {
        OrderDTO orderMaster = orderService.findOne("1547088994161438282");
//        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderDTO orderDTO = orderService.finish(orderMaster);
        Assert.assertEquals(orderDTO.getOrderStatus(),orderMaster.getOrderStatus());
    }

    @Test
    public void paid() {

    }
}