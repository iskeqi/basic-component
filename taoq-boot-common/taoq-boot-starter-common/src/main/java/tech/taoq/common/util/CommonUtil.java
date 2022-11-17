package tech.taoq.common.util;

/**
 * CommonUtil
 *
 * @author keqi
 */
public class CommonUtil {

    /**
     * 替换字符串中的 {}
     *
     * @param str  原始字符串
     * @param args 替换字符数组
     * @return 新的字符串
     */
    public static String replacePlaceholder(String str, String... args) {
        if (str == null || "".equals(str)) {
            throw new RuntimeException("被替换的字符串不能为空");
        }

        for (String arg : args) {
            str = str.replaceFirst("\\{}", arg);
        }

        return str;
    }
}
