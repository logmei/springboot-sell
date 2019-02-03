package com.logmei.sell.service.impl;

import com.logmei.sell.exception.SellException;
import com.logmei.sell.service.ISecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class SecKillServiceImpl implements ISecKillService {

    public static HashMap<String,Integer> products;//商品
    public static HashMap<String,Integer> stocks;//库存
    public static HashMap<String,String> orders;//下单
    //redis同步锁
    @Autowired
    RedisLock redisLock;

    ReentrantLock lock = new ReentrantLock();

    static {
        products = new HashMap<>();
        stocks = new HashMap<>();
        orders = new HashMap<>();
        products.put("12345",10000);
        stocks.put("12345",10000);

    }

    private String query(String productId){
        return "国庆活动，皮蛋粥特价，限量份"
                + products.get(productId)
                +" 还剩：" + stocks.get(productId)+" 份"
                +" 该商品成功下单用户数目："
                +  orders.size() +" 人" ;
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.query(productId);
    }

    @Override
    public   void orderProductMockDiffUser(String productId) {
        //lock.lock();
        redisLock.lock("product",0L);
            //查找订单，若没有库存抛异常
            Integer num = stocks.get(productId);
            if (num == null) {
                throw new SellException(0, "没有库存");
            }
            //有库存则库存减一，插入订单
            String uuid = UUID.randomUUID().toString();
            orders.put(uuid, productId);
            int s = num - 1;
            stocks.put(productId, s);
            System.out.println("当前库存：" + s + ";订单：" + orders.size());
            try {
                TimeUnit.SECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //lock.unlock();
        redisLock.unlock("product",0L);

    }
}
