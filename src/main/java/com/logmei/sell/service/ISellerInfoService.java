package com.logmei.sell.service;

import com.logmei.sell.dataobject.SellerInfo;

public interface ISellerInfoService {
    SellerInfo findByUsername(String username);
    SellerInfo save(SellerInfo sellerInfo);
    SellerInfo findByOpenid(String openid);
}
