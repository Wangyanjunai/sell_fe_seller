package com.imooc.sell.controller;

import com.imooc.sell.dataobject.BookInfo;
import com.imooc.sell.service.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 * @PackageName com.imooc.sell.controller
 * @ClassName BuyerBookInfoController
 * @Desc 买家端电子书信息Controller
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/12/11 10:01
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BuyerBookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    @GetMapping(value = "/list")
    public List<BookInfo> home() {
        List<BookInfo> bookInfoList = bookInfoService.findAll();
        log.info("bookInfoList", bookInfoList);
        return bookInfoList;
    }
}
