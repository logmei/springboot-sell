package com.logmei.sell.dataobject.mapper;

import com.logmei.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Test
    public void insertMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","最好吃的菜");
        map.put("categoryType",13);
        int re = productCategoryMapper.insertMap(map);
        Assert.assertEquals(re,1);
    }

    @Test
    public void insertObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("最难吃的菜");
        productCategory.setCategoryType(10);
        int re = productCategoryMapper.insertObject(productCategory);
        Assert.assertEquals(re,1);
    }

    @Test
    public void updateByValue() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("最难吃的菜");
        productCategory.setCategoryType(10);
        int re = productCategoryMapper.updateByValue("最难吃的菜111",5);
        Assert.assertEquals(re,1);
    }

    @Test
    public void list(){
        List list = productCategoryMapper.findByCategoryName("最好吃的菜");
        log.info(list.toString());
    }

    @Test
    public void delete(){
        int re = productCategoryMapper.delete(6);
        Assert.assertTrue("删除失败",re==1);
    }
}