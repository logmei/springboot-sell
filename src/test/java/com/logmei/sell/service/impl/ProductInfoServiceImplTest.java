package com.logmei.sell.service.impl;

import com.logmei.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void saveProductInfoTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("西红柿炒鸡蛋1");
        productInfo.setProductStatus(1);
        productInfo.setProductIcon("/icon/child.ioc");
        productInfo.setProductId("s0004");
        productInfo.setProductName("西红柿炒鸡蛋1");
        productInfo.setProductPrice(new BigDecimal(14.34));
        productInfo.setProductStock(10);
        ProductInfo productInfo2 = new ProductInfo();
        productInfo2.setCategoryType(3);
        productInfo2.setProductDescription("尖椒鸡蛋1");
        productInfo2.setProductStatus(1);
        productInfo2.setProductIcon("/icon/child.ioc");
        productInfo2.setProductId("s0006");
        productInfo2.setProductName("尖椒鸡蛋1");
        productInfo2.setProductPrice(new BigDecimal(17.34));
        productInfo2.setProductStock(6);
        List<ProductInfo> list = new ArrayList<>();
        list.add(productInfo);
        list.add(productInfo2);
        productInfoService.saveProductInfoList(list);
    }

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> list = productInfoService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }
}