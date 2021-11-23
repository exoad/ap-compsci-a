public class Queen extends Piece {
  public Queen(boolean isWhite, Board board) {
    super(isWhite, board);
  }

  public Queen(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  @Override
  public boolean move(Location loc) {
    //check to make sure there are no pieces in the way
    if (board.getPiece(loc) != null) {
      return false;
    } //make sure there isnt a friendly piece in the way
    else if (board.getPiece(loc) != null && board.getPiece(loc).isWhite() == isWhite()) {
      return false;
    } // remove and then readd the piece on the board
    else {
      //if there is an enemy piece remove it
      if (board.getPiece(loc) != null && board.getPiece(loc).isWhite() != isWhite()) {
        board.removePiece(loc);
      }
      this.board.removePiece(getLocation());
      this.board.addPiece(loc, this);
      location = loc;
      return true;
    }
  }

  public String toString() {
    return isWhite() ? "q" : "Q";
  }
}
