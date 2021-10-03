/**
 * @author Jack Meng
 * @version 1.0
 * @// FIXME: 9/30/2021 
 * 
 */

package resource.quadrilaterals;

import java.util.Scanner;

public class QuadrilateralDriver {
  
	public static void main(String[] args) {
    Point t = new Point(3, 4);
    System.out.println(t.midpoint(new Point(2,2)));

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the first set of coordinates separated by a space like so:\nx y");
    Point x1 = new Point(sc.nextInt(), sc.nextInt());

    System.out.println("Enter the second set of coordinates separated by a space like so:\nx y"); 
    Point x2 = new Point(sc.nextInt(), sc.nextInt());
    
  }
  private int nextInteger() {

  }
}
