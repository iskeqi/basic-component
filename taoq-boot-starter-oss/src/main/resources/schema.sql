-- ----------------------------
-- Table structure for sys_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_file`;
CREATE TABLE `sys_upload_file`
(
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '文件名称',
    `path`         varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件存储路径[相对路径]',
    `type`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '文件类型',
    `size`         bigint unsigned                                                DEFAULT NULL COMMENT '文件大小[单位：字节]',
    `is_deleted`   tinyint                                                        DEFAULT '0' COMMENT '是否删除[0 未删除，1 已删除]',
    `create_time`  datetime                                                       DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime                                                       DEFAULT NULL COMMENT '修改时间',
    `storage_type` tinyint unsigned                                               DEFAULT NULL COMMENT '存储类型[1 本地文件系统，2 MINIO]',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='文件表';