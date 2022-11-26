package tech.taoq.modbus;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 统一存储 AbstractModbusMasterTCP 对象, 断线主动移除
 *
 * @author keqi
 */
@Lazy
@Component
public class ModbusMasterTcpPool {

    private static final Logger log = LoggerFactory.getLogger(AbstractModbusMasterTCP.class);

    /**
     * 保存已建立连接的 AbstractModbusMasterTCP 对象[一级Key存储不同类型的设备,同一个Key中可以存储相同类型的不同设备]
     */
    private final Map<String, Map<String, AbstractModbusMasterTCP>> MODBUS_MASTER_TCP_MAP = new HashMap<>();

    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private ModbusProperties modbusProperties;

    @PostConstruct
    public void init() {
        log.info("ModbusMasterTcpPool init start");
        EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
            // 定时检测连接池中已断开连接的设备,并主动移除对应连接对象
            MODBUS_MASTER_TCP_MAP.forEach((type, abstractModbusMasterTCPMap) -> {
                if (abstractModbusMasterTCPMap.size() > 0) {
                    abstractModbusMasterTCPMap.forEach((s, abstractModbusMasterTCP) -> {
                        if (!abstractModbusMasterTCP.connected()) {
                            try {
                                log.info("remove modbus slave device {} starting", abstractModbusMasterTCP.getDeviceName());
                                this.removeMaster(type, abstractModbusMasterTCP.getDeviceName());
                                log.info("remove modbus slave device {} success", abstractModbusMasterTCP.getDeviceName());
                            } catch (ModbusIOException e) {
                                log.error("remove modbus slave device {} failure", abstractModbusMasterTCP.getDeviceName());
                            }
                        }
                    });
                }
            });
        }, modbusProperties.getInitialDelay(), modbusProperties.getPeriod(), TimeUnit.SECONDS);
        log.info("ModbusMasterTcpPool init end");
    }

    /**
     * 将 AbstractModbusMasterTCP 实现类对象统一进行保存
     *
     * @param type      类型
     * @param masterTCP AbstractModbusMasterTCP 连接对象
     */
    public synchronized void addMaster(String type, AbstractModbusMasterTCP masterTCP) {
        Map<String, AbstractModbusMasterTCP> modbusMasterTCPMap = MODBUS_MASTER_TCP_MAP.computeIfAbsent(type, k -> new HashMap<>());

        AbstractModbusMasterTCP slave = modbusMasterTCPMap.get(masterTCP.getDeviceName());
        if (slave != null) {
            throw new ModbusTCPException("Under the same type, deviceName cannot be the same");
        }

        modbusMasterTCPMap.put(masterTCP.getDeviceName(), masterTCP);
        log.info("ModbusMasterTcpPool addMaster type {}, name {}", type, masterTCP.getDeviceName());
    }

    /**
     * 移除 AbstractModbusMasterTCP 实现类对象
     *
     * @param type      类型
     * @param masterTCP AbstractModbusMasterTCP 连接对象
     */
    public void removeMaster(String type, AbstractModbusMasterTCP masterTCP) throws ModbusIOException {
        this.removeMaster(type, masterTCP.getDeviceName());
    }

    /**
     * 移除 AbstractModbusMasterTCP 实现类对象
     *
     * @param type       类型
     * @param deviceName 连接对象名称
     */
    public synchronized void removeMaster(String type, String deviceName) throws ModbusIOException {
        Map<String, AbstractModbusMasterTCP> modbusMasterTCPMap = MODBUS_MASTER_TCP_MAP.get(type);
        if (modbusMasterTCPMap != null) {
            AbstractModbusMasterTCP masterTCP = modbusMasterTCPMap.get(deviceName);
            modbusMasterTCPMap.remove(deviceName);
            masterTCP.disconnect();
            log.info("ModbusMasterTcpPool removeMaster type {} by deviceName {}", type, deviceName);
        }
    }

    /**
     * 根据类型和名称获取 AbstractModbusMasterTCP 实现类对象
     *
     * @param type       类型
     * @param deviceName 连接对象名称
     * @return AbstractModbusMasterTCP 实现类对象
     */
    public AbstractModbusMasterTCP getMaster(String type, String deviceName) {
        Map<String, AbstractModbusMasterTCP> modbusMasterTCPMap = MODBUS_MASTER_TCP_MAP.get(type);
        if (modbusMasterTCPMap != null) {
            return modbusMasterTCPMap.get(deviceName);
        }
        return null;
    }
}
