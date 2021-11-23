public enum Tools {
  FILE_PATH("./jmeng-chess-cache/"), BOARD_FILE("BOARD.txt"), MOVES_FILES("MOVES.txt"), INSTRUCTOR_NAME("Bernard");

  private final String filePath;

  Tools(String str) {
    this.filePath = str;
  }

  public String get() {
    return this.filePath;
  }
}
