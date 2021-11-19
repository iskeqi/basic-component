package com.keqi.web.validator;

/**
 * 基础枚举类
 *
 * @author keqi
 */
public interface BaseEnumValidate {

    /**
     * 验证指定注解是否存在指定 code
     *
     * @param code code
     * @return r
     */
    boolean existCode(String code);
}
