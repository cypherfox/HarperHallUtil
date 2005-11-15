package de.harper_hall.util.collections.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;

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
  // Enter your code here
  }

  /**
  * MultiAddRemove
  * @throws Exception
  */
  public void multiAddRemove()
  throws Exception
  {
  // Enter your code here
  }
}