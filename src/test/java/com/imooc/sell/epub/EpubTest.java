/**
 * <pre>
 * Project Name: sell
 * File Name: EpubTest.java 
 * Package Name: com.imooc.sell.epub
 * Create Date: 2018年11月15日 下午7:51:34
 * Copyright (c) 2018, 版权所有 (C) 2016-2036 土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 * </pre>
 */
package com.imooc.sell.epub;
/**
 * <pre>
 * @PackageName com.imooc.sell.epub
 * @ClassName EpubTest
 * @Desc 描述此类实现的功能作用
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018年11月15日 下午7:51:34
 * @CreateBy Eclipse IDEA Neon.3 Release(4.6.3)
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技（深圳）有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */

import nl.siegmann.epublib.domain.Book;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;


import lombok.extern.slf4j.Slf4j;
import nl.siegmann.epublib.epub.EpubReader;

@Slf4j
public class EpubTest {

	public EpubTest() {
	}
	
	@Test
	public void parseEpub() {
		EpubReader reader = new EpubReader();
		try {
//			URL url = new URL("http://www.potato369.com:8000/epub/男版/玄幻/大主宰.epub");
//			URLConnection urlConnection = url.openConnection();
//			urlConnection.setDoInput(true);
//			InputStream inputStream = urlConnection.getInputStream();
			Book book = reader.readEpub(new FileInputStream("D:\\Users\\wangyanjun\\workspace\\sell\\src\\test\\java\\com\\imooc\\sell\\epub\\都市特种兵.epub"));
			log.info("size={}", book.getMetadata().getContributors());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

