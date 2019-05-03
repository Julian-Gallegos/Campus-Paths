package graph;

import java.util.List;

public class Graph {

    private List<Node> graph;

    /** Creates a new Graph with an empty graph list.
     * @spec.effects Constructs a new Graph g with g.graph.isEmpty() = true.
     */
    public Graph() { }

    /** Creates a new Graph with one node in the graph list.
     * @param node - The name of the Node being added to Graph.
     * @spec.requires Name of "node" is at least one character.
     * @spec.effects Constructs a new Graph g with Node "node" in g.graph.
     */
    public Graph(String node) { }

    /** Adds a new node to this Graph.
     * @param name - Name of the new node.
     * @spec.requires node has a unique name
     *                node character length less than 0.
     * @spec.modifies this.graph
     * @spec.effects Adds a new Node "node" to this.graph.
     */
    public void addNode(String name) {
        throw new RuntimeException("Graph.addNode() is not yet implemented");
    }

    /** Adds a new edge from one Node to another Node in Graph.
     * @param parent - Name of parent Node for the new edge.
     * @param child - Name of child Node for the new edge.
     * @param edge - Label for the new edge.
     * @spec.requires Node's a and b both exist
     *                an identical edge with a same label from Node a to Node b does not exist.
     * @spec.modifies this.graph
     * @spec.effects Add an edge to Node a that travels to the child Node b.
     */
    public void addEdge(String parent, String child, String edge) {
        throw new RuntimeException("Graph.addEdge() is not yet implemented");
    }

    /** Removes a node from the Graph.
     * @param name - The name of the node to remove.
     * @spec.requires node exists.
     * @spec.modifies this.graph
     * @spec.effects Removes the Node with name "name" from this.graph, as well as
     *               all of the edges going to and from it.
     */
    public void removeNode(String name) {
        throw new RuntimeException("Graph.removeNode() is not yet implemented");
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
        throw new RuntimeException("Graph.removeEdge() is not yet implemented");
    }

    /** Returns true if the specified node exists in the graph.
     * @param nodeName - Node that we are checking for in the graph.
     * @return true is "nodeName" is in the graph, false otherwise.
     */
    public boolean nodeExists(String nodeName) {
        throw new RuntimeException("Graph.nodeExists() is not yet implemented");
    }

    /** Returns true if the specified edge exists in the graph
     * @param parent - the node that "edge" is coming from.
     * @param child - the node that "edge" is going to.
     * @param edge - edge from parent node to child node that we are checking for in the graph.
     * @return true is "edge" is in the graph, false otherwise.
     */
    public boolean edgeExists(String parent, String child, String edge) {
        throw new RuntimeException("Graph.edgeExists() is not yet implemented");
    }

    /** Print a list of all Node a's child Nodes to console.
     * @param a - Parent Node to get list of children from.
     * @spec.requires Node a exists.
     */
    public void listChildren(Node a) {
        throw new RuntimeException("Graph.listChildren() is not yet implemented");
    }

    /** Print a list of all of the out edges and the Nodes they go to from a parent Node to console.
     * @param a - Parent Node to get list of out edges from.
     * @spec.requires Node a exists.
     */
    public void listOutEdges(Node a) {
        throw new RuntimeException("Graph.listOutEdges() is not yet implemented");
    }

    /** Print a list of all the Nodes in this Graph.
     */
    public void listNodes() {
        throw new RuntimeException("Graph.listNodes() is not yet implemented");
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

}
