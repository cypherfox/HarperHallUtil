package de.harper_hall.util.nio.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;

/**
 * Generated code for the test suite <b>ByteBufferHelperTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/nio/tests/ByteBufferHelperTest.testsuite</i>.
 */
public class ByteBufferHelperTest extends HyadesTestCase {
  /**
   * Constructor for ByteBufferHelperTest.
   * @param name
   */
  public ByteBufferHelperTest(String name) {
    super(name);
  }

  /**
     * Returns the JUnit test suite that implements the <b>ByteBufferHelperTest</b>
     * definition.
     */
    public static Test suite() {
      HyadesTestSuite byteBufferHelperTest = new HyadesTestSuite(
                                                                 "ByteBufferHelperTest");
      byteBufferHelperTest.setArbiter(DefaultTestArbiter.INSTANCE)
          .setId("E1429B574E48F49EB579EFB05AC711DA");
  
      byteBufferHelperTest.addTest(new ByteBufferHelperTest("compareNTest")
          .setId("E1429B574E48F49EBAF019605AC711DA")
          .setTestInvocationId("E1429B574E48F49ED1CB63105AC711DA"));
  
      return byteBufferHelperTest;
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
  * compareNTest
  * @throws Exception
  */
  public void compareNTest()
  throws Exception
  {
  // Enter your code here
  }
}