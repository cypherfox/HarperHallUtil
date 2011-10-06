package de.harper_hall.util.collections.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.harper_hall.util.collections.MultiHashMap;

/**
 * Generated code for the test suite <b>MultiHashMapTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/collections/tests/MultiHashMapTest.testsuite</i>.
 * 
 * Test the basic behavior of the MultiHashMap
 */
public class MultiHashMapTest {

  /**
   * SingleAddRemove
   * 
   * @throws Exception
   */
  @Test
  public void singleAddRemove() throws Exception {
    MultiHashMap<String, String> mmap = new MultiHashMap<String, String>();
    //System.err.println("Runnng MultiHashMapTest");

    mmap.put("1", "one");
    mmap.put("2", "two");
    mmap.put("3", "three");

    // CHECKSTYLE OFF MagicNumber
    assertEquals(mmap.size(), 3);
    // CHECKSTYLE ON

    mmap.remove("3");
    assertEquals(mmap.size(), 2);

  }

  /**
   * MultiAddRemove
   * 
   * @throws Exception
   */
  @Test
  public void multiAddRemove() throws Exception {
    MultiHashMap<String, String> mmap = new MultiHashMap<String, String>();

    mmap.put("1", "one");
    mmap.put("2", "two");
    mmap.put("2", "zwo");
    mmap.put("2", "zwei");
    mmap.put("3", "three");

    // CHECKSTYLE OFF MagicNumber
    assertEquals(3, mmap.size());
    assertEquals(5, mmap.totalSize());
    mmap.remove("3");
    assertEquals(2, mmap.size());
    mmap.remove("2", "two");
    assertEquals(2, mmap.size());
    assertEquals(3, mmap.totalSize());
    // CHECKSTYLE ON
  }
}