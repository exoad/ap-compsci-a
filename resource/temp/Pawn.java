public class Pawn extends Piece {

  boolean isFirstMove = true;
  int moveCount = isFirstMove ? 2 : 1;
  Bernard ch;

  public Pawn(boolean isWhite, Board board) {
    super(isWhite, board);
    ch = new Bernard(this, board);

  }

  public Pawn(boolean isWhite, Board board, Location location) {
    super(isWhite, board, location);
  }

  public String toString() {
    return isWhite() ? "p" : "P";
  }

  @Override
  public boolean move(Location loc) {
    //check if the move is valid
    if (!board.isValid(loc)) {
      return false;
    }
    // see if the move is a diagonal that lands on a friendly piece
    if (ch.isDiagonal(loc) && ch.isFriendly(loc)) {
      return false;
    }
    // if the move is the first move, allow for 2 spaces or 1 space
    if (isFirstMove) {
      if (Math.abs(loc.getRow() - this.getLocation().getRow()) > 2) {
        return false;
      }
    } else {
      if (Math.abs(loc.getRow() - this.getLocation().getRow()) > 1) {
        return false;
      }
    }
    // if the move is a diagonal move that lands on an enemy piece, capture it and remove it from the board
    if (Math.abs(loc.getRow() - this.getLocation().getRow()) == 1 && Math.abs(loc.getCol() - this.getLocation().getCol()) == 1) {
      if (board.getPiece(loc) != null && board.getPiece(loc).isWhite() != this.isWhite()) {
        board.removePiece(loc);
        // add and remove the piece from the board
        board.removePiece(location);
        board.addPiece(loc, this);
        return true;
      }
    }
    return false;
  }


}
