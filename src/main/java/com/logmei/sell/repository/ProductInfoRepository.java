package com.logmei.sell.repository;

import com.logmei.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo>  findByProductStatus(Integer productStatus);

    ProductInfo findByProductId(String productId);
}
