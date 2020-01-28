/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /*
     * Testing Strategy
     * 
     * Partition the input:
     * tweets.length: 0, >0
     * username: "", non-empty
     * tweets: contains username, not contain
     */
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        List<Tweet> writtenBy0= Filter.writtenBy(Arrays.asList(tweet1, tweet2), "");
        List<Tweet> writtenByn= Filter.writtenBy(Arrays.asList(), "alyssa");
        List<Tweet> writtenBy1= Filter.writtenBy(Arrays.asList(), "");
        List<Tweet> writtenBy2= Filter.writtenBy(Arrays.asList(tweet1, tweet2), "Markal");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertEquals("expected empty list", 0, writtenBy0.size());
        assertEquals("expected empty list", 0, writtenBy1.size());
        assertEquals("expected empty list", 0, writtenByn.size());
        assertEquals("expected empty list", 0, writtenBy2 .size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    
    /*
     * Testing strategy
     * 
     * Partition the input:
     * tweets: empty, non-empty
     * timespan: 0, >0
     * tweets-timespan: contain, part-contain, not-contain
     */
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        Instant testEnd2= Instant.parse("2016-02-17T10:30:00Z");
        
        List<Tweet> inTimespan  = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        List<Tweet> inTimespan0 = Filter.inTimespan(Arrays.asList(), new Timespan(testStart, testEnd));
        List<Tweet> inTimespan1 = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testStart));
        List<Tweet> inTimespan2 = Filter.inTimespan(Arrays.asList(), new Timespan(testStart, testStart));
        List<Tweet> inTimespan3 = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd2));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
        assertTrue("expected empty list", inTimespan0.isEmpty());
        assertTrue("expected empty list", inTimespan1.isEmpty());
        assertTrue("expected empty list", inTimespan2.isEmpty());
        assertFalse("expected non-empty list", inTimespan3.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan3.containsAll(Arrays.asList(tweet1)));
        
    }
    /*
     * Testing strategy
     * 
     * Partition the input:
     * tweets: empty, non-empty
     * words: empty, non-empty
     * tweets-words: contain, not contain, part-contain
     */
    @Test
    public void testContaining() {
        List<Tweet> containing  = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        List<Tweet> containing0 = Filter.containing(Arrays.asList(), Arrays.asList("talk"));
        List<Tweet> containing1 = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList());
        List<Tweet> containing2 = Filter.containing(Arrays.asList(), Arrays.asList());
        List<Tweet> containing3 = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("reasonable"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));
        assertTrue("expected empty list", containing0.isEmpty());
        assertTrue("expected empty list", containing1.isEmpty());
        assertTrue("expected empty list", containing2.isEmpty());
        assertFalse("expected non-empty list", containing3.isEmpty());
        assertTrue("expected list to contain tweets", containing3.containsAll(Arrays.asList(tweet1)));
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
