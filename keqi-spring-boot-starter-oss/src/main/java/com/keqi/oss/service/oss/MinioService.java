package com.keqi.oss.service.oss;

import com.keqi.oss.domain.enums.FileStorageType;
import org.springframework.stereotype.Service;

/**
 * MinioService
 *
 * @author keqi
 */
@Service
public class MinioService implements OssService {

    @Override
    public String fileStorageType() {
        return FileStorageType.MINIO.getCode();
    }

    @Override
    public void deleteByName(String name) {

    }
}
