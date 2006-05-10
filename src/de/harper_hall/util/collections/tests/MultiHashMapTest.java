package de.harper_hall.util.collections.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;

import de.harper_hall.util.collections.MultiHashMap;

/**
 * Generated code for the test suite <b>MultiHashMapTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/collections/tests/MultiHashMapTest.testsuite</i>.
 *
 * Test the basic behavior of the MultiHashMap
 */
public class MultiHashMapTest extends HyadesTestCase {
  /**
   * Constructor for MultiHashMapTest.
   * @param name
   */
  public MultiHashMapTest(String name) {
    super(name);
  }

  /**
     * Returns the JUnit test suite that implements the <b>MultiHashMapTest</b>
     * definition.
     */
    public static Test suite() {
      HyadesTestSuite multiHashMapTest = new HyadesTestSuite("MultiHashMapTest");
      multiHashMapTest.setArbiter(DefaultTestArbiter.INSTANCE)
          .setId("EF13B452B7A0818D358F1850560011DA");
  
      multiHashMapTest.addTest(new MultiHashMapTest("singleAddRemove")
          .setId("EF13B452B7A0818D43DD3450560011DA")
          .setTestInvocationId("EF13B452B7A0818D56A89B10560011DA"));
  
      multiHashMapTest.addTest(new MultiHashMapTest("multiAddRemove")
          .setId("EF13B452B7A0818D49934D30560011DA")
          .setTestInvocationId("EF13B452B7A0818D5A421580560011DA"));
  
      return multiHashMapTest;
    }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception {
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
  }

  /**
  * SingleAddRemove
  * @throws Exception
  */
  public void singleAddRemove()
  throws Exception
  {
    MultiHashMap<String,String> mmap = new MultiHashMap<String,String>();
    System.err.println("Runnng MultiHashMapTest");
    
    mmap.put("1","one");
    mmap.put("2","two");
    mmap.put("3","three");
    
    assertEquals(mmap.size(),3);
    
    mmap.remove("3");
    assertEquals(mmap.size(),2);

  }

  /**
  * MultiAddRemove
  * @throws Exception
  */
  public void multiAddRemove()
  throws Exception
  {
    MultiHashMap<String,String> mmap = new MultiHashMap<String,String>();
    
    mmap.put("1","one");
    mmap.put("2","two");
    mmap.put("2","zwo");
    mmap.put("2","zwei");
    mmap.put("3","three");
    
    assertEquals(3,mmap.size());
    assertEquals(5,mmap.totalSize());
    mmap.remove("3");
    assertEquals(2,mmap.size());
    mmap.remove("2","two");
    assertEquals(2,mmap.size());
    assertEquals(3,mmap.totalSize());
  }
}