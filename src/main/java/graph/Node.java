package graph;

import java.util.*;

public class Node {

    private static final boolean DEBUG = false;

    // Name of this node.
    private String name;

    // Map of nodes this node has out edges to, with the keys being the other nodes' names and the values being the list
    // of edges going to the key node.
    private Map<String, List<String>> list;

    // Representation Invariant:
    // For each edge "e" from this node to an arbitrary node x,
    // there does not exist another edge from this to x with the same edge label.
    //
    // For any String (node) x, if (this.list.get(x).size() == 0) { this.list.get(x) = null }
    //
    // this.name != null && != "".
    //
    //
    // Abstraction Function:
    //  AF(this) = a node, n, such that
    //   n.name = the name of this node.
    //   n.list contains >= 0 String keys representing other nodes
    //   n.list.get(name of node in keyset) contains >= 1 edges between nodes

    /** Creates a new Node.
     * @param name Name of the new Node.
     * @spec.effects Constructs a new Node n with n.name = String a.
     * @throws NullPointerException if input is null.
     */
    public Node(String name) {
        if (name == null) {
            throw new NullPointerException("input must be a string of characters");
        }
        this.name = name;
        this.list = new Hashtable<String, List<String>>();
        this.checkRep();
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

    /** Adds an edge from this Node to a child Node. If edge with same label already exists from this node to a child
     *  node, do nothing.
     * @param child - Name of the child node this edge points to.
     * @param edge  - Label of the newly created edge.
     * @spec.requires child exists
     * @spec.modifies this.list
     * @spec.effects If Node not in this.list, adds it as a key and the String representation of a edge to its value set.
     *               Otherwise adds the String edge to the value set for Node child in this.list
     */
    public void addEdge(String child, String edge) {
        this.checkRep();
        if (this.list.containsKey(child)) {
            if (!this.list.get(child).contains(edge)) {
                this.list.get(child).add(edge);
            }
        } else {
            this.list.put(child, new ArrayList<String>());
            this.list.get(child).add(edge);
        }
        this.checkRep();

    }

    /** Removes an edge from this Node to a child Node. Does nothing if child node and/or edge does no exist.
     * @param child - Name of the child node this edge points to.
     * @param edge  - Label of the edge to remove.
     * @spec.modifies this.list
     * @spec.effects Removes edge between this node and another node, if only edge connecting this node and child node,
     *               remove child node from map keys.
     */
    public void removeEdge(String child, String edge) {
        this.checkRep();
        this.list.get(child).remove(edge);
        if (this.list.get(child).isEmpty()) {
            this.list.remove(child);
        }
        this.checkRep();
    }

    /** Removes the child Node "name" from this.list as well as the edges to it.
     * @param name - name of the node we wish to remove.
     * @spec.modifies this.list
     * @spec.effects Remove key Node "name" from this.list, also remove the edges attached to it.
     */
    public void removeChild(String name) {
        this.checkRep();
        this.list.remove(name);
        this.checkRep();
    }

    // Representation Invariant:
    // For each edge "e" from this node to an arbitrary node x,
    // there does not exist another edge from this to x with the same edge label.
    // For any String (node) x, if (this.list.get(x).size() == 0) { this.list.get(x) = null }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {
        if (DEBUG) {
            // check name is non-zero length, non-null string
            assert !(this.name == null || this.name == "");

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
}
