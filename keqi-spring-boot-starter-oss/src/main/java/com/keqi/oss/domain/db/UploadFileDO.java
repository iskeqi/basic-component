package com.keqi.oss.domain.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.keqi.mp.pojo.BaseDO;

/**
 * 文件表
 */
@TableName(value = "sys_upload_file")
public class UploadFileDO extends BaseDO {

	/**
	 * 文件名称
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 文件存储路径（相对路径）
	 */
	@TableField(value = "path")
	private String path;

	/**
	 * 文件类型
	 */
	@TableField(value = "type")
	private String type;

	/**
	 * 文件大小（单位：字节）
	 */
	@TableField(value = "size")
	private Long size;

	/**
	 * 存储类型[1 本地文件系统，2 MINIO]
	 */
	@TableField(value = "storage_type")
	private Integer storageType;

	/**
	 * 逻辑删除字段（0 未删除，1 已删除）
	 */
	@TableField(value = "is_deleted")
	private Integer deleted;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getStorageType() {
		return storageType;
	}

	public void setStorageType(Integer storageType) {
		this.storageType = storageType;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}