package de.harper_hall.util.nio.tests;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;

import org.junit.Test;

import de.harper_hall.util.nio.ByteBufferHelper;

/**
 * Generated code for the test suite <b>ByteBufferHelperTest</b> located at
 * <i>/HarperHallUtil/src/de/harper_hall/util/nio/tests/ByteBufferHelperTest.testsuite</i>.
 */
public class ByteBufferHelperTest {

  /**
  * compareNTest
  * @throws Exception
  */
  @Test
  public void compareNTest()
  throws Exception {
    // CHECKSTYLE OFF MagicNumber
    byte[] a = { 0 , 1, 2, 3};      // base  
    byte[] b = { 0 , 1, 2};         // for n>3: -1
    byte[] c = { 0 , 1, 2, 3, 4};   // for n<=4: 0, else +1
    byte[] d = { 1, 2, 3};          // +1
    byte[] e = { -1, 0, 1, 2};      // -1

    ByteBuffer bufA = ByteBuffer.wrap(a);
    ByteBuffer bufB = ByteBuffer.wrap(b);
    ByteBuffer bufC = ByteBuffer.wrap(c);
    ByteBuffer bufD = ByteBuffer.wrap(d);
    ByteBuffer bufE = ByteBuffer.wrap(e);
    
    assertEquals(0, ByteBufferHelper.compareBufferN(bufA, bufB, 3));
    assertEquals(+1, ByteBufferHelper.compareBufferN(bufA, bufB, 4));
    assertEquals(+1, ByteBufferHelper.compareBufferN(bufA, bufB, 5));
    
    assertEquals(0, ByteBufferHelper.compareBufferN(bufA, bufC, 4));
    assertEquals(-1, ByteBufferHelper.compareBufferN(bufA, bufC, 5));
    assertEquals(-1, ByteBufferHelper.compareBufferN(bufA, bufC, 6));

    assertEquals(-1, ByteBufferHelper.compareBufferN(bufA, bufD, 3));
    assertEquals(-1, ByteBufferHelper.compareBufferN(bufA, bufD, 4));

    assertEquals(1, ByteBufferHelper.compareBufferN(bufA, bufE, 4));
    // CHECKSTYLE ON MagicNumber

  }
}