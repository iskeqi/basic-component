package tech.taoq.oss.service.oss;

import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.UploadInfoDto;

/**
 * OssService
 *
 * @author keqi
 */
public interface OssService {

    void deleteByName(String fileName);

    DownloadInfoDto getDownloadInfo(String fileName);

    UploadInfoDto uploadFile(String fileName);
}
