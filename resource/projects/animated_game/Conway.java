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

public class Conway extends JPanel {
  public static class LoadingPanel {
    private JFrame frame;
    private JPanel panel;
    private JLabel jl;

    public LoadingPanel() {
      frame = new JFrame("Loading");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      panel = new JPanel();
      setOpaque(true);
      setBackground(Config.FRAME_BG_COLOR);
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      jl = new JLabel("Loading the program...!");
      panel.add(jl);
      frame.add(panel);
      frame.pack();
    }

    public JFrame getFrame() {
      return frame;
    }

    public void show() {
      frame.setVisible(true);
    }

    public void hide() {
      frame.setVisible(false);
    }
  }

  private JFrame f;
  private Random seeder = new Random();
  private JPanel controlPanel;
  private JButton startButton, resetButton, readManual;
  private boolean started = false;
  private JPanel[][] cells = new JPanel[Config.GRID_MODIFIER][Config.GRID_MODIFIER];
  private transient Thread worker = new Thread();

  public Conway() {
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
      JPanel[] row = new JPanel[Config.GRID_MODIFIER];
      for (int j = 0; j < Config.GRID_MODIFIER; j++) {
        JPanel b = new JPanel();
        b.setPreferredSize(new Dimension(Config.CELL_SIZE_MODIFIER, Config.CELL_SIZE_MODIFIER));
        b.setOpaque(true);
        b.setForeground(null);
        b.setBorder(BorderFactory.createLineBorder(Config.BORDER_COLOR));
        b.addMouseListener(new ClickHandler.CellClickHandler(this));
        b.setBackground(
            Config.DEBUG ? (seeder.nextInt() % 2 == 0 ? Config.ALIVE_COLOR : Config.DEAD_COLOR) : Config.DEAD_COLOR);
        add(b);
        row[j] = b;
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
    readManual = new JButton("How To Play");
    readManual.addActionListener(new ClickHandler.ControlPanelHandler(this));
    readManual.setForeground(Config.CONTROL_PANEL_BUTTON_FG);
    readManual.setBackground(Config.CONTROL_PANEL_BUTTON_BG);
    readManual.setBorder(BorderFactory.createLineBorder(Config.CONTROL_PANEL_BUTTON_FG));
    readManual.setAlignmentX(Component.CENTER_ALIGNMENT);
    controlPanel.add(startButton);
    controlPanel.add(Box.createVerticalStrut(Config.VERT_STRUCT));
    controlPanel.add(resetButton);
    controlPanel.add(Box.createVerticalStrut(Config.VERT_STRUCT));
    controlPanel.add(readManual);
    controlPanel.add(Box.createVerticalStrut(Config.VERT_STRUCT * 25));
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
  
  public void titleFrameUpdater() {
    new Thread(() -> {
      while(true) {
        f.setTitle("Jack Meng - Conway's Game of Life | Memory Used: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + "MB"); 
        try {
          Thread.sleep(Config.MAX_TLE);
        } catch (InterruptedException e) {
          System.out.println("Worker Killed."); 
        }
      }
    }).start();
  }

  public synchronized JPanel[][] getCells() {
    return cells;
  }

  public synchronized JButton getStartButton() {
    return startButton;
  }

  public synchronized JButton getReseButton() {
    return resetButton;
  }

  public synchronized JButton getManualButton() {
    return readManual;
  }

  public synchronized void start() {
    if (!started) {
      worker = new Thread(() -> {
        while (true) {

          // apply rules
          for (int i = 0; i < Config.GRID_MODIFIER; i++) {
            for (int j = 0; j < Config.GRID_MODIFIER; j++) {
              if (i == 0 || i == Config.GRID_MODIFIER - 1 || j == 0 || j == Config.GRID_MODIFIER - 1) {
                continue;
              }
              int alive = 0;
              for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                  if (cells[i + k][j + l].getBackground() == Config.ALIVE_COLOR) {
                    alive++;
                  }
                }
              }
              if (cells[i][j].getBackground() == Config.ALIVE_COLOR) {
                if (alive < 2 || alive > 3) {
                  cells[i][j].setBackground(Config.DEAD_COLOR);
                }
              } else if (alive == 3) {
                cells[i][j].setBackground(Config.ALIVE_COLOR);
              }
            }
          }
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
  }

  public synchronized void reset() {
    new Thread(() -> {
      if (worker != null) {
        worker.interrupt();
        System.out.println(worker.getState().toString());
      }

      started = false;
      for (JPanel[] row : cells) {
        for (JPanel b : row) {
          b.setBackground(Config.DEAD_COLOR);
        }
      }
    }).start();

  }
}
