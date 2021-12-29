package com.keqi.oss.service.oss;

import com.keqi.oss.OssProperties;
import com.keqi.oss.domain.dto.DownloadInfoDto;
import com.keqi.oss.domain.dto.UploadInfoDto;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * MinioService
 *
 * @author keqi
 */
@Service
public class MinioService implements OssService {

    @Autowired
    private OssProperties ossProperties;
    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder()
                .endpoint(ossProperties.getMinio().getEndpoint())
                .credentials(ossProperties.getMinio().getAccessKey(), ossProperties.getMinio().getSecretKey())
                .build();
    }

    @Override
    public void deleteByName(String fileName) {

    }

    @Override
    public DownloadInfoDto getDownloadInfo(String fileName) {
        return null;
    }

    @Override
    public UploadInfoDto uploadFile(String fileName) {
        //minioClient.getPresignedPostFormData()

        return null;
    }
}
