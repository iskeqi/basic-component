package tech.taoq.oss.domain.param;

import org.springframework.web.multipart.MultipartFile;

/**
 * UploadParam
 *
 * @author keqi
 */
public class UploadParam {

    /**
     * 上传文件名称[包含路径]
     */
    private String fileName;

    /**
     * 文件
     */
    private MultipartFile uploadFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }
}
