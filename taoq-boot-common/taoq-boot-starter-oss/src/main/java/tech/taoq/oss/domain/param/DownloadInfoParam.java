package tech.taoq.oss.domain.param;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DownloadInfoParam
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DownloadInfoParam {

    /**
     * 文件名称
     */
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
