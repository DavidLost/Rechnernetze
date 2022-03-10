package edu.kit.informatik;

import edu.kit.informatik.ip.IP;
import edu.kit.informatik.ip.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Network {

    Node root;

    public Network(final IP root, final List<IP> children) {
        this.root = new Node(root.copy(), null, IP.copy(children));
    }

    public Network(final String bracketNotation) throws ParseException {

        String[] ipStrings = bracketNotation.substring(1, bracketNotation.length() - 1).split(" ");
        for (String a : ipStrings) {
            System.out.println(a);
        }
        boolean isRoot = true;

        for (String ipString : ipStrings) {
            //System.out.println(wasFirst);
            if (ipString.startsWith("(")) {
                root = addChild(root, ipString.substring(1));
            }
            else if (ipString.endsWith(")")) {
                int count = (int) ipString.chars().filter(c -> c == ')').count();
                addChild(root, ipString.substring(0, ipString.length() - count));
                for (int i = 0; i < count; ++i) {
                    root = root.getRoot();
                }
            }
            else {
                if (isRoot) {
                    root = new Node(new IP(ipString), null, Collections.emptyList());
                    isRoot = false;
                }
                else {
                    addChild(root, ipString);
                }
            }
            System.out.print("current address: " + ipString + " croot: " + root.getIp() + " - ");
            if (root.getRoot() != null) {
                System.out.print("root: " + root.getRoot().getIp() + " childs: ");
            }
            else System.out.print("childs: ");
            for (Node child : root.getChildren()) {
                System.out.print(child.getIp() + " ");
            }
            System.out.println();
            //System.out.println(c+" - "+currentIP+" - "+wasFirst+" - "+wasBClosed);
        }

        System.out.println(root.getIp());

    }

    private Node addChild(Node root, String childIP) throws ParseException {
        System.out.println("adding childIP: " + childIP);
        Node newChild = new Node(new IP(childIP), root, Collections.emptyList());
        root.getChildren().add(newChild);
        return newChild;
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

    private ArrayList<IP> getIPsRecursive(ArrayList<IP> list, Node root) {
        list.add(root.getIp());
        for (Node child : root.getChildren()) {
            getIPsRecursive(list, child);
        }
        return list;
    }
}
