package src.MatrixMethods;

/**
 * Name: Jack Meng Class: APCS 2021-2022 File Name: Matrix.java Purpose:
 * 
 */
public class Matrix {
  /**
   * Properties that will be used
   */
  private int[][] partMatrix;
  private int rows, cols;

  /**
   * Initiate the matrix through the constructor
   * 
   * @param matrix the matrix to be set to var partMatrix Otherwise overload the
   *               constructor in which the user can then use the setMatrix()
   */
  public Matrix(int[][] matrix) {
    partMatrix = matrix;
    rows = partMatrix.length;
    cols = partMatrix[0].length;
  }

  public Matrix() {
  }

  /*
   * Setter & Mutator Methods
   */
  public int getRows() {
    return partMatrix.length;
  }

  public int getColumns() {
    return partMatrix[0].length;
  }

  public void setMatrix(int[][] matrix) {
    partMatrix = matrix;
  }

  public int[][] getMatrix() {
    return partMatrix;
  }

  /**
   * @param arr the 2D array in question
   * @return the final array after it's columns and rows have been inversed
   */
  public int[][] transpose() {
    int[][] temp = new int[partMatrix.length][partMatrix[0].length];
    for (int i = 0; i < partMatrix.length; i++)
      for (int j = 0; j < partMatrix[i].length; j++)
        temp[i][j] = partMatrix[j][i];
    return temp;
  }

  /*
   * Helper method for the rotateArray() method and thus is private
   */
  private int[][] rotateOnce(int[][] ar) {
    /* Store the modified array here with the sizes of the array inversed */
    int[][] temp = new int[ar[0].length][ar.length];
    final int row_length = ar.length;

    /*
     * Loop through the 2D array and append the new values to the new array (temp)
     * in the correct location within a time complexity of O(N^2)
     */
    for (int col = 0; col < ar[0].length; col++)
      for (int row = ar.length - 1; row >= 0; row--)
        temp[col][row_length - 1 - row] = ar[row][col];

    return temp;
  }

  /*
   * Rotates the matrix rot number of times
   */
  public int[][] rotateArray(int rot) {
    if (rot == 90)
      return rotateOnce(partMatrix);
    else if (rot == 180)
      return rotateOnce(rotateOnce(partMatrix));
    else if (rot == 270)
      return rotateOnce(rotateOnce(rotateOnce(partMatrix)));
    else if (rot == 360)
      /* Just to make sure */
      return rotateOnce(rotateOnce(rotateOnce(rotateOnce(partMatrix))));

    return null;
  }

  /**
   * @param toAdd this is the 2D array that will be added to the current 2D array
   * @return the sum of the 2 2D arrays
   */
  public int addArrays(int[][] toAdd) {
    int sum = 0;
    for (int i = 0; i < partMatrix.length; i++)
      for (int j = 0; j < partMatrix[i].length; j++)
        sum += toAdd[i][j] + partMatrix[i][j];
    return sum;
  }

  /**
   * @param matrix    the matrix to be used with the internal methods
   * @param rotations the amount of rotations needed for the rotateArray() method
   * @param addMatrix this is the 2D array that will be used for the addArrays()
   *                  method
   * @return the final value returned to the user without the user having to set
   *         everything up
   */
  public String toString(int[][] addMatrix, int rotations) {
    String con = "";
    con += "Add 2D arrays: " + addArrays(addMatrix) + "\n";
    con += "Rotate Arrays with degrees " + rotations + " : " + arrayToString(rotateArray(rotations)) + "\n";
    con += "Transpose the Array: " + arrayToString(transpose()) + "\n";
    con += "Matrix Properties, Rows : " + rows + " Columns : " + cols + "\n";

    return con;

  }

  /*
   * Helper method for the main toString()
   * 
   * Only converts 2D arrays of integers to String
   */
  private String arrayToString(int[][] arr) {
    String con = "";
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        con += arr[i][j] + " ";
      }
      con += "\n";
    }
    return con;
  }
}
