package tech.taoq.common.util;

import tech.taoq.common.exception.client.ClientErrorException;
import tech.taoq.common.pojo.AuthBO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 通过 ThreadLocal 存储当前线程可用的信息
 *
 * @author keqi
 */
public class Auth {

    private static final ThreadLocal<Map<String, Object>> AUTH_THREAD_LOCAL = new ThreadLocal<>();

    public static void setAuthBO(AuthBO<?> authBO) {
        Map<String, Object> data = AUTH_THREAD_LOCAL.get();
        if (Objects.isNull(data)) {
            data = new HashMap<>();
            AUTH_THREAD_LOCAL.set(data);
        }
        data.put(authBO.getKey(), authBO);
    }

    public static AuthBO<?> getAuthBO(String key) {
        Map<String, Object> data = AUTH_THREAD_LOCAL.get();
        if (data == null) {
            throw new ClientErrorException("no auth threadlocal data");
        }

        AuthBO<?> obj = (AuthBO<?>) data.get(key);
        if (obj == null) {
            throw new ClientErrorException("no auth threadlocal data for key ->" + key);
        }
        return obj;
    }
}
