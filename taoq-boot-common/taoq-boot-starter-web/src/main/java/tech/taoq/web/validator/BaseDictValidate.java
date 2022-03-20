package tech.taoq.web.validator;

/**
 * 用于字典验证注解
 *
 * @author keqi
 */
public interface BaseDictValidate {

    /**
     * 验证是否存在指定 typeCode 下的 itemCode
     *
     * @param typeCode typeCode
     * @param itemCode itemCode
     * @return r
     */
    boolean existItemCode(String typeCode, String itemCode);
}
