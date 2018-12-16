package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.CategoryForm;
import com.imooc.sell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * @description 卖家类目Controller
 * @Author 王艳军
 * @Date 2017-12-17 09:26
 */
@Controller
@RequestMapping(value = "/seller/category")
@Slf4j
public class SellerCategoryController {
  
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页显示商品类目列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                             Map<String, Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = new PageRequest(page - 1, size, sort);
        Page<ProductCategory> productCategoryPage = categoryService.findAll(pageRequest);
        map.put("productCategoryPage", productCategoryPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("category/list", map);
    }

    /**
     * 修改商品类目
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(name = "categoryId", required = false) Integer categoryId, Map<String, Object> map){
        if (categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 保存商品的类目信息
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping(value = "/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Map<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {
            //如果categoryForm.getCategoryId()不为空，则为更新
            if (categoryForm.getCategoryId() != null){
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
                map.put("msg", ResultEnum.CATEGORY_UPDATE_SUCCESS.getMessage());
            } else {//否则，为新增
                map.put("msg", ResultEnum.CATEGORY_SAVE_SUCCESS.getMessage());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        }catch (SellException e){
            log.error("【商品类目】 新增商品类目失败, e={}", e);
            map.put("msg", ResultEnum.CATEGORY_SAVE_OR_UPDATE_FAIL.getMessage() + e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
    
    /**
     * 根据categoryId删除一个类目
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping(value = "/delete")
    public ModelAndView delete(@RequestParam(name = "categoryId", required = true) Integer categoryId, Map<String, Object> map){
        map.put("url","/sell/seller/category/list");
        try {
            categoryService.deleteOne(categoryId);
        }catch (SellException e){
            log.error("【商品类目】 删除商品类目失败，categoryId={}, e={}", categoryId, e);
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }
    
}