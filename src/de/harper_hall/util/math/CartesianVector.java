/**
 * 
 */
package de.harper_hall.util.math;

/**
 * 
 * While some utility functions exist to aid the use in three dimensions,
 * the package supports arbitrary dimensions.
 * 
 * TODO: basis-operation (sub), 
 *        
 * TODO:  Funktion die add implementiert mit vektor kleinerer
 *        Dimension, mit int parameter um n Dimensionen verschoben
 * 
 * @author sage
 *
 */
public class CartesianVector extends Vector {
  
  public CartesianVector(double[] vals){
    int i,dimension = vals.length;
    
    values = new double[dimension];
    for(i=0;i<dimension;i++){
      values[i] = vals[i];
    }
  }
  
  /**
   * 
   * @param dimension
   */
  public CartesianVector(int dimension){
    super(dimension);
  }
  
  /** transform a SpericalVector into a cartesian one.
   * 
   * This constructor is only able to convert 3D Vectors. It will throw
   * IndexOutOfBoundsException otherwise
   * 
   * @param a a SpericalVector
   */
  public CartesianVector(SphericalVector a) throws IndexOutOfBoundsException {
    super(a.getDimension());
    
    if(a.getDimension() > 3) throw new IndexOutOfBoundsException("dimension > 3");
    /*
    x = r * sin(theta) * cos(phi) 
    y = r * sin(theta) * sin(phi) 
    z = r * cos(theta)    
     */
    
    values[0] = a.values[0] * Math.sin(a.values[1]) * Math.cos(a.values[2]);
    values[0] = a.values[0] * Math.sin(a.values[1]) * Math.sin(a.values[2]);
    values[0] = a.values[0] * Math.cos(a.values[1]);
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
   * @return distance along the x-axis
   * @throws ArrayIndexOutOfBoundsException if the vector has less than one dimension
   */
  public double getX()throws ArrayIndexOutOfBoundsException{
    return values[0];
  }
  
  /** Utility accessor
   * 
   * @return distance along the y-axis
   * @throws ArrayIndexOutOfBoundsException if the vector has less than two dimensions
   */
  public double getY()throws ArrayIndexOutOfBoundsException{
    return values[1];
  }
  
  /** Utility accessor
   * 
   * @return distance along the z-axis
   * @throws ArrayIndexOutOfBoundsException if the vector has less than three dimensions
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
    
    if(a instanceof SphericalVector) a= new CartesianVector((SphericalVector)a);
    
    if (dimension != a.getDimension())
       throw new IndexOutOfBoundsException("Dimension of vectors differ");
      
    Vector retval = new CartesianVector(dimension);
    for(i=0;i<dimension;i++){
      retval.values[i]= this.values[i] + a.values[i];
    }
    return retval;
  }

  /** subtract vector from this one.
   * 
   * @param a a Vector
   * @return
   * @throws IndexOutOfBoundsException
   */
  public Vector sub(CartesianVector a) throws IndexOutOfBoundsException {
    int i;
    int dimension = getDimension();
    
    if (dimension != a.getDimension())
       throw new IndexOutOfBoundsException("Dimension of vectors differ");
      
    Vector retval = new CartesianVector(dimension);
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
  @Override
  public Vector mult(double factor){
    int i,dimension = getDimension();
    Vector retval = new CartesianVector(dimension);

    for(i=0;i<dimension;i++){
      retval.values[i]= this.values[i] * factor;
    }
    
    return retval;
  }
  
  /** dot product of two vectors with the angle phi between them.
   * 
   * for cartesian coordinate systems, cos_phi is 1;
   * 
   * @param b
   * @param phi
   * @return
   */
  public double dotProduct(CartesianVector b, double cos_phi){
    int i,dimension = getDimension();
    double retval =0;
   
    for(i=0;i<dimension;i++){
      retval += values[i]* b.values[i];
    }

    return retval*cos_phi;
  }

  /** get the length of the vector
   * 
   * @return absolute length of the vector
   */
  @Override
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
  public Vector xProduct(CartesianVector b){
    int dimension = getDimension();
    
    if(dimension != 3)
      throw new IndexOutOfBoundsException("Dimension of this vector is not 3");
    if(b.getDimension() != 3)
      throw new IndexOutOfBoundsException("Dimension of param b is not 3");

    
    Vector retval = new CartesianVector(dimension);

    retval.values[0] = values[1] * b.values[2] - values[2] * b.values[1]; 
    retval.values[1] = values[2] * b.values[0] - values[0] * b.values[2]; 
    retval.values[2] = values[0] * b.values[1] - values[1] * b.values[0]; 
    
    return retval;
  }
  
  
  /** normalize the vector
   * 
   * will return a vector with the length of 1, pointing into the same direction as this vector
   * 
   * @return normalized vector
   */
  @Override
  public Vector normalize(){
    int i,dimension = getDimension();
    double norm = length();
    Vector retval = new CartesianVector(dimension);

    for(i=0;i<dimension;i++){
      retval.values[i] = values[i]/ norm;
    }
        
    return retval;
  }

}