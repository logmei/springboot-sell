package com.logmei.sell.enums;

public enum ProductStatusEnum  implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
