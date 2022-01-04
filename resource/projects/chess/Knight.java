package resource.projects.chess;

public class Knight extends Piece {
  public Knight(boolean isWhite, Board board) {
    super(isWhite, board);
  }
  public Knight(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  
  /** 
   * @return String
   */
  public String toString() {
    return isWhite() ? Util.WHITE_KNIGHT.get() : Util.BLACK_KNIGHT.get();
  }

  
  /** 
   * @param loc
   * @return boolean
   */
  @Override
  public boolean move(Location loc) {
    // move in an L shape
    return Math.abs(loc.getRow() - myLocation().getRow()) == 2 && Math.abs(loc.getCol() - myLocation().getCol()) == 1 ||
           Math.abs(loc.getRow() - myLocation().getRow()) == 1 && Math.abs(loc.getCol() - myLocation().getCol()) == 2;
  }

  
}
