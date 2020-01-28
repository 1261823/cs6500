/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:30:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T12:30:00Z");
    
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "yunlong", "sb kakaf @alyssa", d3);
    private static final Tweet tweet4 = new Tweet(4, "aklius", "bb fuck kakaff semtna @alyssa", d4);
    private static final Tweet tweet5 = new Tweet(5, "sasaxb", "semtna @alyssa shijie@126.com", d4);
    
    
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    /*
     * Testing strategy
     * 
     * Partition the inputs as follows:
     * tweets.length(): 0, 1, 2, >2
     * 
     */
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        Timespan timespan2= Extract.getTimespan(new ArrayList<>());
        Timespan timespan3= Extract.getTimespan(Arrays.asList(tweet1,tweet2,tweet3));
        Timespan timespan1= Extract.getTimespan(Arrays.asList(tweet1));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
        assertEquals("expected null", null, timespan2);
        assertEquals("expected start", d1, timespan3.getStart());
        assertEquals("expected end", d3, timespan3.getEnd());
        assertEquals("expected start", d1, timespan1.getStart());
        assertEquals("expected end", d1, timespan1.getEnd());
        
        
    }
    /*
     * Testing strategy
     * 
     * Partition the input as follows:
     * tweets.length(): 0, >0
     * users in tweets: 0, >0
     * has duplicate ones, no duplicate ones
     * contain email address
     * 
     * Partition the output as follows:
     * mentionedUsers.length(): 0, >0
     * 
     * 
     */
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        Set<String> mentionedUsers0= Extract.getMentionedUsers(new ArrayList<>());
        Set<String> mentionedUsers1= Extract.getMentionedUsers(Arrays.asList(tweet1, tweet3));
        Set<String> mentionedUsers2= Extract.getMentionedUsers(Arrays.asList(tweet1, tweet3,tweet4));
        Set<String> mentionedUsers3= Extract.getMentionedUsers(Arrays.asList(tweet1, tweet3,tweet4,tweet5));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
        assertTrue("expected empty set", mentionedUsers0.isEmpty());
        Set<String> set = new HashSet<>();
        set.add("alyssa");
        assertEquals("expected mentioneduser", set, mentionedUsers1);
        assertEquals("expected mentioneduser", set, mentionedUsers2);
        assertEquals("expected mentioneduser", set, mentionedUsers3);
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
