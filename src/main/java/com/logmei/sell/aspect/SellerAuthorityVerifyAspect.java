package com.logmei.sell.aspect;

import com.logmei.sell.constant.ClientConst;
import com.logmei.sell.constant.SellerRedisConst;
import com.logmei.sell.exception.SellerAuthorizeException;
import com.logmei.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
@Slf4j
public class SellerAuthorityVerifyAspect {

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Pointcut(value = "execution(public * com.logmei.sell.controller.Seller*.*(..) ) && !execution(public * com.logmei.sell.controller.SellerLoginController.login*(..))")
    public void verify(){

    }

    @Before("verify()")
    public void beforeVerify(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Cookie cookie = CookieUtil.get(request, ClientConst.TOKEN);
        if(cookie == null){
            log.warn("【登录校验】 没有cookie");
            throw new SellerAuthorizeException();
        }
        String openid = redisTemplate.opsForValue().get(String.format(SellerRedisConst.TOKEN_PRIFIX,cookie.getValue()));
        if(openid == null){
            log.warn("【登录校验】 没有redis 中不存在openid={}",openid);
            throw new SellerAuthorizeException();
        }
    }
}
