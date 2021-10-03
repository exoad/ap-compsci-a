package resource.homeworks;

public class MaxRow {
  public static int[] largestRow(int[][] arr) {
    int rowSum = 0, maxRow = 0, maxSum = rowSum;
    for (int i = 0; i < arr.length; i++) {
      rowSum = 0;
      for (int j = 0; j < arr[i].length; j++) {
        rowSum += arr[i][j];
        System.out.println("ROW: " + i + " " + "COL: " + j + " CURR: " + rowSum);
      }
      System.out.println("SUM " + rowSum);
      if(rowSum > maxSum) {
        maxSum = rowSum;
        maxRow = i;
      }
    }
    System.out.println("MAX ROW: " + (maxRow));
    return arr[maxRow];


  }
  public static void main(String[] args) {
    //return row 2
    int[][] temp = {{4,4},{8,8},{-4,-4}};
    for(int ele : largestRow(temp))
      System.out.printf("%d ", ele);
  }
}