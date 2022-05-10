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
public class Spinner implements Brain {

    @Override
    public String getName() {
        return "Spinner";
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    @Override
    public Action getMove(Player p, Board b) {
        int newDir = Direction.roundTo8(p.getDirection() + 45);
        return new Action("turn", newDir);
    }

    @Override
    public String getCoder() {
        return "Mr. Weisswange";
    }
    
}
