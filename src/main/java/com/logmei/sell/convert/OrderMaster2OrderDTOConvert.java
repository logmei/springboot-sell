package com.logmei.sell.convert;

import com.logmei.sell.DataTranslateObject.OrderDTO;
import com.logmei.sell.dataobject.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConvert {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> list){
        return list.stream().map(o->convert(o)).collect(Collectors.toList());

    }
}
