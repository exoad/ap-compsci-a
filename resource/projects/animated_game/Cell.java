import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

public class Cell {
  public enum LOCATION_CORNER {
    CORNER_TL,
    CORNER_TR,
    CORNER_BL,
    CORNER_BR,
    CORNER_NT,
  }

  public enum LOCATION_EDGE {
    EDGE_L,
    EDGE_R,
    EDGE_T,
    EDGE_B,
    EDGE_N,
  }

  public enum LOCATION_CENTER {
    CENTER_CM,
    CENTER_NT;
  }

  public static final int CORNER_BLOCKS_TOTAL = 4;
  public static final int EDGE_BLOCKS_TOTAL = (Config.GRID_MODIFIER - 2) * 4;
  public static final int EDGE_BLOCKS_PER_SIDE = Config.GRID_MODIFIER - 2;
  public static final int CORNER_BLOCKS_PER_SIDE = CORNER_BLOCKS_TOTAL / 2;
  public static final int TOTAL_CELLS = Config.GRID_MODIFIER * Config.GRID_MODIFIER;
  public static final int CENTER_CELLS_TOTAL = TOTAL_CELLS - CORNER_BLOCKS_TOTAL - EDGE_BLOCKS_TOTAL;

  private Cell() {
  }

  public static synchronized LOCATION_CORNER isCorner(int row, int col) {
    if (row == 0 && col == 0) {
      return LOCATION_CORNER.CORNER_TL;
    } else if (row == 0 && col == Config.GRID_MODIFIER - 1) {
      return LOCATION_CORNER.CORNER_TR;
    } else if (row == Config.GRID_MODIFIER - 1 && col == 0) {
      return LOCATION_CORNER.CORNER_BL;
    } else if (row == Config.GRID_MODIFIER - 1 && col == Config.GRID_MODIFIER - 1) {
      return LOCATION_CORNER.CORNER_BR;
    }
    return LOCATION_CORNER.CORNER_NT;
  }

  public static synchronized LOCATION_EDGE isEdge(int row, int col) {
    if(isCorner(row, col) != LOCATION_CORNER.CORNER_NT) {
      return LOCATION_EDGE.EDGE_N;
    } else if (row == 0) {
      return LOCATION_EDGE.EDGE_T;
    } else if (row == Config.GRID_MODIFIER - 1) {
      return LOCATION_EDGE.EDGE_B;
    } else if (col == 0) {
      return LOCATION_EDGE.EDGE_L;
    } else if (col == Config.GRID_MODIFIER - 1) {
      return LOCATION_EDGE.EDGE_R;
    }
    return LOCATION_EDGE.EDGE_N;
  }

  public static synchronized LOCATION_CENTER isCenter(int row, int col) {
    if(isCorner(row, col) == LOCATION_CORNER.CORNER_NT && isEdge(row, col) == LOCATION_EDGE.EDGE_N) 
      return LOCATION_CENTER.CENTER_CM;
    return LOCATION_CENTER.CENTER_NT;
  }

  public static synchronized JPanel[] getNeighbors(JPanel[][] cells, JPanel xc) {
    int row = 0, col = 0;
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        if (cells[i][j].equals(xc)) {
          row = i;
          col = j;
          break;
        }
      }
    }
    List<JPanel> neighbors = new ArrayList<>();
    if (isCorner(row, col).equals(LOCATION_CORNER.CORNER_TL)) {
      neighbors.add(cells[row+1][col]);
      neighbors.add(cells[row][col+1]);
      neighbors.add(cells[row+1][col+1]);
    } else if (isCorner(row, col).equals(LOCATION_CORNER.CORNER_TR)) {
      neighbors.add(cells[row][col-1]);
      neighbors.add(cells[row+1][col]);
      neighbors.add(cells[row+1][col-1]);
    } else if (isCorner(row, col).equals(LOCATION_CORNER.CORNER_BL)) {
      neighbors.add(cells[row-1][col]);
      neighbors.add(cells[row][col+1]);
      neighbors.add(cells[row-1][col+1]);
    } else if (isCorner(row, col).equals(LOCATION_CORNER.CORNER_BR)) {
      neighbors.add(cells[row-1][col]);
      neighbors.add(cells[row][col-1]);
      neighbors.add(cells[row-1][col-1]);
    }
    if (isEdge(row, col).equals(LOCATION_EDGE.EDGE_T)) {
      neighbors.add(cells[row+1][col]);
      neighbors.add(cells[row+1][col-1]);
      neighbors.add(cells[row+1][col+1]);
      neighbors.add(cells[row][col-1]);
      neighbors.add(cells[row][col+1]);
    } else if (isEdge(row, col).equals(LOCATION_EDGE.EDGE_B)) {
      neighbors.add(cells[row-1][col]);
      neighbors.add(cells[row-1][col-1]);
      neighbors.add(cells[row-1][col+1]);
      neighbors.add(cells[row][col-1]);
      neighbors.add(cells[row][col+1]);
    } else if (isEdge(row, col).equals(LOCATION_EDGE.EDGE_L)) {
      neighbors.add(cells[row][col+1]);
      neighbors.add(cells[row-1][col+1]);
      neighbors.add(cells[row+1][col+1]);
      neighbors.add(cells[row-1][col]);
      neighbors.add(cells[row+1][col]);
    } else if (isEdge(row, col).equals(LOCATION_EDGE.EDGE_R)) {
      neighbors.add(cells[row][col-1]);
      neighbors.add(cells[row-1][col-1]);
      neighbors.add(cells[row+1][col-1]);
      neighbors.add(cells[row-1][col]);
      neighbors.add(cells[row+1][col]);
    }
    if (isCenter(row, col).equals(LOCATION_CENTER.CENTER_CM)) {
      neighbors.add(cells[row-1][col-1]);
      neighbors.add(cells[row-1][col]);
      neighbors.add(cells[row-1][col+1]);
      neighbors.add(cells[row][col-1]);
      neighbors.add(cells[row][col+1]);
      neighbors.add(cells[row+1][col-1]);
      neighbors.add(cells[row+1][col]);
      neighbors.add(cells[row+1][col+1]);
    }
    System.out.println(neighbors.toString());
    return neighbors.toArray(new JPanel[neighbors.size()]);
  }

  public static int aliveNeighbors(JPanel[] neighbors) {
    int count = 0;
    for (JPanel neighbor : neighbors) {
      if (neighbor.getBackground().equals(Config.ALIVE_COLOR)) {
        count++;
      }
    }
    return count;
  }

  public static int deadNeighbors(JPanel[] neighbors) {
    int count = 0;
    for (JPanel neighbor : neighbors) {
      if (neighbor.getBackground().equals(Config.DEAD_COLOR)) {
        count++;
      }
    }
    return count;
  }

  public static synchronized void printValuesCells() {
    System.out.println(CORNER_BLOCKS_TOTAL);
    System.out.println(EDGE_BLOCKS_TOTAL);
    System.out.println(EDGE_BLOCKS_PER_SIDE);
    System.out.println(CORNER_BLOCKS_PER_SIDE);
    System.out.println(CENTER_CELLS_TOTAL);
    System.out.println(TOTAL_CELLS);
  }

}
