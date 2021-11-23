public class Location {
  int col;
  int row;
  public Location(int col, int row) {
    this.col = col;
    this.row = row;
  }
  public Location(String not) {
    // parse from conventional notation
    if (not.length() != 2) {
      throw new IllegalArgumentException("Invalid location string");
    }
    this.col = not.charAt(0) - 'a';
    this.row = not.charAt(1) - '1';
  }
  public int getCol() { return col; }
  public int getRow() { return row; }
  public void setCol(int col) { this.col = col; }
  public void setRow(int row) { this.row = row; }

  public boolean isValid(Location loc) {
    return loc.col >= 0 && loc.col < 8 && loc.row >= 0 && loc.row < 8;
  }
  public boolean isValid() {
    return col >= 0 && col < 8 && row >= 0 && row < 8;
  }
  public boolean equals(Location loc) {
    return loc.col == this.col && loc.row == this.row;
  }
  public String toString() { return "" + (char)('a' + col) + (row + 1); }
}
