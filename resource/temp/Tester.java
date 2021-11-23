import java.util.Scanner;

public class Tester {
  public static void test2() {
    while (true) {

      Scanner sc = new Scanner(System.in);
      String s = sc.nextLine();
      System.out.println("[]: " + new Location(s).toString());
    }

  }
  public static void test3() {
    Board b = new Board(true);
    System.out.println(b.toString());
    Scanner sc = new Scanner(System.in);
    while (true) {
      String loc = sc.nextLine();
      if (b.getPiece(new Location(loc)) != null) {
        System.out.println(b.getPiece(new Location(loc)).toString() + " " + new Location(loc).toString());
      } else {
        System.out.println("No piece at that location @" + new Location(loc).toString());
      }
    }
  }

  public static void test4() {
    Board b = new Board(true);
    Scanner sc = new Scanner(System.in);
    // test the Bernard class's isReachable() method
    Bernard bernard;
    while(true) {
      String loc = sc.nextLine();
      // parse start and end from loc
      Location start = new Location(loc.substring(0, 2));
      Location end = new Location(loc.substring(3, 5));
      System.out.println("start: " + start.toString() + " end: " + end.toString());
      bernard = new Bernard(b.getPiece(start), b);
      System.out.print(bernard.isReachable(end));
      System.out.println(" @ " + b.getPiece(new Location(loc)).toString() + " | " + start.toString() +" -> " + end.toString());
    }
  }

  public static void main(String[] args) {
    test4();
  }
}
