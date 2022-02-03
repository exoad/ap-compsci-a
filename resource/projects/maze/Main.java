import java.io.*;
import java.util.*;

public class Main {
  public class Pair<T, E> {
    public final T first;
    public final E second;

    public Pair(T first, E second) {
      this.first = first;
      this.second = second;
    }
  }

  public static class Config {
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
    protected static final String[] heatMap = { ".", "\u001B[32m.\u001B[0m", "\u001B[33m.\u001B[0m",
        "\u001B[31m.\u001B[0m", "\u001B[35m.\u001B[0m" };
    public static final long TLE = 1000;
    public static final String CURSOR = "\u001B[34mX\u001B[0m";

    private Config() {
    }

  }

  public class Maze {
    private char[][] originalPrettyMaze;
    private int[][] parsedMaze;
    private boolean[][] visited;
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
            floodfill(i, j);
            break;
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

    private void floodfill(int i, int j) {
      System.out.println(toString(parsedMaze));
      Queue<Pair<Integer, Integer>> q = new LinkedList<>();
      q.add(new Pair<>(i, j));
      while (!q.isEmpty()) {
        Pair<Integer, Integer> curr = q.remove();
        if (curr.first < 0 || curr.first >= parsedMaze.length || curr.second < 0
            || curr.second >= parsedMaze[0].length || parsedMaze[curr.first][curr.second] == -1
            || visited[curr.first][curr.second]) {
          continue;
        }
        current = new Pair<>(curr.first, curr.second);
        System.out.println(toString(parsedMaze));
        try {
          Thread.sleep(Config.TLE);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        visited[curr.first][curr.second] = true;
        parsedMaze[curr.first][curr.second]++;
        q.add(new Pair<>(curr.first + 1, curr.second));
        q.add(new Pair<>(curr.first - 1, curr.second));
        q.add(new Pair<>(curr.first, curr.second + 1));
        q.add(new Pair<>(curr.first, curr.second - 1));
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
              sb.append("\u001B[31m" + Config.heatMap[s[i][j]] + "\u001B[0m ");
          }
        }
        sb.append('\n');
      }
      return "\n\n" + sb.toString();
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
