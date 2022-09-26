package tech.taoq.modbus;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ModbusProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "taoq.modbus")
public class ModbusProperties {

    /**
     * ModbusMasterTcpPool 对象初始化后,第几秒钟开始执行 移除已断开连接的设备 的定时任务
     */
    private long initialDelay = 10;

    /**
     * 定时任务每隔几秒钟开始重复执行一次
     */
    private long period = 5;

    public long getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
