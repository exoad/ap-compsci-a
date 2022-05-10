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
public class DumbBaseCharger implements Brain {
    
    int myTeam = -1;
    int baseRow = 16;
    int baseCol = -1;

    @Override
    public String getName() {
        return "DumbBaseCharger";
    }

    @Override
    public String getCoder() {
        return "Mr. Weisswange";
    }

    @Override
    public Color getColor() {
        return Color.PINK;
    }

    @Override
    public Action getMove(Player p, Board b) {
        if (myTeam == -1) {
            myTeam = p.getTeam();
            if (myTeam == 1)
                baseCol = 49;
            else
                baseCol = 0;
        }
        int distToBase = Direction.moveDistance(p.getRow(), p.getCol(),
                                   baseRow, baseCol);
        int dirToBase = Direction.getDirectionTowards(p.getRow(), p.getCol(),
                                   baseRow, baseCol);
        if (distToBase == 1) {
            if (dirToBase != p.getDirection())
                return new Action("T", dirToBase);
            else
                return new Action("S");
        } else {
            int[] nextSpace = Direction.getLocInDirection(p.getRow(),
                    p.getCol(), dirToBase);
            if (b.isEmpty(nextSpace[0], nextSpace[1]))
                return new Action("M", dirToBase);
            else
                return new Action("M", 
                        45* ((int) (Math.random() * 8)) );
        }
    }
    
}
