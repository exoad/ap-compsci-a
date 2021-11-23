public class Bishop extends Piece {
  public Bishop(boolean isWhite, Board board) {
    super(isWhite, board);
  }

  public Bishop(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  @Override
  public boolean move(Location loc) {
    // move piece
    // check if move is valid
    // check if move puts own king in check
    // check if move puts opponent king in check
    // check if move puts own king in checkmate
    // check if move puts opponent king in checkmate
    // return true if move is valid
    // return false if move is invalid
    return false;
  }

  public String toString() {
    return isWhite() ? "b" : "B";
  }
}
