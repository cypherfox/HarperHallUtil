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
public class Vector{
  double[] values;
  
  public Vector(int dimension){
    if(dimension <0) throw new NegativeArraySizeException();
    values = new double[dimension];
  }

  public Vector(double[] vals){
    int i,dimension = vals.length;
    
    values = new double[dimension];
    for(i=0;i<dimension;i++){
      values[i] = vals[i];
    }
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
  
  /**
   * 
   * @param i the position of the element to return
   * @return value at the n-th position in the vector
   */
  public double getElement(int i){
    return values[i];
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
    return values[2];
  }

  /**
   * 
   * @param a
   * @return
   * @throws IndexOutOfBoundsException
   */
  public Vector add(Vector a) throws IndexOutOfBoundsException {
    int i;
    int dimension = getDimension();
    
    if (dimension != a.getDimension())
       throw new IndexOutOfBoundsException("Dimension of vectors differ");
      
    Vector retval = new Vector(dimension);
    for(i=0;i<dimension;i++){
      retval.values[i]= this.values[i] + a.values[i];
    }
    return retval;
  }

  /**
   * 
   * @param a
   * @return
   * @throws IndexOutOfBoundsException
   */
  public Vector sub(Vector a) throws IndexOutOfBoundsException {
    int i;
    int dimension = getDimension();
    
    if (dimension != a.getDimension())
       throw new IndexOutOfBoundsException("Dimension of vectors differ");
      
    Vector retval = new Vector(dimension);
    for(i=0;i<dimension;i++){
      retval.values[i]= this.values[i] - a.values[i];
  }
    return retval;
  }

  /** scalar multiple
   * 
   * @param factor
   * @return
   */
  public Vector mult(double factor){
    int i,dimension = getDimension();
    Vector retval = new Vector(dimension);

    for(i=0;i<dimension;i++){
      retval.values[i]= this.values[i] * factor;
    }
    
    return retval;
  }
  
  /** dot product of two vectors with the angle phi between them.
   * 
   * for karthesic coordinates, cos_phi is 1;
   * 
   * @param b
   * @param phi
   * @return
   */
  public double dotProduct(Vector b, double cos_phi){
    int i,dimension = getDimension();
    double retval =0;
   
    for(i=0;i<dimension;i++){
      retval += values[i]* b.values[i];
    }

    return retval*cos_phi;
  }

  /** get the length of the vector
   * 
   * @return
   */
  public double length(){
    double len = dotProduct(this,1);

    return Math.sqrt(len);
  }

  /** cross product
   * 
   * The cross multiple is only defined for dimension = 3
   *  
   * @return
   * @param b
   */
  public Vector xProduct(Vector b){
    int dimension = getDimension();
    
    if(dimension != 3)
      throw new IndexOutOfBoundsException("Dimension of this vector is not 3");
    if(b.getDimension() != 3)
      throw new IndexOutOfBoundsException("Dimension of param b is not 3");

    
    Vector retval = new Vector(dimension);

    retval.values[0] = values[1] * b.values[2] - values[2] * b.values[1]; 
    retval.values[1] = values[2] * b.values[0] - values[0] * b.values[2]; 
    retval.values[2] = values[0] * b.values[1] - values[1] * b.values[0]; 
    
    return retval;
  }
  
  
  /** normalize the vecor
   * 
   * will return a vector with the norm of 1, pointing into the same direction as this vector
   * 
   * @return normalized vector
   */
  public Vector normalize(){
    int i,dimension = getDimension();
    double norm = 0;
    Vector retval = new Vector(dimension);

    norm = length();
    
    for(i=0;i<dimension;i++){
      retval.values[i] /= norm;
    }
        
    return retval;
  }
}
