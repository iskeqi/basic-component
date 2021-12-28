package com.keqi.oss.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.keqi.common.pojo.enums.DeletedEnum;
import com.keqi.oss.domain.db.UploadFileDO;
import com.keqi.oss.domain.dto.DownloadInfoDto;
import com.keqi.oss.domain.dto.UploadInfoDto;
import com.keqi.oss.mapper.UploadFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UploadFileService
 *
 * @author keqi
 */
@Service
public class UploadFileService {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    public void deleteFile(String fileName) {
        // 删除文件 todo


        UploadFileDO entity = new UploadFileDO();
        entity.setDeleted(DeletedEnum.DISABLE.getCode());
        UploadFileDO updateWrapper = new UploadFileDO();
        updateWrapper.setName(fileName);
        uploadFileMapper.update(entity, Wrappers.query(updateWrapper));
    }

    public DownloadInfoDto getDownloadInfo(String fileName) {
        return null;
    }

    public UploadInfoDto uploadFile(String fileName) {
        return null;
    }

    public void notification(UploadFileDO param) {
        uploadFileMapper.insert(param);
    }
}
