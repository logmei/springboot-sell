package com.logmei.sell.repository;

import com.logmei.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductCategoryDaoTest {

    @Autowired
    private ProductCategoryRepository productCategoryDao;
    @Test
    public void getProductCategoryTest(){
       List<ProductCategory> list = productCategoryDao.findProductCategoriesByCategoryType(8);
       System.out.println(list.toString());

       ProductCategory productCategory = productCategoryDao.findProductCategoryByCategoryId(2);
       System.out.println(productCategory);
    }

    @Test
    @Transactional //回滚，添加到数据库后删除
    public void saveProductCategoryTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("我很好3");
        productCategory.setCategoryType(2);
        ProductCategory p = productCategoryDao.save(productCategory);
       // Assert.assertEquals(p,null);
        System.out.println("-----------------");
        Assert.assertNotEquals(null,p);

    }

    @Test
    public void updateProductCategoryTest() {
        //List list = productCategoryDao.findAll();
        ProductCategory productCategory = productCategoryDao.findProductCategoryByCategoryId(1);
        productCategory.setCategoryName("logmei98511");
        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
    }
}