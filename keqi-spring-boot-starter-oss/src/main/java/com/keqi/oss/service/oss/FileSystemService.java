package com.keqi.oss.service.oss;

import com.keqi.oss.domain.dto.DownloadInfoDto;
import com.keqi.oss.domain.dto.UploadInfoDto;

/**
 * FileSystemService
 *
 * @author keqi
 */
//@Service
//@ConditionalOnProperty(prefix = "keqi.oss", name = "fileStorageType", havingValue = "localFileSystem")
public class FileSystemService implements OssService {

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
