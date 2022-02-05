import java.io.*;

import java.util.*;

public class MazeSolver {
  public class Pair<T, E> {
    public final T first;
    public final E second;

    public Pair(T first, E second) {
      this.first = first;
      this.second = second;
    }

    public String toString() {
      return "[ " + first + ", " + second + " ]";
    }
  }

  public static class Config {
    public static final String TLED = "Moving the time limit to anything below this, may cause unwanted slowdown of the program...\nPlease consider changing it to something >= 100";
    public static final String INPUT_FILE = "maze.txt";
    public static final String FILE_EMPTY = "\nFile is empty.\nI have created the local " + INPUT_FILE
        + " for you, just put your originalPrettyMaze in it.";
    public static final String FILE_NOT_FOUND = "\nFile not found.\nI have created the local " + INPUT_FILE
        + " for you, just put your originalPrettyMaze in it.";
    public static final char WALL = '#';
    public static final char PATH = '.';
    public static final char START = 'S';
    public static final char END = 'F';
    public static final int WALL_INT = -1;
    public static final int PATH_INT = 0;
    private static final String TRAVELLED = "^";
    protected static final String[] heatMap = { ".", TRAVELLED, TRAVELLED, TRAVELLED, TRAVELLED };
    public static final long TLE = 100;
    public static final String CURSOR = "X";

    private Config() {
    }

  }

  public class Maze {
    private char[][] originalPrettyMaze;
    private int[][] parsedMaze;
    private boolean[][] visited;
    private int footSteps = 0;
    private Pair<Integer, Integer> current = new Pair<>(0, 0);

    public Maze(char[][] c) {
      originalPrettyMaze = c;
      visited = new boolean[originalPrettyMaze.length][originalPrettyMaze[0].length];
      quantify();
    }

    public void solve() {
      for (int i = 0; i < originalPrettyMaze.length; i++)
        for (int j = 0; j < originalPrettyMaze[0].length; j++)
          if (originalPrettyMaze[i][j] == Config.START) {
            current = new Pair<>(i, j);
            floodfill2(i, j);
          }
    }

    private void quantify() {
      parsedMaze = new int[originalPrettyMaze.length][originalPrettyMaze[0].length];
      for (int i = 0; i < originalPrettyMaze.length; i++) {
        for (int j = 0; j < originalPrettyMaze[i].length; j++) {
          if (originalPrettyMaze[i][j] == Config.WALL) {
            parsedMaze[i][j] = Config.WALL_INT;
          } else if (originalPrettyMaze[i][j] == Config.PATH || originalPrettyMaze[i][j] == Config.START
              || originalPrettyMaze[i][j] == Config.END) {
            parsedMaze[i][j] = Config.PATH_INT;
          }
        }
      }
    }

    private boolean isDeadEnd(int i, int j) {
      return parsedMaze[i][j] == Config.WALL_INT && parsedMaze[i - 1][j] == Config.WALL_INT
          && parsedMaze[i + 1][j] == Config.WALL_INT && parsedMaze[i][j - 1] == Config.WALL_INT
          && parsedMaze[i][j + 1] == Config.WALL_INT;
    }

    private void floodfillButWithAStack(int i, int j) {
      Deque<Pair<Integer, Integer>> stack = new ArrayDeque<>();
      stack.push(new Pair<>(i, j));
      while (!stack.isEmpty()) {
        Pair<Integer, Integer> p = stack.pop();
        if (p.first < 0 || p.first >= originalPrettyMaze.length || p.second < 0
            || p.second >= originalPrettyMaze[0].length)
          continue;
        if (Objects.equals(p.first, current.first) && Objects.equals(p.second, current.second))
          continue;
        if (parsedMaze[p.first][p.second] == Config.PATH_INT) {
          parsedMaze[p.first][p.second] = footSteps;
          visited[p.first][p.second] = true;
          if (isDeadEnd(p.first, p.second)) {
            footSteps++;
            continue;
          }
          stack.push(new Pair<>(p.first - 1, p.second));
          stack.push(new Pair<>(p.first + 1, p.second));
          stack.push(new Pair<>(p.first, p.second - 1));
          stack.push(new Pair<>(p.first, p.second + 1));
        }
      }
    }

