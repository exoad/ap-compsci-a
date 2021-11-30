package main.pkgs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Window implements ActionListener, Runnable {
  private JPanel jf;
  private Dimension windowSize;

  public Window(String windowName, Dimension windowSize) {
    this.windowSize = windowSize;
    jf = new JFrame(windowName);
  }

  @Override
  public void run() {
    jf.pack();
    jf.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {}
}
