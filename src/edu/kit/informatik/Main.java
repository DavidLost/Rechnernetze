package edu.kit.informatik;

public class Main {

    public static void main(String[] args) throws ParseException {

        //new Network("(85.193.148.81 (141.255.1.133 122.117.67.158 0.146.197.108) 34.49.145.239 (231.189.0.127 77.135.84.171 39.20.222.120 252.29.23.0 116.132.83.77))");
        Network n = new Network("(1.1.1.1 (2.2.2.2 3.3.3.3) 4.4.4.4 (5.5.5.5 6.6.6.6 (7.7.7.7 8.8.8.8)) 9.9.9.9)");
        System.out.println("-------------------");
        System.out.println(n.list());

    }
}
