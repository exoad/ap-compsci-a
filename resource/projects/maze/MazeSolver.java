import java.io.*;

import java.util.*;

/**
 * <p>
 * This main big class will hold:
 * <ul>
 * <li>The maze itself and the solver class</li>
 * <li>The maze configuration class</li>
 * <li>A Pair utility class</li>
 * </ul>
 * 
 * This class itself only will contain the 
 * main method which is used for basic user IO &
 * interactions.
 * </p>
 * 
 * @author Jack Meng
 * @see MazeSolver.Maze
 * @see MazeSolver.Config
 * @see MazeSolver.Pair
 */
public class MazeSolver {
  /**
   * <p>
   * An inheritance or a literal
   * copy of the C++ std::pair where
   * this is created to suffice
   * the usage of points and more.
   * </p>
   * 
   * @author Jack Meng
   * @see java.awt.Point
   */
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

  /**
   * <p>
   * This static nested class
   * represents most if not everything the user 
   * can config about the program within source code 
   * in order to allow for the user to swap out
   * different variables to suit their needs.
   * </p>
   * 
   * @author Jack Meng
   */
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

  /**
   * <p>
   * This method represents a
   * generic Maze and the potential Maze Solver
   * inside, however this class is not the main
   * class of the project to actually run it.
   * 
   * Use the MazeSolver (outer class) to run the 
   * program.
   * </p>
   * @author Jack Meng
   */
  public class Maze {
    private char[][] originalPrettyMaze;
    private int[][] parsedMaze;
    private boolean[][] visited;
    private boolean right = true;
    private int footSteps = 0;
    private Pair<Integer, Integer> current = new Pair<>(0, 0);
    private Thread worker = new Thread();

    public Maze(char[][] c, boolean right) {
      originalPrettyMaze = c;
      visited = new boolean[originalPrettyMaze.length][originalPrettyMaze[0].length];
      quantify();
      this.right = right;
    }

    /**
     * <p>
     * This method is the main entry point into the MazeSolver
     * program.
     * 
     * It will first find the start as defined in 
     * {@link Config#START}. However the end is not
     * required to be defined in the maze and will be found
     * during the floodFill algorithm {@link #floodFill(int, int)}.
     * </p>
     * 
     * @see #floodFill(int, int)
     * @see MazeSolver.Config
     */
    public void solve() {
      for (int i = 0; i < originalPrettyMaze.length; i++)
        for (int j = 0; j < originalPrettyMaze[0].length; j++)
          if (originalPrettyMaze[i][j] == Config.START) {
            current = new Pair<>(i, j);
            floodfill2(i, j);
          }
    }

    /**
     * <p>
     * This method basically parses those values of the original 
     * unparsed "pretty" maze and parses them into numbers based
     * on the values given in {@link MazeSolver.Config}
     * </p>
     * @see MazeSolver.Config
     */
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

    /**
     * <p>
     * This method is used to check if the given
     * coordinates is DeadEnd.
     * 
     * To fulfill a deadend requirement, the following:
     * <ul>
     * <li>The current cell must be a path cell.</li>
     * <li>The current cell must have at least one adjacent path cell.</li>
     * <li>The three cells are wall cells.</li>
     * <li>There must be at least one path cell between the three cells.</li>
     * </ul>
     * </p>
     * 
     * @param i row index
     * @param j column index
     * @return true if the given coordinates is DeadEnd, false otherwise
     */
    private boolean isDeadEnd(int i, int j) {
      return parsedMaze[i][j] == Config.WALL_INT && parsedMaze[i - 1][j] == Config.WALL_INT
          && parsedMaze[i + 1][j] == Config.WALL_INT && parsedMaze[i][j - 1] == Config.WALL_INT
          && parsedMaze[i][j + 1] == Config.WALL_INT;
    }

    /**
     * <p>
     * This method is constantly called from the {@link MazeSolver.Maze#floodFill(int, int)} 
     * to determine if the program should be ended
     * </p>
     * @param p A Pair representing a coordinate for the stats to print
     */
    private synchronized void callEnd(final Pair<Integer, Integer> p) {
      worker.interrupt();
      System.out.println(toString(parsedMaze));
      System.out.println(
          "== MAZE SOLVED ==\nStats:\nFoot Steps: " + footSteps + "\nFound End At: "
              + p.toString());
      System.exit(0);
    }

