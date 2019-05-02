package graph;

import java.util.Map;
import java.util.Set;

public class Node {

    private String name;
    private Map<Node, Set<String>> list;

    public Node() { }

    public Node(Node a, String edge) { }

    public Set<String> getEdge(Node a) {
        throw new RuntimeException("Node.getEdge() is not yet implemented");
    }

    public String getName() {
        throw new RuntimeException("Node.getName() is not yet implemented");
    }

    public void addEdge(String edge) {
        throw new RuntimeException("Node.addEdge() is not yet implemented");
    }

}
