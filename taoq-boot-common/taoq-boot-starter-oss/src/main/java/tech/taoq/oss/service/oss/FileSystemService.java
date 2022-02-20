package tech.taoq.oss.service.oss;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import tech.taoq.oss.OssProperties;
import tech.taoq.oss.domain.db.UploadFileDO;
import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.UploadFileDto;

import java.io.File;

/**
 * FileSystemService
 *
 * @author keqi
 */
@Service
@ConditionalOnProperty(name = "taos.oss.storage-type", havingValue = "localFileSystem", matchIfMissing = true)
public class FileSystemService implements OssService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public void deleteByName(String fileName) {
        // 在本地文件系统中删除此文件
        String rootPath = ossProperties.getLocalFileSystem().getRootPath();
        File file = new File(rootPath + fileName);
        FileUtils.deleteQuietly(file);
    }

    @Override
    public DownloadInfoDto getDownloadInfo(String fileName) {
        // 文件存储在本地文件系统时，实际上用不到此方法
        DownloadInfoDto dto = new DownloadInfoDto();
        dto.setFileName(fileName);
        dto.setDownloadUrl(fileName);
        return dto;
    }

    @Override
    public UploadFileDto uploadFile(String fileName) {
        // 用于文件上传的接口访问url
        String url = "/sys/upload/upload";

        UploadFileDto dto = new UploadFileDto();
        dto.setStorageType(getStorageType());
        dto.setFileName(fileName);
        dto.setUploadUrl(url);
        return dto;
    }

    @Override
    public String getStorageType() {
        return UploadFileDO.StorageType.LOCAL_FILE_SYSTEM.getCode();
    }
}
