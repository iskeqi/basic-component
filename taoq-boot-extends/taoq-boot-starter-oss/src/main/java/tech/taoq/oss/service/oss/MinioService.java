package tech.taoq.oss.service.oss;

import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.third.ThirdServiceErrorException;
import tech.taoq.oss.OssProperties;
import tech.taoq.oss.domain.db.UploadFileDO;
import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.UploadFileDto;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * MinioService
 *
 * @author keqi
 */
@Service
@ConditionalOnProperty(name = "taos.oss.storage-type", havingValue = "minio")
public class MinioService implements OssService {

    private static final Logger log = LoggerFactory.getLogger(MinioService.class);

    @Autowired
    private OssProperties ossProperties;
    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        if (!UploadFileDO.StorageType.MINIO.name().equals(ossProperties.getStorageType())) {
            return;
        }

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
        String url;
        try {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(ossProperties.getMinio().getDefaultBucket())
                            .object(fileName)
                            .expiry(2, TimeUnit.MINUTES)
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
    public UploadFileDto uploadFile(String fileName) {
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

        UploadFileDto dto = new UploadFileDto();
        dto.setStorageType(getStorageType());
        dto.setFileName(fileName);
        dto.setUploadUrl(url);
        return dto;
    }

    @Override
    public String getStorageType() {
        return UploadFileDO.StorageType.MINIO.name();
    }
}
