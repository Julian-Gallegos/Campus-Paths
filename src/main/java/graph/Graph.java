package graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Graph {

    /**
     * Representation Invariant:
     *
     *
     *
     */

    private Hashtable<String, Node> graph;

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
    }

    /** Adds a new node to this Graph.
     * @param name - Name of the new node.
     * @spec.requires node has a unique name
     * @spec.modifies this.graph
     * @spec.effects Adds a new Node "node" to this.graph.
     * @throws RuntimeException if input is null or a zero length string.
     */
    public void addNode(String name) {
        graph.put(name, new Node(name));
    }

    /** Adds a new edge from one Node to another Node in Graph.
     * @param parent - Name of parent Node for the new edge.
     * @param child - Name of child Node for the new edge.
     * @param edge - Label for the new edge.
     * @spec.requires Nodes parent and child both exist
     * @thorws RuntimeException if edge already exists between parent and child.
     * @spec.modifies this.graph
     * @spec.effects Add an edge to Node a that travels to the child Node b.
     */
    public void addEdge(String parent, String child, String edge) {
        this.graph.get(parent).addEdge(child, edge);
;    }

    /** Removes a node from the Graph.
     * @param name - The name of the node to remove.
     * @spec.requires node "name" exists.
     * @throws RuntimeException if input is null.
     * @spec.modifies this.graph
     * @spec.effects Removes the Node with name "name" from this.graph, as well as
     *               all of the edges going to and from it.
     */
    public void removeNode(String name) {
        if (name == null) {
            throw new RuntimeException("input must not be null");
        }
        this.graph.remove(name);
    }

    /** Removes an edge from the Graph.
     * @param parent - Name of the parent node that the edge is coming from.
     * @param child - Name of the child node that the edge is going to.
     * @param edge  - Label of the edge going from parent to child.
     * @spec.requires parent, child, and edge exists.
     * @spec.modifies this.graph
     * @spec.effects Removes the edge with label "edge" coming from parent
     *               to child in this.graph.
     */
    public void removeEdge(String parent, String child, String edge) {
        this.graph.get(parent).removeEdge(child, edge);
    }

    /** Returns true if the specified node exists in the graph.
     * @param nodeName - Node that we are checking for in the graph.
     * @return true is "nodeName" is in the graph, false otherwise.
     */
    public boolean nodeExists(String nodeName) {
        if (this.graph.containsKey(nodeName)) {
            return true;
        }
        return false;
    }

    /** Returns true if the specified edge exists in the graph
     * @param parent - the node that "edge" is coming from.
     * @param child - the node that "edge" is going to.
     * @param edge - edge from parent node to child node that we are checking for in the graph.
     * @return true is "edge" is in the graph, false otherwise.
     */
    public boolean edgeExists(String parent, String child, String edge) {
        if (this.graph.get(parent).getEdges().get(child).contains(edge)) {
            return true;
        }
        return false;
    }

    /** Return a string listing all of Node name's child Nodes.
     * @param name - Name of parent Node to get list of children from.
     * @spec.requires Node a exists.
     * @return The string representation of child node.
     */
    public List<String> listChildren(String name) {
        ArrayList<String> nodeList = new ArrayList<String>();
        nodeList.addAll(this.graph.get(name).getEdges().keySet());
        return nodeList;
    }

    /** Return a string listing all of the out edges and the Nodes they go to from a parent Node.
     * @param name - Name of parent Node to get list of out edges from.
     * @spec.requires Node a exists.
     * @return The string representation of each out edge.
     */
    public String listOutEdges(String name) {
        return this.graph.get(name).getEdges();
        throw new RuntimeException("Graph.listOutEdges() is not yet implemented");
    }

    /** Returns a string listing all of the nodes in the graph.
     * @return The string representation of each node in the graph.
     */
    public List<String> listNodes() {
        ArrayList<String> nodeList = new ArrayList<String>();
        for (Node node : this.graph.values()) {
            nodeList.add(node.getName());
        }
        return nodeList;
    }

    /** Removes all Nodes and edges from the Graph.
     * @spec.modifies this.graph
     * @spec.effects Removes all Nodes and edges from this.graph.
     */
    public void clear() {
        throw new RuntimeException("Graph.clear() is not yet implemented");
    }

    /** Returns an int count of the number of nodes in graph.
     * @return an int representing number of nodes in this.graph.
     */
    public int nodeCount() {
        throw new RuntimeException("Graph.nodeCount() is not yet implemented");
    }

    private void checkRep() { throw new RuntimeException("Graph.checkRep() is not yet implemented"); }

}
