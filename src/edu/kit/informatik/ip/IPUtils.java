package edu.kit.informatik.ip;

import edu.kit.informatik.ParseException;

import static edu.kit.informatik.ip.IP.IPV4_MAX_VALUE;
import static edu.kit.informatik.ip.IP.IPV4_MIN_VALUE;
import static edu.kit.informatik.ip.IP.IPV4_VALUE_RANGE;

public class IPUtils {

    public static int byteAddressValueToInt(byte addrValue) {
        return addrValue + IPV4_VALUE_RANGE / 2;
    }

    public static int[] byteAddressToInts(byte[] address) {
        int[] intAddr = new int[address.length];
        for (int i = 0; i < address.length; ++i) {
            intAddr[i] = byteAddressValueToInt(address[i]);
        }
        return intAddr;
    }

    public static byte intAddressValueToByte(int addrValue) throws ParseException {
        if (addrValue < IPV4_MIN_VALUE || addrValue > IPV4_MAX_VALUE) {
            throw new ParseException("IPv4 addresses only 0-255");
        }
        return (byte) (addrValue - IPV4_VALUE_RANGE / 2);
    }

    public static byte[] intAddressToBytes(int[] address) throws ParseException {
        byte[] byteAddr = new byte[address.length];
        for (int i = 0; i < address.length; ++i) {
            byteAddr[i] = intAddressValueToByte(address[i]);
        }
        return byteAddr;
    }

}
