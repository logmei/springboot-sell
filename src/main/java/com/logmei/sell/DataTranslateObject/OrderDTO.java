package com.logmei.sell.DataTranslateObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.logmei.sell.dataobject.OrderDetail;
import com.logmei.sell.enums.OrderStatusEnum;
import com.logmei.sell.enums.PayStatusEnum;
import com.logmei.sell.utils.EnumUtil;
import com.logmei.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //单独类判断若为null则不写入json
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderStatusEnum.NEWDOWN.getCode();
    private Integer payStatus = PayStatusEnum.UNPAID.getCode();
    @JsonSerialize(using = Date2LongSerializer.class)//生成json时修改内容
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    private OrderStatusEnum getOrderStatusEnum(Integer orderStatus){
        return EnumUtil.getEnumByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    private PayStatusEnum getPayStatusEnum(Integer payStatus){
        return EnumUtil.getEnumByCode(payStatus,PayStatusEnum.class);
    }

}
