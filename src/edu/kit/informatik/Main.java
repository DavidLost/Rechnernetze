package edu.kit.informatik;

import edu.kit.informatik.ip.IP;
import edu.kit.informatik.ip.IPUtils;
import edu.kit.informatik.ip.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {

        new Network("(85.193.148.81 (141.255.1.133 122.117.67.158 0.146.197.108) 34.49.145.239 (231.189.0.127 77.135.84.171 39.20.222.120 252.29.23.0 116.132.83.77))");

        System.out.println(Arrays.toString("dji1mb11dk22po1mcj222hihi1l".split("11|22")));

        try {
            IP a = new IP("0.0.0.0");
            IP b = new IP("0.0.1.0");
            IP c = new IP("0.0.1.1");
            IP d = new IP("0.0.1.2");
            IP e = new IP("0.0.13.2");
            IP f = new IP("0.0.11.26");

            List<IP> children = List.of(f, b, c, d);

            Network network = new Network(a, children);
            /*System.out.println(network.list());

            System.out.println(a);
            System.out.println(b);
            System.out.println(Arrays.toString(a.getAddressBytes()));
            System.out.println(Arrays.toString(b.getAddressBytes()));
            System.out.println(Integer.MAX_VALUE);
            System.out.println(a.compareTo(b));
            System.out.println(b.compareTo(a));*/

            //System.out.println(a.compareTo(b));
        } catch (ParseException pe) {
            pe.printStackTrace();
        }


    }
}
