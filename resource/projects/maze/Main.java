import java.util.Scanner;
import java.io.File;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.System.*;

public class Main {
    public class Point {
        public int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private Integer[] ROWC = {
        0,
        1,
        0,
        -1
    }, COLC = {
        1,
        0,
        -1,
        0
    };
    private Integer[][] maze;
    private boolean[][] visited;
    private int currRow, currCol;
    public Main(final ArrayList < ArrayList < Integer >> maze) {
        this.maze = maze.stream().map(u - > u.toArray(new Integer[0])).toArray(Integer[][]::new);
        this.visited = new boolean[maze[0].length][maze.length];
    }

    /**
     * <p>
     * This function performs the necessary floodFill operation
     * however one can simply alter the Stack to a queue to
     * use this algorithm into a BFS-like algorithm.
     * </p>
     *
     * @param r Row
     * @param c Column
     */
    public void flood(int r, int c) {
        Stack < Point > maps = new Stack < > ();
        maps.push(new Point(r, c));
        while (!maps.isEmpty()) {
            Point temp = maps.pop();
            r = temp.row;
            c = temp.col;
            if (r < 0 || r >= currRow || c < 0 || c >= currCol || Integer[r][c] == 0 || visited[r][c])
                continue;
            visited[r][c] = true;
            for (int i = 0; i < 4; i++) {
                maps.add(new Point(r + ROWC[i], c + COLC[i]));
            }
        }
    }

    public String toString() {
        return null;
    }

    public static void main(String[] args) throws java.io.IOException {
        Scanner sc;
        try {
            sc = new Scanner(new File("maze.txt"));
        } catch (java.io.FileNotFoundException fnofe) {
            System.err.println("The maze file was not found");
            System.exit(2);
        }
        sc = new Scanner(System.in);
        System.out.println("File not found, please enter from STDIN.");
        ArrayList < ArrayList < ? >> maze = new ArrayList < > ();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            maze.add((ArrayList) Arrays.asList(line.toIntegerArray()));
        }

        sc.close();
    }
}