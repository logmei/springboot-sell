package com.logmei.sell.service.impl;

import com.logmei.sell.dataobject.SellerInfo;
import com.logmei.sell.repository.SellerInfoRepository;
import com.logmei.sell.service.ISellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements ISellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findByUsername(String username) {
        return sellerInfoRepository.findByUsername(username);
    }

    @Override
    public SellerInfo findByOpenid(String openid){
        return sellerInfoRepository.findByOpenid(openid);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo){
        return sellerInfoRepository.save(sellerInfo);
    }
}
