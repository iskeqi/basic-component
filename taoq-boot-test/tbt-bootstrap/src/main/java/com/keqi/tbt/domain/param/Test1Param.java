package com.keqi.tbt.domain.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.web.validator.BaseEnumValidate;
import tech.taoq.web.validator.annotation.EnumValidate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Test1Param {

    private String name;

    @EnumValidate(message = "type 参数不合法", value = Type.class)
    private String type;

    @EnumValidate(message = "type2 参数不合法", value = Type.class, useBaseEnumValidate = true)
    private String type2;

    public enum Type implements BaseEnumValidate {
        SCOPE,

        STATION,

        TOOL_CODE;

        @Override
        public boolean existCode(String code) {
            return false;
        }
    }
}
