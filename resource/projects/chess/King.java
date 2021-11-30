package resource.projects.chess;

public class King extends Piece {

  public King(boolean isWhite, Board board) {
    super(isWhite, board);
  }
  public King(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  
  /** 
   * @return String
   */
  public String toString() {
    return isWhite() ? Util.WHITE_KING.get() : Util.BLACK_KING.get();
  }

  
  /** 
   * @param loc
   * @return boolean
   */
  @Override
  public boolean move(Location loc) {
    int startCol = myLocation().getCol();
    int eC = loc.getCol();
    int eR = loc.getRow();
    int dx = loc.getCol() - this.myLocation().getCol();
    int dy = loc.getRow() - this.myLocation().getRow();
    if (this.myLocation().isValid() && loc.isValid() && !loc.equals(this.myLocation())) {
      if (getMyBoard().getPiece(loc) != null && getMyBoard().getPiece(loc).isWhite() == this.isWhite()) {
        return false;
      }
      if ((Math.abs(dx) == 1 && Math.abs(dy) == 1) || (Math.abs(dx) == 0 && Math.abs(dy) == 1) || (Math.abs(dx) == 1 && Math.abs(dy) == 0)) {
        getMyBoard().movePiece(this.myLocation(), loc);
        hasMoved = true;
        return true;
      }
      if (dx == 2 && dy == 0) {
        if (this.isWhite()) {
          if (getMyBoard().getPiece(new Location(7, 7)) != null
              && getMyBoard().getPiece(new Location(7, 7)).isWhite()
              && getMyBoard().getPiece(new Location(7, 7)) instanceof Rook) {
            if (!getMyBoard().getPiece(new Location(7, 7)).hasMoved()
                && getMyBoard().getPiece(new Location(eC - 1, eR)) == null
                && getMyBoard().getPiece(new Location(eC, eR)) == null) {
              Piece p = getMyBoard().getPiece(new Location(7, 7));
              getMyBoard().movePiece(this.myLocation(), loc);
              getMyBoard().movePiece(p.myLocation(), new Location(startCol + 1, eR));
              hasMoved = true;
              return true;
            }
          }
        }
        if (!this.isWhite()) {

          if (getMyBoard().getPiece(new Location(7, 0)) != null
              && !getMyBoard().getPiece(new Location(7, 0)).isWhite()
              && getMyBoard().getPiece(new Location(7, 0)) instanceof Rook) {
            if (!getMyBoard().getPiece(new Location(7, 0)).hasMoved()
                && getMyBoard().getPiece(new Location(eC - 1, eR)) == null
                && getMyBoard().getPiece(new Location(eC, eR)) == null) {
              Piece p = getMyBoard().getPiece(new Location(7, 0));
              getMyBoard().movePiece(this.myLocation(), loc);
              getMyBoard().movePiece(p.myLocation(), new Location(startCol + 1, eR));
              hasMoved = true;
              return true;
            }
          }
        }
        return false;
      }
      if (dx == -2 && Math.abs(dy) == 0) {
        if (this.isWhite()) {

          if (getMyBoard().getPiece(new Location(0, 7)) != null
              && getMyBoard().getPiece(new Location(0, 7)).isWhite()
              && getMyBoard().getPiece(new Location(0, 7)) instanceof Rook) {
            if (!getMyBoard().getPiece(new Location(0, 7)).hasMoved()
                && getMyBoard().getPiece(new Location(eC - 1, eR)) == null
                && getMyBoard().getPiece(new Location(eC, eR)) == null
                && getMyBoard().getPiece(new Location(eC + 1, eR)) == null) {
              Piece p = getMyBoard().getPiece(new Location(0, 7));
              getMyBoard().movePiece(this.myLocation(), loc);
              getMyBoard().movePiece(p.myLocation(), new Location(startCol - 1, eR));
              hasMoved = true;
              return true;
            }
            return false;
          }
          return false;
        }
        if (!this.isWhite()) {
          if (getMyBoard().getPiece(new Location(0, 0)) != null
              && !getMyBoard().getPiece(new Location(0, 0)).isWhite()
              && getMyBoard().getPiece(new Location(0, 0)) instanceof Rook) {
            if (!getMyBoard().getPiece(new Location(0, 0)).hasMoved()
                && getMyBoard().getPiece(new Location(eC - 1, eR)) == null
                && getMyBoard().getPiece(new Location(eC, eR)) == null
                && getMyBoard().getPiece(new Location(eC + 1, eR)) == null) {
              Piece p = getMyBoard().getPiece(new Location(0, 0));
              getMyBoard().movePiece(this.myLocation(), loc);
              getMyBoard().movePiece(p.myLocation(), new Location(startCol - 1, eR));
              hasMoved = true;
              return true;
            }
          }
        }
      }
    }
    return false;
  }

}
