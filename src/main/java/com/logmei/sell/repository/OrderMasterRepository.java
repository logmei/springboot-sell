package com.logmei.sell.repository;

import com.logmei.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String openid , Pageable pageable);

    Page<OrderMaster> findAll(Pageable pageable);
}
