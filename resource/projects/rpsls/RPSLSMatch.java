package resource.projects.rpsls;

public class RPSLSMatch {
  private RPSLSStrategy player1;
  private RPSLSStrategy player2;
  
  public RPSLSMatch(RPSLSStrategy strat1, RPSLSStrategy strat2) {
    player1 = strat1;
    player2 = strat2;
  }
  
  public RPSLSStrategy getPlayer1() {
    return player1;
  }
  
  public RPSLSStrategy getPlayer2() {
    return player2;
  }
}
