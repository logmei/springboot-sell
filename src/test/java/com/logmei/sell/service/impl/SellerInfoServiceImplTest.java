package com.logmei.sell.service.impl;

import com.logmei.sell.dataobject.SellerInfo;
import com.logmei.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoServiceImpl sellerInfoService;
    private String sellerId = KeyUtil.getUniqueKey();
    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("openid111111");
        sellerInfo.setUsername("logmei");
        sellerInfo.setPassword("123");
        sellerInfo.setSellerId(sellerId);
        SellerInfo sellerInfo1 = sellerInfoService.save(sellerInfo);
        Assert.assertTrue("保存失败",sellerInfo1!=null);
    }
    @Test
    public void findByUsername() {
        SellerInfo sellerInfo = sellerInfoService.findByUsername("logmei");
        Assert.assertTrue("查询用户logmei的信息失败",sellerInfo.getSellerId().equals("1547543074427640755"));
    }


}