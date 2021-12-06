package resource.projects.rpsls.examples;

import resource.projects.rpsls.RPSLSStrategy;

public class SheldonCooperBot implements RPSLSStrategy {
  public String strategyName() { return "SheldonCooperBot"; }

  public String coderName() { return "Jim Parsons"; }

  public int getThrow() { return 4; }

  public void opponentsLastThrow(int lastThrow) {}

  public void reset() {}
}
