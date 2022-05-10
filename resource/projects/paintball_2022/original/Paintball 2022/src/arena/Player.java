/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arena;

import java.util.Random;

/**
 *
 * @author Thomas Weisswange
 */
public class Player extends Occupant {
    private Brain controller;
    private int direction;
    private int turnsUntilShoot;
    private int kills;
    private int frags;
    private int deaths;
    private int enemyBaseHits;
    private int selfBaseHits;
    
    private final boolean DEBUG = false;
    
    Player(int team, Brain brain, int dir) {
        super(team);
        controller = brain;
        direction = Direction.roundTo8(dir);
    }
    
    Player(int team, Brain brain) {
        this(team, brain, 0);
    }
    
    Player(Brain brain) {
        this(0, brain, 0);
    }

    void setTeam(int i) {
        super.setTeam(i);
    }
    public int getDirection() {
        return direction;
    }

    public int getTurnsUntilShoot() {
        return turnsUntilShoot;
    }
    
    public boolean canShoot() {
        if (turnsUntilShoot > 0)
            return false;
        int[] dest = Direction.getLocInDirection(row, col, 
                    direction);
        int destRow = dest[0];
        int destCol = dest[1];

        //destination is off of board
        if (!myBoard.isValid(destRow, destCol)) {
            return false;
        }

        Occupant target = myBoard.get(destRow, destCol);
        //destination is occupied by player or blocker, no shot
        if (target instanceof Player || target instanceof Blocker) {
            return false;
        }
        
        return true;
    }

    public int getKills() {
        return kills;
    }

    public int getFrags() {
        return frags;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getEnemyBaseHits() {
        return enemyBaseHits;
    }

    public int getSelfBaseHits() {
        return selfBaseHits;
    }
    
    public int getScore() {
        return kills + 3*enemyBaseHits - deaths - frags
                - 3*selfBaseHits;
    }

    Brain getController() {
        return controller;
    }
    
    void hit(Shot shot) {
        deaths++;
        if(team != shot.getOwner().getTeam()) {
            shot.getOwner().hitEnemy();
            myBoard.scorePoints(3 - team, 1);
        } else {
            shot.getOwner().hitFriend();
            myBoard.losePoints(team, 1);
        }
        removeSelfFromBoard();
    }
    
    void hitEnemyBase() {
        enemyBaseHits++;
    }
    
    void hitOwnBase() {
        selfBaseHits++;
    }
    
    void hitEnemy() {
        kills++;
        
    }
    
    void hitFriend() {
        frags++;
        
    }
    
    void respawn(Board board) {
        Random randGen = new Random();
        int row, col;
        do {
            row = randGen.nextInt(33);
            if (team == 1)
                col = randGen.nextInt(10);
            else
                col = randGen.nextInt(10) + 40;
        } while (!board.isEmpty(row, col));
        direction = (team == 1 ? 90 : 270);
        addSelfToBoard(board, row, col);
    }
    
    void act() {
        debugPrint("Player acts");
        if (turnsUntilShoot > 0)
            turnsUntilShoot--;
        Action action;
        try {
            action = controller.getMove(this, myBoard);
        } catch (Exception e) {
            action = new Action("P");
            System.err.println(controller.getName() + ": " + e);
        }
        String actionType = action.getType();
        
        //pass
        if (actionType.equals("P"))
            return;
        
        //shoot
        if (actionType.equals("S")) {
            debugPrint("Player shoots");
            if (turnsUntilShoot > 0)
                return;
            
            int[] dest = Direction.getLocInDirection(row, col, 
                    direction);
            int destRow = dest[0];
            int destCol = dest[1];
            
            //destination is off of board
            if (!myBoard.isValid(destRow, destCol)) {
                debugPrint("Destination is off board");
                return;
            }
            
            Occupant target = myBoard.get(destRow, destCol);
            //destination is occupied by player or blocker, no shot
            if (target instanceof Player || target instanceof Blocker) {
                debugPrint("Destination is occupied by player or blocker");
                return;
            }
            
            //destination is occupied by shot
            if (target instanceof Shot) {
                debugPrint("Destination is occupied by shot");
                target.removeSelfFromBoard();
                turnsUntilShoot = 3;
                return;
            }
            
            //destination is occupied by base
            if (target instanceof Base) {
                debugPrint("Destination is occupied by base");
                ((Base) target).hit(new Shot(team, direction, this));
                turnsUntilShoot = 3;
                return;
            }
            
            //destination is unoccupied
            debugPrint("Destination is unoccupied");
            new Shot(team, direction, this).addSelfToBoard(myBoard, destRow, destCol);
            turnsUntilShoot = 3;
        }
        
        //turn
        if (actionType.equals("T")) {
            debugPrint("Player turns");
            direction = Direction.roundTo8(action.getDirection());
        }
        
        //move
        if (actionType.equals("M")) {
            debugPrint("Player moves");
            int[] dest = Direction.getLocInDirection(row, col, 
                    action.getDirection());
            int destRow = dest[0];
            int destCol = dest[1];
            
            //destination is off of board
            if (!myBoard.isValid(destRow, destCol)) {
                debugPrint("Destination is off board");
                return;
            }
            
            Occupant target = myBoard.get(destRow, destCol);
            //destination is occupied by blocker, player, or
            //base: no movement
            if (target instanceof Blocker ||
                    target instanceof Player ||
                    target instanceof Base) {
                return;
            }
            
            //destination is occupied by shot
            if (target instanceof Shot) {
                //removeSelfFromBoard();
                target.removeSelfFromBoard();
                hit((Shot) target);
            }
            
            //destination is empty
            moveTo(destRow, destCol);
        }
    }
    
    private void debugPrint(String s) {
        if (DEBUG)
            System.out.println(s);
    }
}
