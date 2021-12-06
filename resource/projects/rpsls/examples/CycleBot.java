package resource.projects.rpsls;

public class CycleBot implements RPSLSStrategy {
  private int oppLast;
  
  public String strategyName() {
    return "CycleBot";
  }
  
  public String coderName() {
    return "Zinnderella";
  }
  
  public int getThrow() {
    return (oppLast + 1) % 5;
  }
  
  public void opponentsLastThrow(int lastThrow) {
    oppLast = lastThrow;
  }
  
  public void reset() {
    
  }
}
