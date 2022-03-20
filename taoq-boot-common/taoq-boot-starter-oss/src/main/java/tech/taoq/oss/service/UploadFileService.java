package tech.taoq.oss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.pojo.enums.DeletedEnum;
import tech.taoq.oss.OssProperties;
import tech.taoq.oss.domain.db.UploadFileDO;
import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.NotificationDto;
import tech.taoq.oss.domain.dto.UploadFileDto;
import tech.taoq.oss.domain.param.NotificationParam;
import tech.taoq.oss.domain.param.UploadParam;
import tech.taoq.oss.mapper.UploadFileMapper;
import tech.taoq.oss.service.oss.OssService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

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
    private OssService ossService;

    public UploadFileDto uploadFileInfo(String fileName) {
        // 重命名文件名[文件模板为：/当天日期/UUID-真实文件名称，如 /2022-02-19/1bf7b69f9fb0a79ac611acb6bd8fa4c1-readme.md]
        fileName = "/" + LocalDate.now() + "/"
                + UUID.randomUUID().toString().replace("-", "") + "-" + fileName;
        return ossService.uploadFile(fileName);
    }

    public DownloadInfoDto downloadInfo(String fileName) {
        return ossService.getDownloadInfo(fileName);
    }

    public void deleteFile(String id) {
        // 先逻辑删除，保留一定期限后再删除掉
        // ossService.deleteByName(fileName);

        UploadFileDO entity = new UploadFileDO();
        entity.setDeleted(DeletedEnum.DISABLE.getCode());
        entity.setId(id);
        uploadFileMapper.updateById(entity);
    }

    public NotificationDto notification(NotificationParam param) {
        UploadFileDO uploadFileDO = new UploadFileDO();
        uploadFileDO.setName(param.getName());
        uploadFileDO.setPath(param.getPath());
        uploadFileDO.setType(param.getType());
        uploadFileDO.setSize(param.getSize());
        uploadFileDO.setStorageType(ossService.getStorageType());
        uploadFileMapper.insert(uploadFileDO);

        NotificationDto dto = new NotificationDto();
        dto.setId(uploadFileDO.getId());
        return dto;
    }

    /**
     * 保存到本地文件系统
     *
     * @param param param
     */
    public void uploadFile(UploadParam param) throws IOException {
        String rootPath = ossProperties.getLocalFileSystem().getRootPath();
        File file = new File(rootPath + param.getFileName());
        if (!file.exists()) {
            file.mkdirs();
        }
        param.getUploadFile().transferTo(file);
    }
}
