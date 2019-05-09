package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

    private final String name;
    private Map<String, List<String>> list;

    // Representation Invariant:
    // For each edge "e" from this node to an arbitrary node x,
    // there does not exist another edge from this to x with the same edge label.
    //
    // For any String (node) x, if (this.list.get(x).size() == 0) { this.list.get(x) = null }
    //
    //
    // Abstraction Function:
    //  AF(this) = a node, n, such that
    //   n.name = the name of this node.
    //   n.list contains >= 0 String keys representing other nodes
    //   n.list.get(name of node in keyset) contains >= 1 edges between nodes

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
     * @throws RuntimeException if edge with same label already exists between this and child.
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

    /** Removes an edge from this Node to a child Node. Does nothing if child node and/or edge does no exist.
     * @param child - Name of the child node this edge points to.
     * @param edge  - Label of the edge to remove.
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

    /** Removes the child Node "name" from this.list as well as the edges to it.
     * @param name - name of the node we wish to remove.
     * @spec.modifies this.list
     * @spec.effects Remove key Node "name" from this.list, also remove the edges attached to it.
     */
    public void removeChild(String name) {
        this.list.remove(name);
    }

    // Representation Invariant:
    // For each edge "e" from this node to an arbitrary node x,
    // there does not exist another edge from this to x with the same edge label.
    // For any String (node) x, if (this.list.get(x).size() == 0) { this.list.get(x) = null }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        // Check unique edge labels
        for (List<String> edgeList : this.list.values()) {
            for (int edge1 = 0; edge1 < edgeList.size(); edge1++) {
                for (int edge2 = edge1 + 1; edge2 < edgeList.size(); edge2++) {
                    assert edgeList.get(edge1) != edgeList.get(edge2);
                }
            }
        }


        for (List<String> edgeList : this.list.values()) {
            assert edgeList.size() != 0;
        }
    }
}
