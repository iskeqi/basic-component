create table if not exists `sys_mail`
(
    `id`          bigint unsigned not null,
    `identifier`  varchar(255)  default null comment '邮件服务商标识',
    `host`        varchar(255)  default null,
    `port`        int           default null,
    `username`    varchar(255)  default null,
    `password`    varchar(255)  default null,
    `properties`  varchar(1024) default null comment '额外属性[以json字符串方式填写]',
    `priority`    int           default '0' comment '优先级[数字越大,优先级越高]',
    `is_connect`  tinyint       default '0' comment '是否可连接[0:不可连接 1:连接]',
    `is_disable`  tinyint       default '0' comment '是否禁用[0:未禁用 1:已禁用]',
    `create_time` datetime      default null,
    `update_time` datetime      default null,
    primary key (`id`),
    unique key `uk_identifier` (`identifier`)
) comment ='邮件服务表';