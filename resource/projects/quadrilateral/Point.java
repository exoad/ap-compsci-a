package resource.projects.quadrilateral;

/*
 * Author Jack Meng
 * Purpose: This is a class that creates a point on a XY coordinate plane
 * Class: APCS 2021-2022
 */

public class Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /* Accessor & Mutator Methods */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * This method uses the distance formula to find the distance between two points
     * 
     * @param p the second point
     * @return the distance between the current instance and p
     */
    public double distance(Point p) {
        return Math.sqrt(Math.pow((p.getX() - this.x), 2) + Math.pow(p.getY() - this.y, 2));
    }

    /**
     * This method creates a new Point representing the midpoint between two points
     * 
     * @param p the second point
     * @return a new Point which represents the midpoint
     */
    public Point midpoint(Point p) {
        return new Point((p.getX() + this.x) / 2, (p.getY() + this.y) / 2);
    }

    /**
     * This method returns the slope between two points
     * 
     * @param p the second point
     * @return a double value representing the slope
     */
    public double slope(Point p) {
        return (p.y - y) / (p.x - x);
    }

    /**
     * This method utilizes the approx() method to check if two points are within
     * the acceptable range
     * 
     * @param p the second point
     * @return a boolean representing if the two points are within range
     */
    public boolean equals(Point p) {
        return approx(p.getX(), this.x) && approx(p.getY(), this.y);
    }

    /**
     * This method checks if any two given double values are within a set precision
     * value
     * 
     * @param a first value
     * @param b second value
     * @return a boolean which represents if they are within precision
     */
    public static boolean approx(double a, double b) {
        return (a - b) < 0.000001;
    }

    /* Bonus Methods Start */

    /**
     * @param deltaX the change of the X value
     * @param deltaY the change of the Y value
     * @return a new instance after the change in values
     */
    public Point translate(double deltaX, double deltaY) {
        return new Point(x - deltaX, y - deltaY);
    }

    /**
     * @param factor the dilation scale factor
     * @return the final point after the dilation
     */
    public Point dilate(double factor) {
        return new Point(x * factor, y * factor);
    }

    // reflects across line y = x
    public Point reflectYEqualsX() {
        return new Point(y, x);
    }

    // reflects across line y = yValue
    public Point reflectVertical(double yValue) {
        return new Point(x, y - ((y - yValue) * 2));
    }

    // reflect across line x = xValue
    public Point reflectHorizontal(double xValue) {
        return new Point(x - ((x - xValue) * 2), y);
    }

    // rotate a point at a angle
    public Point rotate(int angle) {
        if (angle == 0 || angle == 360)
            return this;
        else if (angle == 90)
            return new Point(y, -x);
        else if (angle == 180)
            return new Point(-x, -y);
        else if (angle == 270)
            return new Point(-y, x);
        return this;
    }

    /* Bonus Methods End */

    /**
     * @return the point to return
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
