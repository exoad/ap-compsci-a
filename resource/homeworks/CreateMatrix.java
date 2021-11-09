package resource.homeworks;

public class CreateMatrix {
  public static String[][] createMatrix(String[] arr) {
    String[][] newArr = new String[arr.length][arr.length];
    for (int i = 0; i < newArr.length; i++)
      for (int j = 0; j < newArr.length; j++)
        newArr[i][j] = arr[j];
    return newArr;
  }
  public static void main(String[] args) {
    String[] testArr = {"hello", "world", "period", "1"};
    for (String element[] : createMatrix(testArr)) {
      for (String ele : element) {
        System.out.print(ele + " ");
      }
      System.out.println();
    }
  }
}
