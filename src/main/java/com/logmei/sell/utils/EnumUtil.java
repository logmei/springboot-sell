package com.logmei.sell.utils;

import com.logmei.sell.enums.CodeEnum;

public class EnumUtil<T> {

    public static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> enumClass){
       for (T each : enumClass.getEnumConstants()){
           if(code.equals(each.getCode())) return each;
       }
       return null;
    }
}
