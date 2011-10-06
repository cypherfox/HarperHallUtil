package de.harper_hall.util.collections.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.harper_hall.util.collections.IntervalArrayHashMap;
import de.harper_hall.util.collections.IntervalMap;

/**
 * Generated code for the test suite <b>IntervalArrayHashMapTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/collections/tests/IntervalArrayHashMapTest.testsuite</i>.
 */
public class IntervalArrayHashMapTest {


  IntervalMap<Integer, String> map = new IntervalArrayHashMap<Integer, String>();

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Before
  public void setUp() throws Exception {
    // load some values
    map.clear();
    // CHECKSTYLE OFF MagicNumber
    map.put(1, "one");
    map.put(5, "five");
    map.put(10, "ten");
    // CHECKSTYLE ON

  }

  /**
   * testIntervalRetrieval
   * 
   * Try various keys when retrieving from the container.
   * 
   * @throws Exception
   */
  @Test
  public void testIntervalRetrieval() throws Exception {
    // CHECKSTYLE OFF MagicNumber
    assertEquals("five", map.get(2));
    assertEquals("one", map.get(0));
    map.setLastForBeyond(false);
    assertEquals(null, map.get(11));
    map.setLastForBeyond(true);
    assertEquals("ten", map.get(11));
    // CHECKSTYLE ON

  }

}
