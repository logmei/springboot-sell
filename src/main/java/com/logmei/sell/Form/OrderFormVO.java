package com.logmei.sell.Form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderFormVO {

    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotEmpty(message = "电话不能为空")
    private String phone;
    @NotEmpty(message = "地址不能为空")
    private String address;
    @NotEmpty(message = "openid不能为空")
    private String openid;
    @NotEmpty(message = "购物车内容不能为空")
    private String items;
}
