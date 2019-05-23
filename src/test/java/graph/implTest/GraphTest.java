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

        Graph<String, String> newGraph2 = new Graph<>("node1");
        assertEquals(1, newGraph2.listNodes().size());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addNode
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddNode() {
        Graph<String, String> newGraph = new Graph<String, String>();

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));

        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
    }

    @Test
    public void testAddExistingNode() {
        Graph<String, String> newGraph = new Graph<String, String>();

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  nodeExists
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testNodeExists() {
        Graph<String, String> newGraph = new Graph<>();

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        assertFalse(newGraph.nodeExists("node-1"));

    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  edgeExists
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testEdgeExists() {
        Graph<String, String> newGraph = new Graph<>();

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));

        newGraph.addEdge("node1", "node2", "Love");
        assertTrue(newGraph.edgeExists("node1", "node2", "Love"));
        assertFalse(newGraph.edgeExists("node1", "node2", "Peace"));

    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  removeNode
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testRemoveNode() {
        Graph<String, String> newGraph = new Graph<>();
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
        assertEquals("[node2]", newGraph.listNodes().toString());
    }

    @Test
    public void testRemoveNodeWhenNoNode() {
        Graph<String, String> newGraph = new Graph<>();

        newGraph.removeNode("node1");
        assertFalse(newGraph.nodeExists("node1"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddEdge() {
        Graph<String, String> newGraph = new Graph<>();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));

        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));

        newGraph.addEdge("node1", "node2", "111");
        assertEquals("{node2=[123, 111]}", newGraph.listOutEdges("node1").toString());

        newGraph.addEdge("node1", "node2", "456");
        assertEquals("{node2=[123, 111, 456]}", newGraph.listOutEdges("node1").toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  removeEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testRemoveEdge() {
        Graph<String, String> newGraph = new Graph<>();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));
        newGraph.addEdge("node1", "node2", "456");
        assertEquals("{node2=[123, 456]}", newGraph.listOutEdges("node1").toString());

        newGraph.removeEdge("node1", "node2", "123");
        assertEquals("{node2=[456]}", newGraph.listOutEdges("node1").toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  listChildren
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testListChildren() {
        Graph<String, String> newGraph = new Graph<String, String>();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
        newGraph.addNode("node3");
        assertEquals(3, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node3"));
        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));
        newGraph.addEdge("node1", "node3", "123");
        assertTrue(newGraph.edgeExists("node1", "node3", "123"));

        assertEquals("[node3, node2]", newGraph.listChildren("node1").toString());

    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  listNodes
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testListNodes() {
        Graph<String, String> newGraph = new Graph<>();
        assertEquals("[]", newGraph.listNodes().toString());
        assertTrue(newGraph.listNodes().isEmpty());

        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());

        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());

        assertEquals("[node2, node1]", newGraph.listNodes().toString());

        assertTrue(newGraph.listNodes().contains("node1") && newGraph.listNodes().contains("node2"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  listOutEdges
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testListOutEdges() {
        Graph<String, String> newGraph = new Graph<>();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
        newGraph.addNode("node3");
        assertEquals(3, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node3"));
        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));
        newGraph.addEdge("node1", "node3", "123");
        assertTrue(newGraph.edgeExists("node1", "node3", "123"));
        newGraph.addEdge("node1", "node3", "123456");
        assertTrue(newGraph.edgeExists("node1", "node3", "123456"));

        assertEquals("{node3=[123, 123456], node2=[123]}", newGraph.listOutEdges("node1").toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  clear
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testClear() {
        Graph<String, String> newGraph = new Graph<>();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.listNodes().size());
        assertTrue(newGraph.nodeExists("node2"));
        newGraph.addEdge("node1", "node2", "123");
        assertTrue(newGraph.edgeExists("node1", "node2", "123"));
        newGraph.addEdge("node1", "node2", "456");
        assertEquals("{node2=[123, 456]}", newGraph.listOutEdges("node1").toString());

        newGraph.clear();
        assertEquals(0, newGraph.listNodes().size());
        assertFalse(newGraph.nodeExists("node1"));
        assertFalse(newGraph.nodeExists("node2"));
        assertFalse(newGraph.edgeExists("node1", "node2", "123"));
        assertFalse(newGraph.edgeExists("node1", "node2", "456"));

    }

}
