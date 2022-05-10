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
public class RandoBot implements Brain {
    //behavior weightings
    private final int PASS = 0;
    private final int SHOOT = 3;
    private final int MOVE = 5;
    private final int TURN = 3;
    
    private Random randGen;

    public RandoBot() {
        randGen = new Random();
    }
    
    @Override
    public String getName() {
        return "RandoBot";
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public Action getMove(Player p, Board b) {
        String action;
        int choice;
        if (!p.canShoot()) {
            choice = randGen.nextInt(PASS + MOVE + TURN);
        } else {
            choice = randGen.nextInt(PASS + MOVE + TURN + SHOOT);
        }
        if (choice < PASS) {
            return new Action("pass");
        } else if (choice < PASS + MOVE) {
            return new Action("move", randGen.nextInt(8) * 45);
        } else if (choice < PASS + MOVE + TURN) {
            return new Action("turn", randGen.nextInt(8) * 45);
        } else {
            return new Action("shoot");
        }
            
    }

    @Override
    public String getCoder() {
        return "Mr. Weisswange";
    }
}
