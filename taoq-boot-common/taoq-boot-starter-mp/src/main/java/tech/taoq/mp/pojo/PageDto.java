package tech.taoq.mp.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;

/**
 * 分页响应Dto
 *
 * @author keqi
 */
public class PageDto<T> {

	@ApiModelProperty("总页数")
	private long total;

	@ApiModelProperty("数据列表")
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
