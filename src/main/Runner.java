package main;

/**
 * C-License 1.2 RENEWED
 * @author Jack Meng
 * @since 1.2
 * COPYRIGHT (C) 2021 JACKMENG
 * File Purpose: The main handler for this project using sub project files
 */

import main.pkgs.DaxIO;
import main.pkgs.Kattio;
import main.pkgs.Eval;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Runner {
  protected static Eval e;
  protected static Kattio sc;
  protected static DaxIO std;
  /**
   * @throws java.lang.Exception usage of exception inclusive operations
   */
  public static void main(String[] args) throws Exception {
    sc = new Kattio(System.in);
    std = new DaxIO();
    std.println("Enter file");
    
    new Eval("java ./test/Test.java");
  }  
  public Runner() {
    JFrame jf = new JFrame("Jack Meng - APCS 2021-2022 | Dax Program");


    JPanel jp = new JPanel();
    jf.add(jp);
    jf.pack();
    jp.setVisible(true);
  }
  @Override
  public void run() {
    jf.add(jp);
    jf.pack();
  }
}
