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
        Node<String, String> newNode = new Node<>("node1");
        assertNotNull(newNode);
        assertEquals("node1", newNode.getName());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddEdge() {
        Node<String, String> newNode = new Node<>("node1");
        assertNotNull(newNode);

        newNode.addEdge("node2", "123");
        assertEquals("{node2=[123]}", newNode.getEdges().toString());
    }

    @Test
    public void testAddExistingEdge() {
        Node<String, String> newNode = new Node<>("node1");
        assertNotNull(newNode);
        newNode.addEdge("node2", "123");
        assertEquals("{node2=[123]}", newNode.getEdges().toString());

        newNode.addEdge("node2", "123");
        assertEquals("{node2=[123]}", newNode.getEdges().toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  removeEdge
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testRemoveEdge() {
        Node<String, String> newNode = new Node<>("node1");
        assertNotNull(newNode);
        newNode.addEdge("node2", "123");
        assertEquals("{node2=[123]}", newNode.getEdges().toString());

        newNode.removeEdge("node2", "123");
        assertEquals("{}", newNode.getEdges().toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  removeChild
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testRemoveChild() {
        Node<String, String> newNode = new Node<>("node1");
        assertNotNull(newNode);
        newNode.addEdge("node2", "123");
        assertEquals("{node2=[123]}", newNode.getEdges().toString());
        newNode.removeChild("node2");
        assertEquals("{}", newNode.getEdges().toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  getName
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testGetName() {
        Node<String, String> newNode = new Node<>("node1");
        assertNotNull(newNode);
        assertEquals("node1", newNode.getName());
    }
}
