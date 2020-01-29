/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    private Graph<String> graphE = new ConcreteEdgesGraph();
    
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    /*
     * Testing strategy
     * 
     * Partition inputs:
     * L: empty, 1
     * add: new, duplicated
     * 
     */
    @Test
    public void testAddVertices() {
        Graph<String> graphV = new ConcreteVerticesGraph();
        graphV.add(new String("0"));
        assertTrue("expected added successfully", graphV.add(new String("1")));
        assertFalse("expected added failed", graphV.add(new String("1")));
        Set<String> set = new HashSet<>();
        set.add("0");
        set.add("1");
        assertEquals("expected added vertices set", set, graphV.vertices());
    }
    /*
     * Testing Strategy
     * 
     * set: add, change, remove, duplicated
     */
    @Test
    public void testSetVertices() {
        
    }
    /*
     * Testing strategy
     * 
     * remove successful, failed.
     */
    @Test
    public void testRemoveVertices() {
        Graph<String> graphV = new ConcreteVerticesGraph();
        graphV.add(new String("0"));
        graphV.add(new String("1"));
        graphV.add(new String("2"));
        graphV.add(new String("3"));
        
    }
}
