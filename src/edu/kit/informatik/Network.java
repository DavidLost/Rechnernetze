package edu.kit.informatik;

import edu.kit.informatik.ip.IP;
import edu.kit.informatik.ip.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Network {

    private final List<Node> roots = new ArrayList<>();

    public Network(final IP root, final List<IP> children) {
        if (root == null || children == null || children.isEmpty()) {
            throw new RuntimeException("Network constructor call with uninstantiated or empty parameters.");
        }
        roots.add(new Node(root.copy(), null, IP.copy(children)));
    }

    public Network(final String bracketNotation) throws ParseException {

        String[] ipStrings = bracketNotation.substring(1, bracketNotation.length() - 1).split(" ");

        Node root = null;
        boolean isRoot = true;

        for (String ipString : ipStrings) {
            if (root == null && !isRoot) throw new ParseException("invalid bracket notation");
            if (ipString.startsWith("(")) {
                root = addChild(root, ipString.substring(1));
            }
            else if (ipString.endsWith(")")) {
                int count = (int) ipString.chars().filter(c -> c == ')').count();
                addChild(root, ipString.substring(0, ipString.length() - count));
                for (int i = 0; i < count; ++i) {
                    assert root != null;
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
            /*assert root != null;
            System.out.print("current address: " + ipString + " croot: " + root.getIp() + " - ");
            if (root.getRoot() != null) {
                System.out.print("root: " + root.getRoot().getIp() + " childs: ");
            }
            else System.out.print("childs: ");
            for (Node child : root.getChildren()) {
                System.out.print(child.getIp() + " ");
            }
            System.out.println();*/
        }
        roots.add(root);
    }

    private Node addChild(Node root, String childIP) throws ParseException {
        System.out.println("adding childIP: " + childIP);
        Node newChild = new Node(new IP(childIP), root, Collections.emptyList());
        root.getChildren().add(newChild);
        return newChild;
    }

    public List<IP> getRootIPs() {
        List<IP> list = new ArrayList<>();
        for (Node root : roots) {
            list.add(root.getIp());
        }
        return list;
    }

    public boolean add(final Network subnet) {
        return false;
    }

    public List<IP> list() {
        List<IP> list = new ArrayList<>();
        for (Node root : roots) {
            System.out.println("listing root " + root.getIp());
            list.addAll(getAllIPsRecursive(new ArrayList<>(), root));
        }
        return list.stream().sorted().collect(Collectors.toList());
    }

    private List<IP> getAllIPsRecursive(ArrayList<IP> list, Node root) {
        list.add(root.getIp());
        for (Node child : root.getChildren()) {
            getAllIPsRecursive(list, child);
        }
        return list;
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
        Node rootNode = getNode(root);
        if (rootNode == null) return 0;
        //return getLevels(root).size() could also be used, but in therms of efficiency height got its own recursive
        return getHeightRecursive(0, 0, rootNode);
    }

    private int getHeightRecursive(int height, int counter, Node root) {
        if (counter > height) height = counter;
        for (Node child : root.getChildren()) {
            height = getHeightRecursive(height, counter + 1, child);
        }
        return height;
    }

    public List<List<IP>> getLevels(final IP root) {
        Node rootNode = getNode(root);
        if (rootNode == null) return null;
        List<List<IP>> levels = getLevelsRecursive(0, new ArrayList<>(), rootNode);
        List<List<IP>> sortedLevels = new ArrayList<>();
        for (List<IP> level : levels) {
            sortedLevels.add(level.stream().sorted().collect(Collectors.toList()));
        }
        return sortedLevels;
    }

    private List<List<IP>> getLevelsRecursive(int level, List<List<IP>> list, Node root) {
        if (list.size() <= level) list.add(new ArrayList<>());
        list.get(level).add(root.getIp());
        for (Node child : root.getChildren()) {
            getLevelsRecursive(level + 1, list, child);
        }
        return list;
    }

    public List<IP> getRoute(final IP start, final IP end) {
        return null;
    }

    public String toString(IP root) {
        Node rootNode = getNode(root);
        if (rootNode == null) return "";
        StringBuilder result = buildStringRecursive(new StringBuilder(), rootNode, true);
        return result.deleteCharAt(result.length() - 1).toString();
    }

    private StringBuilder buildStringRecursive(StringBuilder str, Node root, boolean first) {
        if (first) str.append("(");
        str.append(root.getIp()).append(" ");
        for (Node child : root.getChildren().stream().sorted().collect(Collectors.toList())) {
            if (child.getChildren().isEmpty()) {
                str = new StringBuilder(buildStringRecursive(str, child, false));
            }
            else {
                str = new StringBuilder(buildStringRecursive(str, child, true));
            }
        }
        if (first) {
            str.deleteCharAt(str.length() - 1);
            str.append(") ");
        }
        return str;
    }

    public Network copy() {
        return null;
    }

    public Node getNode(IP ip) {
        for (Node root : roots) {
            Node result = getNodeRecursive(ip, null, root);
            if (result != null) return result;
        }
        return null;
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
}
