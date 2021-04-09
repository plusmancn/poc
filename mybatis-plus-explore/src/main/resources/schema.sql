SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
     `name` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名字',
     `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `content` varchar(50) NOT NULL DEFAULT '' COMMENT '文章内容',
    `camel_style` varchar(255) NOT NULL DEFAULT '' COMMENT '驼峰字段',
    `json_field` json NOT NULL COMMENT 'json 字段',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Mybatis Blog Test';

SET FOREIGN_KEY_CHECKS = 1;

