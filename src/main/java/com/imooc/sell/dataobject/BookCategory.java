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
 * @ClassName BookCategory
 * @Desc 精品小说分类信息
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
@Entity(name = "BookCategory")
@Table(name  = "store_book_category")
public class BookCategory implements Serializable {

	private static final long serialVersionUID = -3462676633500918996L;

	/**
     *@Fields categoryId：类目id，主键
     */
    @Id
    @GeneratedValue
    @Column(name = "book_category_id", nullable = false, length = 11)
    private Integer categoryId;
    
	/**
     *@Fields parentCategoryId：父级类目id
     */
    @Column(name = "book_parent_category_id", nullable = true, length = 11)
    private Integer parentCategoryId;

    /**
     *@Fields categoryName：类目名称
     */
    @Column(name = "book_category_name", nullable = true, length = 64)
    private String categoryName;

    /**
     *@Fields categoryType：类目编号
     */
    @Column(name = "book_category_type", nullable = true, length = 11)
    private Integer categoryType;

    /**
     *@Fields isDeleted：类目是否删除，0-否；1-是，“默认：0-否”
     */
    @Builder.Default
    @Column(name = "book_is_deleted", nullable = true, length = 1)
    private Integer isDeleted = BookStatusEnum.NOT_DELETE.getCode();
    /**
     *@Fields createTime：创建时间
     */
    @Column(name = "book_create_time", nullable = false)
    private Date createTime;

    /**
     *@Fields updateTime：更新时间
     */
    @Column(name = "book_update_time", nullable = false)
    private Date updateTime;
}
