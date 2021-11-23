import java.awt.Color;

public abstract class Piece {
  public boolean isWhite;
  public Board board;
  public Location location;
  public Piece(boolean isWhite, Board board) {
    this.isWhite = isWhite;
    this.board = board;
  }

  public Piece(boolean isWhite, Board board, Location location) {
    this.isWhite = isWhite;
    this.board = board;
    this.location = location;
  }

  public boolean isWhite() {
    return isWhite;
  }

  public Board getBoard() {
    return board;
  }

  public Color getColor() {
    return isWhite ? Color.WHITE : Color.BLACK;
  }

  public Location getLocation() {
    return location;
  }

  public Piece getPiece(Location loc) {
    //return a piece at the given location from the board
    return board.getPiece(loc);
    
  }

  public Location myLocation() {
    return location;
  }

  /*
   * Attempts to move this Piece to the specified location. If such a move is
   * legal, the piece is moved and the method returns true. If the move is
   * illegal, the piece is not moved and the method returns false. This method is
   * abstract and is implemented in the subclasses, with each method deciding on
   * the move's legality based on the type of the piece.
   */
  public abstract boolean move(Location loc);
}
