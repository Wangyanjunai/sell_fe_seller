package com.imooc.sell.repository;

import com.imooc.sell.dataobject.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * @PackageName com.imooc.sell.repository
 * @InterfaceName BookCategoryRepository
 * @Desc 书籍分类Dao
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 18:43
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
}
