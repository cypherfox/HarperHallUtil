/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 *
 * $Id$
 * 
 * $Log$
 * Revision 1.2  2005/11/18 10:40:59  behnke_l
 * lockdown
 *
 * Revision 1.1  2005/11/17 23:22:04  behnke_l
 * files added
 *
 */
package de.harper_hall.util.math;

/** Mathematical vector package for arbitrary dimension
 * 
 * While some utility functions exist to aid the use in three dimensions,
 * the package supports arbitrary dimensions.
 * 
 * TODO: kreuzprodukt, normalisieren, basis-operation (add,sub), 
 *     skalar-product
 * 
 * @author sage
 *
 */
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
  
  /** Get the dimension of this vector
   * 
   * @return dimension of this vector
   */
  public int getDimension(){
	  return values.length;
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
  
  public Vector add(Vector a) throws IndexOutOfBoundsException {
	if (getDimension() != a.getDimension())
	   throw new IndexOutOfBoundsException("Dimension of vectors differ");
	  
	Vector retval = new Vector(getDimension());
	  
  }
  
  // TODO:Funktion die add implementiert mit vektor kleinerer
  // Dimension, mit int parameter um n Dimensionen verschoben
}
