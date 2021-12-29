package com.keqi.oss.service.oss;

import com.keqi.oss.domain.dto.DownloadInfoDto;
import com.keqi.oss.domain.dto.UploadInfoDto;

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
