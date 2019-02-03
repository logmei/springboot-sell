package com.logmei.sell.controller;

import com.logmei.sell.config.ProjectUrlConfig;
import com.logmei.sell.constant.ClientConst;
import com.logmei.sell.constant.SellerRedisConst;
import com.logmei.sell.dataobject.SellerInfo;
import com.logmei.sell.enums.ExceptionEnum;
import com.logmei.sell.service.ISellerInfoService;
import com.logmei.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerLoginController {



    @Autowired
    private ISellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, HttpServletResponse response, Map<String,Object> map){
        SellerInfo sellerInfo = sellerInfoService.findByOpenid(openid);
        //登录失败
        if(sellerInfo == null){
            //提示信息
            map.put("msg",ExceptionEnum.NO_USER.getMsg());
            map.put("url",projectUrlConfig.getSell()+"/sell/seller/order/list");
            return new ModelAndView("/seller/loginout");
        }
        //设置token到redis   一定要设置过期时间
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(SellerRedisConst.TOKEN_PRIFIX,token),openid,SellerRedisConst.EXPIRE, TimeUnit.SECONDS);

        //写入cookie
        CookieUtil.set(response, ClientConst.TOKEN,token,ClientConst.EXPIRE);
        return new ModelAndView("redirect:"+ projectUrlConfig.getSell()+"/sell/seller/order/list");
    }

    @GetMapping("/loginOut")
    public ModelAndView loginOut(HttpServletRequest request,HttpServletResponse response,Map<String,String> map) {
        Cookie cookie = CookieUtil.get(request,ClientConst.TOKEN);
        if(cookie != null){
            String token = cookie.getValue();
            redisTemplate.opsForValue().getOperations().delete(String.format(SellerRedisConst.TOKEN_PRIFIX,token));
            CookieUtil.set(response,ClientConst.TOKEN,"",0);
            map.put("msg","您已退出登录");
        }
        return new ModelAndView("/seller/loginout");

    }
}
