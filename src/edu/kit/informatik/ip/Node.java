package edu.kit.informatik.ip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Comparable<Node> {

    private final IP ip;
    private final Node root;
    private final List<Node> children;

    public Node(IP ip, Node root, List<IP> children) {
        this.ip = ip;
        this.root = root;
        this.children = new ArrayList<>();
        for (IP child : children) {
            this.children.add(new Node(child, this, Collections.emptyList()));
        }
    }

    public IP getIp() {
        return ip;
    }

    public Node getRoot() {
        return root;
    }

    public List<Node> getChildren() {
        return children;
    }

    @Override public String toString() {
        return ip.toString();
    }

    @Override
    public int compareTo(Node o) {
        return ip.compareTo(o.ip);
    }
}
