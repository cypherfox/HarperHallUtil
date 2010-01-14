package de.harper_hall.util.math.tests;

import junit.framework.Test;

import org.eclipse.hyades.test.common.junit.DefaultTestArbiter;
import org.eclipse.hyades.test.common.junit.HyadesTestCase;
import org.eclipse.hyades.test.common.junit.HyadesTestSuite;

import de.harper_hall.util.math.ByteHelper;

/**
 * Generated code for the test suite <b>ByteHelperTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/math/tests/ByteHelperTest.testsuite</i>.
 */
public class ByteHelperTest extends HyadesTestCase {
  /**
   * Constructor for ByteHelperTest.
   * 
   * @param name
   */
  public ByteHelperTest(String name) {
    super(name);
  }

  /**
   * Returns the JUnit test suite that implements the <b>ByteHelperTest</b> definition.
   */
  public static Test suite() {
    HyadesTestSuite byteHelperTest = new HyadesTestSuite("ByteHelperTest");
    byteHelperTest.setArbiter(DefaultTestArbiter.INSTANCE).setId("CA6FFE00591C665FA466AFE0E3E511DA");

    byteHelperTest.addTest(new ByteHelperTest("testToByteArray").setId("CA6FFE00591C665F02A575C0E3FD11DA")
        .setTestInvocationId("DFEDB4C6012A56FC3C92CB50F08911DA"));

    byteHelperTest.addTest(new ByteHelperTest("testByteToSigned").setId("CA6FFE00591C665FB407EAE0E40811DA")
        .setTestInvocationId("DFEDB4C6012A56FC401143B0F08911DA"));

    byteHelperTest.addTest(new ByteHelperTest("testLongValueRanges").setId("DFEDB4C6012A56FC1B3031A0F08911DA")
        .setTestInvocationId("DFEDB4C6012A56FC42C73BF0F08911DA"));

    return byteHelperTest;
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

  class TestvalRecord {
    public boolean littleEndian;

    public byte[] correct;

    public TestvalRecord(boolean le, int size) {
      littleEndian = le;
      correct = new byte[size];

    }
  };

  class TestvalRecordInt extends TestvalRecord {
    public int val;

    public TestvalRecordInt(int i, boolean le, int a, int b, int c, int d) {
      // CHECKSTYLE OFF MagicNumber
      super(le, 4);
      correct[0] = (byte) a;
      correct[1] = (byte) b;
      correct[2] = (byte) c;
      correct[3] = (byte) d;      
      val = i;
      // CHECKSTYLE ON MagicNumber
    }
  };

  class TestvalRecordLong extends TestvalRecord {
    public long val;

    public TestvalRecordLong(long i, boolean le, int a, int b, int c, int d, int e, int f, int g, int h) {
      // CHECKSTYLE OFF MagicNumber
      super(le, 8);
      correct[0] = (byte) a;
      correct[1] = (byte) b;
      correct[2] = (byte) c;
      correct[3] = (byte) d;
      correct[4] = (byte) e;
      correct[5] = (byte) f;
      correct[6] = (byte) g;
      correct[7] = (byte) h;
      val = i;
      // CHECKSTYLE ON MagicNumber
    }
  };

  private static TestvalRecord[] testVals = null;;

  private void init_testvals() {
    // CHECKSTYLE OFF MagicNumber
    testVals = new TestvalRecord[10];
    testVals[0] = new TestvalRecordInt(1, true, 0, 0, 0, 1);
    testVals[1] = new TestvalRecordInt(1, false, 1, 0, 0, 0);
    testVals[2] = new TestvalRecordInt(1 + 256 + 65536, true, 0, 1, 1, 1);
    testVals[3] = new TestvalRecordInt(1 + 256 + 65536, false, 1, 1, 1, 0);
    testVals[4] = new TestvalRecordInt(65535, true, 0, 0, 255, 255);
    // CHECKSTYLE ON MagicNumber

  };

  /**
   * testToByteArray
   * 
   * @throws Exception
   */
  public void testToByteArray() throws Exception {
    if (testVals == null) { init_testvals(); }
    int i;
    byte[] retval = null;

    for (i = 0; i < testVals.length; i++) {
      if (testVals[i] == null) { continue; }

      if (testVals[i] instanceof TestvalRecordInt) {
        // if(testval_record_int.class.isInstance(instance test_vals[i])
        retval = ByteHelper.toByteArray(((TestvalRecordInt) testVals[i]).val, testVals[i].littleEndian);
      } else {
        throw new ClassCastException("unknown testval_record type");
      }

      // CHECKSTYLE OFF MagicNumber
      assertTrue(retval[0] == testVals[i].correct[0] && retval[1] == testVals[i].correct[1]
          && retval[2] == testVals[i].correct[2] && retval[3] == testVals[i].correct[3]);
      // CHECKSTYLE ON MagicNumber
    }
  }

  /**
   * testByteToSigned
   * 
   * @throws Exception
   */
  public void testByteToSigned() throws Exception {
    // CHECKSTYLE OFF MagicNumber
    assertTrue("expected    0, was " + ByteHelper.byteToSigned(0), 0 == ByteHelper.byteToSigned(0));
    assertTrue("expected    1, was " + ByteHelper.byteToSigned(1), 1 == ByteHelper.byteToSigned(1));
    assertTrue("expected   -1, was " + ByteHelper.byteToSigned(-1), -1 == ByteHelper.byteToSigned(-1));
    assertTrue("expected  127, was " + ByteHelper.byteToSigned(127), 127 == ByteHelper.byteToSigned(127));
    assertTrue("expected -127, was " + ByteHelper.byteToSigned(-127), -127 == ByteHelper.byteToSigned(-127));
    assertTrue("expected -128, was " + ByteHelper.byteToSigned(128), -128 == ByteHelper.byteToSigned(128));
    assertTrue("expected   -1, was " + ByteHelper.byteToSigned(255), -1 == ByteHelper.byteToSigned(255));
    // CHECKSTYLE ON MagicNumber
  }

  /**
   * testLongValueRanges
   * 
   * @throws Exception
   */
  public void testLongValueRanges() throws Exception {
    // CHECKSTYLE OFF MagicNumber
    byte[] valArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    long retval;

    retval = ByteHelper.longValue(valArr, 0, 1, true);
    assertEquals("Incorrect value", 1, retval);

    retval = ByteHelper.longValue(valArr, 0, 1, false);
    assertEquals("Incorrect value", 1, retval);

    retval = ByteHelper.longValue(valArr, 0, 2, false);
    assertEquals("Incorrect value", 1 + (256 * 2), retval);

    retval = ByteHelper.longValue(valArr, 0, 2, true);
    assertEquals("Incorrect value", (256 * 1) + 2, retval);

    retval = ByteHelper.longValue(valArr, 0, 3, false);
    assertEquals("Incorrect value", 1 + (256 * 2) + (256 * 256 * 3), retval);

    retval = ByteHelper.longValue(valArr, 0, 3, true);
    assertEquals("Incorrect value", (256 * 256 * 1) + (256 * 2) + 3, retval);

    retval = ByteHelper.longValue(valArr, 0, 4, false);
    assertEquals("Incorrect value", 1 + (256 * 2) + (256 * 256 * 3) + (256 * 256 * 256 * 4), retval);

    retval = ByteHelper.longValue(valArr, 0, 4, true);
    assertEquals("Incorrect value", (256 * 256 * 256 * 1) + (256 * 256 * 2) + (256 * 3) + 4, retval);

    retval = ByteHelper.longValue(valArr, 0, 5, false);
    assertEquals("Incorrect value",
                 (long) (1 + (256 * 2) + (256 * 256 * 3) + (256 * 256 * 256 * 4) + ((long) 256 * 256 * 256 * 256 * 5)),
                 retval);

    retval = ByteHelper.longValue(valArr, 0, 5, true);
    assertEquals("Incorrect value", (long) ((long) 256 * 256 * 256 * 256 * 1) + (256 * 256 * 256 * 2) + (256 * 256 * 3)
        + (256 * 4) + 5, retval);

    retval = ByteHelper.longValue(valArr, 0, 8, false);
    assertEquals("Incorrect value", (long) 578437695752307201L, retval);

    retval = ByteHelper.longValue(valArr, 0, 8, true);
    assertEquals("Incorrect value", 72623859790382856L, retval);

    try {
      retval = ByteHelper.longValue(valArr, 0, 9, false);
      fail("longValue should only accept a maximum lenght of 8");
    } catch (NumberFormatException nfe) {
    }

    try {
      retval = ByteHelper.longValue(valArr, 0, 9, true);
      fail("longValue should only accept a maximum lenght of 8");
    } catch (NumberFormatException nfe) {
    }
    // CHECKSTYLE ON MagicNumber

  }

}
