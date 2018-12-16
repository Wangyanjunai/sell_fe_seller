package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.BookCategory;
import com.imooc.sell.repository.BookCategoryRepository;
import com.imooc.sell.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * @PackageName com.imooc.sell.service.impl
 * @ClassName BookCategoryServiceImpl
 * @Desc 书籍Service接口实现
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 19:06
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private BookCategoryRepository categoryRepository;

    @Override
    public BookCategory save(BookCategory bookCategory) {
        return categoryRepository.save(bookCategory);
    }

    @Override
    public BookCategory findOne(Integer categoryId) {
       return  categoryRepository.findOne(categoryId);
    }

    @Override
    public Page<BookCategory> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer categoryId) {
        categoryRepository.delete(categoryId);
    }
}
