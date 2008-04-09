/**
 * 
 */
package de.harper_hall.util.math;

/** (Hyper-)SphericalVector
 * 
 * describes a direction and distance in a Spherical coordinate system. The 
 * angle definition follows the definitions for spherical coordinate system in
 * ISO 31-11 for distance(r), zenith(theta) and azimuth(phi) respectively.
 * @see http://en.wikipedia.org/wiki/Spherical_coordinates
 * 
 * While some utility methods exist for the first three dimensions, the class
 * may be used for higher dimensions, provided the method used is defined for
 * higher dimensions. An IndexOutOfBoundsException is thrown otherwise.
 *  
 * @author sage
 *
 */
public class SphericalVector extends Vector {

  /**
   * @param length
   */
  public SphericalVector(int length) {
    super(length);
  }

  /**
   * @see de.harper_hall.util.math.Vector#length()
   */
  @Override
  public double length() {    
    return values[0];
  }

  /**
   * @see de.harper_hall.util.math.Vector#mult(double)
   */
  @Override
  public Vector mult(double factor) {
    Vector retval = new SphericalVector(values.length);

    retval.values[0] *= factor;
    
    return retval;
  }

  /** Add a vector
   * 
   * @return a Vector holding the sum of vec and this one
   * @see de.harper_hall.util.math.Vector#add(de.harper_hall.util.math.Vector)
   */
  @Override
  public Vector add(Vector vec) {
    CartesianVector a= new CartesianVector(this);
    return a.add(vec);
  }

  /**
   * 
   * @return a Vector holding the difference between this one and vec
   * @see de.harper_hall.util.math.Vector#sub(de.harper_hall.util.math.Vector)
   */
  @Override
  public Vector sub(Vector vec) {
    CartesianVector a= new CartesianVector(this);
    return a.add(vec);
  }

  /**
   * @see de.harper_hall.util.math.Vector#normalize()
   */
  @Override
  public Vector normalize() {
    Vector retval = new CartesianVector(values.length);

    System.arraycopy(values, 0, retval.values, 0, values.length);
    retval.values[0]=1;
        
    return retval;
  }

}
