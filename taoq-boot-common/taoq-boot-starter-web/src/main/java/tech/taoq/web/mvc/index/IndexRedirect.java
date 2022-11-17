package tech.taoq.web.mvc.index;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 将 / 请求重定向到 /contextPath/index.html
 *
 * @author keqi
 */
@Controller
public class IndexRedirect {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        String location = "/index.html";
        if (StringUtils.hasText(contextPath)) {
            location = contextPath + location;
        }
        response.sendRedirect(location);
    }
}
