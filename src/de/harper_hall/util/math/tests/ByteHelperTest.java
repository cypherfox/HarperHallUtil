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
   * @param name
   */
  public ByteHelperTest(String name) {
    super(name);
  }

  /**
     * Returns the JUnit test suite that implements the <b>ByteHelperTest</b>
     * definition.
     */
    public static Test suite() {
      HyadesTestSuite byteHelperTest = new HyadesTestSuite("ByteHelperTest");
      byteHelperTest.setArbiter(DefaultTestArbiter.INSTANCE)
          .setId("CA6FFE00591C665FA466AFE0E3E511DA");
  
      byteHelperTest.addTest(new ByteHelperTest("testByteToSigned")
          .setId("CA6FFE00591C665FA2A5FF30E40811DA")
          .setTestInvocationId("CA6FFE00591C665FB015C920E40811DA"));
  
      byteHelperTest.addTest(new ByteHelperTest("testToByteArray")
          .setId("CA6FFE00591C665F02A575C0E3FD11DA")
          .setTestInvocationId("CA6FFE00591C665F47A86420E3FD11DA"));
  
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

  class testval_record{
    public boolean little_endian;
    public byte[] correct;    
    public testval_record(boolean le, int size){
      little_endian=le; correct=new byte[size];
      
    }
  };
  
  class testval_record_int extends testval_record{
    public int val;
    public testval_record_int(int i, boolean le, int a, int b, int c, int d){
      super(le,4);
      correct[0]=(byte)a; correct[1]=(byte)b; correct[2]=(byte)c; correct[3]=(byte)d;
      val=i; 
    }
  };

  class testval_record_long extends testval_record{
    public long val;
    public testval_record_long(long i, boolean le, int a, int b, int c, int d, int e, int f, int g, int h){
      super(le,8);
      correct[0]=(byte)a; correct[1]=(byte)b; correct[2]=(byte)c; correct[3]=(byte)d;
      correct[4]=(byte)e; correct[5]=(byte)f; correct[6]=(byte)g; correct[7]=(byte)h;
      val=i; 
    }
  };

  private static testval_record[] test_vals=null;;
  
  private void init_testvals(){
    test_vals = new testval_record[10];                
    test_vals[0] = new testval_record_int(1,true,0,0,0,1);
    test_vals[1] = new testval_record_int(1,false,1,0,0,0);
    test_vals[2] = new testval_record_int(1+256+65536,true,0,1,1,1);
    test_vals[3] = new testval_record_int(1+256+65536,false,1,1,1,0);
    test_vals[4] = new testval_record_int(65535,true,0,0,255,255);

  };
  
  /**
  * testToByteArray
  * @throws Exception
  */
  public void testToByteArray()
  throws Exception {
    if(test_vals==null)init_testvals();
    int i;
    byte[] retval =null;
    
    for(i=0;i<test_vals.length;i++){
      if(test_vals[i]==null)continue;
      
      if(test_vals[i] instanceof testval_record_int)
//      if(testval_record_int.class.isInstance(instance test_vals[i])
          retval = ByteHelper.toByteArray(((testval_record_int)test_vals[i]).val,
                                          test_vals[i].little_endian);
      else
        throw new ClassCastException("unknown testval_record type");
      
      assertTrue(retval[0] == test_vals[i].correct[0] && 
                 retval[1] == test_vals[i].correct[1] &&
                 retval[2] == test_vals[i].correct[2] && 
                 retval[3] == test_vals[i].correct[3]);
    }
  }

  /**
  * testByteToSigned
  * @throws Exception
  */
  public void testByteToSigned()
  throws Exception
  {
    assertTrue("expected    0, was "+ ByteHelper.byteToSigned(   0),    0 == ByteHelper.byteToSigned(0));
    assertTrue("expected    1, was "+ ByteHelper.byteToSigned(   1),    1 == ByteHelper.byteToSigned(1));
    assertTrue("expected   -1, was "+ ByteHelper.byteToSigned(  -1),   -1 == ByteHelper.byteToSigned(-1));
    assertTrue("expected  127, was "+ ByteHelper.byteToSigned( 127),  127 == ByteHelper.byteToSigned(127));
    assertTrue("expected -127, was "+ ByteHelper.byteToSigned(-127), -127 == ByteHelper.byteToSigned(-127));
    assertTrue("expected -128, was "+ ByteHelper.byteToSigned( 128), -128 == ByteHelper.byteToSigned(128));
    assertTrue("expected   -1, was "+ ByteHelper.byteToSigned( 255),   -1 == ByteHelper.byteToSigned(255));
  }

}