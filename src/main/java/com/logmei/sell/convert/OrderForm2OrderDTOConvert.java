package com.logmei.sell.convert;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.logmei.sell.DataTranslateObject.OrderDTO;
import com.logmei.sell.Form.OrderFormVO;
import com.logmei.sell.dataobject.OrderDetail;
import com.logmei.sell.enums.ExceptionEnum;
import com.logmei.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConvert {
    public static OrderDTO convert(OrderFormVO formVO){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(formVO.getName());
        orderDTO.setBuyerOpenid(formVO.getOpenid());
        orderDTO.setBuyerPhone(formVO.getPhone());
        orderDTO.setBuyerAddress(formVO.getAddress());
        List<OrderDetail> details = new ArrayList<>();

        try {
            details = gson.fromJson(formVO.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【转换】 json格式不正确 items = {}",formVO.getItems());
            throw new SellException(ExceptionEnum.JSON_WRONG);
        }
        orderDTO.setOrderDetails(details);
        return orderDTO;

    }
}
