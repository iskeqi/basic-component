create database if not exists `taoq-boot` default charset utf8mb4 collate utf8mb4_0900_ai_ci;

create table if not exists `sys_department` (
  `id` bigint unsigned not null auto_increment comment '主键id',
  `name` varchar(32) default null comment '部门名称',
  `code` varchar(32) default null comment '部门编码',
  `parent_id` bigint unsigned default null comment '上级部门id',
  `head_id` bigint unsigned default null comment '负责人id',
  `order_num` int default null comment '排序字段',
  `is_disable` tinyint default '0' comment '是否禁用[false:未禁用 true:已禁用]',
  `create_time` datetime default null comment '创建时间',
  `update_time` datetime default null comment '修改时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='部门表';

create table if not exists `sys_job` (
  `id` bigint unsigned not null auto_increment comment '主键id',
  `name` varchar(32) default null comment '岗位名称',
  `code` varchar(32) default null comment '岗位编码',
  `order_num` int default null comment '排序字段',
  `is_disable` tinyint default '0' comment '是否禁用[false:未禁用 true:已禁用]',
  `create_time` datetime default null comment '创建时间',
  `update_time` datetime default null comment '修改时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='岗位表';

create table if not exists `sys_account` (
  `id` bigint unsigned not null auto_increment comment '主键id',
  `account` varchar(32) default null comment '用户名',
  `password` varchar(512) default null comment '密码',
  `name` varchar(32) default null comment '用户昵称',
  `phone` varchar(32) default null comment '手机号码',
  `email` varchar(64) default null comment '邮箱',
  `gender` char(1) default null comment '性别[M:男性 F:女性]',
  `note` varchar(32) default null comment '备注',
  `is_disable` tinyint default '0' comment '是否禁用[false:未禁用 true:已禁用]',
  `create_time` datetime default null comment '创建时间',
  `update_time` datetime default null comment '修改时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='用户表';

create table if not exists `sys_account_department` (
  `account_id` bigint unsigned not null comment '用户id',
  `department_id` bigint unsigned not null comment '部门id',
  primary key (`account_id`,`department_id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='用户-部门关联表';

create table if not exists `sys_account_job` (
  `account_id` bigint unsigned not null comment '用户id',
  `job_id` bigint unsigned not null comment '岗位id',
  primary key (`account_id`,`job_id`) using btree
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='用户-岗位关联表';

create table if not exists `sys_application` (
  `id` bigint unsigned not null auto_increment comment '主键id',
  `name` varchar(32) default null comment '应用名称',
  `code` varchar(32) default null comment '应用编码',
  `order_num` int default null comment '排序字段',
  `type` char(1)  default 'Z' comment '角色类型[N:内置 Z:自定义]',
  `url` varchar(512) default null comment '应用访问地址',
  `is_disable` tinyint default '0' comment '是否禁用[false:未禁用 true:已禁用]',
  `create_time` datetime default null comment '创建时间',
  `update_time` datetime default null comment '修改时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='应用表';

create table `sys_login_log` (
  `id` bigint unsigned not null auto_increment comment '主键id',
  `account_name` varchar(32) default null comment '用户名称',
  `account_id` varchar(32) default null comment '用户id',
  `login_ip` varchar(32) default null comment '登录ip',
  `type` char(3) default null comment '日志类型[IN:登录 OUT:退出]',
  `user_agent` varchar(512) default null comment 'userAgent',
  `is_login_error` tinyint default '0' comment '是否登录失败[false:登录成功 true:登录失败]',
  `create_time` datetime default null comment '创建时间',
  `update_time` datetime default null comment '修改时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='登录日志表';