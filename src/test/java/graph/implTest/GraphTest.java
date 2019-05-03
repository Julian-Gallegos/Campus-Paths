package graph.implTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import graph.*;

import org.junit.Rule;
import org.junit.rules.Timeout;

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
        assertEquals(0, newGraph.nodeCount());

        Graph newGraph2 = new Graph("node1");
        assertEquals(1, newGraph.nodeCount());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addNode
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddNode() {
        Graph newGraph = new Graph();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.nodeCount());
        assertEquals(true, newGraph.nodeExists("node1"));

        newGraph.addNode("node2");
        assertEquals(2, newGraph.nodeCount());
        assertEquals(true, newGraph.nodeExists("node2"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddEdge() {
        Graph newGraph = new Graph();
        newGraph.addNode("node1");
        assertEquals(1, newGraph.nodeCount());
        assertEquals(true, newGraph.nodeExists("node1"));
        newGraph.addNode("node2");
        assertEquals(2, newGraph.nodeCount());
        assertEquals(true, newGraph.nodeExists("node2"));
        newGraph.addEdge("node1", "node2", "123");
        assertEquals("123", newGraph.edgeExists("node1", "node2", "123"));
        newGraph.addEdge("node1", "node2", "123");
        assertTrue("Edges from a specifc node to another specifc node each have unique label.", trueasdas);
    }
}
