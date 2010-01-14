package de.harper_hall.util.math.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;

import de.harper_hall.util.math.CartesianVector;
import de.harper_hall.util.math.Vector;

/**
 * Generated code for the test suite <b>VectorTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/math/tests/VectorTest.testsuite</i>.
 *
 * Test the methods of the de.harper_hall.util.math.Vector class
 */
public class VectorTest extends HyadesTestCase {
  /**
   * Constructor for VectorTest.
   * @param name
   */
  public VectorTest(String name) {
    super(name);
  }

  /**
   * Returns the JUnit test suite that implements the <b>VectorTest</b>
   * definition.
   */
  public static Test suite() {
    HyadesTestSuite vectorTest = new HyadesTestSuite("VectorTest");
    vectorTest.setArbiter(DefaultTestArbiter.INSTANCE)
        .setId("FB58EDE596C3875D8184EC50582411DA");

    vectorTest.addTest(new VectorTest("testAdd")
        .setId("E664C69E32C04AB2CB95A5A0582411DA")
        .setTestInvocationId("E664C69E32C04AB201B033D0582511DA"));

    return vectorTest;
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
   * testAdd
   * @throws Exception
   */
  public void testAdd() throws Exception {
    // CHECKSTYLE OFF MagicNumber
    double[] initA = {1.0, 2.0, 3.0};
    double[] initB = {5.0, 6.0, 7.0};
    double[] resultArr = {6.0, 8.0, 10.0};

    int i; boolean exCaught = false;

    CartesianVector a = new CartesianVector(initA);
    Vector b = new CartesianVector(initB);
    CartesianVector result;
    Vector c = new CartesianVector(4);
    
    result = (CartesianVector) a.add(b);
    
    for (i = 0; i < 3; i++) {
      assertEquals(resultArr[i], result.getElement(i));
    }
    // CHECKSTYLE ON MagicNumber
    
    try {
      c.add(a);
    } catch (IndexOutOfBoundsException ex) {
      exCaught = true;
    }
    assertTrue("Exception not caught for addition with differing vector addition", exCaught);
    
  }

}