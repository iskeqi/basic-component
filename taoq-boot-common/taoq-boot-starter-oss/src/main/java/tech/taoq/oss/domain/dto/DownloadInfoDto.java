package tech.taoq.oss.domain.dto;

/**
 * DownloadInfoDto
 *
 * @author keqi
 */
public class DownloadInfoDto {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件下载url
     */
    private String downloadUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
