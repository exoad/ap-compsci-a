package resource.quadrilaterals;

/**
 * @author Jack Meng
 * <p>
 * :) This will be updated once the project has been submitted or pass
 * it's due date
 * <p>
 * No cheating :)))))
 * <p>
 * If your in my class and you need help, feel free to contact me by
 * submitting a issue on this GitHub repo! :)))
 * <p>
 * Or you can just email the teacher or ask me on Discord (exoad#9292)
 * or here
 */
public class Point {
    private double x, y, distance;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        distance = 0;
    }

    public static boolean approx(double a, double b) {

        return false;
    }

    /*
     * Accessor & Mutator methods
     */
    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public vooid setY(double y) {
        this.y = y;
    }

    /**
     * @param p this is the instance that will be passed to calculate the distance
     *          between the current instance and the other instance
     * @return the returned distance using the distance formula between two points
     */
    public double distance(Point p) {
        distance = Math.sqrt(((p.y - this.y) * (p.y - this.y) + (p.x - this.x) * (p.x - this.x)));
        return distance;
    }

    public boolean equals(Point p) {
        return p.getX() == this.x && p.getY() == this.y;
    }

    /**
     * @param p the second point on the line segment
     * @return the midpoint between these two points using the midpoint formula
     */
    public Point midpoint(Point p) {
        return new Point((p.getX() + this.x) / 2, (p.getY() + this.y) / 2);
    }

    public String toString() {
        String con = "";


        return con;
    }
	
    /**
     * @param p the Point instance to converted to a string
     * @return the point value after being turned into a string
     */ 
    protected String pointToString(Point p) {
	return "(" + p.getX() + "," + p.getY() + ")";
    }

}
