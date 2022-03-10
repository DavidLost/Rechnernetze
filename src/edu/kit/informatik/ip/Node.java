package edu.kit.informatik.ip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

    private final IP ip;
    private Node root;
    private List<Node> children;

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

    public void setRoot(final Node root) {
        this.root = root;
    }
}
