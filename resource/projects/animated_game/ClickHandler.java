import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.Desktop;

import java.net.URI;

import java.io.IOException;

import javax.swing.JPanel;

public class ClickHandler {
  private ClickHandler() {
  }

  public static class CellClickHandler implements MouseListener {
    private Conway gol;

    public CellClickHandler(Conway g) {
      this.gol = g;
    }

    @Override
    public synchronized void mouseClicked(MouseEvent arg0) {
      JPanel cell = (JPanel) arg0.getSource();
      if (cell.getBackground() == Config.ALIVE_COLOR) {
        cell.setBackground(Config.DEAD_COLOR);
      } else {
        cell.setBackground(Config.ALIVE_COLOR);
        if (Config.DEBUG) {
          JPanel[] neighbors = Cell.getNeighbors(gol.getCells(), cell);
          for (JPanel neighbor : neighbors) {
            neighbor.setBackground(Config.DEBUG_NEIGHBOR);
          }
        }
      }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

  }

  public static class ControlPanelHandler implements ActionListener {
    private Conway gol;

    public ControlPanelHandler(Conway g) {
      this.gol = g;
    }

    public Conway getGame() {
      return gol;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(gol.getManualButton())) {
        try {
          Desktop.getDesktop().browse(URI.create(Config.GAME_MANUAL_URL));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      } else if (e.getSource().equals(gol.getReseButton())) {
        gol.reset();
      } else if (e.getSource().equals(gol.getStartButton())) {
        gol.start();
      }
    }

  }

}
