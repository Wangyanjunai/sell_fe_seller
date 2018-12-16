package com.imooc.sell.repository;

import com.imooc.sell.SellApplication;
import com.imooc.sell.dataobject.BookCategory;
import com.imooc.sell.enums.BookStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class BookCategoryRepositoryTest {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Test
    public void save() {
        BookCategory bookCategory = BookCategory.builder().build();
        bookCategory.setCategoryName("Economics");
        bookCategory.setCategoryType(104);
        bookCategory.setIsDeleted(BookStatusEnum.NOT_DELETE.getCode());
        BookCategory result = bookCategoryRepository.save(bookCategory);
        assertNotNull(result);
    }
}