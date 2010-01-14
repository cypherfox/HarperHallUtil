/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.5  2006/05/31 13:10:28  behnke_l
 * correct implementation an implement full tests for longValue. Some additional little tools
 *
 * Revision 1.4  2006/05/18 16:31:28  behnke_l
 * auf Shift und bit-mask umgestellt
 *
 * Revision 1.3  2006/05/16 18:11:36  behnke_l
 * lockdown
 *
 * Revision 1.2  2006/05/15 14:22:02  behnke_l
 * byteHelper tests and toByteArray function for ByteHelper
 *
 * Revision 1.1  2005/11/28 20:46:53  behnke_l
 * lockdown
 *
 */
package de.harper_hall.util.math;

public class ByteHelper {

  protected ByteHelper() {
  }

  /**
   * converts a unsigned byte value (represented as integer value) to signed byte value of same binary representation
   * returnes a signed byte value from an unsigned integer between 0 and 255
   * 
   * @param val
   *          integer value between 0 and 0xff to be converted
   * @return value between -128 and 127
   */
  public static byte byteToSigned(int val) {
    // CHECKSTYLE OFF MagicNumber
    if (val <= 127) { return (byte) val; }

    return (byte) ((128 - (val - 128)) * -1);
    // CHECKSTYLE ON MagicNumber
  }

  /**
   * convert a sequence of bytes to a integer value
   * 
   * @param arr
   * @param offset
   * @param lenght
   * @param littleEndian
   *          if true the values is taken to be represented as little endian, big endian if false
   * @return integer value
   */
  public static int intValue(byte[] arr, int offset, int length, boolean littleEndian) {
    int ret = 0, i, pos;

    final int maxLength = 4;

    if (length > maxLength) { throw new NumberFormatException("Max length for int value is 4 byte"); }

    if (littleEndian) {
      pos = offset;
      for (i = 0; i < length; i++, pos++) {
        // CHECKSTYLE OFF MagicNumber
        ret *= 256;
        // CHECKSTYLE ON MagicNumber
        ret += arr[pos];
      }
    } else {
      pos = offset + length - 1;
      for (i = 0; i < length; i++, pos--) {
        // CHECKSTYLE OFF MagicNumber
        ret *= 256;
        // CHECKSTYLE ON MagicNumber
        ret += arr[pos];
      }
    }

    return ret;
  }

  /**
   * convert a sequence of bytes to a integer value
   * 
   * @param arr
   * @param offset
   * @param lenght
   * @param littleEndian
   *          if true the values is taken to be represented as little endian, big endian if false
   * @return integer value
   */
  public static long longValue(byte[] arr, int offset, int length, boolean littleEndian) {
    int i, pos;
    long ret = 0;

    final int maxLength = 8;
    
    if (length > maxLength) {
      throw new NumberFormatException("Max length for long value is 8 byte");
    }

    if (littleEndian) {
      pos = offset + length - 1;
      for (i = 0; i < length; i++, pos--) {
        // CHECKSTYLE OFF MagicNumber
        long stepval = (long) (arr[pos] & 255) << (8 * i);
        // CHECKSTYLE ON MagicNumber

        ret += stepval;
      }
    } else {
      pos = offset;
      for (i = 0; i < length; i++, pos++) {
        // CHECKSTYLE OFF MagicNumber
        long stepval = (long) (arr[pos] & 255) << (8 * i);    
        // CHECKSTYLE ON MagicNumber

        ret += stepval;
      }
    }

    return ret;
  }

  /**
   * same as calling toByteArray(i,true)
   * 
   * @param i
   *          integer value
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(int i) {
    return toByteArray(i, true);
  }

  /**
   * @param i
   * @param littleEndian
   *          if set to true the representation will be little endian, big endian otherwise.
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(int i, boolean littleEndian) {
    // CHECKSTYLE OFF MagicNumber
    byte[] retval = new byte[4];
    if (littleEndian) {
      retval[0] = (byte) (i >>> 24);
      retval[1] = (byte) (i >>> 16);
      retval[2] = (byte) (i >>> 8);
      retval[3] = (byte) (i >>> 0);
    } else {
      retval[3] = (byte) (i >>> 24);
      retval[2] = (byte) (i >>> 16);
      retval[1] = (byte) (i >>> 8);
      retval[0] = (byte) (i >>> 0);
    }
    // CHECKSTYLE ON MagicNumber
    return retval;
  }

  /**
   * same as calling toByteArray(i,true)
   * 
   * @param i
   *          long value
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(long i) {
    return toByteArray(i, true);
  }

  /**
   * @param i
   * @param littleEndian
   *          if set to true the representation will be little endian, big endian otherwise.
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(long i, boolean littleEndian) {
    // CHECKSTYLE OFF MagicNumber
    byte[] retval = new byte[8];
    if (littleEndian) {
      retval[0] = (byte) (i >>> 56);
      retval[1] = (byte) (i >>> 48);
      retval[2] = (byte) (i >>> 40);
      retval[3] = (byte) (i >>> 32);
      retval[4] = (byte) (i >>> 24);
      retval[5] = (byte) (i >>> 16);
      retval[6] = (byte) (i >>> 8);
      retval[7] = (byte) (i >>> 0);
    } else {
      retval[7] = (byte) (i >>> 56);
      retval[6] = (byte) (i >>> 48);
      retval[5] = (byte) (i >>> 40);
      retval[4] = (byte) (i >>> 32);
      retval[3] = (byte) (i >>> 24);
      retval[2] = (byte) (i >>> 16);
      retval[1] = (byte) (i >>> 8);
      retval[0] = (byte) (i >>> 0);
    }
    // CHECKSTYLE OFF MagicNumber

    return retval;
  }

  /**
   * same as calling toByteArray(i,true)
   * 
   * @param i
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(byte i) {
    return toByteArray(i, true);
  }

  /**
   * @param i
   * @param littleEndian
   *          if set to true the representation will be little endian, big endian otherwise.
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(byte i, boolean littleEndian) {
    byte[] retval = new byte[1];
    retval[0] = i;
    return retval;
  }
}
