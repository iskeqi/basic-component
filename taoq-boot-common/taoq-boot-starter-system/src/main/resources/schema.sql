DROP TABLE IF EXISTS sys_config;
CREATE TABLE sys_config
(
    id           bigint unsigned NOT NULL COMMENT '主键id',
    config_key   varchar(32) COMMENT '配置项key',
    config_value varchar(32) COMMENT '配置项value',
    note         varchar(128) COMMENT '配置项描述信息',
    extra        varchar(128) COMMENT '扩展字段',
    is_disable   tinyint unsigned DEFAULT 0 COMMENT '是否禁用[0:未禁用 1:已禁用]',
    create_time  datetime COMMENT '创建时间',
    update_time  datetime COMMENT '修改时间',
    PRIMARY KEY (id)
) COMMENT = '系统配置表';

drop table if exists sys_dict_item;
create table sys_dict_item
(
    type_code  varchar(32) not null comment '字典类型编码',
    type_name  varchar(32) comment '字典类型名称',
    item_code  varchar(32) not null comment '字典项编码',
    item_name  varchar(32) comment '字典项值',
    order_num  int comment '排序字段',
    is_disable tinyint unsigned default 0 comment '是否禁用[0:未禁用 1:已禁用]',
    primary key (type_code, item_code)
) comment = '字典表';