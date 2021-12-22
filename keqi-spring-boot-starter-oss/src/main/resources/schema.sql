-- ----------------------------
-- Table structure for sys_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_file`;
CREATE TABLE `sys_upload_file`
(
    `id`          bigint(0) UNSIGNED                                             NOT NULL AUTO_INCREMENT,
    `name`        varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '文件名称',
    `path`        varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储路径[相对路径]',
    `type`        varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '文件类型',
    `size`        bigint(0) UNSIGNED                                             NULL DEFAULT NULL COMMENT '文件大小[单位：字节]',
    `is_deleted`  tinyint(0)                                                     NULL DEFAULT 0 COMMENT '是否删除[0 未删除，1 已删除]',
    `create_time` datetime(0)                                                    NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                    NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_name` (`name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '文件表'
  ROW_FORMAT = Dynamic;