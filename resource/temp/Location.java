public class Location {
  int file;
  int rank;
  public Location(int file, int rank) {
    this.file = file;
    this.rank = rank;
  }
  public Location(String not) {
    //parse from conventional notation
    if(not.length() != 2) {
      throw new IllegalArgumentException("Invalid location string");
    }
    this.file = not.charAt(0) - '1';
    this.rank = not.charAt(1) - 'a';    
  }
  public int getCol() {
    return file;
  }
  public int getRow() {
    return rank;
  }
  public boolean isValid(Location loc) {
    return loc.file >= 0 && loc.file < 8 && loc.rank >= 0 && loc.rank < 8;
  }
  public boolean isValid() {
    return file >= 0 && file < 8 && rank >= 0 && rank < 8;
  }
  public boolean equals(Location loc) {
    return loc.file == this.file && loc.rank == this.rank;
  }
  public String toString() {
    //parse file and rank back to conventional notation
    return "" + (char)('1' + file) + (char)('a' + rank);
  }
}