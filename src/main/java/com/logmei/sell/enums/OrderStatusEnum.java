package com.logmei.sell.enums;

public enum OrderStatusEnum implements CodeEnum{
    NEWDOWN(0,"新下单"),
    FINISHED(1,"已完结"),
    CANCOL(2,"已取消");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
