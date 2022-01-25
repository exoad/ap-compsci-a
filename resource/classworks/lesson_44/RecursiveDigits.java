package resource.classworks.lesson_44;

import static java.lang.Math.*;
import static java.lang.System.*;

public class RecursiveDigits {
    public static int findDigits(int n) {
        n = abs(n);
        if(n == 0)
          return n;
        return n % 10 + (findDigits(n / 10));
    }
    
    public static void main(String[] args) {
        out.println(findDigits(-96));
    }
}
