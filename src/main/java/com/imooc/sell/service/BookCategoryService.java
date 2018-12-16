package com.imooc.sell.service;

import com.imooc.sell.dataobject.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <pre>
 * @PackageName com.imooc.sell.service
 * @InterfaceName BookCategoryService
 * @Desc 书籍类目Service接口
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 19:04
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface BookCategoryService {

    BookCategory save(BookCategory bookCategory);

    BookCategory findOne(Integer categoryId);

    Page<BookCategory> findAll(Pageable pageable);

    void delete(Integer categoryId);
}
