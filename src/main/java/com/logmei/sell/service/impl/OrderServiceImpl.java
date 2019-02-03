package com.logmei.sell.service.impl;

import com.logmei.sell.DataTranslateObject.CartDTO;
import com.logmei.sell.DataTranslateObject.OrderDTO;
import com.logmei.sell.convert.OrderDetail2CartDTOConvert;
import com.logmei.sell.convert.OrderMaster2OrderDTOConvert;
import com.logmei.sell.dataobject.OrderDetail;
import com.logmei.sell.dataobject.OrderMaster;
import com.logmei.sell.dataobject.ProductInfo;
import com.logmei.sell.enums.ExceptionEnum;
import com.logmei.sell.enums.OrderStatusEnum;
import com.logmei.sell.enums.PayStatusEnum;
import com.logmei.sell.exception.SellException;
import com.logmei.sell.repository.OrderDetailRepository;
import com.logmei.sell.repository.OrderMasterRepository;
import com.logmei.sell.repository.ProductInfoRepository;
import com.logmei.sell.service.IOrderService;
import com.logmei.sell.service.IProductInfoService;
import com.logmei.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    private final String BUYER_OPENID="223234";


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private IProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private WebSocetServer webSocetServer;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //订单id
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
        //1、查询商品信息 计算总金额 保存订单详情
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()){
            ProductInfo productInfo = productInfoRepository.findByProductId(orderDetail.getProductId());
            //若没有抛异常
            if(productInfo == null) throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //保存
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetailRepository.save(orderDetail);
        }
        //保存订单
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderDTO.setOrderId(orderId);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEWDOWN.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());
        orderMasterRepository.save(orderMaster);
        //减少库存
       List<CartDTO> list = OrderDetail2CartDTOConvert.convert(orderDTO.getOrderDetails());
        productInfoService.decreaseStock(list);

        webSocetServer.sendMessage(orderDTO.toString());

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        OrderMaster orderMaster1 = orderMasterRepository.findById(orderId).get();
        if(orderMaster1 == null){
            throw new SellException(ExceptionEnum.NO_ORDER);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster1,orderDTO);
        List<OrderDetail> details = orderDetailRepository.findByOrderId(orderId);
        orderDTO.setOrderDetails(details);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String openid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(openid,pageable);
        List<OrderDTO> list = OrderMaster2OrderDTOConvert.convert(orderMasterPage.getContent());
        Page<OrderDTO> pageable1 = new PageImpl(list,pageable,orderMasterPage.getTotalElements());

        return pageable1;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> list = OrderMaster2OrderDTOConvert.convert(orderMasterPage.getContent());
        Page<OrderDTO> pageable1 = new PageImpl(list,pageable,orderMasterPage.getTotalElements());

        return pageable1;
    }

    @Override
    @Transactional
    //取消订单
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEWDOWN)){
            throw new SellException(ExceptionEnum.WRONG_STATUS);
        }
        //修改订单状态
        OrderMaster order = new OrderMaster();
        order.setOrderId(orderDTO.getOrderId());
        OrderMaster orderMaster = orderMasterRepository.findOne(Example.of(order)).get();
        orderMaster.setOrderStatus(OrderStatusEnum.CANCOL.getCode());

        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);

        if (orderMaster1==null)throw  new SellException(ExceptionEnum.NO_ORDER);

        //返还库存
        List<CartDTO> list = OrderDetail2CartDTOConvert.convert(orderDTO.getOrderDetails());
        productInfoService.increaseStock(list);

        //如果已经支付，需要退款
        if(orderDTO.getOrderStatus().equals(PayStatusEnum.PAID)){
            //TODO
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEWDOWN.getCode())){
            throw new SellException(ExceptionEnum.WRONG_STATUS);
        }

        //修改状态
        OrderMaster order = new OrderMaster();
        order.setOrderId(orderDTO.getOrderId());
        OrderMaster orderMaster = orderMasterRepository.findOne(Example.of(order)).get();
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
       //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEWDOWN)){
            log.error("【修改支付状态错误】 orderDTO={}",orderDTO);
            throw new SellException(ExceptionEnum.WRONG_STATUS);

        }

        //判断支付状态
        if(orderDTO.getPayStatus().equals(PayStatusEnum.PAID)){
            log.error("【订单已支付】 orderDTO={}",orderDTO);
            throw new SellException(ExceptionEnum.WRONG_PAID_STATUS);
        }
        //修改支付状态
        OrderMaster order = new OrderMaster();
        order.setOrderId(orderDTO.getOrderId());
        OrderMaster orderMaster = orderMasterRepository.findOne(Example.of(order)).get();
        orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
