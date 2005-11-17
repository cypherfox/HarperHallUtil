/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 *
 * $Id$
 * 
 * $Log$
 * Revision 1.1  2005/11/17 23:22:04  behnke_l
 * files added
 *
 */
package de.harper_hall.util.math;

public class Vector{
  double[] values;
  
  public Vector(int dimension){
    if(dimension <1) throw new NegativeArraySizeException();
    values = new double[dimension];
  }

  /**
   * creates a 3D Vector
   *
   */
  public Vector(){
    values = new double[3];
  }
  
  /** Utility accessor
   * 
   * @return
   */
  public double getX(){
    return values[0];
  }
  
  /** Utility accessor
   * 
   * @return
   */
  public double getY()throws ArrayIndexOutOfBoundsException{
    return values[1];
  }
  
  /** Utility accessor
   * 
   * @return
   */
  public double getZ()throws ArrayIndexOutOfBoundsException{
    return values[1];
  }
}
