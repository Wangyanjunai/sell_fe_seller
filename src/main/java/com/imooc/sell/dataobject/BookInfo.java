package com.imooc.sell.dataobject;

import com.imooc.sell.enums.BookStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * @PackageName com.imooc.sell.dataobject
 * @ClassName BookInfo
 * @Desc 精品小说信息
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 17:37
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BookInfo")
@Table(name  = "store_book_info")
public class BookInfo implements Serializable {

	private static final long serialVersionUID = 4483013371993300300L;

	/**
     *@Fields id：主键id
     */
	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false, length = 11)
    private Integer id;

    /**
     *@Fields fileName：文件名称
     */
    @Column(name = "file_name", nullable = true, length = 256)
    private String fileName;

    /**
     *@Fields cover：封面图片路径
     */
    @Column(name = "cover", nullable = true, length = 256)
    private String cover;

    /**
     *@Fields title：书名
     */
    @Column(name = "title", nullable = true, length = 256)
    private String title;

    /**
     *@Fields author：作者
     */
    @Column(name = "author", nullable = true, length = 128)
    private String author;

    /**
     *@Fields publisher：出版社
     */
    @Column(name = "publisher", nullable = true, length = 128)
    private String publisher;

    /**
     *@Fields bookId：书籍的唯一标识
     */
    @Column(name = "book_id", nullable = true, length = 256)
    private String bookId;

    /**
     *@Fields categoryTypeId：分类id
     */
    @Column(name = "category", nullable = true, length = 3)
    private Integer category;

    /**
     *@Fields categoryText：分类id对应的分类名称
     */
    @Column(name = "category_text", nullable = true, length = 256)
    private String categoryText;

    /**
     *@Fields language：语种，“cn“-中文；“en“-英文，默认“cn”中文
     */
    @Builder.Default
    @Column(name = "language", nullable = true, length = 10)
    private String language = BookStatusEnum.LANG_CN.getMessage();

    /**
     *@Fields rootFile：电子书opf文件路径
     */
    @Column(name = "root_file", nullable = true, length = 256)
    private String rootFile;

    /**
     *@Fields selected：是否被选中（书架中应用到），0-未选中，1-已选中；默认“0”未选中
     */
    @Builder.Default
    @Column(name = "selected", nullable = true, length = 3)
    private Integer selected = BookStatusEnum.NOT_SELECTED.getCode();

    /**
     *@Fields author：是否为私密阅读，0-否，1-是；默认“0”未私阅
     */
    @Builder.Default
    @Column(name = "private", nullable = true, length = 3)
    private Integer privateCode = BookStatusEnum.NOT_PRIVATED.getCode();

    /**
     *@Fields cache：是否被缓存（书架中会应用到，使用IndexedDB数据库缓存电子书），0-未缓存，1-已缓存；默认“0”未缓存
     */
    @Builder.Default
    @Column(name = "cache", nullable = true, length = 3)
    private Integer cache = BookStatusEnum.NOT_CACHE.getCode();

    /**
     *@Fields haveRead：是否已经阅读过，0-未阅读，1-已阅读；默认“0”未阅读
     */
    @Builder.Default
    @Column(name = "have_read", nullable = true, length = 3)
    private Integer haveRead = BookStatusEnum.NO_READ.getCode();

    /**
     *@Fields readers：阅读人数；默认“0 - 阅读人数为零”
     */
    @Builder.Default
    @Column(name = "readers", nullable = true, length = 11)
    private Integer readers = 0;

    /**
     *@Fields createTime：创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     *@Fields updateTime：更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
