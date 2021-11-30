package resource.projects.chess;

public class Bishop extends Piece {

  /**
   * Constructors for objects of class Bishop
   * @param isWhite
   * @param board
   * @see Piece
   */
  public Bishop(boolean isWhite, Board board) {
    super(isWhite, board);
  }
  public Bishop(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }
  
  /** 
   * @return String the string representation of the piece
   */
  public String toString() {
    return isWhite() ? Util.WHITE_BISHOP.get() : Util.BLACK_BISHOP.get();
  }

  
  /** 
   * @param loc A location to move to
   * @return boolean true if the move is valid and false otherwise
   */
  @Override
  public boolean move(Location loc) {
    return Math.abs(loc.getRow() - myLocation().getRow()) != Math.abs(loc.getCol() - myLocation().getCol());
  }

}
