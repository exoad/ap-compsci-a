package resource.projects.chess;

/**
 * <h1>Piece</h1>
 * <p>This a template class for a piece in the game. It will contain the following:</p>
 * <ul>
 * <li>Pawn</li>
 * <li>Knight</li>
 * <li>Bishop</li>
 * <li>Rook</li>
 * <li>Queen</li>
 * <li>King</li>
 * </ul>
 * @author Jack Meng
 * @see Location
 * @see Board
 * @see Chess
 * @see Util
 * 
 * @{code Piece(isWhite, board, loc);}
 */

public abstract class Piece {
  boolean isWhite;
  boolean hasMoved = false;
  Board board;
  Location loc;

  /**
   * Constructors for objects of class Piece
   * @param isWhite boolean true if the piece is white or false if it is black
   * @param board Board the board the piece is onf
   */
  protected Piece (boolean isWhite, Board board) {
    this.isWhite = isWhite;
    this.board = board;
  }
  protected Piece (boolean isWhite, Board board, Location loc) {
    this.isWhite = isWhite;
    this.board = board;
    this.loc = loc;
  }
  
  /** 
   * @return boolean true if the piece is white and false if the piece is black
   * {@code isWhite();}
   */
  public boolean isWhite() {
    return this.isWhite;
  }
  
  /** 
   * <p>This method is used to get the current board instance</p>
   * @return Board The current board instance
   * {@code getBoard();}
   */
  public Board getMyBoard() {
    return this.board;
  }
  
  /** 
   * <p>This method checks if the move is valid by checking if the move is on the same row or column as the piece.</p>
   * @param loc A location to move to
   * @return boolean true if the move is valid and false otherwise
   * @see Rook
   * @see Bishop
   * @see Knight
   * @see Queen
   * @see King
   * @see Pawn
   * {@code move(loc);}
   */
  public abstract boolean move(Location loc);

  /** 
   * @return Location the current location of the piece
   * {@code getLoc();}
   */
  public Location myLocation() {
    return this.loc;
  }
  public boolean isOccupied() {
    return this.board.isOccupied(this.loc);
  }
  public boolean hasMoved() {
    return this.hasMoved;
  }
}
