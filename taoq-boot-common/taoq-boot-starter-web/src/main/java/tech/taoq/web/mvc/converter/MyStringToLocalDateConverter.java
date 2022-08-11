package tech.taoq.web.mvc.converter;

import org.springframework.core.convert.converter.Converter;
import tech.taoq.common.util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * String -> LocalDate 类型转换器
 *
 * @author keqi
 */
public final class MyStringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        if (source.isEmpty() || "\"\"".equals(source)) {
            return null;
        }
        return LocalDate.parse(source, DateTimeFormatter.ofPattern(DateUtil.LOCAL_DATE));
    }
}
