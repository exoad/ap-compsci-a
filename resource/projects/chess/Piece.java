public abstract class Piece {

  
  private final PieceNames names;
  
  public Piece(boolean isWhite, Board board) {
    System.out.println("bruh");
      
  }
  
  abstract public boolean move(Location loc);
}