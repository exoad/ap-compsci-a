package main;

/**
  C-License 1.2 RENEWED
  @author Jack Meng
 * @since 1.2
 * COPYRIGHT (C) 2021 JACKMENG
 * File Purpose: The main handler for this project using sub project files
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.pkgs.DaxIO;
import main.pkgs.Eval;
import main.pkgs.Kattio;

public class Runner implements Runnable {
  private static Eval e;
  private final JFrame jf;
  private final JPanel jp;

  /**
   * @throws java.lang.Exception usage of exception inclusive operations
   */
  public static void main(String[] args) throws Exception {
    Kattio sc = new Kattio(System.in);
    DaxIO std = new DaxIO();
  }

  public Runner() {
    jf = new JFrame("Jack Meng - APCS 2021-2022 | Dax Program");

    jp = new JPanel();
    jf.add(jp);
  }

  @Override
  public void run() {
    jf.add(jp);
    jf.pack();
    jf.setVisible(true);
  }
}
