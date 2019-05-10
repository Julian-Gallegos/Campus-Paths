package graph;

import java.util.*;

/**
 * This class represents a directed, weighted graph of nodes with unique names and string based edge labels.
 */
public class Graph {

    // For more in depth checkRep.
    private static final boolean DEBUG = false;

    /** The set of nodes within the graph */
    private Map<String, Node> graph;

    // Representation Invariant:
    // For all x, y; x != y: this.graph.get(x).getName() != this.graph.get(y).getName()
    //
    // For each edge "e" from node x to node y, there does not exist another edge from x to y with the same edge label.
    //
    // Arbitrary node x != null
    //
    // x.name != null && x.name != ""
    //
    // For any edge e, this.edgeExists(e) != true when this.listNodes.size() < 2
    //
    //
    // Abstraction Function:
    //  AF(this) = a graph, g, such that
    //   g.list contains >= 0 nodes with unique names
    //                   >= 0 edges between nodes

    /** Creates a new Graph with an empty graph list.
     * @spec.effects Constructs a new Graph g with g.graph.isEmpty() = true.
     */
    public Graph() {
        this.graph = new Hashtable<String, Node>();
    }

    /** Creates a new Graph with one node in the graph list.
     * @param node - The name of the Node being added to Graph.
     * @spec.requires Name of "node" is at least one character.
     * @spec.effects Constructs a new Graph g with Node "node" in g.graph.
     */
    public Graph(String node) {
        this.graph = new Hashtable<String, Node>();
        this.graph.put(node, new Node(node));
        this.checkRep();
    }

    /** Adds a new node to this Graph.
     * @param name - Name of the new node.
     * @spec.requires node has a unique name
     * @spec.modifies this.graph
     * @spec.effects Adds a new Node "node" to this.graph.
     * @throws RuntimeException if input is zero length string.
     * @throws NullPointerException if name input is null.
     */
    public void addNode(String name) {
        this.checkRep();
        graph.put(name, new Node(name));
        this.checkRep();
    }

    /** Adds a new edge from one Node to another Node in Graph. Does nothing if edge with same label from parent to
     *  child node already exists.
     * @param parent - Name of parent Node for the new edge.
     * @param child - Name of child Node for the new edge.
     * @param edge - Label for the new edge.
     * @spec.requires Child exists in graph.
     * @throws NullPointerException if parent input value is null.
     * @spec.modifies this.graph
     * @spec.effects Add an edge to Node a that travels to the child Node b.
     */
    public void addEdge(String parent, String child, String edge) {
        this.checkRep();
        this.graph.get(parent).addEdge(child, edge);
        this.checkRep();
;    }

    /** Removes a node from the Graph, if it exists, otherwise does nothing.
     * @param name - The name of the node to remove.
     * @throws NullPointerException if input is null.
     * @spec.modifies this.graph
     * @spec.effects Removes the Node with name "name" from this.graph, as well as
     *               all of the edges going to and from it.
     */
    public void removeNode(String name) {
        this.checkRep();
        this.graph.remove(name);
        for (Node node : this.graph.values()) {
            node.removeChild(name);
        }
        this.checkRep();
    }

    /** Removes an edge from the Graph. Does nothing if Nodes parent or child or edge does not exist on the graph.
     * @param parent - Name of the parent node that the edge is coming from.
     * @param child - Name of the child node that the edge is going to.
     * @param edge  - Label of the edge going from parent to child.
     * @spec.modifies this.graph
     * @throws NullPointerException if parent input is null.
     * @spec.effects Removes the edge with label "edge" coming from parent
     *               to child in this.graph.
     */
    public void removeEdge(String parent, String child, String edge) {
        this.checkRep();
        this.graph.get(parent).removeEdge(child, edge);
        this.checkRep();
    }

    /** Returns true if the specified node exists in the graph.
     * @param nodeName - Node that we are checking for in the graph.
     * @throws NullPointerException if nodeName input is null.
     * @return true is "nodeName" is in the graph, false otherwise.
     */
    public boolean nodeExists(String nodeName) {
        if (this.graph.containsKey(nodeName)) {
            return true;
        }
        return false;
    }

    /** Returns true if the specified edge exists in the graph from parent node to child node
     * @param parent - the node that "edge" is coming from.
     * @param child - the node that "edge" is going to.
     * @param edge - edge from parent node to child node that we are checking for in the graph.
     * @return true is "edge" is in the graph, false otherwise.
     */
    public boolean edgeExists(String parent, String child, String edge) {
        if (this.graph.containsKey(parent)) {
            if (this.graph.get(parent).getEdges().get(child).contains(edge)) {
                return true;
            }
        }
        return false;
    }

    /** Return an alphabetically sorted list of all the Node name's child Nodes.
     *  If Node name does not exist, returns an empty list.
     * @param name - Name of parent Node to get list of children from.
     * @throws NullPointerException if input is null
     * @return Sorted list of node names.
     */
    public List<String> listChildren(String name) {
        Map<String, List<String>> list = this.graph.get(name).getEdges();
        ArrayList<String> nodeList = new ArrayList<String>();
        nodeList.addAll(this.graph.get(name).getEdges().keySet());
        nodeList.sort(String.CASE_INSENSITIVE_ORDER);
        return nodeList;
    }

    /** Return a map listing of all of the out edges and the Nodes they go to from a parent Node.
     *
     * @param name - Name of parent Node to get list of out edges from.
     * @spec.requires Node "name" exists in this graph.
     * @throws NullPointerException if input is null.
     * @return A Map with key node name, and value list edgelabels from Node name.
     */
    public Map<String, List<String>> listOutEdges(String name) {
        return this.graph.get(name).getEdges();
    }

    /** Returns a list of the names of all the nodes in the graph in alphabetic order.
     * @return An alphabetically sorted list of node names
     */
    public List<String> listNodes() {
        ArrayList<String> nodeList = new ArrayList<String>();
        nodeList.addAll(this.graph.keySet());
        nodeList.sort(String.CASE_INSENSITIVE_ORDER);
        return nodeList;
    }

    /** Removes all Nodes and edges from the Graph.
     * @spec.modifies this.graph
     * @spec.effects Removes all Nodes and edges from this.graph.
     */
    public void clear() {
        this.graph.clear();
    }

    /** Throws an exception if the representation invariant is violated. */
    private void checkRep() {

        if (DEBUG) {
            for (Node n : graph.values()) {
                // Check node is not null
                assert n != null;

                // Check node name is not null
                assert !(n.getName() == null || n.getName() == "");

                // Check unique edge labels
                for (List<String> edgeList : n.getEdges().values()) {
                    for (int edge1 = 0; edge1 < edgeList.size(); edge1++) {
                        for (int edge2 = edge1 + 1; edge2 < edgeList.size(); edge2++) {
                            assert edgeList.get(edge1) != edgeList.get(edge2);
                        }
                    }
                }

                // Check no edges if node count < 2
                if (this.listNodes().size() < 2) {
                    assert n.getEdges().size() == 0;
                }

                // Check each node name is unique
                ArrayList<String> tempList = new ArrayList<String>();
                tempList.addAll(this.graph.keySet());
                for (int node1 = 0; node1 < tempList.size(); node1++) {
                    for (int node2 = node1 + 1; node2 < tempList.size(); node2++) {
                        assert tempList.get(node1) != tempList.get(node2);
                    }
                }
            }
        }
    }

}
