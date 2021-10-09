package resource.quadrilateral;

/*
 * Author Jack Meng
 * Purpose: This is a driver class for the Quadrilateral & Point class
 * Class: APCS 2021-2022
 */

import java.util.Scanner;

public class QuadrilateralDriver {
    public static void main(String[] args) throws Exception {

        /* In the order of Top Left, Top Right, Bottom Left, Bottom Right */
        Point[] pointList;

        Scanner sc = new Scanner(System.in);

        int userChoice = 1;
        do {
            /* Get user input from user IN ORDER */
            pointList = new Point[4];
            System.out.println("Enter Top Left Point (1) in the following format:\nx y");
            double x = sc.nextDouble();
            pointList[0] = new Point(x, sc.nextDouble());
            System.out.println("Enter Top Right Point (2) in the following format:\nx y");
            x = sc.nextDouble();
            pointList[1] = new Point(x, sc.nextDouble());
            System.out.println("Enter Bottom Right Point (3) in the following format:\nx y");
            x = sc.nextDouble();
            pointList[2] = new Point(x, sc.nextDouble());
            System.out.println("Enter Bottom Left Point (4) in the following format:\nx y");
            x = sc.nextDouble();
            pointList[3] = new Point(x, sc.nextDouble());

            /* Print out the results of the user's inputs */
            System.out.println("\nPrinting out everything from Quadrilateral Class(NO USER INPUT)\n");
            Quadrilateral ql = new Quadrilateral(pointList[0], pointList[1], pointList[2], pointList[3]);
            System.out.println(ql.toString());
            System.out.println("IS TRAPEZOID: " + (ql.isTrapezoid() ? "YES" : "NO"));
            System.out.println("IS PARALLELOGRAM: " + (ql.isParallelogram() ? "YES" : "NO"));
            System.out.println("IS RECTANGLE: " + (ql.isRectangle() ? "YES" : "NO"));
            System.out.println("IS SQUARE: " + (ql.isSquare() ? "YES" : "NO"));
            System.out.println("IS RHOMBUS: " + (ql.isRhombus() ? "YES" : "NO"));

            /* User Controlled Exit */
            System.out.println("\nDo Another?\n0 = No\nAny other Number = Yes");
            userChoice = sc.nextInt();
            System.out.flush();
        } while (userChoice != 0);
        System.out.close();
        sc.close();
    }
}
