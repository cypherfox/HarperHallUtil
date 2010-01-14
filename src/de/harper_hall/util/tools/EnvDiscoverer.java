/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.1  2006/05/31 13:10:28  behnke_l
 * correct implementation an implement full tests for longValue. Some additional little tools
 *
 */
package de.harper_hall.util.tools;

import java.util.Iterator;

/**
 * @author sage
 */
public class EnvDiscoverer {

  protected EnvDiscoverer() { }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    Iterator iter = System.getenv().keySet().iterator();

    while (iter.hasNext()) {
      String next = (String) iter.next();
      System.out.println(next + ":" + System.getenv(next));
    }
  }
}
