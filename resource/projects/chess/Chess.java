package resource.projects.chess;

import java.util.Scanner;

public class Chess {

  static Board board;

  /**
   * @param mod the message that we should print This method is shorthand way for
   *            the easy printing of certain text that may be repetitive
   *            throughout the program
   *            <p>
   */
  static synchronized void printMessage(int mod) {
    /*
     * we use a switch functionality so there would be less dependence on a if-else
     * statements Cases: 1 = This message is printed during either a reset of the
     * game or when the game has just started 2 = Shows if the move is invalid 4 =
     * Shows when a new move is prompted (success) 5 = Shows when the user prompts
     * for a reset of the game 6 = Shows if the move was invalid 7 = Shows if a
     * wrong piece was selected on the wrong turn
     */
    switch (mod) {
      case 1:
        System.out.println(
            "Welcome to bad Chess Game...\nHere are some things you need to know:\nLowerCase = WHITE\nUpperCase = BLACK\nR/r = Rook | N/n = Knight | B/b = Bishop | Q/q = Queen | K/k = King | P/p = Pawn\nInput your moves in the format of \"starting ending\", e.g. \"e8 e5\"\nAlso typing the word \"END END\" will immediately exit the game and start a new game\nTyping \"EXIT EXIT\" will EXIT THE PROGRAM\n");
        System.out.println();
        break;
      case 2:
        System.out.println("You can't move there\nRetry: ");
        break;
      case 4:
        System.out.println(Util.HEADER.get() + "\n" + board.toString() + "" + Util.HEADER.get()
            + "\nEnter your move for side: " + (board.isWhiteTurn ? "WHITE" : "BLACK") + "(start end) [e.g. e8 e5]: ");
        break;
      case 5:
        System.out.println("Board Reset Successful!");
        break;
      case 6:
        System.out.println("Invalid Move\nTry again: ");
        break;
      case 7:
        System.out.println("Incorrect Turn\nLowerCase = WHITE\nUpperCase = BLACK\nTry again: ");
        break;
    }
  }

  static void print(String o) {
    System.out.println(o);
  }

  public static void main(String[] args) {
    /**
     * Here we ask the user for input 
     * The turn will switch every time a move is made that is valid
     * 
     * It will also make sure to check if the user has entered 
     * a valid move
     */
    Scanner sc = new Scanner(System.in);
    board = new Board(true);
    printMessage(1);
    printMessage(4);
    while (true) {
      try {
        if (sc.hasNext()) {
          String input = sc.nextLine();
          if (input.equals("END END")) {
            printMessage(5);
            break;
          } else if (input.equals("EXIT EXIT")) {
            System.out.flush();
            sc.close();
            System.exit(1);
          }
          Location start = new Location(input.substring(0, 2));
          Location end = new Location(input.substring(3, 5));
          // check if it is the correct turn
          if (board.isWhiteTurn != board.getPiece(start).isWhite()) {
            printMessage(4);
            print("Try again (4): ");
            continue;
          }
          if (board.getPiece(start) == null) {
            print("Try again (3): ");
            continue;
          }
          if (board.isValid(end) && board.isValid(start)) {
            if (board.getPiece(start).isWhite() == board.isWhiteTurn) {
              if (board.movePiece(start, end) != null || board.movePiece(start, end) == null) {
                board.isWhiteTurn = !board.isWhiteTurn;
                printMessage(4);
                System.out.println("4");
              } else {
                print("Try again (2): ");
              }
            }
          } else {
            printMessage(4);
          }
        }
      } catch (IllegalArgumentException | NullPointerException e) {
        board.isWhiteTurn = !board.isWhiteTurn;
        printMessage(4);
      }
    }
  }
}
