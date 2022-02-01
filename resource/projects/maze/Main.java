import java.io.*;
import java.util.*;

public class Main {
  public class Maze {
    private char[][] maze;
    private String[][] prettify;
    private boolean[][] visited;

    public Maze(char[][] c) {
      maze = c;
      visited = new boolean[maze.length][maze[0].length];
      prettify = new String[maze.length][maze[0].length];
      for (int i = 0; i < maze.length; i++)
        for (int j = 0; j < maze[0].length; j++)
          prettify[i][j] = c[i][j] + "";
    }

    public void solve() {
      for (int i = 0; i < maze.length; i++)
        for (int j = 0; j < maze[0].length; j++)
          if (maze[i][j] == 'S') {
            floodfill(i, j);
            break;
          }
      quantify(prettify);
    }

    public void quantify(String[][] paths) {
      for (int i = 0; i < paths.length; i++)
        for (int j = 0; j < paths[0].length; j++)
          if (paths[i][j].equals("P"))
            System.out.println(i + " " + j);
    }

    public void floodfill(int i, int j) {
      Queue<Integer> q = new LinkedList<>();
      q.add(i * maze[0].length + j);
      while (!q.isEmpty()) {
        int curr = q.remove();
        int x = curr / maze[0].length;
        int y = curr % maze[0].length;
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || visited[x][y] || maze[x][y] == '#')
          continue;
        visited[x][y] = true;
        if (maze[x][y] == '.')
          prettify[x][y] = "B";
        q.add(x * maze[0].length + y - 1);
        q.add(x * maze[0].length + y + 1);
        q.add((x - 1) * maze[0].length + y);
        q.add((x + 1) * maze[0].length + y);
      }
    }
    public String toString(String[][] s) {
      String t = "";
      for (int i = 0; i < s.length; i++) {
        for (int j = 0; j < s[0].length; j++)
          t += s[i][j];
        t += "\n";
      }
      return t;
    }
  }

  public static void main(String... args) {
    File f = new File("maze.txt");
    if (!f.exists() || !f.isFile()) {
      System.out.println("\nFile not found.\nI have created the local 'maze.txt' for you, just put your maze in it.");
      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return;
    }
    try (Scanner sc = new Scanner(new File("maze.txt"))) {
      if(f.length() == 0) {
        System.out.println("\nFile is empty.\nI have created the local 'maze.txt' for you, just put your maze in it.");
        sc.close();
        return;
      }

      ArrayList<ArrayList<Character>> maze = new ArrayList<>();
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        ArrayList<Character> row = new ArrayList<>();
        for (int i = 0; i < line.length(); i++)
          row.add(line.charAt(i));
        maze.add(row);
      }

      char[][] c = new char[maze.size()][maze.get(0).size()];
      for (int i = 0; i < maze.size(); i++)
        for (int j = 0; j < maze.get(0).size(); j++)
          c[i][j] = maze.get(i).get(j);

      Maze m = new Main().new Maze(c);
      m.solve();
      System.out.println(m.toString(m.prettify));


      sc.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
