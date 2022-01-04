package resource.projects.chess;

/**
 * <h1>Pawn</h1>
 * <p>
 * This class represents a standard pawn in the game of chess. And inherits
 * things from the Piece class.
 * </p>
 * 
 * @author Jack Meng
 * @see Piece
 * @see Location
 * @see Board
 * @see Chess
 * @see Util
 * 
 *      {@code Pawn(isWhite, board, loc);}
 *      {@code Pawn(isWhite, board);}
 */

public class Pawn extends Piece {
  boolean isFirstMove; // to indicate if this moved, if it did not it has 1-2 moves else only 1

  /**
   * Constructors for objects of class Pawn
   * All constructors are the same as the Piece class which it inherits from
   * 
   * @param isWhite
   * @param board
   * @see Piece
   * 
   *      {@code Pawn(isWhite, board);}
   *      {@code Pawn(isWhite, board, loc);}
   */
  public Pawn(boolean isWhite, Board board) {
    super(isWhite, board);
  }

  public Pawn(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  /**
   * @return String A string representation of this piece.
   *         {@code toString()}
   */
  public String toString() {
    return isWhite() ? Util.WHITE_PAWN.get() : Util.BLACK_PAWN.get();
  }

  /**
   * <p>
   * This method is used to check the validity of the move of a pawn
   * </p>
   * 
   * @param loc A location to move to
   * @return boolean true if the move is valid, false otherwise
   *         {@code System.out.println(move(loc));}
   * @see Board#movePiece(Location, Location)
   */
  @Override
  public boolean move(Location loc) {
    // pawn move for black pawn
    // get the distance
    int distance = Math.abs(loc.getRow() - myLocation().getRow());
    if((distance == 1 && !isFirstMove) || (distance == 2 && isFirstMove || distance == 1 && isFirstMove)) {
    if (isWhite()) {
      if (loc.getRow() == myLocation().getRow() + 1 && loc.getCol() == myLocation().getCol()) {
        if (getMyBoard().getPiece(loc) == null) {
          return true;
        }
      } else if (loc.getRow() == myLocation().getRow() + 2 && loc.getCol() == myLocation().getCol() && isFirstMove) {
        if (getMyBoard().getPiece(loc) == null
            && getMyBoard().getPiece(new Location(myLocation().getRow() + 1, myLocation().getCol())) == null) {
          isFirstMove = false;
          return true;
        }
      } else if (loc.getRow() == myLocation().getRow() + 1 && Math.abs(loc.getCol() - myLocation().getCol()) == 1) {
        if (getMyBoard().getPiece(loc) != null && !getMyBoard().getPiece(loc).isWhite()) {
          return true;
        }
      }
    } else {
      if (loc.getRow() == myLocation().getRow() - 1 && loc.getCol() == myLocation().getCol()) {
        if (getMyBoard().getPiece(loc) == null) {
          return true;
        }
      } else if (loc.getRow() == myLocation().getRow() - 2 && loc.getCol() == myLocation().getCol() && isFirstMove) {
        if (getMyBoard().getPiece(loc) == null
            && getMyBoard().getPiece(new Location(myLocation().getRow() - 1, myLocation().getCol())) == null) {
          isFirstMove = false;
          return true;
        }
      } else if (loc.getRow() == myLocation().getRow() - 1 && Math.abs(loc.getCol() - myLocation().getCol()) == 1) {
        if (getMyBoard().getPiece(loc) != null && !getMyBoard().getPiece(loc).isWhite()) {
          return true;
        }
      }
    }
    }
    


    return false;

  }

  private boolean isFirstMove() {
    return isFirstMove;
  }

}
