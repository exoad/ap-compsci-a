package resource.classworks.l_43;

public class IterativeVRecursion {  
  public static int recursiveFib(int n) {
    return iterativeFib(n-1) + iterativeFib(n-2);
  }
  
  public static int iterativeFib(int n) {
    int num1 = 0, num2 = 1;
    for(int i = 0; i < n - 1; i++) {
      int temp = num1 + num2;
      num1 = num2;
      num2 = temp;
    }
    return num2;
  }
  
  public static void main(String[] args) {
    System.out.println(recursiveFib(55));
    System.out.println(iterativeFib(55));
  }
}
