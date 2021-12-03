package com.keqi.mail.domain.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;

import javax.activation.DataSource;
import java.io.File;
import java.util.List;

/**
 * InlineMailParam
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class InlineMailParam extends AttachmentMailParam {

    /**
     * 内嵌文件列表
     */
    private List<Inline> inlineList;

    @Data
    @Accessors(chain = true)
    public static class Inline {

        /**
         * 内嵌文件ID
         */
        private String contentId;

        private File file;

        private DataSource dataSource;

        private Resource resource;

        private InputStreamSource inputStreamSource;

        private String contentType;
    }
}
