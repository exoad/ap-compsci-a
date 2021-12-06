package resource.projects.rpsls.examples;

import resource.projects.rpsls.RPSLSStrategy;

public class RandoCalrissian implements RPSLSStrategy {
  public String strategyName() { return "RandoCalrissian"; }

  public String coderName() { return "Cards Against Humanity"; }

  public int getThrow() { return (int)(Math.random() * 5); }

  public void opponentsLastThrow(int lastThrow) {}

  public void reset() {}
}
