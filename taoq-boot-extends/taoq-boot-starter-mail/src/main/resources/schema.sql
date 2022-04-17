create database if not exists `taoq-boot` default charset utf8mb4 collate utf8mb4_0900_ai_ci;

create table if not exists `sys_mail`
(
    `id`          bigint unsigned not null,
    `identifier`  varchar(32)  default null comment '邮件服务商标识',
    `host`        varchar(128) default null comment '邮件服务域名或ip',
    `port`        int          default null comment '端口号',
    `username`    varchar(128) default null comment '用户名',
    `password`    varchar(128) default null comment '密码',
    `properties`  json         default null comment '额外属性[以json字符串方式填写]',
    `priority`    int          default '0' comment '优先级[数字越大,优先级越高]',
    `is_connect`  tinyint      default '0' comment '是否可连接[0:不可连接 1:可连接]',
    `is_disable`  tinyint      default '0' comment '是否禁用[false:未禁用 true:已禁用]',
    `create_time` datetime     default null comment '创建时间',
    `update_time` datetime     default null comment '修改时间',
    primary key (`id`),
    unique key `uk_identifier` (`identifier`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='邮件服务表';