package com.imooc.sell.controller;

import com.imooc.sell.dataobject.BookInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.BookInfoService;

import com.imooc.sell.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.Map;

/**
 * <pre>
 * @PackageName com.imooc.sell.controller
 * @ClassName BookInfoController
 * @Desc 小说书籍信息Controller
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/20 15:44
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping(value = "/book/info")
public class SellerBookInfoController {

    @Autowired
    private BookInfoService bookInfoService;
    
    @PostMapping(value = "/upload")
    public ModelAndView upload (@RequestParam(name = "file") MultipartFile file, Map<String, Object> map) {
    	if (log.isDebugEnabled()) {
    		log.debug("====================start upload====================");
    	}
    	if (file.isEmpty()) {
    	    log.error("【上传文件】文件为空");
            map.put("msg", ResultEnum.PRODUCT_OFF_SALE_FAIL.getMessage());
            map.put("url", "/sell/book/info/index");
            return new ModelAndView("/common/error", map);
        }
        try {
            EpubReader epubReader = new EpubReader();
            Book book = epubReader.readEpub(file.getInputStream(), "UTF-8");
            Metadata metadata = book.getMetadata();
            String title = metadata.getTitles().toString();
            BookInfo bookInfo = BookInfo.builder().build();
            bookInfo.setBookId(UUIDUtil.gen32UUID());
            bookInfo.setTitle(title);
            bookInfo.setFileName(file.getOriginalFilename().toString());
            bookInfo.setPublisher(metadata.getPublishers().toString());
            map.put("bookInfo", bookInfo);
            log.info("bookInfo================={}", bookInfo);
        } catch (IOException e) {
            log.error("", e);
        }
    	if (log.isDebugEnabled()) {
    		log.debug("====================end upload====================");
    	}
    	return new ModelAndView("book/info/index", map);
    }
    
    @PostMapping(value = "/add")
    public ModelAndView add (BookInfo bookInfo, Map<String, Object> map) {
    	if (log.isDebugEnabled()) {
    		log.debug("====================start add====================");
    	}
    	if (log.isDebugEnabled()) {
    		log.debug("====================end add====================");
    	}
    	return null;
    }

    @GetMapping(value = "/index")
    public ModelAndView index (@RequestParam(name = "bookId", required = false) String bookId, BookInfo bookInfo, Map<String, Object> map) {
        if (bookId != null) {
            bookInfo = bookInfoService.findByBookId(bookId);
        }
        map.put("bookInfo", bookInfo);
        return new ModelAndView("book/info/index", map);
    }

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                             Map<String, Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = new PageRequest(page - 1, size, sort);
        Page<BookInfo> BookInfoPage = bookInfoService.findAll(pageRequest);
        map.put("BookInfoPage", BookInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("book/info/list", map);
    }
}
