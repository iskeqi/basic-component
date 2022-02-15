drop table if exists sys_upload_file;
create table sys_upload_file
(
    id           bigint unsigned not null auto_increment comment '',
    name         varchar(512) comment '文件名称',
    path         text comment '文件存储路径[相对路径]',
    type         varchar(128) comment '文件类型',
    size         int comment '文件大小[单位:字节]',
    storage_type tinyint unsigned comment '存储类型[1:本地文件系统 2:minio]',
    is_deleted   tinyint unsigned default 0 comment '是否删除[0:未删除 1:已删除]',
    create_time  datetime comment '创建时间',
    update_time  datetime comment '修改时间',
    primary key (id)
) comment = '文件表';