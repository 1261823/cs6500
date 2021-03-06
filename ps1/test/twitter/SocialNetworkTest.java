/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:30:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T12:30:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T13:00:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T11:40:00Z");
    
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "yunlong", "sb kakaf @alyssa", d3);
    private static final Tweet tweet4 = new Tweet(4, "aklius", "bb fuck kakaff semtna @yunlong", d4);
    private static final Tweet tweet5 = new Tweet(5, "sasaxb", "semtna @alyssa shijie@126.com", d5);
    private static final Tweet tweet6 = new Tweet(6, "yunlong", "sdtrxdv @bbitdiddle dongbala", d6);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
        
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    /*
     * Testing strategy
     * 
     * Partition the input: has follows, no follows
     * 
     */
    @Test
    public void testGuessFollowsGraph() {
        Map<String, Set<String>> followsGraph0 = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2));
        Map<String, Set<String>> followsGraph1 = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6));
        Map<String, Set<String>> map           = new HashMap<>();
        Map<String, Set<String>> map2          = new HashMap<>();
        
        map.put("yunlong", new HashSet<>(Arrays.asList("alyssa","bbitdiddle")));
        map.put("aklius", new HashSet<>(Arrays.asList("yunlong")));
        map.put("sasaxb", new HashSet<>(Arrays.asList("alyssa")));
        map.put("alyssa", new HashSet<>());
        map.put("bbitdiddle", new HashSet<>());
        map2.put("alyssa", new HashSet<>());
        map2.put("bbitdiddle", new HashSet<>());
        
        assertEquals("expected empty graph", map2, followsGraph0);
        assertEquals("expected graph", map, followsGraph1);
    }
    
    /*
     * Testing strategy
     * 
     * Partition the input:
     * followsgraph: no duplicate, has duplicate
     */
    
    @Test
    public void testInfluencers() {
        Map<String, Set<String>> followsGraph0 = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5));
        List<String> influencers0 = SocialNetwork.influencers(followsGraph0);
        
        Map<String, Set<String>> followsGraph1 = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6));
        List<String> influencers1 = SocialNetwork.influencers(followsGraph1);
        
        assertEquals("expected top 2", Arrays.asList("alyssa", "yunlong"), influencers0);
        assertEquals("expected top 2", Arrays.asList("alyssa","bbitdiddle", "yunlong"), influencers1);
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
