package edu.kit.informatik;

import edu.kit.informatik.ip.IP;
import edu.kit.informatik.ip.Node;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {

        Network n1 = new Network("(85.193.148.81 (141.255.1.133 122.117.67.158 0.146.197.108) 34.49.145.239 (231.189.0.127 77.135.84.171 39.20.222.120 252.29.23.0 116.132.83.77))");
        Network n2 = new Network("(1.1.1.1 (2.2.2.2 3.3.3.3) 9.9.9.9 (5.5.5.5 7.7.7.7 (6.6.6.6 8.8.8.8)) 4.4.4.4)");
        System.out.println("-------------------");
        System.out.println(n1.list());
        System.out.println(n2.list());
        System.out.println(n2.toString(n2.getRootIPs().get(0)));
        System.out.println(n2.getHeight(n2.getRootIPs().get(0)));
        for (List<IP> list : n2.getLevels(n2.getRootIPs().get(0))) {
            for (IP ip : list) {
                System.out.print(ip + " ");
            }
            System.out.println();
        }

        //test();
    }

    public static void test() throws ParseException {
        // Construct initial network
        System.out.println("--------TEST--------");
        IP root = new IP("141.255.1.133");

        List<List<IP>> levels = List.of(List.of(root),
                List.of(new IP("0.146.197.108"), new IP("122.117.67.158")));
        final Network network = new Network(root, levels.get(1));
        // (141.255.1.133 0.146.197.108 122.117.67.158)
        System.out.println(network.toString(root));
        // true
        System.out.println((levels.size() - 1) == network.getHeight(root));
        // true
        System.out.println(List.of(List.of(root), levels.get(1)).equals(network.getLevels(root)));
        // "Change" root and call toString, getHeight and getLevels again
        root = new IP("122.117.67.158");
        levels = List.of(List.of(root), List.of(new IP("141.255.1.133")),
                List.of(new IP("0.146.197.108")));
        // true
        System.out.println("(122.117.67.158 (141.255.1.133 0.146.197.108))"
                        .equals(network.toString(root)));
        // true
        System.out.println((levels.size() - 1) == network.getHeight(root));
        // true
        System.out.println(levels.equals(network.getLevels(root)));
        // Try to add circular dependency
        // false
        System.out.println(
                network.add(new Network("(122.117.67.158 0.146.197.108)")));
        // Merge two subnets with initial network
        // true
        System.out.println(network.add(new Network(
                "(85.193.148.81 34.49.145.239 231.189.0.127 141.255.1.133)")));
        // true
        System.out
                .println(network.add(new Network("(231.189.0.127 252.29.23.0"
                                + " 116.132.83.77 39.20.222.120 77.135.84.171)")));
    }
}
