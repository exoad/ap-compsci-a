/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arena;

import java.awt.Color;

/**
 * AI for players of the Paintball game. All strategies to be used should
 * implement this interface and must be placed in the brains package.
 * @author Thomas Weisswange
 */
public interface Brain {
    /**
     * Returns the name of the Paintball strategy ("bot").
     * @return the name of this strategy
     */
    public String getName();
    
    /**
     * Returns the name of the *person* who wrote the code.
     * @return the name of the Brain's coder.
     */
    public String getCoder();
    
    /**
     * Returns the color of the Player that uses this strategy.
     * @return the Player's color
     */
    public Color getColor();
    
    /**
     * Returns the chosen action by the given Player
     * located on the given Board.
     * @return the passed Player's chosen Action.
     * @param p the Player to act. Note that the player is passed as an argument,
     * this way multiple Player objects could utilize the same Brain. However,
     * if the Brain stores local information about its particular Player, then
     * a separate instance of the class implementing Brain should be created
     * for each Player.
     */
    public Action getMove(Player p, Board b);
}
