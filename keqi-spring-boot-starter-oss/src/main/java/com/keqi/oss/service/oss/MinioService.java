package com.keqi.oss.service.oss;

import com.keqi.common.exception.third.ThirdServiceErrorException;
import com.keqi.oss.OssProperties;
import com.keqi.oss.domain.dto.DownloadInfoDto;
import com.keqi.oss.domain.dto.UploadInfoDto;
import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * MinioService
 *
 * @author keqi
 */
@Service
public class MinioService implements OssService {

    private static final Logger log = LoggerFactory.getLogger(MinioService.class);

    @Autowired
    private OssProperties ossProperties;
    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        OssProperties.Minio propertiesMinio = ossProperties.getMinio();

        minioClient = MinioClient.builder()
                .endpoint(propertiesMinio.getEndpoint())
                .credentials(propertiesMinio.getAccessKey(), propertiesMinio.getSecretKey())
                .build();

        boolean exist;
        try {
            exist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(propertiesMinio.getDefaultBucket()).build());

            if (exist) {
                log.info("bucket {} already exists", propertiesMinio.getDefaultBucket());
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(propertiesMinio.getDefaultBucket()).build());
                log.info("make bucket {} success", propertiesMinio.getDefaultBucket());
            }
        } catch (Throwable e) {
            log.error("minio has an error", e);
            throw new ThirdServiceErrorException("minio has an error");
        }
    }

    @Override
    public void deleteByName(String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(ossProperties.getMinio().getDefaultBucket())
                    .object(fileName)
                    .build());
        } catch (Throwable e) {
            log.error("minio has an error", e);
            throw new ThirdServiceErrorException("minio has an error");
        }
    }

    @Override
    public DownloadInfoDto getDownloadInfo(String fileName) {
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("response-content-type", "application/json");

        String url;
        try {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(ossProperties.getMinio().getDefaultBucket())
                            .object(fileName)
                            .expiry(2, TimeUnit.MINUTES)
                            .extraQueryParams(reqParams)
                            .build());
        } catch (Throwable e) {
            log.error("minio has an error", e);
            throw new ThirdServiceErrorException("minio has an error");
        }

        DownloadInfoDto dto = new DownloadInfoDto();
        dto.setFileName(fileName);
        dto.setDownloadUrl(url);
        return dto;
    }

    @Override
    public UploadInfoDto uploadFile(String fileName) {
        String url;
        try {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(ossProperties.getMinio().getDefaultBucket())
                            .object(fileName)
                            .expiry(2, TimeUnit.MINUTES)
                            .build());
        } catch (Throwable e) {
            log.error("minio has an error", e);
            throw new ThirdServiceErrorException("minio has an error");
        }

        UploadInfoDto dto = new UploadInfoDto();
        dto.setFileName(fileName);
        dto.setUploadUrl(url);
        return dto;
    }
}
