package com.logmei.sell.repository;

import com.logmei.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByUsername(String username);

    SellerInfo findByOpenid(String openid);
}
