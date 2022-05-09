create database if not exists `taoq-boot` default charset utf8mb4 collate utf8mb4_0900_ai_ci;

create table `sys_operate_log` (
  `id` bigint unsigned not null auto_increment comment '主键id',
  `log_id` varchar(256) not null comment '生成的uuid',
  `biz_id` varchar(256) not null comment '业务唯一id',
  `biz_type` varchar(256) not null comment '业务类型',
  `exception` varchar(256) not null comment '函数执行失败时写入的异常信息',
  `operate_Date` datetime default null comment '操作时间',
  `success` varchar(256) not null comment '函数是否执行成功',
  `msg` varchar(256) not null comment '操作日志主体信息',
  `tag` varchar(256) not null comment '用户自定义标签',
  `return_str` varchar(256) not null comment '方法执行成功之后的返回值',
  `execution_time` varchar(256) not null comment '方法执行耗时[毫秒]',
  `extra` varchar(256) not null comment '额外信息',
  `operator_id` varchar(256) not null comment '操作人id',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '修改时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='操作日志表';