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
import java.util.Random;

/**
 *
 * @author tweis0306
 */
public class RandomWalker implements Brain {

    private Random randGen;
    
    public RandomWalker() {
        randGen = new Random();
    }

    @Override
    public String getName() {
        return "Random walker";
    }

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }

    @Override
    public Action getMove(Player p, Board b) {
        return new Action("move", randGen.nextInt(8) * 45);
    }

    @Override
    public String getCoder() {
        return "Mr. Weisswange";
    }
    
}
