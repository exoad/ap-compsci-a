package src.MatrixMethods;
// Test Matrix.java
public class MatrixDriver {
  public static void main(String[] args) throws java.lang.Exception {
    int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; //original
    int[][] test1 = { { 2, 2, 3 }, { 3, 5, 6 }, { 19, 8, 9 } }; //add
    Matrix t = new Matrix(test);
    System.out.println(t.toString(test1, 90));
  }
}