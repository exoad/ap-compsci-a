import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * This is the class that will display the actual triangles and do the actual
 * recursion
 * 
 * All of this is done via the usage of a JPanel and a JFrame and the graphics
 * class
 * </p>
 * 
 * @author Jack Meng
 * @see javax.swing.JFrame
 * @see javax.swing.JPanel
 * @see java.awt.Graphics
 */
public class Solver extends JPanel {

  /**
   * <p>
   * This is a wrapper class that basically represents 3 points in a 2D
   * plane and is used to represent the triangles
   * </p>
   */
  public static class Triangle {
    public Point a, b, c;

    /**
     * Constructor
     * 
     * @param a Point 1
     * @param b Point 2
     * @param c Point 3
     */
    public Triangle(Point a, Point b, Point c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    /**
     * <p>
     * A toString method to help with debugging
     * </p>
     * 
     * @return a String representation of the triangle
     */
    public synchronized String toString() {
      return "(" + a.getX() + ", " + a.getY() + ") -> (" + b.getX() + ", " + b.getY() + ") -> (" + c.getX() + ", "
          + c.getY() + ")";
    }
  }

  private JFrame f;
  private JLabel stepsCount;
  private JPanel topPanel;
  private int kio = 1000;
  private BigInteger triangleCount;
  private transient List<Triangle> triangles;

  /**
   * <p>
   * This constructor inits all of the swing values and components while also
   * drawing the big triangle
   * (AKA the starting triangle)
   * 
   * The triangleCount variable is init as a BigInteger because a standard long or
   * int
   * might not be enough for when the value is larger than said primitive types.
   * As the number
   * of triangles gets very large for a slight increase
   * </p>
   * 
   * @param steps The amount of steps needed to be taken to generate the right
   *              amount of triangles
   */
  public Solver(int steps) {
    topPanel = new JPanel();
    triangles = new ArrayList<>();
    if (steps >= 10)
      kio += 100;
    topPanel.setPreferredSize(new Dimension(kio, kio / 6));
    topPanel.setBackground(Color.RED);
    triangleCount = BigInteger.valueOf((long) Math.pow(3, steps));
    stepsCount = new JLabel(
        "<html><p>Amount of Steps: " + steps + "<br>Triangles to draw: "
            + BigInteger.valueOf((long) Math.pow(3, steps)).toString() + "</p></html>");
    setLayout(new BorderLayout());
    stepsCount.setForeground(Color.WHITE);
    topPanel.add(stepsCount);
    add(topPanel, BorderLayout.SOUTH);
    setPreferredSize(new Dimension(kio + 300, kio));
    setOpaque(true);
    setBackground(Color.BLACK);
    f = new JFrame("Sierpenski Triangle");
    f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    f.setPreferredSize(new Dimension(kio + 300, kio));
    f.add(this);
    f.pack();
    f.setVisible(true);
    Triangle curr = new Triangle(new Point(0, (int) Math.round((kio - 100) * Math.sqrt(3) / 2)),
        new Point((kio - 100) / 2, 0),
        new Point(kio - 100, (int) Math.round((kio - 100) * Math.sqrt(3) / 2)));
    sierpenski(steps, curr);
  }

  private synchronized void updateFrameTitle(String msg) {
    f.setTitle(msg);
  }

  /**
   * <p>
   * This recursive method performs the necessary operations to generate the
   * triangles
   * This is done by taking the current triangle and splitting it into 3 new
   * triangles
   * 
   * We update the JFrame title to show the status of the program especially for
   * when
   * the program is trying to deal with step values (n>=10)
   * </p>
   * 
   * @param n The steps
   * @param t The current triangle we are looking at
   */
  public synchronized void sierpenski(int n, Triangle t) {
    updateFrameTitle("Calculating positions for " + triangleCount.toString() + " triangles");
    if (n == 1) {
      triangles.add(t);
      triangleCount = triangleCount.subtract(BigInteger.valueOf((long) Math.pow(3, n)));
    } else {
      Point a = midpoint(t.a, t.b);
      Point b = midpoint(t.b, t.c);
      Point c = midpoint(t.c, t.a);
      sierpenski(n - 1, new Triangle(t.a, a, c));
      sierpenski(n - 1, new Triangle(a, t.b, b));
      sierpenski(n - 1, new Triangle(c, b, t.c));
    }
    repaint();
  }

  /**
   * <p>
   * Disposes the current frame. A wrapper method for
   * {@link javax.swing.JFrame#dispose()}
   * </p>
   */
  public void hideFrame() {
    f.dispose();
  }

  /**
   * <p>
   * Finds the midpoint between two points
   * </p>
   * 
   * @param p First point
   * @param c Second point
   * @return A point that is the midpoint between the two points
   */
  public synchronized Point midpoint(Point p, Point c) {
    return new Point((p.x + c.x) / 2, (p.y + c.y) / 2);
  }

  /**
   * <p>
   * This method is called to help with the painting of the triangles
   * It is called manually by the program after the triangles all have been
   * added to the ArrayList of triangles
   * 
   * We use a Polygon to represent the triangle as it is far more easier
   * to use this already provided STL-class than to paint each of the individual
   * lines with drawLine(x1, y1, x2, y2)
   * 
   * The class is synchronized to minimize the amount of
   * ConcurrentModificationExceptions
   * that can happen
   * 
   * We use drawPolygon instead of fillPolygon as for some reason when the steps
   * is over
   * or equal to 10, the Java API is unable to draw anything.
   * </p>
   * 
   * @param g The graphics object
   * @see javax.swing.JPanel
   */
  @Override
  public synchronized void paintComponent(Graphics g) {
    super.paintComponent(g);
    updateFrameTitle("Drawing...");

    triangleCount = null;
    for (Triangle s : triangles) {
      g.setColor(Color.ORANGE);
      Polygon x = new Polygon();
      x.addPoint(s.a.x, s.a.y);
      x.addPoint(s.b.x, s.b.y);
      x.addPoint(s.c.x, s.c.y);

      g.drawPolygon(x);
    }
    updateFrameTitle("Done");
  }
}