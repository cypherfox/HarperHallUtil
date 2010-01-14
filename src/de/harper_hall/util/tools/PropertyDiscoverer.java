/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.1  2005/12/01 17:05:22  behnke_l
 * lockdown
 *
 */
package de.harper_hall.util.tools;

import java.util.Iterator;

public class PropertyDiscoverer {

  protected PropertyDiscoverer() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Iterator iter = System.getProperties().keySet().iterator();

    while (iter.hasNext()) {
      String next = (String) iter.next();
      System.out.println(next + ":" + System.getProperties().getProperty(next));
    }

  }

}
