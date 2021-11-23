public class King extends Piece {
  private Location loc;
  public King(boolean isWhite, Board board) {
    super(isWhite, board);
  }

  public King(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
    this.loc = loc;
  }

  @Override
  public boolean move(Location loc) {
    if (loc.equals(this.loc)) {
      return false;
    }
    if (Math.abs(loc.getRow() - this.loc.getRow()) > 1 || Math.abs(loc.getCol()) - this.loc.getCol() > 1) {
      return false;
    }
    if (this.board.getPiece(loc) != null && this.board.getPiece(loc).isWhite() == this.isWhite) {
      return false;
    }
    this.loc = loc;
    return true;
  }

  public String toString() {
    return isWhite() ? "k" : "K";
  }
}