    /**
     * <p>
     * Input:
     * Input can either be hardcoded or we can read it from a file. For this part it
     * will be your decision. Another thing
     * is the characters presented, for the most part it is much easier for one to
     * use numbers instead of ASCII chars,
     * this allows us to manipulate the numbers easily (+, -, /, *).
     * 
     * Output:
     * One can output each tick (each recursive call) or we can just outout the
     * original, then we instantly output our
     * finished maze with the modifed array.
     * 
     * Storing Data:
     * One can store your data in many different ways, for example, for my part I
     * used the Flood-Fill algo, which mostly
     * involves the usage of a queue or a stack. However, one doesn't have to do
     * this and just use something like a Hashmap:
     * HashMap: Key: maze-val, Value: status (blocked, etc.)
     * 
     * Or
     * 
     * A simple 2D array which is much more primitive however this allows for much
     * intensive actions to be executed in a short
     * amount of time (increase in time for runtime).
     * 
     * ------
     * 
     * Basically after asking the teacher, it is required for the project to have at
     * least FOUR recursive calls, I used these 4
     * recursive calls for looking in the directions of the blocks near us.
     * 
     * Now there are many other ways to approach this, but for me one could quickly
     * identify this could be solveable with a simple
     * implementation of a FF-algorithm (Flood-Fill), however one could also try a
     * BFS-approach (although this seems counter-intuitive).
     * 
     * (To use a BFS-approach, one can simply just use a Queue inplace of a Stack
     * when compared to the usage of a Flood-Fill implementation.)
     * 
     * Flood-Fill in simple terms is basically an algorithm that runs through the
     * avaliable paths and literally fill them with a value.
     * Flood-Fill for our example will of course utilize 4 major recursive calls
     * which will help us to walk in the directions:
     * 1. Right
     * 2. Left
     * 3. Up
     * 4. Down
     * 
     * Each of these directions will be checked and we will continously call this
     * method to either backtrack
     * or move forward in that desired direction.
     * 
     * Whenever we traverse a spot where we have previously gone, we can the mark
     * that area with a final char, where our program will know to exclude when it
     * is checking.
     * 
     * One can find a good pseudocode-ish implementation of this algorithm here:
     * https://en.wikipedia.org/wiki/Flood_fill
     * 
     * Each of these will calls for each direction will fill each avaliable "open"
     * path. Each time it is called, it will attempt
     * this recursive behavior for a N times or 1 time and thus proceed to whenever
     * it finds the finish.
     * 
     * However we cannot simply just move whenever there is an open space, we can
     * then determine if such path is a "valid" path.
     * 
     * Given this we end with a time complexity of somewhere around O(nm) where n =
     * rows & m = columns. However, the only thing you
     * have to watch out for is that you are allocating the resources properly
     * without running into some kinda of stackoverflowerror, etc..
     * 
     * <br>
     * A similar approach can be done so via the following:
     * <code>
     * private void floodfillButWithAStack(int i, int j) {
     * Deque<Pair<Integer, Integer>> stack = new ArrayDeque<>();
     * stack.push(new Pair<>(i, j));
     * while (!stack.isEmpty()) {
     * Pair<Integer, Integer> p = stack.pop();
     * if (p.first < 0 || p.first >= originalPrettyMaze.length || p.second < 0
     * || p.second >= originalPrettyMaze[0].length)
     * continue;
     * if (Objects.equals(p.first, current.first) && Objects.equals(p.second,
     * current.second))
     * continue;
     * if (parsedMaze[p.first][p.second] == Config.PATH_INT) {
     * parsedMaze[p.first][p.second] = footSteps;
     * visited[p.first][p.second] = true;
     * if (isDeadEnd(p.first, p.second)) {
     * footSteps++;
     * continue;
     * }
     * stack.push(new Pair<>(p.first - 1, p.second));
     * stack.push(new Pair<>(p.first + 1, p.second));
     * stack.push(new Pair<>(p.first, p.second - 1));
     * stack.push(new Pair<>(p.first, p.second + 1));
     * }
     * }
     * }
     * <code>
     * 
     * <br>
     * Performs using four recursive calls, each of which will perform a floodfill
     * </p>
     * @param i The first coordinate 
     * @param j The second coordinate (y)
     */
    private void floodfill2(int i, int j) {
      if (right)
        System.out.println(toString(parsedMaze));
      if (i < 0 || j < 0 || i >= originalPrettyMaze.length || j >= originalPrettyMaze[0].length)
        return;
      if (parsedMaze[i][j] == Config.WALL_INT) {
        return;
      }
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
        if (right) {
          System.out.println(toString(parsedMaze));

          try {
            Thread.sleep(Config.TLE);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        if (originalPrettyMaze[i][j] == Config.END) {
          callEnd(new Pair<>(i, j));
          return;
        }

        floodfill2(i + 1, j);
        floodfill2(i - 1, j);
        floodfill2(i, j + 1);
        floodfill2(i, j - 1);
      }

    }

    /**
     * <p>
     * Overriden method from the superclass.
     * {@link java.lang.Object#toString()}
     * 
     * Prints the maze in a pretty format.
     * </p>
     * @param s The underlying maze
     * @return The pretty maze in a String format
     */
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

  }

  /**
   * <p>
   * Will read and run the program from the command line and also
   * read the maze from a file.
   * </p>
   * @param args Main method to start the program, however it is not used for arguments
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
      Scanner sc2 = new Scanner(System.in);
      System.out.println("Right to the results? (y/n)");
      String ans = sc2.nextLine();
      sc2.close();
      Maze m;
      if (ans.equals("y")) {
        m = new MazeSolver().new Maze(c, false);
      } else {
        m = new MazeSolver().new Maze(c, true);
      }
      m.solve();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
