package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;
    private int nodeID;
    private Map<String, List<String>> list;

    /** Creates a new Node.
     * @param name Name of the new Node.
     * @spec.requires String a.length less than 0.
     * @spec.effects Constructs a new Node n with n.name = String a.
     * @throws RuntimeException if input is null or a zero length string.
     */
    public Node(String name) {
        if (name == null || name.equals("")) {
            throw new RuntimeException("input must be a non-zero length string of characters");
        }
        this.name = name;
    }

    /** Returns a copy of Node's set of edges.
     * @spec.requires Node.list.isEmpty() = false.
     * @return this.list
     */
    public Map<String, List<String>> getEdges() {
        return this.list;
    }

    /** Returns the name of this Node.
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /** Adds an edge from this Node to a child Node.
     * @param child - Name of the child node this edge points to.
     * @param edge  - Label of the newly created edge.
     * @spec.requires child exists
     * @throws RuntimeException if edge already exists between this and child.
     * @spec.modifies this.list
     * @spec.effects If Node not in this.list, adds it as a key and the String representation of a edge to its value set.
     *               Otherwise adds the String edge to the value set for Node child in this.list
     */
    public void addEdge(String child, String edge) {
        if (this.list.containsKey(child)) {
            if (!this.list.get(child).contains(edge)) {
                this.list.get(child).add(edge);
            } else {
                throw new RuntimeException("Edges between two nodes must be unique");
            }
        } else {
            this.list.put(child, new ArrayList<String>());
            this.list.get(child).add(edge);
        }
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
        this.list.get(child).remove(edge);
        if (this.list.get(child).isEmpty()) {
            this.list.remove(child);
        }
    }

}
