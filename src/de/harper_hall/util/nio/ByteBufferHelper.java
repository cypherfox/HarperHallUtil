/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.2  2005/11/22 19:36:37  behnke_l
 * lockdown
 *
 * Revision 1.1  2005/11/21 19:49:13  behnke_l
 * files added
 *
 */
package de.harper_hall.util.nio;

import java.nio.*;

public class ByteBufferHelper {
  
  /**
   * 
   * @param a
   * @param b
   * @param n number of byte to which compare the buffers
   * @return -1 is a is smaller; 0 if they are equal to ; +1 if a is larger than b 
   */
  public static  int compareBufferN(ByteBuffer a, ByteBuffer b, int n){
    int i, comp_n;
    ByteBuffer bt = b.slice();
    ByteBuffer at = a.slice();
    
    comp_n = Math.min(n, at.capacity());
    comp_n = Math.min(comp_n, bt.capacity());

    for(i=0;i<comp_n;i++){
      byte ab = at.get();
      byte bb = bt.get();
      if(ab < bb) return -1;
      if(ab > bb) return +1;
    }
    // ok, so far they are equal
    
    if(at.hasRemaining() && bt.hasRemaining()){
      
    }
      
    if((at.capacity() < bt.capacity()) && ( n > at.capacity())){
      return -1;
    } 
    if((bt.capacity() < at.capacity()) && ( n > bt.capacity())){
      return +1;
    }
    

    return 0;
  }

}
