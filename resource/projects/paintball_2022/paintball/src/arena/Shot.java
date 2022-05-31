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
public class Shot extends Occupant {
    private int direction;
    private Player owner;
    private boolean firstMove;
    
    Shot(int team, int direction, Player owner) {
        super(team);
        this.direction = direction;
        this.owner = owner;
        firstMove = true;
    }

    public int getDirection() {
        return direction;
    }

    public Player getOwner() {
        return owner;
    }
    
    void move() {
        //make sure shot is on the board
        if (myBoard == null)
            return;
        //determine intended destination location
        int destRow, destCol;
        if (firstMove) {
            firstMove = false;
            return;
        } else {
            int[] dest = Direction.getLocInDirection(row, col, 
                    direction);
            destRow = dest[0];
            destCol = dest[1];
        }
        
        //check for move-off-of-board
        if (!myBoard.isValid(destRow, destCol)) {
            removeSelfFromBoard();
            return;
        }
        
        //if space in front is empty, just move
        if (myBoard.isEmpty(destRow, destCol)) {
            moveTo(destRow,destCol);
            return;
        }
        
        //resolve collision with object in front
        Occupant target = myBoard.get(destRow, destCol);
        if (target instanceof Blocker) {
            removeSelfFromBoard();
            return;
        }
        if (target instanceof Shot) {
            removeSelfFromBoard();
            target.removeSelfFromBoard();
            return;
        }
        if (target instanceof Base) {
            removeSelfFromBoard();
            ((Base)target).hit(this);
            return;
        }
        if (target instanceof Player) {
            removeSelfFromBoard();
            ((Player)target).hit(this);
        }
    }
    
    @Override
    public String toString() {
        return "Shot: team " + team + " located at "
                + row + ", " + col + ", direction " +
                direction;
    }
}
