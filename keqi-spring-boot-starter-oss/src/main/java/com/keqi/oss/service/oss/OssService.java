package com.keqi.oss.service.oss;

/**
 * OssService
 *
 * @author keqi
 */
public interface OssService {

    String fileStorageType();

    void deleteByName(String name);
}
