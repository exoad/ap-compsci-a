/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arena;

/**
 *
 * @author tweis0306
 */
public class Base extends Occupant {
    private int numHits;

    Base(int team) {
        super(team);
    }

    public int getNumHits() {
        return numHits;
    }
    
    void hit(Shot shot) {
        numHits++;
        if(team != shot.getOwner().getTeam()) {
            shot.getOwner().hitEnemyBase();
            myBoard.scorePoints(3 - team, 3);
        } else {
            shot.getOwner().hitOwnBase();
            myBoard.losePoints(team, 3);
        }
    }

    @Override
    boolean moveTo(int row, int col) {
        return false;
    }
    
    @Override
    public String toString() {
        return "Occupant: team " + team + " located at "
                + row + ", " + col;
    }
}
