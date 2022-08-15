create database if not exists `taoq-boot` default charset utf8mb4 collate utf8mb4_0900_ai_ci;

create table `sys_package_record` (
  `id` bigint unsigned not null auto_increment,
  `name` varchar(32) default null comment '安装包文件名称',
  `type` varchar(32) default null comment '应用类型',
  `size` int default null comment '文件大小[单位:字节]',
  `file_id` bigint unsigned not null comment '安装包文件表id',
  `note` varchar(1024) default null comment '备注',
  `tag`   tinyint unsigned default '0' comment '当前使用版本[false:否 true:是]',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='安装包记录表';

create table `sys_package_file` (
  `id` bigint unsigned not null auto_increment,
  `package_bytes` longblob comment '安装包文件字节',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci comment='安装包文件表';