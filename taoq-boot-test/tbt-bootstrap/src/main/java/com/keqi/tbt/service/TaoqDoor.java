package com.keqi.tbt.service;

import tech.taoq.modbus.AbstractModbusMasterTCP;
import tech.taoq.modbus.ModbusTCPException;

/**
 * Taoq 自动门
 */
public class TaoqDoor extends AbstractModbusMasterTCP {

    public TaoqDoor(String host, int port, String deviceName) throws ModbusTCPException {
        super(host, port, deviceName);
    }

    public boolean isOpen() {
        return this.readCoils(1);
    }

    public void open() {
        this.writeSingleCoil(5, true);
    }
}
