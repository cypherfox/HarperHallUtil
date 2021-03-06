package de.harper_hall.util.math.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.harper_hall.util.math.CartesianVector;
import de.harper_hall.util.math.Vector;

/**
 * Generated code for the test suite <b>VectorTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/math/tests/VectorTest.testsuite</i>.
 *
 * Test the methods of the de.harper_hall.util.math.Vector class
 */
public class VectorTest {
  /**
   * testAdd
   * @throws Exception
   */
  @Test
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
      assertEquals(resultArr[i], result.getElement(i), 0.0);
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