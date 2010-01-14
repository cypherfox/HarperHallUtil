/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.3  2005/11/28 20:46:53  behnke_l
 * lockdown
 *
 * Revision 1.2  2005/11/22 19:36:37  behnke_l
 * lockdown
 *
 * Revision 1.1  2005/11/21 19:49:13  behnke_l
 * files added
 *
 */
package de.harper_hall.util.nio;

import java.nio.ByteBuffer;

public class ByteBufferHelper {

  protected ByteBufferHelper() { }
  /**
   * 
   * @param a
   * @param b
   * @param n
   *          number of byte to which compare the buffers
   * @return -1 is a is smaller; 0 if they are equal to ; +1 if a is larger than b
   */
  public static int compareBufferN(ByteBuffer a, ByteBuffer b, int n) {
    int i, compN;
    ByteBuffer bt = b.slice();
    ByteBuffer at = a.slice();

    compN = Math.min(n, at.capacity());
    compN = Math.min(compN, bt.capacity());

    for (i = 0; i < compN; i++) {
      byte ab = at.get();
      byte bb = bt.get();
      if (ab < bb) { return -1; }
      if (ab > bb) { return +1; }
    }
    // ok, so far they are equal

    if (bt.capacity() < Math.min(at.capacity(), n)) {
      return +1;
    }
    if (at.capacity() < Math.min(bt.capacity(), n)) {
      return -1;
    }

    return 0;
  }

}
