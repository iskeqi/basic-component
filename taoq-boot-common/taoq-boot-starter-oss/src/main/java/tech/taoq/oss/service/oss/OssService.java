package tech.taoq.oss.service.oss;

import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.UploadFileDto;

/**
 * OssService
 *
 * @author keqi
 */
public interface OssService {

    void deleteByName(String fileName);

    DownloadInfoDto getDownloadInfo(String fileName);

    UploadFileDto uploadFile(String fileName);

    String getStorageType();
}
