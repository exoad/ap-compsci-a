import java.util.Scanner;
import java.io.File;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.Math.*;

public class Main {
    public class Point {
        public int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private ArrayList<ArrayList<?>> toSolve = new ArrayList<>();
    private ArrayList<ArrayList<boolea>> visited = new ArrayList<>();
    private int currRow, currCol;
    public Main(ArrayList<ArrayList<?>> maze) {
        toSolve = maze;
    }
    
    public void flood(int r, int c) {
        Stack<Point> maps = new Stack<>();
        maps.push(new Point(r, c));
        while(!maps.isEmpty()) {
            Point temp = maps.pop();
            r = temp.row;
            c = temp.col;
            if(r < 0 || row >= currRow || )
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
        ArrayList<ArrayList<?>> maze = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            maze.add((ArrayList) Arrays.asList(line.toCharArray()));
        }
        
        sc.close();
    }
}
