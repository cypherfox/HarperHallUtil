/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
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
  public byte byteToSigned(int val){
    if(val <= 127) return (byte)val;
    
    return (byte)((128-(val-128))*-1);
  }
}
