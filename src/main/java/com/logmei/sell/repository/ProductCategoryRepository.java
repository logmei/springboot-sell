package com.logmei.sell.repository;

import com.logmei.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findProductCategoriesByCategoryType(int categoryType);

    ProductCategory findProductCategoryByCategoryId(int categoryId);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIds);
}
