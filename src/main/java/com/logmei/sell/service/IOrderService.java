package com.logmei.sell.service;

import com.logmei.sell.DataTranslateObject.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
//    创建订单
    OrderDTO create(OrderDTO orderDTO);
//    查询单个订单
    OrderDTO findOne(String orderId);
//    查询订单列表
    Page<OrderDTO> findList(String openid, Pageable pageable);

    Page<OrderDTO> findList(Pageable pageable);
//    取消订单
    OrderDTO cancel(OrderDTO orderDTO);
//    完结订单
    OrderDTO finish(OrderDTO orderDTO);
//    支付订单
    OrderDTO paid(OrderDTO orderDTO);
}
