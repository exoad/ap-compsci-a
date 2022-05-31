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
public class Occupant {
    protected int row;
    protected int col;
    protected int team;
    protected Board myBoard;

    Occupant(int team) {
        this.team = team;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getTeam() {
        return team;
    }

    public Board getMyBoard() {
        return myBoard;
    }
    
    void setTeam(int i) {
        team = i;
    }
    
    /**
     * Attempts to add this Occupant to a Board at a specified
     * location. The space to which the Occupant is to be added
     * must be empty.
     * @param b the Board to which the Occupant is to be added
     * @param row the intended row location
     * @param col the intended column location
     * @return true on successful adding, false on failure
     */
    boolean addSelfToBoard(Board b, int row, int col) {
        if (b == null || !b.isEmpty(row, col)) return false;
        this.row = row;
        this.col = col;
        this.myBoard = b;
        b.add(this, row, col);
        return true;
    }
    
    /**
     * Attempts to remove itself from the Board to which it
     * currently belongs.
     * @return true on success, false on failure
     */
    boolean removeSelfFromBoard() {
        if (myBoard == null) return false;
        myBoard.remove(row, col);
        myBoard = null;
        return true;
    }
    
    /**
     * Attempts to move self to location (row, col) in its
     * current board. The space to which the Occupant wishes
     * to move must be empty.
     * @param row the intended row location
     * @param col the intended column location
     * @return true on success, false on failure
     */
    boolean moveTo(int row, int col) {
        if (myBoard == null || !myBoard.isEmpty(row, col))
            return false;
        myBoard.move(this.row, this.col, row, col);
        this.row = row;
        this.col = col;
        return true;
    }
    
    public String toString() {
        return "Occupant: team " + team + " located at "
                + row + ", " + col;
    }
}
