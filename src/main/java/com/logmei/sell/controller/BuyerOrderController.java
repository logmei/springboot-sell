package com.logmei.sell.controller;

import com.logmei.sell.DataTranslateObject.OrderDTO;
import com.logmei.sell.Form.OrderFormVO;
import com.logmei.sell.ViewObject.ResultVO;
import com.logmei.sell.convert.OrderForm2OrderDTOConvert;
import com.logmei.sell.enums.ExceptionEnum;
import com.logmei.sell.exception.SellException;
import com.logmei.sell.service.IOrderService;
import com.logmei.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private IOrderService orderService;
    //创建订单
    @RequestMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderFormVO form, BindingResult result){
        //如果存在错误
        if(result.hasErrors()){
            log.error("【创建订单错误】 参数不正确 formVO={}",form);
            throw new SellException(ExceptionEnum.PARAM_WRONG.getCode(),result.getFieldError().getDefaultMessage());
        }
        //创建订单
        if(StringUtils.isEmpty(form.getItems())){
            log.error("【创建订单错误】购物车内容为空");
            throw new SellException(ExceptionEnum.CART_EMPTY);
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(form);

        OrderDTO orderDTO1 = orderService.create(orderDTO);

        if (orderDTO1 == null){
            log.error("【创建订单错误】 ");
            throw new SellException(ExceptionEnum.CREATE_ORDER_FAILD);
        }
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderDTO1.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @RequestMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam String openid,
                                         @RequestParam(defaultValue = "0",value = "page") Integer page,
                                         @RequestParam(value = "size",defaultValue = "12") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】 openid不能为空");
            throw new SellException(ExceptionEnum.OPEN_ID_EMPTY);
        }
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<OrderDTO> list =  orderService.findList(openid, pageRequest);
        return ResultVOUtil.success(list.getContent());
    }


    //订单详情
    public ResultVO<OrderDTO> detail(@RequestParam String openid,@RequestParam String orderid){
        //TODO
        OrderDTO orderDTO = orderService.findOne(orderid);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    public ResultVO<OrderDTO> cancel(@RequestParam String openid,@RequestParam String orderid){
        //TODO
        OrderDTO orderDTO = orderService.findOne(orderid);
        OrderDTO orderDTO1 = orderService.cancel(orderDTO);
        return ResultVOUtil.success(orderDTO1);
    }
}
