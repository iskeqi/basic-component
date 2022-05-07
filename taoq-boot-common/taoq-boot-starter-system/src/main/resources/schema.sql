create database if not exists `taoq-boot` default charset utf8mb4 collate utf8mb4_0900_ai_ci;

create table if not exists `sys_config`
(
    `id`           bigint unsigned not null auto_increment comment '主键id',
    `config_key`   varchar(32)  default null comment '配置项key',
    `config_value` varchar(32)  default null comment '配置项value',
    `note`         varchar(128) default null comment '配置项描述信息',
    `extra`        varchar(128) default null comment '扩展字段',
    `is_disable`   tinyint unsigned default '0' comment '是否禁用[false:未禁用 true:已禁用]',
    `create_time`  datetime     default null comment '创建时间',
    `update_time`  datetime     default null comment '修改时间',
    primary key (`id`),
    unique key `uk_config_key` (`config_key`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='配置表';

create table if not exists `sys_dict_type`
(
    `id`          bigint unsigned not null auto_increment comment '主键id',
    `name`        varchar(32)  default '' comment '字典名称',
    `type`        varchar(32)  default '' comment '字典类型',
    `note`        varchar(512) default null comment '备注',
    `is_internal`  tinyint unsigned default '0' comment '是否系统内置[false:否 true:是]',
    `create_time` datetime     default null comment '创建时间',
    `update_time` datetime     default null comment '更新时间',
    primary key (`id`) using btree,
    unique key `dict_type` (`type`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='字典类型表';

create table if not exists `sys_dict_item`
(
    `id`          bigint unsigned not null auto_increment comment '主键id',
    `item_code`   varchar(32)  default null comment '字典项编码',
    `item_name`   varchar(32)  default '' comment '字典项名称',
    `item_value`  varchar(32)  default '' comment '字典项值',
    `dict_type_id` bigint unsigned not null comment '字典类型id',
    `note`        varchar(512) default null comment '备注',
    `order_num`   int          default '0' comment '字典排序',
    `is_internal`  tinyint unsigned default '0' comment '是否系统内置[false:否 true:是]',
    `create_time` datetime     default null comment '创建时间',
    `update_time` datetime     default null comment '更新时间',
    primary key (`id`) using btree,
    unique key `uk_dict_type_item_code` (`dict_type_id`,`item_code`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='字典数据表';