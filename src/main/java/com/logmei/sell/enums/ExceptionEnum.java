package com.logmei.sell.enums;

public enum ExceptionEnum  implements CodeEnum {
    PARAM_WRONG(14,"参数错误"),
    PRODUCT_NOT_EXIST(0,"商品不存在"),
    NOT_STOCK(10,"没有库存"),
    NO_ORDER(11,"订单不存在"),
    WRONG_STATUS(12,"订单状态错误"),
    WRONG_PAID_STATUS(13,"订单支付状态错误"),
    JSON_WRONG(15,"不是正确的json格式"),
    CART_EMPTY(16,"购物车内容为空"),
    CREATE_ORDER_FAILD(17,"创建订单失败"),
    OPEN_ID_EMPTY(18,"openid为空"),
    NO_USER(19,"没有该用户"),
    NO_LOGIN(20,"用户未登录"),

    ;

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
