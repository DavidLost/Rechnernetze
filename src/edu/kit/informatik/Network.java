package edu.kit.informatik;

import edu.kit.informatik.ip.IP;
import edu.kit.informatik.ip.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Network {

    Node root;

    public Network(final IP root, final List<IP> children) {
        this.root = new Node(root.copy(), IP.copy(children));
    }

    public Network(final String bracketNotation) throws ParseException {
        String[] parts = bracketNotation.split("[()]");
        for (String part : parts) {
            System.out.println(part);
        }
    }

    public boolean add(final Network subnet) {
        return false;
    }

    public List<IP> list() {
        return getIPsRecursive(new ArrayList<>(), root);
    }

    public boolean connect(final IP ip1, final IP ip2) {
        return false;
    }

    public boolean disconnect(final IP ip1, final IP ip2) {
        return false;
    }

    public boolean contains(final IP ip) {
        return false;
    }

    public int getHeight(final IP root) {
        return 0;
    }

    public List<List<IP>> getLevels(final IP root) {
        return null;
    }

    public List<IP> getRoute(final IP start, final IP end) {
        return null;
    }

    public String toString(IP root) {
        return null;
    }

    public Network copy() {
        return null;
    }

    public ArrayList<IP> getIPsRecursive(ArrayList<IP> list, Node root) {
        list.add(root.getIp());
        for (Node child : root.getChildren()) {
            getIPsRecursive(list, child);
        }
        return list;
    }
}