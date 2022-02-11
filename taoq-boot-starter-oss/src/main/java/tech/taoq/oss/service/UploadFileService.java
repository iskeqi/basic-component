package tech.taoq.oss.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.taoq.common.pojo.enums.DeletedEnum;
import tech.taoq.oss.OssProperties;
import tech.taoq.oss.domain.db.UploadFileDO;
import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.UploadInfoDto;
import tech.taoq.oss.domain.enums.FileStorageType;
import tech.taoq.oss.mapper.UploadFileMapper;
import tech.taoq.oss.service.oss.FileSystemService;
import tech.taoq.oss.service.oss.MinioService;
import tech.taoq.oss.service.oss.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * UploadFileService
 *
 * @author keqi
 */
@Service
public class UploadFileService {

    @Autowired
    private UploadFileMapper uploadFileMapper;
    @Autowired
    private OssProperties ossProperties;
    @Autowired
    private ApplicationContext applicationContext;
    private OssService ossService;

    @PostConstruct
    public void init() {
        if (FileStorageType.MINIO.getCode().equals(ossProperties.getFileStorageType())) {
            ossService = applicationContext.getBean(MinioService.class);
        } else {
            ossService = applicationContext.getBean(FileSystemService.class);
        }
    }

    public UploadInfoDto uploadFile(String fileName) {
        return ossService.uploadFile(fileName);
    }

    public DownloadInfoDto getDownloadInfo(String fileName) {
        return ossService.getDownloadInfo(fileName);
    }

    public void deleteFile(String fileName) {
        ossService.deleteByName(fileName);

        UploadFileDO entity = new UploadFileDO();
        entity.setDeleted(DeletedEnum.DISABLE.getCode());
        UploadFileDO updateWrapper = new UploadFileDO();
        updateWrapper.setName(fileName);
        uploadFileMapper.update(entity, Wrappers.query(updateWrapper));
    }

    public void notification(UploadFileDO param) {
        uploadFileMapper.insert(param);
    }
}
