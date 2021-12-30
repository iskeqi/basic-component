package com.keqi.oss.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.keqi.common.pojo.enums.DeletedEnum;
import com.keqi.oss.OssProperties;
import com.keqi.oss.domain.db.UploadFileDO;
import com.keqi.oss.domain.dto.DownloadInfoDto;
import com.keqi.oss.domain.dto.UploadInfoDto;
import com.keqi.oss.domain.enums.FileStorageType;
import com.keqi.oss.mapper.UploadFileMapper;
import com.keqi.oss.service.oss.FileSystemService;
import com.keqi.oss.service.oss.MinioService;
import com.keqi.oss.service.oss.OssService;
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
