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
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws java.lang.Exception {
    sc = new Kattio(System.in);
    std = new DaxIO();
    std.println("Enter file");
    //nvm
    
    new Eval("java ./test/Test.java");


    
    //Process p = Runtime.getRuntime().exec(null);
  }  
}
