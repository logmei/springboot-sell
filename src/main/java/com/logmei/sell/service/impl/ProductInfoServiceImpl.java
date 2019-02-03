package com.logmei.sell.service.impl;

import com.logmei.sell.DataTranslateObject.CartDTO;
import com.logmei.sell.dataobject.ProductInfo;
import com.logmei.sell.enums.ExceptionEnum;
import com.logmei.sell.enums.ProductStatusEnum;
import com.logmei.sell.exception.SellException;
import com.logmei.sell.repository.OrderDetailRepository;
import com.logmei.sell.repository.ProductInfoRepository;
import com.logmei.sell.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements IProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public void saveProductInfo(ProductInfo productInfo) {
        productInfoRepository.save(productInfo);
    }
    @Override
    public void saveProductInfoList(List<ProductInfo> list){
        productInfoRepository.saveAll(list);
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> list) {
        for (CartDTO cartDTO:list){
            ProductInfo p = new ProductInfo();
            p.setProductId(cartDTO.getProductId());
            Example<ProductInfo> example = Example.of(p);
            ProductInfo productInfo = productInfoRepository.findOne(example).get();
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);


        }


    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> list) {
        for (CartDTO cartDTO:list){
            ProductInfo productInfo = productInfoRepository.getOne(cartDTO.getProductId());
            if(productInfo == null) throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0) throw new SellException(ExceptionEnum.NOT_STOCK);
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

    }
}
