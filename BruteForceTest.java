import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BruteForceTest.
 *
 * @author  Barry Brown
 * @version Oct 2, 2017
 */
public class BruteForceTest
{
    /**
     * Default constructor for test class BruteForceTest
     */
    public BruteForceTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testInit()
    {
        BruteForce b = new BruteForce();
        b.init(5);
        assertEquals("aaaaa", b.getCurrent());
        b.init(2);
        assertEquals("aa", b.getCurrent());
        b.init(0);
        assertEquals("", b.getCurrent());
    }
    
    @Test
    public void testIncrement()
    {
        BruteForce b = new BruteForce();
        b.init(2);
        b.increment();
        assertEquals("ab", b.getCurrent());
        b.increment();
        assertEquals("ac", b.getCurrent());
    }
    
    @Test
    public void testIncrementLength2()
    {
        BruteForce b = new BruteForce();
        b.init(2);
        // Increment 26 times
        for (int i = 0; i < 26; i++)
            b.increment();
        assertEquals("ba", b.getCurrent());
        
        // Increment 26 more times
        for (int i = 0; i < 26; i++)
            b.increment();
        assertEquals("ca", b.getCurrent());
    }
    
    @Test
    public void testIncrementLength3()
    {
        BruteForce b = new BruteForce();
        b.init(3);
        
        // Increment 26 times
        for (int i = 0; i < 26; i++)
            b.increment();
        assertEquals("aba", b.getCurrent());
        
        // Increment 26 more times
        for (int i = 0; i < 26; i++)
            b.increment();
        assertEquals("aca", b.getCurrent());
        
        // Increment 26*26 times
        for (int i = 0; i < 26*26; i++)
            b.increment();
        assertEquals("bca", b.getCurrent());
        
        // Reset back to "aaa"
        b.init(3);
        // Increment 26*26*26-1 times (almost all the way around)
        for (int i = 0; i < 26*26*26-1; i++)
            b.increment();
        assertEquals("zzz", b.getCurrent());
        
        // One more increment
        b.increment();
        assertEquals("aaa", b.getCurrent());
    }
    
    @Test
    public void testIncrementPos()
    {
        BruteForce b = new BruteForce();
        b.init(3);
        assertFalse(b.increment(2));
        assertEquals("aab", b.getCurrent());
        assertFalse(b.increment(1));
        assertEquals("abb", b.getCurrent());
        
        // Increment the rightmost character 24 more times
        for (int i = 0; i < 24; i++)
            b.increment(2);
        assertEquals("abz", b.getCurrent());
        assertTrue(b.increment(2));
        
        // Increment the middle character 24 more times
        for (int i = 0; i < 24; i++)
            b.increment(1);
        assertEquals("aza", b.getCurrent());
        assertTrue(b.increment(1));
        assertEquals("aaa", b.getCurrent());
    }
    
    
    @Test
    public void testAtEnd()
    {
        BruteForce b = new BruteForce();
        b.init(3);
        assertFalse(b.atEnd());
        // Increment 26*26*26-2 times (up to "zzy")
        for (int i = 0; i < 26*26*26-2; i++)
            b.increment();
        assertFalse(b.atEnd());
        b.increment();  // Now it should be "zzz"
        assertTrue(b.atEnd());
        b.increment();  // Rolls over to "aaa"
        assertFalse(b.atEnd());
    }
    
    @Test
    public void testInitPatternDigit()
    {
        BruteForce b = new BruteForce();
        b.init("ddd");
        assertEquals("000", b.getCurrent());
        
        b.init("ddddd");
        assertEquals("00000", b.getCurrent());
        
        b.init("d");
        assertEquals("0", b.getCurrent());
    }
    
    @Test
    public void testInitPatternLetter()
    {
        BruteForce b = new BruteForce();
        b.init("lll");
        assertEquals("aaa", b.getCurrent());
        
        b.init("l");
        assertEquals("a", b.getCurrent());
        
        b.init("lllll");
        assertEquals("aaaaa", b.getCurrent());
    }
    
    @Test
    public void testInitPatternVowel()
    {
        BruteForce b = new BruteForce();
        b.init("vvv");
        assertEquals("aaa", b.getCurrent());
        
        b.init("v");
        assertEquals("a", b.getCurrent());
        
        b.init("vvvvv");
        assertEquals("aaaaa", b.getCurrent());
    }
    
