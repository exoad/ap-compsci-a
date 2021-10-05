package resource.matrixmethod;

import java.util.Arrays;

// Test Matrix.java
public class MatrixDriver {
  public static void main(String[] args) throws java.lang.Exception {
    int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; //original
    int[][] test1 = { { 2, 2, 3 }, { 3, 5, 6 }, { 19, 8, 9 } }; //add
    int[][] testCase1 = { { 1, 3, 4, 9, 4 }, { 4, 3, 1, 8, 4 }, { 1, 3, 5, 2, 8 }, { 4, 2, 5, 0, 8 } };
    int[][] testCase1Correct = { { 4, 1, 4, 1 }, { 2, 3, 3, 3 }, { 5, 5, 1, 4 }, { 0, 2, 8, 9 } };
    Matrix t = new Matrix(test);
    System.out.println(t.toString(test1, 90));
    System.out.println("--------------");
    Matrix t2 = new Matrix(testCase1);
    System.out.println(t2.toString(testCase1, 360));
    System.out.println(Arrays.deepEquals(t.rotateArray(testCase1, 90), testCase1Correct) ? "OK 1" : "NO");
    System.out.println();

    Matrix t3 = new Matrix(3,3);

    System.out.println(Arrays.deepToString(t3.transpose()));
    System.out.println(Arrays.deepToString(t3.rotateArray(90)));
    System.out.flush();
  }
}