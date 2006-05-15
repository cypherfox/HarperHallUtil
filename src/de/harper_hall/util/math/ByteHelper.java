/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.2  2006/05/15 14:22:02  behnke_l
 * byteHelper tests and toByteArray function for ByteHelper
 *
 * Revision 1.1  2005/11/28 20:46:53  behnke_l
 * lockdown
 *
 */
package de.harper_hall.util.math;

public class ByteHelper {

  /** converts a unsigned byte value (represented as integer value) to signed byte value of same binary representation
   * returnes a signed byte value from an unsigned integer between 0 and 255 
   * @param val integer value between 0 and 0xff to be converted
   * @return value between -128 and 127
   */
  public static byte byteToSigned(int val){
    if(val <= 127) return (byte)val;
    
    return (byte)((128-(val-128))*-1);
  }
  
  /**
   * same as calling toByteArray(i,true)
   * @param i integer value
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(int i){return toByteArray(i,true);}
  /**
   * @param i
   * @param little_endian if set to true the representation will be little endian, big endian otherwise.
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public static byte[] toByteArray(int i,boolean little_endian){
    byte[] retval = new byte[4];
    if(little_endian){
      retval[3]= Integer.valueOf(i%256).byteValue() ; i/=256;
      retval[2]= Integer.valueOf(i%256).byteValue() ; i/=256;
      retval[1]= Integer.valueOf(i%256).byteValue() ; i/=256;
      retval[0]= Integer.valueOf(i%256).byteValue() ;
    } else {
      retval[0]= Integer.valueOf(i%256).byteValue() ; i/=256;
      retval[1]= Integer.valueOf(i%256).byteValue() ; i/=256;
      retval[2]= Integer.valueOf(i%256).byteValue() ; i/=256;
      retval[3]= Integer.valueOf(i%256).byteValue() ;
    }
    return retval;
    }
  /**
   * same as calling toByteArray(i,true)
   * @param i long value
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public byte[] toByteArray(long i){
    return toByteArray(i,true);
    }
  /**
   * @param i
   * @param little_endian if set to true the representation will be little endian, big endian otherwise.
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public byte[] toByteArray(long i,boolean little_endian){
    byte[] retval = new byte[8];
    if(little_endian){
      retval[7]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[6]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[5]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[4]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[3]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[2]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[1]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[0]= Long.valueOf(i%256).byteValue() ;
    } else {
      retval[0]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[1]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[2]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[3]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[4]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[5]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[6]= Long.valueOf(i%256).byteValue() ; i/=256;
      retval[7]= Long.valueOf(i%256).byteValue() ;
    }
    return retval;
  }
  /**
   * same as calling toByteArray(i,true)
   * @param i
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public byte[] toByteArray(byte i){return null;}
  /**
   * @param i
   * @param little_endian if set to true the representation will be little endian, big endian otherwise.
   * @return a byte array containing the integer in little endian two-complement representation
   */
  public byte[] toByteArray(byte i,boolean little_endian){return null;}
}
