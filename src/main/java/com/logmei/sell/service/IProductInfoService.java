package com.logmei.sell.service;

import com.logmei.sell.DataTranslateObject.CartDTO;
import com.logmei.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductInfoService {

    void saveProductInfo(ProductInfo productInfo);

    void saveProductInfoList(List<ProductInfo> list);

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    void increaseStock(List<CartDTO> list);

    void decreaseStock(List<CartDTO> list);
}
