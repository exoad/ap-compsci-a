package resource.projects.chess;

/**
 * <h1>Rook Chess Piece</h1>
 * <p>A standard chess piece that can move in only vertical or horizontal directions.</p>
 * <p>Class: Rooke</p>
 * @see Piece
 * @see Location
 * @see Board
 * {@code new Rook(true, board, new Location(0,0))}
 * @author Jack Meng
 */

public class Rook extends Piece {
  /**
   * Constructors for objects of class Bishop
   * @param isWhite
   * @param board
   * @see Piece
   * {@code Rook(isWhite, board);}
   * {@code Rook(isWhite, board, loc);}
   */
  public Rook(boolean isWhite, Board board) {
    super(isWhite, board);
  }
  public Rook(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  
  /** 
   * @return String the string representation of the piece
   * {@code System.out.println(toString());}
   */
  public String toString() {
    return isWhite() ? Util.WHITE_ROOK.get() : Util.BLACK_ROOK.get();
  }

  
  /** 
   * @param loc A location to move to
   * @return boolean true if the move is valid and false otherwise
   * 
   * <p> This method checks if the move is valid by checking if the move is on the same row or column as the piece.
   * 
   * {@code System.out.println(move(loc));}
   * @see Piece
   * @see Board
   */
  @Override
  public boolean move(Location loc) {
    if (loc.getRow() == myLocation().getRow() || loc.getCol() == myLocation().getCol()) {
      if (getMyBoard().isValid(loc)) {
        getMyBoard().removePiece(myLocation());
        getMyBoard().placePiece(this, loc);
        setLocation(loc);
        hasMoved = true;
        return true;
      }
    }
    return false;
  }
  private void setLocation(Location loc) {
    this.loc = loc;
  }

}
