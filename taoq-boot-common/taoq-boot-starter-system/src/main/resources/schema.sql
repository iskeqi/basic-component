drop table if exists sys_config;
create table `sys_config`
(
    `id`           bigint unsigned not null comment '主键id',
    `config_key`   varchar(32)      default null comment '配置项key',
    `config_value` varchar(32)      default null comment '配置项value',
    `note`         varchar(128)     default null comment '配置项描述信息',
    `extra`        varchar(128)     default null comment '扩展字段',
    `is_disable`   tinyint unsigned default '0' comment '是否禁用[0:未禁用 1:已禁用]',
    `create_time`  datetime         default null comment '创建时间',
    `update_time`  datetime         default null comment '修改时间',
    primary key (`id`),
    unique key `uk_config_key` (`config_key`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_0900_ai_ci comment ='系统配置表';

drop table if exists sys_dict_item;
create table sys_dict_item
(
    id          bigint unsigned not null comment '主键id',
    type_code   varchar(32) comment '字典类型编码',
    type_name   varchar(32) comment '字典类型名称',
    item_code   varchar(32) comment '字典项编码',
    item_name   varchar(32) comment '字典项值',
    order_num   int comment '排序字段',
    is_disable  tinyint unsigned default 0 comment '是否禁用[0:未禁用 1:已禁用]',
    create_time datetime comment '创建时间',
    update_time datetime comment '修改时间',
    primary key (id)
) comment = '字典表';
create unique index uk_type_item on sys_dict_item (type_code, item_code);