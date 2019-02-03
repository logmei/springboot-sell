package com.logmei.sell.controller;

import com.logmei.sell.ViewObject.FoodVO;
import com.logmei.sell.ViewObject.ProductVO;
import com.logmei.sell.ViewObject.ResultVO;
import com.logmei.sell.dataobject.ProductCategory;
import com.logmei.sell.dataobject.ProductInfo;
import com.logmei.sell.service.IProductCategoryService;
import com.logmei.sell.service.IProductInfoService;
import com.logmei.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProducterController {

    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO getFoodList(){
        List<ProductInfo> productInfos = productInfoService.findUpAll();
        List<Integer> categoryTypes = productInfos.stream().map(t->t.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypes);
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory category : categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            List<FoodVO> foodVOS = new ArrayList<>();
            for(ProductInfo productInfo : productInfos){
                if(category.getCategoryType().equals(productInfo.getCategoryType())){
                    FoodVO foodVO = new FoodVO();
                    BeanUtils.copyProperties(productInfo,foodVO);
                    foodVOS.add(foodVO);
                }
            }
            productVO.setFoodVOList(foodVOS);
            productVOList.add(productVO);
        }
      return ResultVOUtil.success(productVOList);
    }

}
