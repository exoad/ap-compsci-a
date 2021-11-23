/**
 * @author Jack Meng
 * @version 1.0 
 * Class: Bernard
 * Bernard is a trusted chess instructor/coach who can help you find the answer to your question. In this 
 * case, Bernard will help us determine if certain things are true or false within our
 * chess program
 * Without Bernard, it can be very difficult to determine if certain things are true or false.
 * So please respect Bernard!
 * 
 * (This is a joke class, I just named in a funny way because I was bored)
 */

public class Bernard {
  /*
   * Here are the things that Bernard knows about the chess program
   * A piece that tell him
   * A board that tells him things 
   */
  private Piece p;
  private Board b;

  /**
   * Here we are telling Bernard certain things he need to know, this means
   * he will be able to answer questions about the chess program
   * @param p the piece that Bernard needs to know about
   * @param b the chess board that Bernard needs to know about
   */
  public Bernard(Piece p, Board b) {
    this.p = p;
    this.b = b;
  }

  /**
   * Here we can ask Bernard about what he is analyzing or what we gave him
   */
  public Piece gimmePiece() {
    return p;
  }

  public Board gimmeBoard() {
    return b;
  }

  /**
   * @param loc A location we give to Bernard
   * @return true if the location is diagonal from the piece we gave him else false
   */
  boolean isDiagonal(Location loc) {
    return Math.abs(loc.getRow() - p.getLocation().getRow()) == 1
        && Math.abs(loc.getCol() - p.getLocation().getCol()) == 1;
  }

  /**
   * @param loc A location we give to Bernard
   * @return true if the location is on the same column
   */
  boolean isVertical(Location loc) {
    return loc.getCol() == p.getLocation().getCol();
  }

  /**
   * @param loc
   * @return true if the location is on the same row
   */
  boolean isHoritzontal(Location loc) {
    return loc.getRow() == p.getLocation().getRow();
  }

  /**
   * @param loc A location we give to Bernard
   * @return true if the location contains a piece that is a friendly piece
   */
  boolean isFriendly(Location loc) {
    return b.getPiece(loc) != null && b.getPiece(loc).isWhite() == p.isWhite();
  }

  boolean isReachable(Location loc) {
    if (p instanceof Pawn) {
      if (p.isWhite())
        return loc.getRow() < p.getLocation().getRow()
            || (loc.getRow() == p.getLocation().getRow() && loc.getCol() != p.getLocation().getCol());
      else 
        return loc.getRow() > p.getLocation().getRow()
            || (loc.getRow() == p.getLocation().getRow() && loc.getCol() != p.getLocation().getCol());
    } else if (p instanceof Knight) {
      return Math.abs(loc.getRow() - p.getLocation().getRow()) == 2
          && Math.abs(loc.getCol() - p.getLocation().getCol()) == 1
          || Math.abs(loc.getRow() - p.getLocation().getRow()) == 1
              && Math.abs(loc.getCol() - p.getLocation().getCol()) == 2;
    }
    if (p instanceof Bishop)
      return isDiagonal(loc);
    if (p instanceof Rook)
      return loc.getRow() == p.getLocation().getRow()
          || loc.getCol() == p.getLocation().getCol();
    if (p instanceof Queen)
      return isDiagonal(loc) || isHoritzontal(loc) || isVertical(loc);
    if (p instanceof King) {
      return Math.abs(loc.getRow() - p.getLocation().getRow()) == 1
          && Math.abs(loc.getCol() - p.getLocation().getCol()) == 1
          || Math.abs(loc.getRow() - p.getLocation().getRow()) == 1
              && loc.getCol() == p.getLocation().getCol()
              || Math.abs(loc.getCol() - p.getLocation().getCol()) == 1
                  && loc.getRow() == p.getLocation().getRow();
    }

    return false;
  }

}
