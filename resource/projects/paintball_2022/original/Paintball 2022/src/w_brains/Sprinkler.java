/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package w_brains;

import arena.Action;
import arena.Board;
import arena.Brain;
import arena.Direction;
import arena.Player;
import java.awt.Color;

/**
 *
 * @author tweis0306
 */
public class Sprinkler implements Brain {
    
    private int turnCount;

    @Override
    public String getName() {
        return "Sprinkler";
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }

    @Override
    public Action getMove(Player p, Board b) {
        turnCount++;
        if (turnCount % 3 == 1)
            return new Action("shoot");
        if (turnCount % 3 == 2) {
            int newDir = Direction.roundTo8(p.getDirection() + 45);
            return new Action("turn", newDir);
        }
        return new Action("pass");
    }

    @Override
    public String getCoder() {
        return "Mr. Weisswange";
    }
    
}
