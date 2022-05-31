/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

/**
 *
 * @author andrea
 */
public class PaintballMatch {
    private Player player1;
    private Player player2;
    
    public PaintballMatch(Brain strat1, Brain strat2) {
    player1 = new Player(1, strat1);
    player2 = new Player(2, strat2);
  }
    
  public Player getPlayer1() {
    return player1;
  }
  
  public Player getPlayer2() {
    return player2;
  }
}