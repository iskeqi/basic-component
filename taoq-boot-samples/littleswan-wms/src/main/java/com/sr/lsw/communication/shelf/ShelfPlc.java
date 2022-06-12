package com.sr.lsw.communication.shelf;

import tech.taoq.modbus.AbstractModbusMasterTCP;
import tech.taoq.modbus.ModbusTCPException;

/**
 * 货架 PLC
 *
 * @author keqi
 */
public class ShelfPlc extends AbstractModbusMasterTCP {

    public ShelfPlc(String host, int port, String deviceName) throws ModbusTCPException {
        super(host, port, deviceName);
    }
}
