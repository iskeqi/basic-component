package tech.taoq.common.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * JSON 工具类，使用 jackson-bind 封装
 * <p>
 * // @JsonInclude(JsonInclude.Include.NON_NULL) // 此注解用在实体类上，如果实体类中的属性为 null 时，不会被序列化
 *
 * @author keqi
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper;
    private static final ObjectMapper objectMapper2;

    static {
        objectMapper = new ObjectMapper();
        // 反序列化时，忽略掉名字不匹配的字段
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper2 = new ObjectMapper();
        objectMapper2.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化时忽略掉值为 null 的字段
        objectMapper2.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper2.registerModule(new JavaTimeModule());
    }

    /**
     * 同时支持对象/数组/Map，属性值为 null 时，该属性也会输出（如需忽略指定属性可以使用 @JsonIgnore 注解用于修饰具体属性）
     *
     * @param value value
     * @return r
     */
    public static String writeValueAsString(Object value) {
        String json = null;
        if (value != null) {
            try {
                json = objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return json;
    }

    /**
     * 同时支持对象/数组/Map，属性值为 null 时，会忽略该属性
     *
     * @param value           value
     * @param ignoreNullField 默认为 false
     * @return r
     */
    public static String writeValueAsString(Object value, boolean ignoreNullField) {
        if (ignoreNullField) {
            throw new RuntimeException("must be false");
        }

        String json = null;
        if (value != null) {
            try {
                json = objectMapper2.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return json;
    }

    /**
     * 支持JSON字符串转对象/Map，不支持数组（属性名不同会自动忽略）
     *
     * @param content   content
     * @param valueType valueType
     * @param <T>       t
     * @return r
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        T t = null;
        if (StrUtil.isNotBlank(content)) {
            try {
                t = objectMapper.readValue(content, valueType);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return t;
    }

    /**
     * 支持 JSON 字符串转数组/List<Map>（属性名不同会自动忽略）
     *
     * @param content      content
     * @param valueTypeRef valueTypeRef
     * @param <T>          t
     * @return r
     */
    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        T t = null;
        if (StrUtil.isNotBlank(content)) {
            try {
                t = objectMapper.readValue(content, valueTypeRef);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return t;
    }
}
