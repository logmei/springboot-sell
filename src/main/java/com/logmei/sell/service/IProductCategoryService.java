package com.logmei.sell.service;

import com.logmei.sell.dataobject.ProductCategory;

import java.util.List;

public interface IProductCategoryService {

    void saveProductCategory(ProductCategory productCategory);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> gategoryType);

    ProductCategory findByCategoryId(Integer id);
}
