package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.BookInfo;
import com.imooc.sell.repository.BookInfoRepository;
import com.imooc.sell.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * @PackageName com.imooc.sell.service.impl
 * @ClassName BookInfoServiceImpl
 * @Desc 小说书籍信息service接口实现
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/20 11:06
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    /**
     * 新增小说书籍信息
     *
     * @param bookInfo
     * @return
     */
    @Override
    public BookInfo save(BookInfo bookInfo) {
        return bookInfoRepository.save(bookInfo);
    }

    /**
     * 删除小说书籍信息
     *
     * @param bookInfo
     */
    @Override
    public void delete(BookInfo bookInfo) {
        bookInfoRepository.delete(bookInfo);
    }

    /**
     * 修改小说书籍信息
     *
     * @param bookInfo
     */
    @Override
    public BookInfo update(BookInfo bookInfo) {
        return bookInfoRepository.saveAndFlush(bookInfo);
    }

    /**
     * 根据小说的id查找小说信息
     *
     * @param bookId
     * @return
     */
    @Override
    public BookInfo findByBookId(String bookId) {
        return bookInfoRepository.findOne(bookId);
    }

    /**
     * 查找小说书籍列表
     *
     * @return
     */
    @Override
    public List<BookInfo> findAll() {
        return bookInfoRepository.findAll();
    }

    /**
     * 分页查找小说书籍列表
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<BookInfo> findAll(Pageable pageable) {
        return bookInfoRepository.findAll(pageable);
    }
}
