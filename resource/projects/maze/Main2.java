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
    public static final String[] heatMap = {".", TRAVELLED, TRAVELLED, TRAVELLED, TRAVELLED};
    public static final long TLE = 100;
    public static final String CURSOR = "X";

    private Config() {
    }

  }

  public class Maze {
    private char[][] originalPrettyMaze;
    private int[][] parsedMaze;
    private boolean[][] visited;
    private Pair<Integer, Integer> current = new Pair<>(0, 0);
    private Thread worker = new Thread(), encWorker = new Thread();

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
      worker = new Thread(() -> {
        if(originalPrettyMaze[i][j] == 'F') {
          return;
        }
      });
      
      while (!q.isEmpty()) {
        Pair<Integer, Integer> curr = q.remove();

        if (curr.first < 0 || curr.first >= parsedMaze.length || curr.second < 0
            || curr.second >= parsedMaze[0].length || parsedMaze[curr.first][curr.second] == -1
            || visited[curr.first][curr.second]) {
          continue;
        }
        worker = new Thread(() -> {
          current = new Pair<>(curr.first, curr.second);
        });
        worker.start();
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
              //sb.append("\u001B[31m" + Config.heatMap[s[i][j]] + "\u001B[0m ");
              sb.append(Config.heatMap[s[i][j]] + " ");
          }
        }
        sb.append('\n');
      }
      System.out.flush();
      return "\n\n" + sb.toString() + "Solving the Maze...\n";
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
    if(Config.TLE < 100) {
      System.out.println(Config.TLED);
      return;
    }
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
