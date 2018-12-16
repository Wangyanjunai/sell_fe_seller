package com.imooc.sell.controller;

import com.imooc.sell.dataobject.BookCategory;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.BookCategoryForm;
import com.imooc.sell.service.BookCategoryService;
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
 * <pre>
 * @PackageName com.imooc.sell.controller
 * @ClassName BookCategoryController
 * @Desc 书籍信息Controller
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 19:08
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping(value = "/book/category")

public class SellerBookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(name = "categoryId", required = false) Integer categoryId, Map<String, Object> map){
        if (categoryId != null){
            BookCategory bookCategory = bookCategoryService.findOne(categoryId);
            map.put("bookCategory", bookCategory);
        }
        return new ModelAndView("book/category/index", map);
    }

    @PostMapping(value = "/save")
    public ModelAndView save(@Valid BookCategoryForm bookCategoryForm, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/book/category/index");
            return new ModelAndView("common/error",map);
        }
        try {
            BookCategory bookCategory = BookCategory.builder().build();
            if (bookCategoryForm.getCategoryId() != null) {
                bookCategory = bookCategoryService.findOne(bookCategoryForm.getCategoryId());
                map.put("msg", ResultEnum.CATEGORY_UPDATE_SUCCESS.getMessage());
            } else {
                map.put("msg", ResultEnum.CATEGORY_SAVE_SUCCESS.getMessage());
            }
            BeanUtils.copyProperties(bookCategoryForm, bookCategory);
            bookCategoryService.save(bookCategory);
        }catch (SellException e) {
            log.error("【书籍类目】 新增书籍类目失败, e={}", e);
            map.put("msg", ResultEnum.CATEGORY_SAVE_OR_UPDATE_FAIL.getMessage() + e.getMessage());
            map.put("url", "/sell/book/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/sell/book/category/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                             Map<String, Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = new PageRequest(page - 1, size, sort);
        Page<BookCategory> bookCategoryPage = bookCategoryService.findAll(pageRequest);
        map.put("bookCategoryPage", bookCategoryPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("book/category/list", map);
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(@RequestParam(name = "categoryId", required = true) Integer categoryId, Map<String, Object> map){
        map.put("url","/sell/book/category/list");
        try {
            bookCategoryService.delete(categoryId);
        }catch (SellException e){
            log.error("【书籍类目】 删除书籍类目失败，categoryId={}, e={}", categoryId, e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }
}
