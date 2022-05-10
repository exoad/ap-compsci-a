/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package w_brains;

import arena.Action;
import arena.Board;
import arena.Brain;
import arena.Player;
import java.awt.Color;

/**
 *
 * @author tweis0306
 */
public class Shooter implements Brain {

    @Override
    public String getName() {
        return "Shooter";
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public Action getMove(Player p, Board b) {
        return new Action("shoot");
    }

    @Override
    public String getCoder() {
        return "Mr. Weisswange";
    }
    
}
