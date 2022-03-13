package edu.kit.informatik;

import edu.kit.informatik.ip.IP;
import edu.kit.informatik.ip.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Network {

    //private Node root;
    List<Node> roots = new ArrayList<>();

    public Network(final IP root, final List<IP> children) {
        roots.add(new Node(root.copy(), null, IP.copy(children)));
    }

    public Network(final String bracketNotation) throws ParseException {

        String[] ipStrings = bracketNotation.substring(1, bracketNotation.length() - 1).split(" ");
        for (String a : ipStrings) {
            System.out.println(a);
        }
        boolean isRoot = true;

        for (String ipString : ipStrings) {
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
        }

    }

    private Node addChild(Node root, String childIP) throws ParseException {
        System.out.println("adding childIP: " + childIP);
        Node newChild = new Node(new IP(childIP), root, Collections.emptyList());
        root.getChildren().add(newChild);
        return newChild;
    }

    public IP getRootIP() {
        return root.getIp();
    }

    public boolean add(final Network subnet) {
        return false;
    }

    public List<IP> list() {
        List<IP> list = new ArrayList<>();
        for (Node root : roots) {
            list.addAll(getAllIPsRecursive(new ArrayList<>(), root));
        }
        return list.stream().sorted().collect(Collectors.toList());
    }

    public boolean connect(final IP ip1, final IP ip2) {
        return false;
    }

    public boolean disconnect(final IP ip1, final IP ip2) {
        return false;
    }

    public boolean contains(final IP ip) {
        return list().contains(ip);
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
        Node rootNode = getNode(root);
        if (rootNode == null) return "";
        return buildStringRecursive("", rootNode, true).replace(" )", ")");
    }

    public Network copy() {
        return null;
    }

    public Node getNode(IP ip) {
        return getNodeRecursive(ip, null, root);
    }

    private String buildStringRecursive(String str, Node root, boolean first) {
        if (first) str += "(";
        str += root.getIp() + " ";
        for (Node child : root.getChildren()) {
            if (child.getChildren().isEmpty()) {
                str = buildStringRecursive(str, child, false);
            }
            else {
                str = buildStringRecursive(str, child, true);
            }
        }
        if (first) str += ") ";
        return str;
    }

    private Node getNodeRecursive(IP searched, Node found, Node root) {
        if (root.getIp().equals(searched)) {
            return root;
        }
        for (Node child : root.getChildren()) {
            found = getNodeRecursive(searched, found, child);
        }
        return found;
    }

    private ArrayList<IP> getAllIPsRecursive(ArrayList<IP> list, Node root) {
        list.add(root.getIp());
        for (Node child : root.getChildren()) {
            getAllIPsRecursive(list, child);
        }
        return list;
    }


}
