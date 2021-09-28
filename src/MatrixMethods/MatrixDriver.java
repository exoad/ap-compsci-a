package src.MatrixMethods;

public class MatrixDriver {
  public static void main(String[] args) throws java.lang.Exception {
    int[][] test = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] test1 = { { 2, 2, 3 }, { 3, 5, 6 }, { 19, 8, 9 } };
    Matrix t = new Matrix(test);
    System.out.println(t.toString(test1, 90));
  }
}