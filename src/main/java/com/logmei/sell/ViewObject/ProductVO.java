package com.logmei.sell.ViewObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductVO {
    @JsonProperty("name")
   private String categoryName;
    @JsonProperty("type")
   private Integer categoryType;
    @JsonProperty("foods")
   private List<FoodVO> foodVOList;

   public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<FoodVO> getFoodVOList() {
        return foodVOList;
    }

    public void setFoodVOList(List<FoodVO> foodVOList) {
        this.foodVOList = foodVOList;
    }
}
