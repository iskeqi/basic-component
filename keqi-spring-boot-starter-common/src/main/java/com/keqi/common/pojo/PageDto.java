package com.keqi.common.pojo;

import java.util.Collections;
import java.util.List;

/**
 * 分页响应VO（命名和 MyBatisPlus 保持一致）
 *
 * @author keqi
 */
public class PageDto<T> {

	private long total;

	private List<T> records = Collections.emptyList();

	public PageDto(long total, List<T> list) {
		this.total = total;
		if (list != null && list.size() > 0) {
			this.records = list;
		}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
}
