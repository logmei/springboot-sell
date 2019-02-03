package com.logmei.sell.dataobject.mapper;

import com.logmei.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ProductCategoryMapper {

    @Insert("insert into product_category (category_name,category_type) values (#{categoryName},#{categoryType})")
    int insertMap(Map<String,Object> map);

    @Insert("insert into product_category (category_name,category_type) values (#{categoryName},#{categoryType})")
    int insertObject(ProductCategory productCategory);

    @Update("update product_category set category_name=#{categoryName} where category_id = #{categoryId}")
    int updateObject(ProductCategory productCategory);

    @Update("update product_category set category_name=#{categoryName} where category_id = #{categoryId}")
    int updateByValue( String categoryName,Integer categoryId);

    @Select("select * from product_category where category_name=#{categoryName}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    } )
    List<ProductCategory> findByCategoryName(String categoryName);

    @Delete("delete from product_category where category_id=#{categoryId}")
    int delete(Integer categoryId);
}
