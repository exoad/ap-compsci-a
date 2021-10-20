package main.pkgs;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements ActionListener, Runnable {
  private JPanel jf;
  private Dimension windowSize;
  
  public Window(String windowName, Dimension windowSize) {
    this.windowSize = windowSize;
    jf = new JFrame(windowName);
  }
  
  @Override
  public void run() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

}
