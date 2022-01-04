package resource.projects.chess;

public class Queen extends Piece {
  public Queen(boolean isWhite, Board board) {
    super(isWhite, board);
  }
  public Queen(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  
  /**
   * A string when lowercase is "q" and uppercase is "Q". Lowercase signifies a
   * white piece and uppercase signifies a black piece. The user may also change
   * this in the {@link Util} enum
   * 
   * @return String A string representation of this piece. {@code toString()}
   */
  public String toString() {
    return isWhite() ? Util.WHITE_QUEEN.get() : Util.BLACK_QUEEN.get();
  }

  
  /** 
   * This method is used to determine if a move is valid.
   * A queen can move in a star shape "*" and in any amount of squares.
   * However it must not be obstructed by any pieces.
   * 
   * @param loc A location to move to
   * @return boolean true if the move is valid, false otherwise
   * @see Piece#move(Location)
   */
  @Override
  public boolean move(Location loc) {
    return loc.getRow() == this.loc.getRow() || loc.getCol() == this.loc.getCol() || Math.abs(loc.getRow() - myLocation().getRow()) == Math.abs(loc.getCol() - myLocation().getCol());
  }

}
