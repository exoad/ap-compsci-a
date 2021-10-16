package main.pkgs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Eval {
  public Eval(String cmd) throws Exception {
    runProcess(cmd);
  }
  
  private static void runProcessWithComment(String command) throws Exception {
    Process pro = Runtime.getRuntime().exec(command);
    printLines(command + " stdout:", pro.getInputStream());
    printLines(command + " stderr:", pro.getErrorStream());
    pro.waitFor();
    System.out.println(command + " \nreturned " + pro.exitValue());
  }
  
  private static void runProcess(String command) throws Exception {
    Process pro = Runtime.getRuntime().exec(command);
    printLines(pro.getInputStream());
    printLines(pro.getErrorStream());
    pro.waitFor();
  }

  protected static void printLines(String cmd, InputStream ins) throws Exception {
    String line = null;
    BufferedReader in = new BufferedReader(new InputStreamReader(ins));
    while ((line = in.readLine()) != null) {
      System.out.println(cmd + " " + line);
    }
  }
  
  protected static void printLines(InputStream ins) throws Exception {
    String line = null;
    BufferedReader in = new BufferedReader(new InputStreamReader(ins));
    while ((line = in.readLine()) != null) {
      System.out.println(line);
    }
  }
  
}
