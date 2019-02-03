package com.logmei.sell.handler;

import com.logmei.sell.enums.ExceptionEnum;
import com.logmei.sell.exception.SellException;
import com.logmei.sell.exception.SellerAuthorizeException;
import com.logmei.sell.exception.SellerBandException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(SellerAuthorizeException.class)
    @ResponseBody
    public ModelAndView sellerAuthorizeExceptionHandler(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("code",ExceptionEnum.NO_LOGIN.getCode());
        map.put("msg",ExceptionEnum.NO_LOGIN.getMsg()+"(SellerAuthorizeException)");
        return new ModelAndView("/seller/loginout",map);
    }

    @ExceptionHandler(com.logmei.sell.exception.SellException.class)
    @ResponseBody
    public ModelAndView sellerExceptionHandler(SellException ex){
        HashMap<String,Object> map = new HashMap<>();
        map.put("code",ex.getCode());
        map.put("msg",ex.getMessage()+" (SellException)");
        return new ModelAndView("/seller/loginout",map);
    }

    @ExceptionHandler(SellerBandException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sellerBandExceptionHandler(){}
}
