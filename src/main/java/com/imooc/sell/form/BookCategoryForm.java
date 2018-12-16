package com.imooc.sell.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.imooc.sell.form
 * @ClassName BookCategoryForm
 * @Desc 书籍类目数据Form
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 19:12
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryForm {
	
    /**
     * categoryId：类目id，主键
     */
    private Integer categoryId;
    
    /**
     * parentCategoryId：父级类目id
     */
    private Integer parentCategoryId;

    /**
     * categoryName：类目名称
     */
    private String categoryName;

    /**
     * categoryType：类目编号
     */
    private Integer categoryType;

    /**
     * isDeleted：类目是否删除，0-否；1-是，“默认：0-否”
     */
    private Integer isDeleted;
}
