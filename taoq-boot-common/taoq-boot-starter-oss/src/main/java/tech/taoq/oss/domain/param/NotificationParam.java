package tech.taoq.oss.domain.param;

/**
 * 文件表
 */
public class NotificationParam{

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件存储路径[相对路径]
     */
    private String path;

    /**
     * 文件类型[Content-Type]
     */
    private String type;

    /**
     * 文件大小[单位:字节]
     */
    private Long size;

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
}