package com.logmei.sell.convert;

import com.logmei.sell.DataTranslateObject.CartDTO;
import com.logmei.sell.dataobject.OrderDetail;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDetail2CartDTOConvert {

    public static List<CartDTO> convert(List<OrderDetail> list){
        return list.stream().map(t->{
           CartDTO cartDTO = new CartDTO();
           cartDTO.setProductId(t.getProductId());
           cartDTO.setProductQuantity(t.getProductQuantity());
           return cartDTO;
        }).collect(Collectors.toList());
    }
}
