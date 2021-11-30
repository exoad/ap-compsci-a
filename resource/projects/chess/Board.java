package resource.projects.chess;

public class Board {
  Piece[][] pieces = new Piece[8][8];
  boolean isWhiteTurn = true;
  public Board(boolean reset) {
    assert isWhiteTurn == true;
    if(reset) {
      reset();
    }
  }
  /**
   * This function resets the board to the starting positions
   */
  void reset() {
    isWhiteTurn = true;
    for(int i = 0; i < 8; i++) {
      pieces[1][i] = new Pawn(false, this, new Location(i, 1));
      pieces[6][i] = new Pawn(true, this, new Location(i, 6));
    }

    pieces[7][0] = new Rook(true, this, new Location(0, 0));
    pieces[7][1] = new Knight(true, this, new Location(1, 0));
    pieces[7][2] = new Bishop(true, this, new Location(2, 0));
    pieces[7][3] = new Queen(true, this, new Location(3, 0));
    pieces[7][4] = new King(true, this, new Location(4, 0));
    pieces[7][5] = new Bishop(true, this, new Location(5, 0));
    pieces[7][6] = new Knight(true, this, new Location(6, 0));
    pieces[7][7] = new Rook(true, this, new Location(7, 0));

    pieces[0][7] = new Rook(false, this, new Location(0, 7));
    pieces[0][6] = new Knight(false, this, new Location(1, 7));
    pieces[0][5] = new Bishop(false, this, new Location(2, 7));
    pieces[0][4] = new King(false, this, new Location(3, 7));
    pieces[0][3] = new Queen(false, this, new Location(4, 7));
    pieces[0][2] = new Bishop(false, this, new Location(5, 7));
    pieces[0][1] = new Knight(false, this, new Location(6, 7));
    pieces[0][0] = new Rook(false, this, new Location(7, 7));
  }

  
  /** 
   * @param loc A location to check
   * @return Piece the piece at the location
   * {@code getPiece(loc);}
   */
  Piece getPiece(Location loc) {
    return pieces[loc.getRow()][loc.getCol()];
  }
  
  /** 
   * @param piece A piece to place on the board
   * @param loc The location to place the piece
   * @return Piece the piece that was replaced
   */
  Piece placePiece(Piece piece, Location loc) {
    Piece oldPiece = pieces[loc.getRow()][loc.getCol()];
    pieces[loc.getRow()][loc.getCol()] = piece;
    piece.loc = loc;
    return oldPiece;
  }

  /**
   * @return The boolean representing whether it is white's turn
   */
  boolean isWhiteTurn() {
    return isWhiteTurn;
  }
  
  /** 
   * @param loc A location to check
   * @return boolean representing whether the location is occupied
   */
  boolean isEmpty(Location loc) {
    return pieces[loc.getRow()][loc.getCol()] == null;
  }

  
  /** 
   * @param piece A piece to check
   * @return Location the location of the piece
   */
  Location locationOf(Piece piece) {
    for(int i = 0; i < 8; i++) 
      for(int j = 0; j < 8; j++) 
        if(pieces[i][j] == piece)
          return new Location(i, j);
    return null;
  } 

  
  /** 
   * @param loc A location to check
   * @return Piece the piece at the location
   */
  Piece removePiece(Location loc) {
    Piece piece = pieces[loc.getRow()][loc.getCol()];
    pieces[loc.getRow()][loc.getCol()] = null;
    return piece;
  }

  
  /** 
   * @param loc A location to check
   * @return boolean representing whether the location is occupied by a piece of the opposite color
   */
  boolean isValid(Location loc) {
    return loc.getRow() >= 0 && loc.getRow() < 8 && loc.getCol() >= 0 && loc.getCol() < 8;
  }

  
  /** 
   * @return String representing the board
   */
  public String locationsToString() {
    String s = "";
    for(int i = 7; i >= 0; i--) {
      for(int j = 0; j < 8; j++) {
        s += pieces[i][j] == null ? "[" + new Location(j, i).toString() + "]" : "[" + pieces[i][j].myLocation().toString() + "]";
      }
      s += "\n";
    }
    return s;
  }

  
  /** 
   * @param source The location of the piece to move
   * @param destination The location to move the piece to
   * @return Piece  the piece that was moved
   */
  public Piece movePiece(Location source, Location destination) {
    Piece p = pieces[source.getRow()][source.getCol()];
    if(isEmpty(source)) {
      throw new IllegalArgumentException("There is no piece at " + source.toString() + " " + getPiece(source));
    }
    // only a knight can jump over pieces
    if(!(p instanceof Knight)) {
      // make sure there are no pieces in the row and column less than the source and greater than the destination
      if(source.getRow() < destination.getRow() && source.getCol() < destination.getCol()) {
        for(int i = source.getRow() + 1, j = source.getCol() + 1; i < destination.getRow() && j < destination.getCol(); i++, j++) {
          if(!isEmpty(new Location(i, j))) {
            return pieces[i][j];
          }
        }
      }
    }

    // checking if the move is valid and then call that piece's move method
    if (this.isEmpty(destination) == true && this.isValid(source) == true && this.isValid(destination) == true) {
      pieces[destination.getRow()][destination.getCol()] = p;
      pieces[source.getRow()][source.getCol()] = null;
      return null;
    }
    if (this.isEmpty(source) == true) {
      return null;
    }
    if (this.getPiece(destination) != null) {
      Piece pi = this.removePiece(destination);
      pieces[destination.getRow()][destination.getCol()] = p;
      this.removePiece(source);
      return pi;
    }
    return null;
  }

  
  /** 
   * @return String representing the board
   */
  public String toString() {
    String s = "";
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 8; j++) {
        if(pieces[i][j] == null) {
          s += Util.EMPTY.get();
        } else {
          s += "[" + pieces[i][j].toString() + "]";
        }
      }
      s += "\n";
    }
    return s;
  }
  public boolean isOccupied(Location loc) {
    //determines if a location is occupied
    return !this.isEmpty(loc);
  }
}
