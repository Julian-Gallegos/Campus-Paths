package graph;

import java.util.Map;
import java.util.Set;

public class Node {

    private String name;
    private Map<Node, Set<String>> list;

    /** Creates a new Node.
     * @param name Name of the new Node.
     * @spec.requires String a.length less than 0.
     * @spec.effects Constructs a new Node n with n.name = String a.
     */
    public Node(String name) { }

    /** Returns a copy of Node's set of edges.
     * @spec.requires Node.list.isEmpty() = false.
     * @return this.list
     */
    public Set<String> getEdges() {
        throw new RuntimeException("Node.getEdge() is not yet implemented");
    }

    /** Returns the name of this Node.
     * @return this.name
     */
    public String getName() {
        throw new RuntimeException("Node.getName() is not yet implemented");
    }

    /** Adds an edge from this Node to a child Node.
     * @param child - Name of the child node this edge points to.
     * @param edge  - Label of the newly created edge.
     * @spec.requires child exists
     * @spec.modifies this.list
     * @spec.effects If Node not in this.list, adds it as a key and the String representation of a edge to its value set.
     *               Otherwise adds the String edge to the value set for Node child in this.list
     */
    public void addEdge(String child, String edge) {
        throw new RuntimeException("Node.addEdge() is not yet implemented");
    }

    /** Removes an edge from this Node to a child Node.
     * @param child - Name of the child node this edge points to.
     * @param edge  - Label of the edge to remove.
     * @spec.requires child and edge exists.
     * @spec.modifies this.list
     * @spec.effects Removes edge between this node and another node, if only edge connecting this node and child node,
     *               remove child node from map keys.
     */
    public void removeEdge(String child, String edge) {
        throw new RuntimeException("Node.addEdge() is not yet implemented");
    }

}
