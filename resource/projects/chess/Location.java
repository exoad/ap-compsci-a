package resource.projects.chess;

public class Location {
  int x, y;
  public Location(int x, int y) {
    if(x < 0 || x > 7 || y < 0 || y > 7) {
      throw new IllegalArgumentException("x and y must be between 0 and 7");
    }
    this.x = x;
    this.y = y;
  }
  public Location(String loc) {
    if(loc.length() != 2) {
      throw new IllegalArgumentException("loc must be in the form of \"a1\"");
    }
    char x = loc.charAt(0);
    if(x < 'a' || x > 'h') {
      throw new IllegalArgumentException("x must be between a and h");
    }
    switch(x) {
      case 'a':
        this.x = 0;
        break;
      case 'b':
        this.x = 1;
        break;
      case 'c':
        this.x = 2;
        break;
      case 'd':
        this.x = 3;
        break;
      case 'e':
        this.x = 4;
        break;
      case 'f':
        this.x = 5;
        break;
      case 'g': 
        this.x = 6;
        break;
      case 'h': 
        this.x = 7;
        break;
    }
    this.y = loc.charAt(1) - '1';
    if(this.x < 0 || this.x > 7 || this.y < 0 || this.y > 7) {
      throw new IllegalArgumentException("x and y must be between 0 and 7");
    }
  }
  
  /** 
   * @return int A number between 0 and 7
   * 
   * @see #y
   */
  public int getRow() {
    return 7 - y;
  }
  
  /** 
   * @return int A number between 0 and 7
   * 
   * @see #getRow()
   */
  public int getCol() {
    return this.x;
  }
  
  /** 
   * <p>This method attempts to alter this location to the location specified by the parameter.
   * @param y A number between 0 and 7
   * 
   */
  public void setRow(int y) {
    this.y = y;
  }
  
  /** 
   * <p>This method attempts to alter this location to the location specified by the parameter.
   * @param x A number between 0 and 7
   */
  public void setCol(int x) {
    this.x = x;
  }
  
  /** 
   * <p>This method returns a string representation of this location.
   * @return String A string in the form of "a1" or similar
   */
  public String toString() {
    return "" + (char)('a' + this.x) + (this.y + 1);
  }

  public boolean isValid() {
    return this.x >= 0 && this.x <= 7 && this.y >= 0 && this.y <= 7;
  }
  
  /** 
   * <p>This method checks if the location specified by the parameter is equal to this location.
   * @param loc A location to compare to
   * @return boolean true if the locations are the same and false otherwise
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  public boolean equals(Location loc) {
    return this.x == loc.x && this.y == loc.y;
  }
}