package com.imooc.sell.service;

import com.imooc.sell.dataobject.BookInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <pre>
 * @PackageName com.imooc.sell.service
 * @InterfaceName BookInfoService
 * @Desc 小说书籍信息Service接口
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/20 11:05
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface BookInfoService {

    /**
     * 新增小说书籍信息
     * @param bookInfo
     * @return
     */
    BookInfo save (BookInfo bookInfo);

    /**
     * 删除小说书籍信息
     * @param bookInfo
     */
    void delete (BookInfo bookInfo);

    /**
     * 修改小说书籍信息
     * @param bookInfo
     */
    BookInfo update (BookInfo bookInfo);

    /**
     * 根据小说的id查找小说信息
     * @param bookId
     * @return
     */
    BookInfo findByBookId(String bookId);

    /**
     * 查找小说书籍列表
     * @return
     */
    List<BookInfo> findAll();

    /**
     * 分页查找小说书籍列表
     * @param pageable
     * @return
     */
    Page<BookInfo> findAll(Pageable pageable);
}
