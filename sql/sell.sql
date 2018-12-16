/*
	Navicat MySQL Data Transfer
	
	Source Server         : localhost
	Source Server Version : 50723
	Source Host           : localhost:3306
	Source Database       : sell
	
	Target Server Type    : MYSQL
	Target Server Version : 50723
	File Encoding         : 65001
	
	Date: 2018-09-27 12:45:14
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`
(
  `detail_id`        varchar(32) NOT NULL COMMENT '详情id，主键',
  `order_id`         varchar(32) NOT NULL COMMENT '订单id',
  `product_id`       varchar(32) NOT NULL COMMENT '产品id',
  `product_category` int(11) unsigned NOT NULL COMMENT '类目编号',
  `buyer_openid`     varchar(64) NOT NULL COMMENT '买家微信openid',
  `product_name`     varchar(64) NOT NULL COMMENT '产品名称',
  `product_price`    decimal(8,2) unsigned NOT NULL COMMENT '产品单价',
  `product_quantity` int(11) unsigned NOT NULL COMMENT '购买数量',
  `product_icon`     varchar(512)         DEFAULT NULL COMMENT '产品小图',
  `create_time`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`detail_id`),
  KEY                `key_order_id` (`order_id`) USING HASH,
  KEY                `key_product_id_and_product_category` (`product_id`, `product_category`) USING HASH,
  KEY                `key_buyer_openid` (`buyer_openid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`
(
  `order_id`      varchar(32)  NOT NULL COMMENT '订单id，主键',
  `buyer_name`    varchar(32)  NOT NULL COMMENT '买家名字',
  `buyer_phone`   varchar(15)  NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid`  varchar(64)  NOT NULL COMMENT '买家微信openid',
  `order_amount`  decimal(8, 2) unsigned NOT NULL COMMENT '订单总金额',
  `order_status`  tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态，0-新订单；1-已完结；2-已取消，“默认：0-新订单”',
  `pay_status`    tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单支付状态，0-等待支付；1-支付成功，“默认：0-等待支付”',
  `create_time`   timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
  KEY             `key_buyer_openid` (`buyer_openid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';

-- ----------------------------
-- Records of order_master
-- ----------------------------

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`
(
  `category_id`   int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '类目id，主键',
  `category_name` varchar(64) NOT NULL COMMENT '类目名称',
  `category_type` int(11) unsigned NOT NULL COMMENT '类目编号',
  `is_deleted`    tinyint(3) unsigned DEFAULT '0' COMMENT '是否删除，0-否；1-是，“默认：0-否”',
  `create_time`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`),
  KEY             `key_category_type` (`category_type`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COMMENT='类目信息表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category`
VALUES ('1', '热销榜', '2', '0', '2017-12-07 21:52:18', '2017-12-25 16:11:47');
INSERT INTO `product_category`
VALUES ('3', '女生最爱', '6', '0', '2017-12-08 00:40:41', '2017-12-25 16:11:47');
INSERT INTO `product_category`
VALUES ('17', '少妇最爱', '1', '0', '2017-12-08 23:31:08', '2017-12-25 16:11:48');
INSERT INTO `product_category`
VALUES ('21', '女生专项', '5', '0', '2017-12-11 09:30:44', '2017-12-25 16:11:49');
INSERT INTO `product_category`
VALUES ('27', '师兄最爱', '4', '0', '2017-12-17 12:20:22', '2017-12-25 16:11:50');
INSERT INTO `product_category`
VALUES ('29', '老婆最爱', '3', '0', '2017-12-17 12:20:49', '2017-12-25 16:11:51');
INSERT INTO `product_category`
VALUES ('41', '师兄最不爱', '10', '0', '2017-12-25 16:30:02', '2017-12-25 16:30:02');
INSERT INTO `product_category`
VALUES ('45', '师兄最不喜欢', '11', '0', '2017-12-29 16:55:05', '2017-12-29 16:55:05');
INSERT INTO `product_category`
VALUES ('51', '师兄最不爱', '101', '0', '2017-12-25 16:29:41', '2017-12-25 16:29:41');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`
(
  `product_id`          varchar(32) NOT NULL COMMENT '产品id，主键',
  `product_name`        varchar(64) NOT NULL COMMENT '产品名称',
  `product_price`       decimal(8, 2) unsigned NOT NULL COMMENT '产品单价',
  `product_stock`       int(11) unsigned NOT NULL COMMENT '库存',
  `product_description` varchar(1024)        DEFAULT NULL COMMENT '产品描述',
  `product_icon`        varchar(512)         DEFAULT NULL COMMENT '产品小图',
  `product_status`      tinyint(3) unsigned DEFAULT '0' COMMENT '产品状态，0-在架；1-下架，“默认，0-在架“',
  `category_type`       int(11) unsigned NOT NULL COMMENT '类目编号，外键',
  `create_time`         timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`         timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`, `category_type`),
  KEY                   `key_category_type` (`category_type`) USING HASH,
  KEY                   `key_product_status` (`product_status`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品信息表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info`
VALUES ('32ea66d543494ba8b0536a8f42249baa', '农家小炒肉', '0.01', '200',
        '农家农家小炒肉，里面是新鲜的瘦肉和青辣椒一起炒的，味道非常棒的哈，热销榜榜首，也是一道非常地道，传统的湖南农家菜！！！！',
        'https://www.potato369.com/upload/images/xcr.png', '0', '1', '2017-12-09 07:12:40', '2018-09-25 17:45:53');
INSERT INTO `product_info`
VALUES ('3a80c3cd341c44f9834e41a0c499baf6', '麻婆豆腐', '0.01', '200', '麻婆豆腐，是湖南传统菜，很好吃喔！！！',
        'https://www.potato369.com/upload/images/mpdf.png', '0', '3', '2017-12-16 21:52:18', '2018-09-25 17:45:53');
INSERT INTO `product_info`
VALUES ('3ba9477c13ba4b2294d5adf794d4eb37', '白粥', '0.01', '200', '白粥是广东省传统食俗，别称斋粥、米皇。广东粥中将没有放佐料的粥就称为白粥。',
        'https://www.potato369.com/upload/images/bz.png', '0', '6', '2017-12-17 08:51:52', '2018-09-25 11:53:50');
INSERT INTO `product_info`
VALUES ('61ae83410ed64850bb3d334845d83bde', '农家一碗香', '0.01', '200', '农家一碗香，里面加了鸡蛋和瘦肉，一起炒的，味道非常棒的哈！！！！',
        'https://www.potato369.com/upload/images/ywx.png', '0', '2', '2017-12-19 20:22:40', '2018-09-25 11:53:50');
INSERT INTO `product_info`
VALUES ('baeb1abb756a4fa88a62d24c015d62ee', '芒果冰', '0.01', '200', '冰冰的，很爽，很好喝额！！！！',
        'https://www.potato369.com/upload/images/mgb.png', '0', '5', '2017-12-11 14:21:58', '2018-09-25 11:53:50');
INSERT INTO `product_info`
VALUES ('bc71782347834f79a46cba0c96a03cde', '皮蛋廋肉粥', '0.01', '200', '很好喝的粥',
        'https://www.potato369.com/upload/images/pdz.png', '0', '3', '2017-12-11 10:58:55', '2018-09-25 11:53:50');
INSERT INTO `product_info`
VALUES ('bd34d70c13c244b08907a753ef11a2ad', '腐竹牛腩饭', '0.01', '200', '腐竹牛腩饭饭是一份精美的快餐饭，里面加了腐竹和新鲜的牛腩一起焖煮的一道美味佳肴饭。',
        'https://www.potato369.com/upload/images/fznr.png', '0', '5', '2017-12-19 20:30:38', '2018-09-25 11:53:50');
INSERT INTO `product_info`
VALUES ('c750fbe8758f489abe21c2866e3daa3a', '皮皮虾粥', '0.01', '200',
        '皮皮虾，里面是新鲜的瘦肉和虾肉，小米，糯米一起熬的粥，味道非常棒的哈，热销榜榜首，也是一道非常地道，传统的粤系列粥！！！！',
        'https://www.potato369.com/upload/images/ppxz.png', '0', '4', '2017-12-09 08:51:45', '2018-09-25 11:53:50');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info`
(
  `seller_id`   varchar(32) NOT NULL COMMENT '卖家id，主键',
  `username`    varchar(32) NOT NULL COMMENT '卖家名字',
  `password`    varchar(32) NOT NULL COMMENT '登录密码',
  `openid`      varchar(64) NOT NULL COMMENT '卖家微信openid',
  `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`seller_id`),
  KEY           `key_openid` (`openid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卖家信息表';

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES ('0ed2ba762e364ce790661d86e59b162b', 'admin', 'b814b812ec4b322e19fae7bb78d4d330', 'oSkiNv4fBXYxidv0wU_U0UDHNP4M', '2017-12-17 21:18:22', '2018-01-02 18:46:15');
SET FOREIGN_KEY_CHECKS = 1;
