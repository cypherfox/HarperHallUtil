package de.harper_hall.util.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;
import de.harper_hall.util.collections.tests.MultiHashMapTest;
import de.harper_hall.util.math.tests.VectorTest;
import de.harper_hall.util.nio.tests.ByteBufferHelperTest;

/**
 * Generated code for the test suite <b>AllOfHarperHallUtilTests</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/tests/AllOfHarperHallUtilTests.testsuite</i>.
 *
 * Alle Tests in HarperHallUtil
 */
public class AllOfHarperHallUtilTests extends HyadesTestCase {
  /**
   * Constructor for AllOfHarperHallUtilTests.
   * @param name
   */
  public AllOfHarperHallUtilTests(String name) {
    super(name);
  }

  /**
     * Returns the JUnit test suite that implements the <b>AllOfHarperHallUtilTests</b>
     * definition.
     */
    public static Test suite() {
      HyadesTestSuite allOfHarperHallUtilTests = new HyadesTestSuite(
                                                                     "AllOfHarperHallUtilTests");
      allOfHarperHallUtilTests.setArbiter(DefaultTestArbiter.INSTANCE)
          .setId("E999C5CEB9783A2E5A0C4BD05CCC11DA");
  
      allOfHarperHallUtilTests.addTest(((HyadesTestSuite) MultiHashMapTest
          .suite()).setTestInvocationId("E999C5CEB9783A2E6CF2DBB05CCC11DA"));
  
      allOfHarperHallUtilTests.addTest(((HyadesTestSuite) VectorTest.suite())
          .setTestInvocationId("E999C5CEB9783A2E773114C05CCC11DA"));
  
      allOfHarperHallUtilTests.addTest(((HyadesTestSuite) ByteBufferHelperTest
          .suite()).setTestInvocationId("E999C5CEB9783A2E8533CFE05CCC11DA"));
  
      return allOfHarperHallUtilTests;
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
}