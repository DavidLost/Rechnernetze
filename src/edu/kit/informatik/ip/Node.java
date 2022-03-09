package edu.kit.informatik.ip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

    private final IP ip;
    private final List<Node> children;

    public Node(IP ip, List<IP> children) {
        this.ip = ip;
        this.children = new ArrayList<>();
        for (IP child : children) {
            this.children.add(new Node(child, Collections.emptyList()));
        }
    }

    public IP getIp() {
        return ip;
    }

    public List<Node> getChildren() {
        return children;
    }


}
