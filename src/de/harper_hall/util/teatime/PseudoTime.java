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

/**
 * 
 * @author sage
 */
public class PseudoTime implements Comparable {

  protected long pteStart;

  protected long atomicStart;

  private PseudoTime() {
  }

  /**
   * @param atomicStart
   * @param pteStart
   */
  public PseudoTime(long atomicStart, long pteStart) {
    super();
    // TODO Auto-generated constructor stub
    this.atomicStart = atomicStart;
    this.pteStart = pteStart;
  }

  /**
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    try {
      PseudoTime ptObj = (PseudoTime) obj;

      if (ptObj.atomicStart != atomicStart) { return false; }

      if (ptObj.pteStart != pteStart) { return false; }

    } catch (ClassCastException cce) {
      return false;
    }

    return true;
  }

  /**
   * 
   */
  @Override
  public int hashCode() {
    final int intLen = 31;
    
    return (int) ((pteStart * atomicStart) % (1L << intLen));

  }

  /**
   * 
   * @param o
   * @return
   */
  public int compareTo(Object o) {
    PseudoTime ptObj = (PseudoTime) o;

    if (ptObj.pteStart > pteStart) {  return -1; }
    if (ptObj.pteStart < pteStart) {  return +1; }

    if (ptObj.atomicStart > atomicStart) {  return -1; }
    if (ptObj.atomicStart < atomicStart) {  return +1; }

    return 0;
  }

  /**
   * 
   * @param o
   * @return true if this object is less than o
   */

  public boolean lessThen(Object o) {
    return (compareTo(o) == -1);
  }
}
