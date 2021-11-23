public class Rook extends Piece {
  public Rook(boolean isWhite, Board board) {
    super(isWhite, board);
  }

  public Rook(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  @Override
  public boolean move(Location loc) {
    if (loc.equals(getLocation())) {
      return false;
    }
    // make sure there are no pieces in the way
    if (loc.getRow() == getLocation().getRow()) {
      int startCol = getLocation().getCol();
      int endCol = loc.getCol();
      if (startCol < endCol) {
        for (int i = startCol + 1; i < endCol; i++) {
          if (getBoard().getPiece(new Location(getLocation().getRow(), i)) != null) {
            return false;
          }
        }
      } else {
        for (int i = startCol - 1; i > endCol; i--) {
          if (getBoard().getPiece(new Location(getLocation().getRow(), i)) != null) {
            return false;
          }
        }
      }
    } else if (loc.getCol() == getLocation().getCol()) {
      int startRow = getLocation().getRow();
      int endRow = loc.getRow();
      if (startRow < endRow) {
        for (int i = startRow + 1; i < endRow; i++) {
          if (getBoard().getPiece(new Location(i, getLocation().getCol())) != null) {
            return false;
          }
        }
      } else {
        for (int i = startRow - 1; i > endRow; i--) {
          if (getBoard().getPiece(new Location(i, getLocation().getCol())) != null) {
            return false;
          }
        }
      }
    } else {
      return false;
    }
    //make sure the move isnt diagonal
    if (Math.abs(loc.getRow() - getLocation().getRow()) == Math.abs(loc.getCol() - getLocation().getCol())) {
      return false;
    }
    if (loc.getRow() == getLocation().getRow() || loc.getCol() == getLocation().getCol()) {
        //if there is an enemy piece at loc
        if (getBoard().getPiece(loc) != null) {
          getBoard().removePiece(loc);
        }
        //if there is a friendly piece at loc return false
        if (getBoard().getPiece(loc) != null && getBoard().getPiece(loc).isWhite() == isWhite()) {
          return false;
        }
        // remove the piece at this piece's old location
        this.board.removePiece(getLocation());
        // move the piece to the new location
        this.board.addPiece(loc, this);
        // update the piece's location
        location = loc;
      return true;
    } else if (loc.getRow() != getLocation().getRow() && loc.getCol() != getLocation().getCol()) {
      return false;
    }
    return true;
  }

  public String toString() {
    return isWhite() ? "r" : "R";
  }
}
