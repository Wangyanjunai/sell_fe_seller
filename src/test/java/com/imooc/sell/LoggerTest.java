package com.imooc.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <pre>
 * @PackageName com.imooc.sell
 * @ClassName LoggerTest
 * @Desc 测试日志输出框架slog4j和logback
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/10/10 11:05
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoggerTest {

    @Test
    public void test() {
        log.debug("debug......");
        log.info("info......");
        log.error("error......");
        String name = "imooc", password ="123456";
        log.info("name = {}, password = {}", name, password);
        log.info("name: {}, password: {}", name, password);
    }
}
