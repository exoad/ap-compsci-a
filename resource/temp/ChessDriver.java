
/**
 * @author Jack Meng
 * Class: ChessDriver
 * Purpose: This file contains the main method that will run/utilize the chess program 
 *          and all of the following class files:
 * - Board
 * - Tools (enum)
 * - Piece
 * - Pawn
 * - Queen
 * - King
 * - Knight
 * - Location
 * - Bishop
 * - Rook
 * 
 * This project/assignment is for the class of APCS-2021-2022
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ChessDriver {
  // init variables that we will be using globally
  static Bernard bernard;
  static Board b = new Board(true); // our board which should be reset
  static boolean isWhiteTurn = true; // this variable determines who's turn it is, this means we only need a
                                     // primitive type whom only holds 2 values
  static int moves = 0; // this variable is mainly for saving moves and prints how many moves there have
                        // been
  static BufferedWriter bw; // the BufferedWrite class is used to store moves and write it to a file
  static final String HEADER = "----------------------------"; // a placeholder

  /**
   * @param mod the message that we should print This method is shorthand way for
   *            the easy printing of certain text that may be repetitive
   *            throughout the program
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
          "Welcome to bad Chess Game...\nHere are some things you need to know:\nLowerCase = WHITE\nUpperCase = BLACK\nR/r = Rook | N/n = Knight | B/b = Bishop | Q/q = Queen | K/k = King | P/p = Pawn\nInput your moves in the format of \"starting ending\", e.g. \"e8 e5\"\nAlso typing the word \"END END\" will immediately exit the game and start a new game\n");
      System.out.println();
      break;
    case 2:
      System.out.println("You can't move there\nRetry: ");
      System.out.println();
      break;
    case 4:
      System.out.println(HEADER + "\nWHITE\n" + b.toString() + "BLACK\n" + HEADER + "\nEnter your move for side: "
          + (isWhiteTurn ? "WHITE" : "BLACK") + "(start end) [e.g. e8 e5]: ");
      break;
    case 5:
      System.out.println("Board Reset Successful!");
      System.out.println();
      break;
    case 6:
      System.out.println("Invalid Move\nTry again: ");
      System.out.println();
      break;
    case 7:
      System.out.println("Incorrect Turn\nLowerCase = WHITE\nUpperCase = BLACK\nTry again: ");
      System.out.println();
      break;
    }
  }

  /**
   * @param t this is string object or literal that would be printed via this
   *          function This is a shorthand function so I wouldn't have to
   *          constantly type "System.out.println();"
   */
  static void print(String t) {
    System.out.print(t);
    // even though print() flushes the buffer automatically, a call of flush()
    // ensures nothing is left in the buffer
    System.out.flush();
  }

  /**
   * @param move the move in conventional form
   * @throws IOException an ioexception is throwed because this method uses
   *                     java.io This file makes sure that what the user entered
   *                     as their move is saved properly Think of it like this
   *                     method is caching the moves
   */
  static void saveMove(String move) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(Tools.FILE_PATH.get() + Tools.MOVES_FILES.get(), true))) {
      // if the cache directory doesn't exist, we create it

      if (!new File(Tools.FILE_PATH.get()).isDirectory())
        // our cache directory is stored in our Tools enum for easy manipulation
        new File(Tools.FILE_PATH.get()).mkdir();
      // we create our file with the path held and audited by our Tools enum
      // finally we append the var move to the file
      bw.append(moves + " : " + move + "\n");
    }
  }

  /**
   * @throws IOException is thrown because we are using an option of IO
   */
  public static void main(String[] args) throws IOException {
    // this is our first initialization or our "start"
    Scanner sc = new Scanner(System.in);
    // we call our printMessage({mod}) function to print the welcome message
    printMessage(1);

    /*
     * In this "forever" loop, we do the following: Ask the user for input in the
     * follower format: M1 M2 Where ${M1} & ${M2} stands for two arguments
     * 
     * ${M1} & ${M2} can be parsed differently, the first parse method is a standard
     * move: a2 a3 (Note this might not be a valid move) OR END END Which is parsed
     * as a way for the user to easily signal a reset of the game board/game
     * 
     * SIGNALS: END END = Reset the board & game PEAK BOARD = Prints a config file /
     * audit in the directory ${Tools.FILE_PATH}/${Tools.BOARD_FILE} EXIT EXIT =
     * Closes the program
     * 
     * For any calls of the method printMessage({mod}) refer to the methods above
     */
    while (true) {
      try {
        printMessage(4);
        if (sc.hasNextLine()) {
          String input = sc.nextLine(); // read the input
          // determine if the message is a valid SIGNAL term
          if (input.equals("END END")) {
            b.reset();
            printMessage(5);
            moves = 0;
            continue;
          } else if (input.equals("PEAK BOARD")) { // a peak board signal calls a template audit
            b.peekBoard();
            System.out.println();
            continue;
          } else if (input.equals("EXIT EXIT")) { // a exit exit signal calls a quit of the program
            sc.close();
            System.exit(0);
          }

          // if the input does not match any valid SIGNAL terms, we then try to split the
          // input into valid move notations
          String[] inputArr = input.split(" ");
          Location start;
          Location end;
          /*
           * This try-catch tries to append the locations from inputArr and will throw an
           * error if the move could not be parsed properly.
           */
          try {
            start = new Location(inputArr[0]);
            end = new Location(inputArr[1]);
          } catch (IllegalArgumentException e) {
            print("Not a conventional move. Try again\n");
            continue;
          }
          System.out.println(start.toString() + " " + end.toString());
          // we then ask to check the moves if they are in the range of the board
          if (!b.isValid(start) || !b.isValid(end)) {
            printMessage(6);
          } else {
            // check if the piece selected at start is the same color as the current turn
            // if everything works out fine, we pass it on the movePiece({start}, {end})
            // method to move the specified piece
            if (b.movePiece(start, end) != null) {
              saveMove(input);
              moves++;
              isWhiteTurn = !isWhiteTurn; // alternate the turns
            } else {
              // if the move is not valid refer to the Board class's movePiece({start}, {end})
              // method
              saveMove(input);
              printMessage(2);
            }
          }
        }
      } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
        saveMove(e.toString());
        printMessage(2);
      }
    }
  }
}
