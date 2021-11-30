package resource.projects.chess;
// THIS IS NOT PART OF THE ASSIGNMENT
import java.util.Scanner;

public class Test {
  static void loctest() {
    //print out all the locations on a chess board using the location class
    Location loc = new Location(0,0);
    // a on the left side and 1 on the bottom and h on the right side and 8 on the top
    for (int i = 7; i >= 0; i--) {
      for (int j = 0; j < 8; j++) {
        loc.setRow(i);
        loc.setCol(j);
        System.out.print(loc + " ");
      }
      System.out.println();
    }
  }

  static void printBoard() {
    //print out the board using the board class
    Board board = new Board(true);
    System.out.println(board.toString());
  }

  static void printloc() {
    Board board = new Board(true);
    System.out.println(board.locationsToString());
  }

  static void vast_test() {
    
    while (true) {
      Scanner sc = new Scanner(System.in);

      String choice = sc.next();
      if ("1".equals(choice))
        loctest();
      else if ("2".equals(choice))
        printBoard();
      else if ("3".equals(choice))
        printloc();
      else if ("69".equals(choice))
        sc.close();

    }
  }

  static void tunnel_test() {
    Board b = new Board(true);
    System.out.println(b.toString());
    while(true) {
      Scanner sc = new Scanner(System.in);
      String location = sc.next();
      Location loc = new Location(location);
      System.out.println(loc.toString());
      System.out.println("ROW: " + (loc.getRow() + 1));
      System.out.println("COL: " + (loc.getCol() + 1));
      //get piece
      Board board = new Board(true);
      if(board.getPiece(loc) != null) {
        System.out.println("Piece: " + board.getPiece(loc).toString());
      }
      else {
        System.out.println("No piece at location");
      }
    }
  }

  public static void main(String[] args) {
    tunnel_test();
  }
}
