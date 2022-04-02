
/**
 * @author Jack Meng
 * 
 * This class contains everything needed for the program to
 * run my own version of Conway's Game of Life.
 * 
 * This class presents a LoadingWindow and all of the automata
 * functions that utilizes all other util classes to function.
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Random;

public class Game extends JPanel {
  /**
   * This class represents a class that is shown briefly during
   * the original launching the program.
   * 
   * To ensure everythign goes well, it will disappear, if it
   * does not, there could most likely be an uncaught exception.
   */
  public static class LoadingPanel {
    private JFrame frame;
    private JPanel panel;
    private JLabel jl;

    public LoadingPanel() {
      frame = new JFrame("Loading");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      panel = new JPanel();
      panel.setOpaque(true);
      panel.setBackground(Config.FRAME_BG_COLOR);
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      jl = new JLabel("Loading the program...!");
      panel.add(jl);
      frame.add(panel);
      frame.pack();
    }

    /**
     * Gets the frame
     * 
     * @return
     */
    public JFrame getFrame() {
      return frame;
    }

    /**
     * Set visibility of the frame
     */
    public void show() {
      frame.setVisible(true);
    }

    /**
     * Set visibility of the frame
     */
    public void hide() {
      frame.setVisible(false);
    }
  }

  private JFrame f;
  private Random seeder = new Random();
  private JPanel controlPanel;
  private JButton startButton, resetButton;
  private JLabel stopWatch, generationCount;
  private boolean started = false;
  private Cell[][] cells = new Cell[Config.GRID_MODIFIER][Config.GRID_MODIFIER];
  private transient Thread worker = new Thread(), slave = new Thread();
  private int counter = 0;

  /**
   * Init everything in the program and show the LoadingWindow
   */
  public Game() {
    LoadingPanel lp = new LoadingPanel();
    long time = System.currentTimeMillis();
    lp.show();
    f = new JFrame("Jack Meng - Conway's Game of Life");
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setPreferredSize(Config.WINDOW_SIZE);
    f.setLayout(new BorderLayout());
    f.setBackground(Config.FRAME_BG_COLOR);
    f.addWindowListener(Config.DEBUG ? new WindowHandler.DebugLeave(this) : null);
    setPreferredSize(Config.PANE_WINDOW_SIZE);
    setBackground(Config.FRAME_BG_COLOR);
    setLayout(new GridLayout(Config.GRID_MODIFIER, Config.GRID_MODIFIER));
    for (int i = 0; i < Config.GRID_MODIFIER; i++) {
      Cell[] row = new Cell[Config.GRID_MODIFIER];
      for (int j = 0; j < Config.GRID_MODIFIER; j++) {

        row[j] = new Cell(new Dimension(Config.CELL_SIZE_MODIFIER, Config.CELL_SIZE_MODIFIER),
            Config.CONSOLE_FOREGROUND, Config.DEAD_COLOR, Config.BORDER_COLOR,
            this);
        add(row[j]);
      }
      cells[i] = row;
    }
    controlPanel = new JPanel();
    controlPanel
        .setPreferredSize(new Dimension((int) (Config.WINDOW_SIZE.getWidth() - Config.PANE_WINDOW_SIZE.getWidth()),
            (int) Config.PANE_WINDOW_SIZE.getHeight() / 2));
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
    controlPanel.setOpaque(true);
    controlPanel.setBackground(Config.FRAME_BG_COLOR);
    startButton = new JButton(Config.START_BTN_TEXT);
    startButton.addActionListener(new ClickHandler.ControlPanelHandler(this));
    startButton.setForeground(Config.CONTROL_PANEL_BUTTON_FG);
    startButton.setBackground(Config.CONTROL_PANEL_BUTTON_BG);
    startButton.setBorder(BorderFactory.createLineBorder(Config.CONTROL_PANEL_BUTTON_FG));
    startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    resetButton = new JButton("Reset");
    resetButton.addActionListener(new ClickHandler.ControlPanelHandler(this));
    resetButton.setForeground(Config.CONTROL_PANEL_BUTTON_FG);
    resetButton.setBackground(Config.CONTROL_PANEL_BUTTON_BG);
    resetButton.setBorder(BorderFactory.createLineBorder(Config.CONTROL_PANEL_BUTTON_FG));
    resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    stopWatch = new JLabel("Elapsed Time: " + 0 + " ms");
    stopWatch.setForeground(Config.CONTROL_PANEL_LABEL_FG);
    stopWatch.setBackground(Config.CONTROL_PANEL_BUTTON_BG);
    stopWatch.setAlignmentX(Component.CENTER_ALIGNMENT);
    generationCount = new JLabel("Generation Counter: " + counter);
    generationCount.setForeground(Config.CONTROL_PANEL_LABEL_FG);
    generationCount.setBackground(Config.CONTROL_PANEL_BUTTON_BG);
    generationCount.setAlignmentX(Component.CENTER_ALIGNMENT);
    controlPanel.add(stopWatch);
    controlPanel.add(Box.createVerticalStrut(Config.VERT_STRUCT));
    controlPanel.add(generationCount);
    controlPanel.add(Box.createVerticalStrut(Config.VERT_STRUCT));
    controlPanel.add(startButton);
    controlPanel.add(Box.createVerticalStrut(Config.VERT_STRUCT));
    controlPanel.add(resetButton);
    controlPanel.add(Box.createVerticalStrut(Config.VERT_STRUCT));
    DebugConsole dc = new DebugConsole();
    dc.setAlignmentX(Component.CENTER_ALIGNMENT);
    controlPanel.add(dc);
    f.add(controlPanel, BorderLayout.EAST);
    f.add(this, BorderLayout.WEST);
    f.pack();
    f.setVisible(true);
    lp.hide();
    System.out.println("== Load-Up Stats: ==\nTime taken: " + (System.currentTimeMillis() - time) + "ms\nLoaded Cells: "
        + (cells.length * cells.length));
    System.out.println("Memory used: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
        / (1024 * 1024) + "MB");
  }

  /**
   * Constantly updates states of the window
   */
  public void titleFrameUpdater() {
    new Thread(() -> {
      while (true) {
        f.setTitle("Jack Meng's Game of Life | Memory Used: "
            + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + "MB");
        try {
          Thread.sleep(Config.MAX_TLE);
        } catch (InterruptedException e) {
          System.out.println("Worker Killed.");
        }
      }
    }).start();
  }

  /**
   * Returns the cells 2D array
   * 
   * @return JPanel[][]
   */
  public synchronized JPanel[][] getCells() {
    return cells;
  }

  /**
   * The seeder so there would not be multiple Randoms
   * 
   * @return Random
   */
  public synchronized Random getSeeder() {
    return seeder;
  }

  /**
   * @return JButton
   */
  public synchronized JButton getStartButton() {
    return startButton;
  }

  /**
   * @return JButton
   */
  public synchronized JButton getReseButton() {
    return resetButton;
  }

  /**
   * Starts the process of the game.
   * 
   * Comparing this to the regular Conway's of Game of Life, this game is
   * much more different rule, where:
   * 1. If a cell has 3 or more alive neighbors, it dies
   * 2. If a cell has 2 or 3 alive neighbors, it lives
   * 
   * If the cell is dead:
   * 1. It can be revived if there are 3 alive neighbors
   */
  public synchronized void start() {
    if (!Config.DEBUG) {
      if (!started) {
        worker = new Thread(() -> {
          while (true) {
            for (int i = 0; i < Config.GRID_MODIFIER; i++) {
              for (int j = 0; j < Config.GRID_MODIFIER; j++) {
                int alive = 0;
                for (int k = -1; k <= 1; k++) {
                  for (int l = -1; l <= 1; l++) {
                    if (i + k >= 0 && i + k < Config.GRID_MODIFIER && j + l >= 0
                        && j + l < Config.GRID_MODIFIER) {
                      if (cells[i + k][j + l].getBackground().equals(Config.ALIVE_COLOR)) {
                        alive++;
                      }
                    }
                  }
                }
                if (cells[i][j].getBackground().equals(Config.ALIVE_COLOR)) {
                  if (alive >= 4) {
                    cells[i][j].setBackground(Config.DEAD_COLOR);
                  } else if (alive == 2 || alive == 3) {
                    cells[i][j].setBackground(Config.ALIVE_COLOR);
                  }
                } else {
                  if (alive == 3) {
                    cells[i][j].setBackground(Config.ALIVE_COLOR);
                  }
                }
              }
            }
            counter++;
            generationCount.setText("Generation Counter: " + counter);
            try {
              Thread.sleep(Config.MAX_GENERATION_TLE);
            } catch (InterruptedException e) {
              break;
            }

          }
        });
        worker.start();
        started = true;
      } else {
        System.out.println("Game is already running!");
      }
    } else {
      System.out.println("!!! Cannot play with DEBUG Mode on !!!");
    }
  }

  /**
   * Resets the entire grid to the original state; all dead cells.
   */
  public synchronized void reset() {
    new Thread(() -> {
      if (worker != null) {
        worker.interrupt();
      }
      if (slave != null) {
        slave.interrupt();
        slave = new Thread();
        counter = 0;
        generationCount.setText("Generation Counter: " + counter);
      }
      Bernard.writeImage(cells);
      started = false;
      for (JPanel[] row : cells) {
        for (JPanel b : row) {
          b.setBackground(Config.DEAD_COLOR);
        }
      }
    }).start();

  }
}
