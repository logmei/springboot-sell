package com.logmei.sell.service.impl;

import com.logmei.sell.dataobject.ProductCategory;
import com.logmei.sell.repository.ProductCategoryRepository;
import com.logmei.sell.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void saveProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> gategoryType) {
        return productCategoryRepository.findByCategoryTypeIn(gategoryType);
    }

    @Override
    public ProductCategory findByCategoryId(Integer id) {
        return productCategoryRepository.findProductCategoryByCategoryId(id);
    }
}
