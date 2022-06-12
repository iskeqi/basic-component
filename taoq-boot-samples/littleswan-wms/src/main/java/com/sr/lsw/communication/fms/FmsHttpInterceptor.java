package com.sr.lsw.communication.fms;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.taoq.system.service.ConfigService;

import java.util.Map;

/**
 * FmsHttpInterceptor
 *
 * @author keqi
 */
@Slf4j
@Component
public class FmsHttpInterceptor implements Interceptor<Map<String, Object>> {

    public static final String FMS_URL = "FMS_URL";
    public static final String TOKEN = "TOKEN";
    public static final String TOKEN_VALUE = "YWRtaW4sMTk3MDMyMzY2Njg2OSxjM2UyNzNkZmZiYTI0ZDBiNDk2YjNiOGJmNTI3NzE4OA==";

    @Autowired
    private ConfigService configService;

    @Override
    public boolean beforeExecute(ForestRequest request) {
        // 替换 URL
        String fmsUrl = configService.getByConfigKey(FMS_URL);
        if (fmsUrl == null) {
            log.info("configKey FMS_URL value is null");
        }
        String suffix = request.getUrl().substring(request.getUrl().indexOf(FMS_URL) + FMS_URL.length());
        request.setUrl(fmsUrl + suffix);

        // 新增 token 请求头
        String token = configService.getByConfigKey(TOKEN);
        request.addHeader("token", token == null ? TOKEN_VALUE : token);
        return true;
    }
}
