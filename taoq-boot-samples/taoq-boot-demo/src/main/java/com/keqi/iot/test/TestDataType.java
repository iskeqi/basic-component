package com.keqi.iot.test;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author keqi
 */
@Data
@Accessors(chain = true)
@TableName(value = "test_data_type", autoResultMap = true)
public class TestDataType {

    private String id;
    private String v1;
    private Integer v2;

    // 使用字符串还是 BigDecimal 类呢？
    // 建议使用 BigDecimal 类，会自动排除掉后面的0
    // 使用字符串，其实挺好的
//    private String v3;
    //
    private BigDecimal v3;

    private LocalDate v4;
    private LocalDateTime v5;

    private String v6;
    private String v7;
    private String v8;

    // 使用 String 是毫无问题的，唯一要求字符串内部包含转义字符，接收数据和返回数据都没有问题
    // 使用 Object 也是毫无问题的，唯一要求字符串内部包含转义字符，接收数据和返回数据都没有问题

    // 对于json类型，应该使用字符串还是什么类型呢？
    // 直接使用 Object 类型，搭配 MyBatisPlus 提供的 JacksonTypeHandler类型转换器使用
    @TableField(typeHandler = JacksonTypeHandler.class)
//    private String v9;
    private Object v9;
}
