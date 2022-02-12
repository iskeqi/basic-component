package tech.taoq.tdengine.domain.param;

/**
 * UpdateDbKeepParam
 *
 * @author keqi
 */
public class UpdateDbKeepParam {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据库记录保存天数，取值范围[days,365000]，必须大于或等于 days 参数值
     */
    private Integer keep;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Integer getKeep() {
        return keep;
    }

    public void setKeep(Integer keep) {
        this.keep = keep;
    }
}
