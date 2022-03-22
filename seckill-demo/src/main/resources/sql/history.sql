-- 用户表
CREATE TABLE t_user(
                       `id` BIGINT(20) NOT NULL COMMENT '用户ID shoujihaoma',
                       `nickname` VARCHAR(255) not NULL,
                       `pasword`  VARCHAR(32) DEFAULT NULL COMMENT 'MD5二次加密',
                       `slat` VARCHAR(10) DEFAULT NULL,
                       `head` VARCHAR(128) DEFAULT NULL COMMENT '头像',
                       `register_date` datetime DEFAULT NULL COMMENT '注册时间',
                       `last_login_date` datetime DEFAULT NULL COMMENT '最后一次登录时间',
                       `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
                       PRIMARY KEY(`id`)
);

--