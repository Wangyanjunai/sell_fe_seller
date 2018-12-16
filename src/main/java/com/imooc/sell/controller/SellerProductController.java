package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.ProductForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.StringUtil;
import com.imooc.sell.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

/**
 * describe:
 *
 * @author 王艳军
 * @date 2017/12/16 12:19:45
 */
@Controller
@RequestMapping(value = "/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = true, defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = new PageRequest(page - 1, size, sort);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productInfoPage", productInfoPage);
        map.put("productCategoryList", productCategoryList);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping(value = "/off_sale")
    public ModelAndView offSale(@RequestParam(name = "productId", required = true) String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            log.error("【商品下架】   出现错误，商品：productId={}，错误信息：error={}", productId, e.getMessage());
            map.put("msg", ResultEnum.PRODUCT_OFF_SALE_FAIL.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_OFF_SALE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

    /**
     * 商品上架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping(value = "/on_sale")
    public ModelAndView onSale(@RequestParam(name = "productId", required = true) String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            log.error("【商品上架】 出现错误，商品：productId={}，错误信息：error={}", productId, e.getMessage());
            map.put("msg", ResultEnum.PRODUCT_ON_SALE_FAIL.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_ON_SALE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        // 查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 保存/更新
     *
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping(value = "/save")
    @CacheEvict(cacheNames = "product", allEntries = true, beforeInvocation = true)
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult,
                             Map<String, Object> map) {
        String msg = null;
        String url = "/sell/seller/product/index";
        String viewName = "common/error";
        String errorMsg = null;
        if (bindingResult.hasErrors()) {
            msg = bindingResult.getFieldError().getDefaultMessage();
            map.put("msg", msg);
            map.put("url", url);
            return new ModelAndView(viewName, map);
        }
        try {
            ProductInfo productInfo = new ProductInfo();
            // 如果productId不为空，说明是修改
            if (StringUtil.notEmpty(productForm.getProductId())) {
                productInfo = productService.findOne(productForm.getProductId());
                errorMsg = "【修改商品】 出现错误, error={}, msg={}";
                msg = ResultEnum.PRODUCT_UPDATE_FAIL.getMessage();
            } else {
                // 否则如果productId为空，说明是新增
                productInfo.setProductId(UUIDUtil.gen32UUID());
                errorMsg = "【新增商品】 出现错误, error={}, msg={}";
                msg = ResultEnum.PRODUCT_SAVE_FAIL.getMessage();
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        } catch (Exception e) {
            log.error(errorMsg, e, msg);
            map.put("msg", msg);
            map.put("url", url);
            return new ModelAndView(viewName, map);
        }
        msg = ResultEnum.PRODUCT_SAVE_OR_UPDATE_SUCCESS.getMessage();
        url = "/sell/seller/product/list";
        errorMsg = "【新增修改商品】 成功 result={}";
        viewName = "common/success";
        log.info(errorMsg, msg);
        map.put("msg", msg);
        map.put("url", url);
        return new ModelAndView(viewName, map);
    }
}
