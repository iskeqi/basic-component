create database if not exists `taoq-boot` default charset utf8mb4 collate utf8mb4_0900_ai_ci;

create table `sys_config` (
  `id` int not null comment '主键id[自行填写,取值范围为101-999]',
  `category_name` varchar(32) default null comment '分类名称',
  `config_key` varchar(32) default null comment '配置key',
  `display_name` text comment '配置名称',
  `display_description` text comment '配置描述',
  `value_type` varchar(32) default null comment '配置值类型[INPUT:文本 NUMBER:数值 BOOL:开关 DATE:日期 DATETIME:日期时间 TIME:时间 SDICT:单字典 MDICT:多字典 HTTP:接口]',
  `config_value` varchar(512) default null comment '配置值',
  `default_value` varchar(512) default null comment '配置默认值',
  `value_unit` varchar(32) default null comment '配置值单位',
  `value_range` varchar(521) default null comment '配置值取值范围',
  `is_internal` tinyint unsigned default '0' comment '是否系统内置[false:否 true:是]',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '修改时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='系统配置表';

create table if not exists `sys_dict_type`
(
    `id`          bigint unsigned not null auto_increment comment '主键id',
    `name`        varchar(32)  default '' comment '字典名称',
    `type`        varchar(32)  default '' comment '字典类型',
    `note`        varchar(512) default null comment '备注',
    `is_internal`  tinyint unsigned default '0' comment '是否系统内置[false:否 true:是]',
    `create_time` datetime     default current_timestamp comment '创建时间',
    `update_time` datetime     default current_timestamp on update current_timestamp comment '更新时间',
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
    `create_time` datetime     default current_timestamp comment '创建时间',
    `update_time` datetime     default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree,
    unique key `uk_dict_type_item_code` (`dict_type_id`,`item_code`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='字典数据表';

create table if not exists `sys_result_code`
(
    `id`          bigint unsigned not null auto_increment comment '主键id',
    `code`   varchar(32)  default null comment '状态码',
    `code_name`   varchar(1024)  default '' comment '状态码描述',
    `create_time` datetime     default current_timestamp comment '创建时间',
    `update_time` datetime     default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree,
    unique key `uk_code` (`code`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='响应状态码表';