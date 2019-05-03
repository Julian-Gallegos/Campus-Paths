package graph.implTest;

import org.junit.Before;
import org.junit.Test;

import graph.*;

import org.junit.Rule;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public final class NodeTest {
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
        Node newNode = new Node("node1");
        assertNotNull(newNode);

        Node newNode1 = new Node("");
        assertNull(newNode1);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddEdge() {
        Node newNode = new Node("node1");
        assertNotNull(newNode);

        newNode.addEdge("node2", "123");
        assertEquals("node2(123)", newNode.getEdges().toString());
    }

    @Test
    public void testAddExistingEdge() {
        Node newNode = new Node("node1");
        assertNotNull(newNode);
        newNode.addEdge("node2", "123");
        assertEquals("node2(123)", newNode.getEdges().toString());

        newNode.addEdge("node2", "123");
        assertNotEquals("node2(123), node2(123)", newNode.getEdges().toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  removeEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testRemoveEdge() {
        Node newNode = new Node("node1");
        assertNotNull(newNode);
        newNode.addEdge("node2", "123");
        assertEquals("node2(123)", newNode.getEdges().toString());

        newNode.removeEdge("node2", "123");
        assertEquals("", newNode.getEdges().toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  getName
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testGetName() {
        Node newNode = new Node("node1");
        assertNotNull(newNode);
        assertEquals("node1", newNode.getName());
    }
}
