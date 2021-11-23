
/**
 * @author Jack Meng
 * Class: Board
 * Purpose: This class represents a standard Chess Board 
 * It will contain 16 pieces on each of a 2D array of Pieces of the following piece types:
 * 1. Pawn
 * 2. Queen
 * 3. King
 * 4. Bishop
 * 5. Rook
 * 6. Knight
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Board {

  Piece[][] squares, standard; // our board which is represented by a 2D array
  boolean isWhiteTurn = true; // same as in the driver, we also make sure to keep track of the turn

  /**
   * @param reset Constructor. The boolean indicates whether or not the board
   *              should be reset. If the parameter is true, in addition to
   *              instantiating the array, the array should also be reset to
   *              standard opening position (see first diagram, previous page).
   *              This can be accomplished by calling the reset method (see
   *              below). If the parameter is false, instantiate the array and
   *              leave it empty.
   * 
   */
  public Board(boolean reset) {
    squares = new Piece[8][8];
    standard = new Piece[8][8];
    if (reset) {
      reset();
    }
  }

  /**
   * @param loc The Location which is mentioned and to check if valid
   * @return boolean This method returns a boolean, where true represents the
   *         space is empty, and false representing that a space isn't empty
   */
  public boolean isEmpty(Location loc) {
    if (!isValid(loc)) {
      return false;
    }
    return squares[loc.getRow()][loc.getCol()] == null;
  }

  /*
   * This method is used to reset every single piece to it's original state and
   * every game property to it's starting part
   */
  void reset() {
    // setup the board in the right locations
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        squares[i][j] = null;
      }
    }
    // setup the white pieces
    squares[0][0] = new Rook(true, this, new Location(0, 0));
    squares[0][1] = new Knight(true, this, new Location(0, 1));
    squares[0][2] = new Bishop(true, this, new Location(0, 2));
    squares[0][3] = new Queen(true, this, new Location(0, 3));
    squares[0][4] = new King(true, this, new Location(0, 4));
    squares[0][5] = new Bishop(true, this, new Location(0, 5));
    squares[0][6] = new Knight(true, this, new Location(0, 6));
    squares[0][7] = new Rook(true, this, new Location(0, 7));
    for (int i = 0; i < 8; i++) {
      squares[1][i] = new Pawn(true, this, new Location(1, i));
    }
    // setup the black pieces
    squares[7][0] = new Rook(false, this, new Location(7, 0));
    squares[7][1] = new Knight(false, this, new Location(7, 1));
    squares[7][2] = new Bishop(false, this, new Location(7, 2));
    squares[7][3] = new Queen(false, this, new Location(7, 3));
    squares[7][4] = new King(false, this, new Location(7, 4));
    squares[7][5] = new Bishop(false, this, new Location(7, 5));
    squares[7][6] = new Knight(false, this, new Location(7, 6));
    squares[7][7] = new Rook(false, this, new Location(7, 7));
    for (int i = 0; i < 8; i++) {
      squares[6][i] = new Pawn(false, this, new Location(6, i));
    }
    standard = squares;
  }

  /**
   * @param loc A location that is to be checked
   * @return true if the location is on the board, false otherwise
   */
  public boolean isValid(Location loc) {
    return loc.getRow() >= 0 && loc.getRow() < 8 && loc.getCol() >= 0 && loc.getCol() < 8;
  }

  /**
   * @param source      The starting location
   * @param destination The ending location
   * @return a Piece is returned if the piece successfully moves and that piece is
   *         removed from the board This method attempts to check if the move is
   *         valid and if it is, we then call that piece's own abstract move()
   *         method
   */
  public Piece movePiece(Location source, Location destination) {
    if (!isValid(source) || !isValid(destination)) {
      return null;
    }
    Piece piece = squares[source.getRow()][source.getCol()];
    if (piece == null) {
      return null;
    }
    // check if the piece at source is the same color as the piece at destination if
    // it is return null
    if (getPiece(source).isWhite() == getPiece(destination).isWhite()
        || !getPiece(destination).isWhite() == !getPiece(source).isWhite()) {
      return null;
    }
    if (piece.getLocation().isValid(destination) && piece.move(destination)) {
      // if the move is valid, move the piece
      squares[destination.getRow()][destination.getCol()] = piece;
      squares[source.getRow()][source.getCol()] = null;
      piece.getBoard().addPiece(destination, piece);
      return piece;
    }
    return null;
  }

  /**
   * @throws IOException because we are using java.io This method is called when
   *                     the SIGNAL: PEAK BOARD is called It will attempt to print
   *                     some known audits of chess to a file in directory:
   *                     ${Tools.FILE_PATH}/${Tools.BOARD_FILE}
   */
  public void peekBoard() throws IOException {
    // print out the board to a text file
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(Tools.FILE_PATH.get() + "\\" + Tools.BOARD_FILE.get()))) {
      if (!new File(Tools.FILE_PATH.get()).isDirectory())
        new File(Tools.FILE_PATH.get()).mkdir();
      // write if onle the file does not exist
      // we here write the contents of the file
      bw.write("BOARD TEMPLATE & MOVES:\n");
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          bw.write(squares[i][j] != null ? "[" + squares[i][j].getLocation().toString() + "]" : "["+new Location(i, j).toString() +"]");
        }
        bw.write("\n");
      }
      bw.write("\n\nSTANDARD CHESS BOARD\n");
      for (int i = 0; i < 8; i++) {
        for(int j = 0; j < 8; j++) {
          bw.write(standard[i][j] != null ? "[" + standard[i][j].toString() + "]" : "[ ]");
        }
        bw.write("\n");
      }
      bw.write(
          "\n\nBLACK\na8: R\nb8: N\nc8: B\nd8: Q\ne8: K\nf8: B\ng8: N\nh8: R\na7-h7: P\n\nWHITE\na1: r\nb1: n\nc1: b\nd1: q\ne1: k\nf1: b\ng1: n\nh1: r\na2-h2: p\n");
    }
    System.out.println("CHECK THE BOARD FILE: \"" + Tools.FILE_PATH.get() + "/" + Tools.BOARD_FILE.get()
        + "\" FOR ALL MOVES ON A CHESSBOARD"); // this
    // tells
    // the
    // reader
    // where
    // to
    // check
  }

  /**
   * @param p The instance of a piece
   * @return Location The location of that instance of the piece p, if we didn't
   *         find anything we return null to signify nothing Pretty self
   *         explanatory
   */
  public Location locationOf(Piece p) {
    // we loop through the Pieces 2D array to find this instance of this piece p
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++)
        if (squares[i][j] == p)
          return new Location(i, j);
    return null;
  }

  /**
   * @param loc The Location to check
   * @return Piece A piece at that location, if nothing is found, it would return
   *         null The reason this method returns null is because everything is
   *         init as null in reset()
   */
  public Piece getPiece(Location loc) {
    return squares[loc.getRow()][loc.getCol()];
  }

  /**
   * @param loc   The location that is targetted
   * @param piece The piece that is to be added to this location loc
   * @return A boolean representing if the piece was added successfully at that
   *         location or not (true ? false) This method attempts to insert a piece
   *         at the specified location However it must also check the validity of
   *         this location and the piece
   */
  public boolean addPiece(Location loc, Piece piece) {
    if (!isValid(loc)) {
      return false;
    }
    if (squares[loc.getRow()][loc.getCol()] != null) {
      return false;
    }
    squares[loc.getRow()][loc.getCol()] = piece;
    return true;
  }

  /**
   * @param loc The targetted location
   * @return Piece The piece after having been removed from the board Pretty
   *         self-explanatory
   */
  public Piece removePiece(Location loc) {
    if (!isValid(loc) || isEmpty(loc))
      return null;
    Piece piece = squares[loc.getRow()][loc.getCol()];
    squares[loc.getRow()][loc.getCol()] = null;
    return piece;
  }

  /**
   * @return A string representing the board in a user-viewable format Is strictly
   *         used in the Driver class and any runner classes Very self-explanatory
   *         method
   */
  public String toString() {
    String r = "";
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        r += squares[i][j] == null ? "[ ]" : "[" + squares[i][j].toString() + "]";
      }
      r += "\n";
    }
    return r;
  }
}
