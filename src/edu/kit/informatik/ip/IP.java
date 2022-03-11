package edu.kit.informatik.ip;

import edu.kit.informatik.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IP implements Comparable<IP> {

    public static final int IPV4_MIN_VALUE = 0;
    public static final int IPV4_MAX_VALUE = 255;
    public static final int IPV4_VALUE_RANGE = IPV4_MAX_VALUE - IPV4_MIN_VALUE + 1;
    public static final int IPV4_NUMBER_AMOUNT = 4;

    public static final String POINT_SEPARATOR = ".";
    //public static final String ESCAPE_CHAR = "\\";
    private final byte[] address;

    public IP(final String pointNotation) throws ParseException {
        String[] addrStringparts = pointNotation.split("\\" + POINT_SEPARATOR);
        if (addrStringparts.length != IPV4_NUMBER_AMOUNT) {
            throw new ParseException("IPv4 addresses are only allowed to consist of 4 decimal numbers from "
                    + IPV4_MIN_VALUE + " to " + IPV4_MAX_VALUE + ".");
        }
        int[] addrParts = new int[IPV4_NUMBER_AMOUNT];
        for (int i = 0; i < addrParts.length; ++i) {
            try {
                addrParts[i] = Integer.parseInt(addrStringparts[i]);
            } catch (NumberFormatException nfe) {
                throw new ParseException("Please enter valid NUMBERS idiot");
            }
        }
        address = IPUtils.intAddressToBytes(addrParts);
    }

    public IP(final byte[] address) {
        this.address = address;
    }

    public byte[] getAddressBytes() {
        return address;
    }

    public int[] getAddressInts() {
        return IPUtils.byteAddressToInts(address);
    }

    public byte getAddressBytePart(int index) {
        return address[index];
    }

    public int getAddressIntPart(int index) {
        return IPUtils.byteAddressValueToInt(address[index]);
    }

    @Override
    public String toString() {
        StringBuilder addressString = new StringBuilder();
        for (byte part : address) {
            addressString.append(IPUtils.byteAddressValueToInt(part)).append(POINT_SEPARATOR);
        }
        addressString.deleteCharAt(addressString.length() - 1);
        return addressString.toString();
    }

    @Override
    public int compareTo(IP o) {
        for (int i = 0; i < IPV4_NUMBER_AMOUNT; ++i) {
            if (address[i] < o.address[i]) return -1;
            else if (address[i] > o.address[i]) return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Arrays.equals(address, ((IP) o).address);
    }

    public IP copy() {
        return new IP(address);
    }

    public static List<IP> copy(List<IP> list) {
        return new ArrayList<>(list);
    }

}