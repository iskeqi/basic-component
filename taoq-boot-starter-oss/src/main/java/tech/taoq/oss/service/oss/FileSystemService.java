package tech.taoq.oss.service.oss;

import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.UploadInfoDto;
import org.springframework.stereotype.Service;

/**
 * FileSystemService
 *
 * @author keqi
 */
@Service
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
