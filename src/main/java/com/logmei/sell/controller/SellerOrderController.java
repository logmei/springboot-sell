package com.logmei.sell.controller;

import com.logmei.sell.DataTranslateObject.OrderDTO;
import com.logmei.sell.service.IOrderService;
import com.logmei.sell.service.ISecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ISecKillService secKillService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page
            , @RequestParam(value = "size",defaultValue = "12") Integer size, Map<String,Object> map){
        PageRequest pageable = PageRequest.of(page,size);
        Page<OrderDTO> pagelist = orderService.findList(pageable);
        map.put("pageList",pagelist);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/secKill")
    public void secKill(){
        secKillService.orderProductMockDiffUser("12345");
    }


}
