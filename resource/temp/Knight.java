public class Knight extends Piece {
  public Knight(boolean isWhite, Board board) {
    super(isWhite, board);
  }

  public Knight(boolean isWhite, Board board, Location loc) {
    super(isWhite, board, loc);
  }

  @Override
  public boolean move(Location loc) {
    return (loc.isValid() && !loc.equals(loc)) && ((Math.abs(getLocation().getRow() - loc.getRow()) == 2 && Math.abs(loc.getCol() - getLocation().getCol()) == 1) || (Math.abs(getLocation().getRow() - loc.getRow()) == 1 && Math.abs(loc.getCol() - getLocation().getCol()) == 2));
  }

  public String toString() {
    return isWhite() ? "n" : "N";
  }
}
