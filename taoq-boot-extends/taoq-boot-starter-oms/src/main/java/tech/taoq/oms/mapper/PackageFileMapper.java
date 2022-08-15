package tech.taoq.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import tech.taoq.oms.domain.db.PackageFileDO;

public interface PackageFileMapper extends BaseMapper<PackageFileDO> {

    // 临时调大 max_allowed_packet 的值为 500MB
    @Update("set global max_allowed_packet = 524288000")
    void setGlobalMaxAllowedPacket();
}
