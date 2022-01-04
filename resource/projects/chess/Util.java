package resource.projects.chess;

/**
 * <h1>Util</h1>
 * <p>This class provides an interface for the user to alter certain aspects of the game. Such as the look and feel etc.</p>
 * 
 * @author Jack Meng
 * @see Piece
 * @see Chess
 * {@code Util.PROMPT.get();}
 */

public enum Util {
  HEADER("----------------------------"), 
  BLACK_QUEEN("Q"), 
  BLACK_ROOK("R"), 
  BLACK_BISHOP("B"), 
  BLACK_KNIGHT("N"),
  BLACK_PAWN("P"),
  BLACK_KING("K"),
  WHITE_QUEEN("q"), 
  WHITE_KING("k"),
  WHITE_ROOK("r"), 
  WHITE_BISHOP("b"), 
  WHITE_KNIGHT("n"), 
  WHITE_PAWN("p"),
  EMPTY("[ ]"),;

  private String header;

  Util(String header) {
    this.header = header;
  }

  public String get() {
    return header;
  }
}