package src.RotateArray;

/**
 * @author Jack Meng File name: RotateArray.java Purpose: to rotate a 2D array
 *         an X amount of rotations clockwise Assignment Method: rotateArray()
 *         Assignment Details: Write a method that takes a 2D array of integers
 *         as a parameter, as well as an integer (90, 180, 270, 360). The method
 *         should return the array rotated by that number of degrees. CLOCKWISE.
 *         Do NOT assume that the array is a square array. Class: 2021-2022 APCS
 * 
 */

class RotateArray {

  /**
   * Method purpose: This method makes it much easier to implement rotation in the
   * main assignment method. This also helps to avoid repetitive code that can
   * cause other issues
   * 
   * @param ar the array to be rotated once 90 degrees CLOCKWISE
   * @return ar
   */
  public static int[][] rotateOnce(int[][] ar) {
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

  /**
   * Method purpose: This method is the Assignment Method and is linked to method
   * rotateOnce()
   * 
   * @param arr the array in question
   * @param rot the rotation of only parameters 90 180 270 360
   * @return the rotated value
   */
  public static int[][] rotateArray(int[][] arr, int rot) {
    /* For each extra (+1) rotation */
    if (rot == 90)
      return rotateOnce(arr);
    else if (rot == 180)
      return rotateOnce(rotateOnce(arr));
    else if (rot == 270)
      return rotateOnce(rotateOnce(rotateOnce(arr)));
    else if (rot == 360)
      /* Just to make sure */
      return rotateOnce(rotateOnce(rotateOnce(rotateOnce(arr))));

    return null;
  }

  /**
   * MAIN: should only for onboard testing
   */
  public static void main(String[] args) {
    /*
     * var arr init AS: 1 2 3 4 5 6
     * 
     * expect output WITH ROTATION OF 90 AS: 4 1 5 2 6 3
     * 
     * expect output WITH ROTATION OF 180 AS: 6 5 4 3 2 1
     * 
     * expect output WITH ROTATION OF 270 AS: 3 4 2 5 1 6
     * 
     * output normal as 360
     */

    int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 } };

    /* Print the values in a time complexity of O(N^2) */
    for (int[] x : rotateArray(arr, 360)) {
      for (int y : x) {
        System.out.print(y + " ");
      }
      System.out.println();
    }
    System.out.printf("PRINTING ORIGINAL ARRAY \n");
    for (int[] x : arr) {
      for (int y : x) {
        System.out.print(y + " ");
      }
      System.out.println();
    }

    /*
     * Extra Testcases from JUNIT
     * 
     * TestCase1 : rotation = 90
     * TestCase2 : rotation = 180
     * TestCase3 : rotation = 270
     */
    int[][] testCase1 = { { 1, 3, 4, 9 }, { 4, 3, 1, 8 }, { 1, 3, 5, 2 }, { 4, 2, 5, 0 } };
    int[][] testCase1Correct = { { 4, 1, 4, 1 }, { 2, 3, 3, 3 }, { 5, 5, 1, 4 }, { 0, 2, 8, 9 } };

    int[][] testCase2 = { { 1, 3, 4, 9 }, { 4, 3, 1, 8 }, { 1, 3, 5, 2 }, { 4, 2, 5, 0 } };
    int[][] testCase2Correct = { { 0, 5, 2, 4 }, { 2, 5, 3, 1 }, { 8, 1, 3, 4 }, { 9, 4, 3, 1 } };

    int[][] testCase3 = { { 1, 3, 4, 9 }, { 4, 3, 1, 8 }, { 1, 3, 5, 2 }, { 4, 2, 5, 0 } };
    int[][] testCase3Correct = { { 9, 8, 2, 0 }, { 4, 1, 5, 5 }, { 3, 3, 3, 2 }, { 1, 4, 1, 4 } };

    /* Moved the JUNIT sections here so I could easily test them */
    System.out.println(java.util.Arrays.deepEquals(rotateArray(testCase1, 90), testCase1Correct));
    System.out.println(java.util.Arrays.deepEquals(rotateArray(testCase2, 180), testCase2Correct));
    System.out.println(java.util.Arrays.deepEquals(rotateArray(testCase3, 270), testCase3Correct));
    System.out.flush();
    //end
  }
}