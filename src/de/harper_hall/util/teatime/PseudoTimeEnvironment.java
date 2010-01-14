/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.1  2006/05/03 17:19:45  behnke_l
 * junit-target in build.xml
 *
 */
package de.harper_hall.util.teatime;

public class PseudoTimeEnvironment {

  long start;
  long last;
  long deviance;
  
  public PseudoTimeEnvironment() {
    super();
    
    start = System.currentTimeMillis();
    last = 0;
    deviance = 0;
    
  }

  public synchronized PseudoTime getPseudoTime() {

    long now = System.currentTimeMillis();
    if (now == last) {
      deviance++; //adjust time, since we allready have generated value this tick
    } else { 
      last = now;
    }
    
    return new PseudoTime(start, last + deviance);
  }
}
