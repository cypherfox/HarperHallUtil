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
public class PseudoTime implements Comparable{

  protected long pte_start;
  protected long atomic_start;
  
  private PseudoTime() {
  }

  /**
   * @param atomic_start
   * @param pte_start
   */
  public PseudoTime(long atomic_start, long pte_start) {
    super();
    // TODO Auto-generated constructor stub
    this.atomic_start = atomic_start;
    this.pte_start = pte_start;
  }

  /**
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    try{
      PseudoTime pt_obj = (PseudoTime) obj;
      
      if(pt_obj.atomic_start != atomic_start) return false;

      if(pt_obj.pte_start != pte_start) return false;
      
    }catch (ClassCastException cce){return false;}

    return true;
  }

  /**
   * 
   * @param o
   * @return
   */
  public int compareTo(Object o) {
    PseudoTime pt_obj = (PseudoTime) o;

    if(pt_obj.pte_start > pte_start) return -1;
    if(pt_obj.pte_start < pte_start) return +1;

    if(pt_obj.atomic_start > atomic_start) return -1;
    if(pt_obj.atomic_start < atomic_start) return +1;

    return 0;
  }

  /** 
   * 
   * @param o
   * @return true if this object is less than o
   */
  
  public boolean lessThen(Object o){
    return (compareTo(o) == -1);
  }
}
