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
public class Blocker extends Occupant {
    Blocker() {
        super(0);
    }
    
    @Override
    boolean moveTo(int row, int col) {
        return false;
    }
    
    @Override
    public String toString() {
        return "Blocker: located at " + row + ", " + col;
    }
}
