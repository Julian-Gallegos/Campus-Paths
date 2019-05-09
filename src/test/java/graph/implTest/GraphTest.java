package graph.implTest;

import org.junit.Before;
import org.junit.Test;

import graph.*;

import org.junit.Rule;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public final class GraphTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    /** checks that Java asserts are enabled, and exits if not */
    @Before
    public void testAssertsEnabled() {
        CheckAsserts.checkAssertsEnabled();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  Constructor
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testCtor() {
        Graph newGraph = new Graph();
        assertEquals(0, newGraph.listNodes().size());

        Graph newGraph2 = new Graph("node1");
        assertEquals(1, newGraph.listNodes().size());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addNode
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddNode() {
        Graph newGraph = new Graph();

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));

        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
    }

    @Test
    public void testAddExistingNode() {
        Graph newGraph = new Graph();

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
    }

    @Test
    public void testAddInvalidNodeName() {
        Graph newGraph = new Graph();

        newGraph.addNode("");
        assertEquals(0, newGraph.listNodes().size());
        assertFalse(newGraph.nodeExists(""));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  removeNode
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testRemoveNode() {
        Graph newGraph = new Graph();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));

        newGraph.removeNode("node1");
        assertFalse(newGraph.nodeExists("node1"));

        newGraph.addNode("node1");
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));

        newGraph.removeNode("node1");
        assertEquals("Nodes in graph: node2", newGraph.listNodes());
    }

    @Test
    public void testRemoveNodeWhenNoNode() {
        Graph newGraph = new Graph();

        newGraph.removeNode("node1");
        assertFalse(newGraph.nodeExists("node1"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddEdge() {
        Graph newGraph = new Graph();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));

        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));

        newGraph.addEdge("node1", "node2", "123");
        assertEquals("From node1: node2(123)", newGraph.listOutEdges("node1"));

        newGraph.addEdge("node1", "node2", "456");
        assertEquals("From node1: node2(123), node2(456)", newGraph.listOutEdges("node1"));
    }

    public void testAddInvalidEdgeLabel() {
        Graph newGraph = new Graph();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));

        newGraph.addEdge("node1", "node2", "");
        assertFalse(newGraph.edgeExists("node1", "node2", ""));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  removeEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testRemoveEdge() {
        Graph newGraph = new Graph();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));
        newGraph.addEdge("node1", "node2", "456");
        assertEquals("From node1: node2(123), node2(456)", newGraph.listOutEdges("node1"));

        newGraph.removeEdge("node1", "node2", "123");
        assertEquals("From node1: node2(456)", newGraph.listOutEdges("node1"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  clear
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testClear() {
        Graph newGraph = new Graph();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));
        newGraph.addEdge("node1", "node2", "456");
        assertEquals("From node1: node2(123), node2(456)", newGraph.listOutEdges("node1"));

        newGraph.clear();
        assertEquals(0, newGraph.listNodes().size());
        assertFalse(newGraph.nodeExists("node1"));
        assertFalse(newGraph.nodeExists("node2"));
        assertFalse(newGraph.edgeExists("node1", "node2", "123"));
        assertFalse(newGraph.edgeExists("node1", "node2", "456"));

    }

}
