package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * <pre>
 * @PackageName com.imooc.sell.VO
 * @ClassName BookInfoVO
 * @Desc 书籍信息数据
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 17:15
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
public class BookInfoVO {

    /**
     * id：此书在本书城的编号
     */
    @JsonProperty(value = "id")
    private Integer id;

    /**
     * fileName：书名
     */
    @JsonProperty(value = "fileName")
    private String fileName;

    /**
     * cover：书封面图片地址
     */
    @JsonProperty(value = "cover")
    private String cover;

    /**
     * title：标题
     */
    @JsonProperty(value = "title")
    private String title;

    /**
     * author：作者
     */
    @JsonProperty(value = "author")
    private String author;

    /**
     * publisher：出版社
     */
    @JsonProperty(value = "publisher")
    private String publisher;

    /**
     * bookId：书编号唯一
     */
    @JsonProperty(value = "bookId")
    private String bookId;

    /**
     * categoryTypeId：分类id
     */
    @JsonProperty(value = "category")
    private Integer categoryTypeId;

    /**
     * categoryText：分类名称
     */
    @JsonProperty(value = "categoryText")
    private String categoryText;

    /**
     * language：语言
     */
    @JsonProperty(value = "language")
    private String language;

    /**
     * selected：是否收藏
     */
    @JsonProperty(value = "selected")
    private Boolean selected;

    /**
     * author：是否私阅
     */
    @JsonProperty(value = "private")
    private Boolean privated;

    /**
     * cache：离线缓存
     */
    @JsonProperty(value = "cache")
    private Boolean cache;

    /**
     * haveRead：已经阅读数
     */
    @JsonProperty(value = "haveRead")
    private Integer haveRead;
}