    private void floodfill2(int i, int j) {
      System.out.println(toString(parsedMaze));
      if (i < 0 || j < 0 || i >= originalPrettyMaze.length || j >= originalPrettyMaze[0].length)
        return;
      if (parsedMaze[i][j] == Config.WALL_INT)
        return;
      if (isDeadEnd(i, j)) {
        if (i - 1 >= 0 && parsedMaze[i - 1][j] == Config.PATH_INT) {
          floodfill2(i - 1, j);
        } else if (i + 1 < originalPrettyMaze.length && parsedMaze[i + 1][j] == Config.PATH_INT) {
          floodfill2(i + 1, j);
        } else if (j - 1 >= 0 && parsedMaze[i][j - 1] == Config.PATH_INT) {
          floodfill2(i, j - 1);
        } else if (j + 1 < originalPrettyMaze[0].length && parsedMaze[i][j + 1] == Config.PATH_INT) {
          floodfill2(i, j + 1);
        }
      }
      if (parsedMaze[i][j] == Config.PATH_INT) {
        parsedMaze[i][j]++;
        visited[i][j] = true;
        current = new Pair<>(i, j);
        footSteps++;
        System.out.println(toString(parsedMaze));
        try {
          Thread.sleep(Config.TLE);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (originalPrettyMaze[i][j] == Config.END) {
          System.out.println("== MAZE SOLVED ==\nStats:\nFoot Steps: " + footSteps + "\nFound End At: " + new Pair<>(i, j).toString());
          System.exit(0);
        }
        new Thread(() -> {
          floodfill(i + 1, j);
          floodfill(i - 1, j);
          floodfill(i, j + 1);
          floodfill(i, j - 1);
        }).start();
      }
    }

    public String toString(int[][] s) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length; i++) {
        for (int j = 0; j < s[i].length; j++) {
          if (s[i][j] == -1)
            sb.append(Config.WALL + " ");
          else {
            if (i == current.first && j == current.second)
              sb.append(Config.CURSOR + " ");
            else

              sb.append(Config.heatMap[s[i][j]] + " ");
          }
        }
        sb.append('\n');
      }
      System.out.flush();
      return "\n\n" + sb.toString() + "\n";
    }

    public String unparsedToString(int[][] s) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length; i++) {
        for (int j = 0; j < s[i].length; j++) {
          sb.append("[" + s[i][j] + "]");
        }
        sb.append('\n');
      }
      return "\n\n" + sb.toString();
    }
  }

  /**
   * @param args
   */
  public static void main(String... args) {
    File f = new File(Config.INPUT_FILE);
    if (!f.exists() || !f.isFile()) {
      System.out.println(
          Config.FILE_NOT_FOUND);
      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return;
    }
    try (Scanner sc = new Scanner(new File(Config.INPUT_FILE))) {
      if (f.length() == 0) {
        System.out.println(
            Config.FILE_EMPTY);
        return;
      }

      ArrayList<ArrayList<Character>> firstMaze = new ArrayList<>();
      while (sc.hasNextLine()) {
        String line = sc.nextLine().replace("\u0009", "").replace(" ", "").replace("\t", "");
        ArrayList<Character> row = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
          row.add(line.charAt(i));
        }
        firstMaze.add(row);
      }

      char[][] c = new char[firstMaze.size()][firstMaze.get(0).size()];
      for (int i = 0; i < firstMaze.size(); i++)
        for (int j = 0; j < firstMaze.get(0).size(); j++)
          c[i][j] = firstMaze.get(i).get(j);

      Maze m = new Main().new Maze(c);
      System.out.println(m.unparsedToString(m.parsedMaze));
      m.solve();
      System.out.println(m.toString(m.parsedMaze));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
