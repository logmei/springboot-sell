package com.logmei.sell.exception;

import com.logmei.sell.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = code;
    }

    public SellException(Integer code,String msg){
        super(msg);
        this.code=code;
    }
}
