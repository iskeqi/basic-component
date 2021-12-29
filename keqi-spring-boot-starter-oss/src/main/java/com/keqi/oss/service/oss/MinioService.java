package com.keqi.oss.service.oss;

import com.keqi.oss.domain.dto.DownloadInfoDto;
import com.keqi.oss.domain.dto.UploadInfoDto;
import org.springframework.stereotype.Service;

/**
 * MinioService
 *
 * @author keqi
 */
@Service
public class MinioService implements OssService {

    @Override
    public void deleteByName(String fileName) {

    }

    @Override
    public DownloadInfoDto getDownloadInfo(String fileName) {
        return null;
    }

    @Override
    public UploadInfoDto uploadFile(String fileName) {
        return null;
    }
}