    @Test
    public void testInitPatternMixed()
    {
        BruteForce b = new BruteForce();
        b.init("dld");
        assertEquals("0a0", b.getCurrent());
        
        b.init("vvd");
        assertEquals("aa0", b.getCurrent());
        
        b.init("ddvdl");
        assertEquals("00a0a", b.getCurrent());
    }
    
    @Test
    public void testIncrementPatternLetter()
    {
        BruteForce b = new BruteForce();
        b.init("lll");
        b.increment();
        assertEquals("aab", b.getCurrent());
        for (int i = 0; i < 25; i++)
        {
            b.increment();
        }
        assertEquals("aba", b.getCurrent());
        
        b.increment();
        assertEquals("abb", b.getCurrent());
        for (int i = 0; i < 26; i++)
        {
            b.increment();
        }
        assertEquals("acb", b.getCurrent());
    }
    
    @Test
    public void testAtEndLetter()
    {
        BruteForce b = new BruteForce();
        b.init("lll");
        for (int i = 0; i < 26*26*26 - 1; i++)
        {
            assertFalse(b.atEnd());
            b.increment();
        }
        assertEquals("zzz", b.getCurrent());
        assertTrue(b.atEnd());
        b.increment();
        assertEquals("aaa", b.getCurrent());
    }
    
    @Test
    public void testIncrementPatternDigit()
    {
        BruteForce b = new BruteForce();
        b.init("ddd");
        b.increment();
        assertEquals("001", b.getCurrent());
        for (int i = 0; i < 8; i++)
        {
            b.increment();
        }
        assertEquals("009", b.getCurrent());
        
        b.increment();
        assertEquals("010", b.getCurrent());
        for (int i = 0; i < 10; i++)
        {
            b.increment();
        }
        assertEquals("020", b.getCurrent());
        for (int i = 0; i < 100; i++)
        {
            b.increment();
        }
        assertEquals("120", b.getCurrent());
    }
    
    @Test
    public void testAtEndDigit()
    {
        BruteForce b = new BruteForce();
        b.init("ddd");
        for (int i = 0; i < 10*10*10 - 1; i++)
        {
            assertFalse(b.atEnd());
            b.increment();
        }
        assertEquals("999", b.getCurrent());
        assertTrue(b.atEnd());
        b.increment();
        assertEquals("000", b.getCurrent());
    }
    
    @Test
    public void testIncrementPatternVowel()
    {
        BruteForce b = new BruteForce();
        b.init("vvv");
        b.increment();
        assertEquals("aae", b.getCurrent());
        b.increment();
        assertEquals("aai", b.getCurrent());
        b.increment();
        assertEquals("aao", b.getCurrent());
        b.increment();
        assertEquals("aau", b.getCurrent());
        b.increment();
        assertEquals("aea", b.getCurrent());

        for (int i = 0; i < 10; i++)
        {
            b.increment();
        }
        assertEquals("aoa", b.getCurrent());
    }
    
    @Test
    public void testAtEndVowel()
    {
        BruteForce b = new BruteForce();
        b.init("vvv");
        for (int i = 0; i < 5*5*5 - 1; i++)
        {
            assertFalse(b.atEnd());
            b.increment();
        }
        assertEquals("uuu", b.getCurrent());
        assertTrue(b.atEnd());
        b.increment();
        assertEquals("aaa", b.getCurrent());
    }
    
    @Test
    public void testIncrementMixed()
    {
        BruteForce b = new BruteForce();
        b.init("ldv");
        b.increment();
        assertEquals("a0e", b.getCurrent());
        b.increment();
        b.increment();
        b.increment();
        b.increment();
        assertEquals("a1a", b.getCurrent());
        for (int i = 0; i < 10*5; i++) b.increment();
        assertEquals("b1a", b.getCurrent());
        
        
    }
    
    @Test
    public void testAtEndMixed()
    {
        BruteForce b = new BruteForce();
        b.init("ldv");
        for (int i = 0; i < 26*10*5 - 1; i++) {
            assertFalse(b.atEnd());
            b.increment();
        }
        assertEquals("z9u", b.getCurrent());
        assertTrue(b.atEnd());
        b.increment();
        assertEquals("a0a", b.getCurrent());
    }
}