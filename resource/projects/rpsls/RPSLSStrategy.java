package resource.projects.rpsls;

public interface RPSLSStrategy {
  public String strategyName();
  public String coderName();
  // 0 = rock (beats scissors, lizard)
  // 1 = paper (beats rock, Spock)
  // 2 = scissors (beats paper, lizard)
  // 3 = lizard (beats paper, Spock)
  // 4 = Spock (beats scissors, rock)
  public int getThrow();
  public void opponentsLastThrow(int lastThrow);
  public void reset();
}
