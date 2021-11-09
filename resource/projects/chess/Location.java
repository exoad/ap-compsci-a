public class Location {
  private int locX, locY;
  private char rowX;
  
  public Location(int locX, int locY) {
    this.locX = locX;
    this.locY = locY;
  }
  
  public Location(String location) {
    if((location.charAt(0) >= 'a' && location.charAt(0) <= 'h') && (location.charAt(1) >= '1' && location.charAt(1) <= '8')) {
      // set locations here
      this.locY = (int) location.charAt(1);
      rowX = location.charAt(0);
    } else
      throw new IllegalArgumentException("Invalid Position");
  }
  
  public int getRow() {
    return locY;
  }
  
  public int getColumn() {
    return locX;
  }
  
  public void setRow(int row) {
    this.locY = row;
  }
  
  public void setColumn(int col) {
    this.locX = col;
  }
  
  public String toString() {
    return "Position: " + rowX + locX;
  }
}