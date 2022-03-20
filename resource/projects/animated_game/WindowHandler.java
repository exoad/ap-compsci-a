import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import java.util.List;
import javax.swing.JPanel;

public class WindowHandler {
  private WindowHandler() {}
  public static class DebugLeave implements WindowListener {
    private Conway gol;
    public DebugLeave(Conway g) {
      this.gol = g;
    }

    @Override
    public synchronized void windowClosing(WindowEvent e) {
      JPanel[][] cells = gol.getCells();
      for (JPanel[] row : cells) {
        for (JPanel cell : row) {
          System.err.print(cell.getBackground() == Config.ALIVE_COLOR ? "1" : "0");
        }
        System.err.println();
      }
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
  } 
}
