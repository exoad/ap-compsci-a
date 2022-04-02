/**
 * @author Jack Meng
 * 
 * Similar to the ClickHandler class,
 * this class is a side util class meant
 * to handle window events instead of clogging
 * up the main functionality class; Game
 */

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class WindowHandler {
  private WindowHandler() {}
  public static class DebugLeave implements WindowListener {
    private Game gol;
    public DebugLeave(Game g) {
      this.gol = g;
    }

    /**
     * Debug values to the System.err printstream
     */
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
