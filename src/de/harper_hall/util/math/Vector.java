/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 *
 * $Id$
 * 
 * $Log$
 * Revision 1.3  2005/11/18 18:02:45  behnke_l
 * lockdown
 *
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
 * TODO: normalisieren, basis-operation (sub), 
 *        
 * TODO:  Funktion die add implementiert mit vektor kleinerer
 *        Dimension, mit int parameter um n Dimensionen verschoben
 * 
 * @author sage
 *
 */
public abstract class Vector{
  protected double[] values;
  
  /**
   * 
   * @param dimension
   */
  public Vector(int dimension){
    if(dimension <0) throw new NegativeArraySizeException();
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

  /** skalar multiple
   * 
   * @param factor
   * @return Vector of same class as the base vector.
   */
  public abstract Vector mult(double factor);
  
  /** get the length of the vector
   * 
   * @return absolute length of the vector
   */
  public abstract double length();
  
  /** add a vector to this one.
   * 
   * @param vec the vector two be added to this one.
   * @return the sum of the two vectors. It need not be of the same type as this one
   */
  public abstract Vector add(Vector vec);
  
  /** subtract vector from this one.
   * 
   * @param a a Vector
   * @return the difference between this vector and a as a vector
   */
  public abstract Vector sub(Vector a);

  /** normalize the vector
   * 
   * will return a vector with the length of 1, pointing into the same direction as this vector
   * 
   * @return normalized vector
   */
  public abstract Vector normalize();
}
