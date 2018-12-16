package com.imooc.sell.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * @PackageName com.imooc.sell.dataobject
 * @ClassName UserInfo
 * @Desc 精品小说用户信息
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/27 15:14
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "UserInfo")
@Table(name = "store_user_info")
public class UserInfo implements Serializable {

    /**
	 *@Fields serialVersionUID： 序列号
	 */ 
	private static final long serialVersionUID = -5678613720943438874L;

	/**
     *@Fields uid：用户唯一标识
     */
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false, length = 32)
    private Integer uid;

    /**
     *@Fields id：用户id
     */
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    /**
     *@Fields collectionsId：收藏id
     */
    @Column(name = "collections_id", nullable = true, length = 32)
    private String collectionsId;

    /**
     *@Fields forumId：评论id
     */
    @Column(name = "forum_id", nullable = true, length = 32)
    private String forumId;

    /**
     *@Fields bookId：小说id
     */
    @Column(name = "book_id", nullable = true, length = 32)
    private String bookId;

    /**
     *@Fields openId：用户微信openid
     */
    @Column(name = "open_id", nullable = true, length = 32)
    private String openId;

    /**
     *@Fields nickName：用户昵称
     */
    @Column(name = "nick_name", nullable = true, length = 32)
    private String nickName;

    /**
     *@Fields gender：性别，W-女；M-男；N-未知，默认：“N-未知“
     */
    @Column(name = "gender", nullable = true, length = 1)
    private String gender;

    /**
     *@Fields name：用户名
     */
    @Column(name = "name", nullable = true, length = 32)
    private String name;

    /**
     *@Fields signature：用户签名
     */
    @Column(name = "signature", nullable = true, length = 256)
    private String signature;

    /**
     *@Fields alt：用户个人主页URL
     */
    @Column(name = "alt", nullable = true, length = 256)
    private String alt;

    /**
     *@Fields language：语言，zh_CN-中文（普通话）；en_US-英语（美式英语）；en_EG-英语（英式英语），默认：”zh_CN-中文（普通话）“
     */
    @Column(name = "language", nullable = true, length = 25)
    private String language;

    /**
     *@Fields city：城市
     */
    @Column(name = "city", nullable = true, length = 25)
    private String city;

    /**
     *@Fields province：省份
     */
    @Column(name = "province", nullable = true, length = 25)
    private String province;

    /**
     *@Fields country：国家
     */
    @Column(name = "country", nullable = true, length = 25)
    private String country;

    /**
     *@Fields avatarUrl：微信头像URL
     */
    @Column(name = "avatar_url", nullable = true, length = 255)
    private String avatarUrl;

    /**
     *@Fields ip：用户客户端ip
     */
    @Column(name = "ip", nullable = true, length = 25)
    private String ip;

    /**
     *@Fields unionId：联合id
     */
    @Column(name = "union_id", nullable = true, length = 32)
    private String unionId;

    /**
     *@Fields watermarkTimestamp：水印时间戳
     */
    @Column(name = "watermark_timestamp", nullable = true, length = 64)
    private String watermarkTimestamp;

    /**
     *@Fields watermarkAppId：水印appid
     */
    @Column(name = "watermark_app_id", nullable = true, length = 64)
    private String watermarkAppId;

    /**
     *@Fields createTime：创建时间
     */
    @Column(name = "create_date_time", nullable = false)
    private Date createTime;

    /**
     *@Fields updateTime：更新时间
     */
    @Column(name = "update_date_time", nullable = false)
    private Date updateTime;
}
