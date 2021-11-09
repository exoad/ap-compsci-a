public enum PieceNames {
  BLACK_PAWN("black_pawn"), BLACK_KING("black_king"), BLACK_QUEEN("black_queen"), BLACK_BISHOP("black_bishop"), BLACK_ROOK("black_rook"), 
  BLACK_KNIGHT("black_knight"), WHITE_PAWN("white_pawn"), WHITE_KING("white_king"), WHITE_QUEEN("white_queen"), WHITE_BISHOP("white_bishop"),
  WHITE_ROOK("white_rook"), WHITE_KNIGHT("white_knight");
  
  public final String name;
  PieceNames(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "Piece Name: " + name; 
  }
}