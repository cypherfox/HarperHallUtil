package de.harper_hall.util.teatime.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;

/**
 * Generated code for the test suite <b>TeaTimeTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/teatime/tests/TeaTimeTest.testsuite</i>.
 *
 * Tests the correctness of a group of local TeaTime objects.
 */
public class TeaTimeTest extends HyadesTestCase {
  /**
   * Constructor for TeaTimeTest.
   * @param name
   */
  public TeaTimeTest(String name) {
    super(name);
  }

  /**
     * Returns the JUnit test suite that implements the <b>TeaTimeTest</b>
     * definition.
     */
    public static Test suite() {
      HyadesTestSuite teaTimeTest = new HyadesTestSuite("TeaTimeTest");
      teaTimeTest.setArbiter(DefaultTestArbiter.INSTANCE)
          .setId("D015C09FCB08BE91842FC3E091B211DA");
  
      teaTimeTest.addTest(new TeaTimeTest("bankTransaction1")
          .setId("D015C09FCB08BE918A96681091B211DA")
          .setTestInvocationId("D015C09FCB08BE91D91C55D091B211DA"));
  
      teaTimeTest.addTest(new TeaTimeTest("bankTransaction2")
          .setId("D015C09FCB08BE9193B2B66091B211DA")
          .setTestInvocationId("D015C09FCB08BE91DC8857A091B211DA"));
  
      return teaTimeTest;
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
  * bankTransaction1
  *
  * This will run a simple bank transfer operation
  * 
  * @throws Exception
  */
  public void bankTransaction1()
  throws Exception
  {
  // Enter your code here
  }

  /**
  * bankTransaction2
  *
  * This test will try to do a late read on an TObject and expects to catch a Exception
  * 
  * It will also test that the proper CommitRecord has been properly aborted.
  * 
  * @throws Exception
  */
  public void bankTransaction2()
  throws Exception
  {
  // Enter your code here
  }

}
