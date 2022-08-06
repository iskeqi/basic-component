package tech.taoq.mp.pojo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页参数 Param 类
 *
 * @author keqi
 */
public class PageParam<T> {

    @ApiModelProperty("当前页数")
    protected long current = 1;

    @ApiModelProperty("每页大小")
    protected long size = 10;

    public PageParam() {
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 将 PageParam 类转换成 Page 类
     *
     * @return Page 对象
     */
    public Page<T> toPage() {
        return new Page<>(this.current, this.size);
    }
}
