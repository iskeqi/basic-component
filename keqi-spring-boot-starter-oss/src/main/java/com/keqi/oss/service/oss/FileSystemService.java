package com.keqi.oss.service.oss;

import com.keqi.oss.domain.enums.FileStorageType;

/**
 * FileSystemService
 *
 * @author keqi
 */
public class FileSystemService implements OssService {

    @Override
    public String fileStorageType() {
        return FileStorageType.FILE_SYSTEM.getCode();
    }

    @Override
    public void deleteByName(String name) {

    }
}
