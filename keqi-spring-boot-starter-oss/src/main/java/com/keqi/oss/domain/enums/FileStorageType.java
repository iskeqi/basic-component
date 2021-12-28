package com.keqi.oss.domain.enums;

/**
 * FileStorageType
 *
 * @author keqi
 */
public enum FileStorageType {

    LOCAL_FILE_SYSTEM("localFileSystem", "存储在应用程序所在的本地文件系统中"),
    MINIO("minio", "存储在 MINIO 文件系统中");

    private final String code;
    private final String codeName;

    FileStorageType(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static FileStorageType parse(String code) {
        FileStorageType[] values = FileStorageType.values();
        for (FileStorageType value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
