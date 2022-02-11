CREATE TABLE IF NOT EXISTS `sys_mail`
(
    `id`          bigint unsigned NOT NULL,
    `identifier`  varchar(255)                                                   DEFAULT NULL COMMENT '邮件服务商标识',
    `host`        varchar(255)                                                   DEFAULT NULL,
    `port`        int                                                            DEFAULT NULL,
    `username`    varchar(255)                                                   DEFAULT NULL,
    `password`    varchar(255)                                                   DEFAULT NULL,
    `properties`  varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '额外属性(以JSON字符串方式填写)',
    `priority`    int                                                            DEFAULT '0' COMMENT '优先级(数组越大，优先级越高)',
    `is_connect`  tinyint                                                        DEFAULT '0' COMMENT '是否可连接[0 不可连接，1 连接]',
    `is_disable`  tinyint                                                        DEFAULT '0' COMMENT '是否禁用[0 未禁用，1 已禁用]',
    `create_time` datetime                                                       DEFAULT NULL,
    `update_time` datetime                                                       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_identifier` (`identifier`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='邮件服务表';