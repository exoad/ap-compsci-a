package resource.projects.quadrilateral;

/*
 * Author Jack Meng
 * Purpose: This is a class that utilizes the Point class to determine certain properties about the points
 * Class: APCS 2021-2022
 */
public class Quadrilateral {

  private Point[] pointAT = new Point[4];

  /*
   * Values are in this order if not the program will most likely return anomalous
   * results
   */
  public Quadrilateral(Point topLeft, Point topRight, Point botRight, Point botLeft) {
    pointAT[0] = topLeft;
    pointAT[1] = topRight;
    pointAT[2] = botRight;
    pointAT[3] = botLeft;
  }

  /* Getter & Mutator Methods */
  public Point[] getPoints() {
    return pointAT;
  }

  public void setPoints(Point[] arr) {
    pointAT = arr;
  }

  /**
   * This method only checks if at least two segments are parallel
   * 
   * @return True if the given points are a trapezoid if not return false
   */
  public boolean isTrapezoid() {
    return pointAT[0].slope(pointAT[1]) == pointAT[2].slope(pointAT[3]) || pointAT[2].slope(pointAT[1]) == pointAT[0].slope(pointAT[3]);
  }

  /**
   * This method determines if the said points are in the form of a parallelogram
   * 
   * @return True if the given points are a parallelogram if not return false
   */
  public boolean isParallelogram() {
    return pointAT[0].distance(pointAT[1]) == pointAT[2].distance(pointAT[3]) && pointAT[1].distance(pointAT[2]) == pointAT[0].distance(pointAT[3]);
  }

  /**
   * This method determines if the said points are in a square meaning they must
   * be a parallelogram and also have the same all around distance between points
   * 
   * @return True if the given points are a square if not return false
   */
  public boolean isSquare() {
    return (isRectangle()) && pointAT[0].distance(pointAT[1]) == pointAT[1].distance(pointAT[2]);
  }

  /**
  * This method determines if the said points are in the formation of a rectangle
  * 
  * @return True if the given points are a rectangle if not return false
  */
  public boolean isRectangle() {
      return (isParallelogram()) && approx((pointAT[0].distance(pointAT[1]) * pointAT[0].distance(pointAT[1])) + (pointAT[1].distance(pointAT[2]) * pointAT[1].distance(pointAT[2])), pointAT[0].distance(pointAT[2]) * pointAT[0].distance(pointAT[2]));
  }

  /**
   * This method determines if the said points are in the formation of a rhombus
   * 
   * @return True if the given points are a rhombus if not return false
   */
  public boolean isRhombus() {
    return (isParallelogram()) && approx(pointAT[0].distance(pointAT[1]), pointAT[1].distance(pointAT[2]));
  }

  /**
   * @param a the first value to check
   * 
   * @param b the second value to check
   * 
   * @return true if the values are within the acceptable range if not return
   * false
   */
  public static boolean approx(double a, double b) {
    return (b - a) < 0.000001;
  }

  /**
   * @return The four points in order
   */
  public String toString() {
    return "Point 1: " + pointAT[0].toString() + "\nPoint 2: " + pointAT[1].toString() + "\nPoint 3: " +
      pointAT[2].toString() + "\nPoint 4: " + pointAT[3].toString();
  }
}
