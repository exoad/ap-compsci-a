package resource.projects.rpsls;

public class RockBotTom implements RPSLSStrategy {
  public String strategyName() {
    return "RockBotTom";
  }
  
  public String coderName() {
    return "Anonymous";
  }
  
  public int getThrow() {
    return 0;
  }
  
  public void opponentsLastThrow(int lastThrow) {
    
  }
  
  public void reset() {
    
  }
}
