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

public class Runner {
  public Runner() {
    
  }
  /**
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws java.lang.Exception {
    Kattio sc = new Kattio(System.in);
    DaxIO stdout = new DaxIO();
    stdout.println("Enter file");
    //nvm
    
    Runtime.getRuntime().exec("javac ./test/Test.java && java ./test/Test");


    
    //Process p = Runtime.getRuntime().exec(null);
  }  
}
