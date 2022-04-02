/**
 * @author Jack Meng
 * Class: APCS
 * 
 * The action listener for the cells
 * 
 * Project: Game of Life
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


import javax.swing.JPanel;

public class ClickHandler {
  private ClickHandler() {
  }

  public static class CellClickHandler implements MouseListener {
    private Game gol;

    public CellClickHandler(Game g) {
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
            if(!neighbor.getBackground().equals(Config.ALIVE_COLOR))
              neighbor.setBackground(Config.DEBUG_NEIGHBOR);
          }
          String constructor = "Neighbors: " + neighbors.length;
          System.out.println(constructor);
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
    private Game gol;

    public ControlPanelHandler(Game g) {
      this.gol = g;
    }

    public Game getGame() {
      return gol;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(gol.getReseButton())) {
        gol.reset();
      } else if (e.getSource().equals(gol.getStartButton())) {
        gol.start();
      }
    }

  }

}
