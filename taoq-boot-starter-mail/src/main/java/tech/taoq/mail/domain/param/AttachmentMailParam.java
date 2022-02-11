package tech.taoq.mail.domain.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.core.io.InputStreamSource;

import javax.activation.DataSource;
import java.io.File;
import java.util.List;

/**
 * AttachmentMailParam
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AttachmentMailParam extends SimpleMailParam {

    /**
     * 附件列表
     */
    private List<Attachment> attachmentList;

    @Data
    @Accessors(chain = true)
    public static class Attachment {

        /**
         * 附件名称
         */
        private String attachmentFilename;

        private File file;

        private DataSource dataSource;

        private InputStreamSource inputStreamSource;

        private String contentType;
    }
}
