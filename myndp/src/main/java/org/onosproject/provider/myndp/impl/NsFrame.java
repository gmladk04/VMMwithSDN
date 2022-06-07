package org.onosproject.provider.myndp.impl;

import org.onlab.packet.BasePacket;
import org.onlab.packet.IPv6;
import org.onlab.packet.MacAddress;
import org.slf4j.Logger;

import java.nio.ByteBuffer;

import static org.slf4j.LoggerFactory.getLogger;

public class NsFrame extends BasePacket {

	public NsFrame (byte[] bytes) {
		target_addr = new byte[16];
		ByteBuffer bb = ByteBuffer.wrap(bytes);

		packet_type = bb.get();
		code = bb.get();
		checksum = bb.getShort();
		reserved = bb.getInt();
		bb.get(target_addr, 0, 16);
		type = bb.get();
		length = bb.get();

		byte[] mac_buf = new byte[6];
		bb.get(mac_buf, 0, 6);
		mac = MacAddress.valueOf(mac_buf);
	}

	private final Logger log = getLogger(getClass());

	private byte packet_type;
	private byte code;
	private short checksum;
	private int reserved;
	private byte[] target_addr;
	private byte type;
	private byte length;
	private MacAddress mac;


	public byte getPacket_type() {
		return packet_type;
	}

	public void setPacket_type(byte packet_type) {
		this.packet_type = packet_type;
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public short getChecksum() {
		return checksum;
	}

	public void setChecksum(short checksum) {
		this.checksum = checksum;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	public byte[] getTarget_addr() {
		return target_addr;
	}

	public void setTarget_addr(byte[] target_addr) {
		this.target_addr = target_addr;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}

	public MacAddress getMac() {
		return mac;
	}

	public void setMac(MacAddress mac) {
		this.mac = mac;
	}


	protected NsFrame() {
	}

	@Override
	public byte[] serialize() {
		ByteBuffer buf = ByteBuffer.allocate(32);
		buf.put(packet_type);
		buf.put(code);
		buf.putShort(checksum);
		buf.putInt(reserved);
		buf.put(target_addr);
		buf.put(type);
		buf.put(length);
		buf.put(mac.toBytes());

		return buf.array();
	}
}
