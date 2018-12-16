package com.imooc.sell.repository;

import com.imooc.sell.SellApplication;
import com.imooc.sell.dataobject.BookInfo;
import com.imooc.sell.utils.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

//@Rollback
//@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class BookInfoRepositoryTest {

    @Autowired
    private BookInfoRepository repository;

    private static final String BOOKID = "24a8fdced2754c4d99493df2d5917537";

    @Test
    public void save () {
        BookInfo bookInfo = BookInfo.builder().build();
        bookInfo.setFileName("2017_Book_HormonesMetabolismAndTheBenefi");
        bookInfo.setCover("http://47.99.166.157/book/res/img/Biomedicine/978-3-319-72790-5_CoverFigure.jpg");
        bookInfo.setTitle("Hormones, Metabolism and the Benefits of Exercise");
        bookInfo.setAuthor("Bruce Spiegelman");
        bookInfo.setPublisher("Springer International Publishing");
        bookInfo.setBookId(UUIDUtil.gen32UUID());
        bookInfo.setCategory(201);
        bookInfo.setCategoryText("玄幻");
        BookInfo bookInfoResult = repository.save(bookInfo);
        assertNotNull(bookInfoResult);
    }

    @Test
    public void delete () {
        BookInfo bookInfo = repository.findOne(BOOKID);
        repository.delete(bookInfo);
        BookInfo bookInfoResult = repository.findOne(BOOKID);
        assertTrue("bookInfo信息已经被删除", bookInfoResult == null);
    }

    @Test
    public void update () {}

    @Test
    public void findOne() {
        BookInfo bookInfo = repository.findOne(BOOKID);
        assertTrue("查找bookInfo信息不为空", bookInfo != null);
    }

    @Test
    public void findAll () {}

    @Test
    public void findAllByPage () {}
}