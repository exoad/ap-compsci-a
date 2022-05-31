/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arena;

/**
 * This class is designed to be returned by the getMove method of the
 * Brain interface. Actions designate what a particular player wants to do
 * at that time.
 * @author Thomas Weisswange
 */
public class Action {
    String type;
    int direction;

    /**
     * Constructor function. Initializes both the type of
     * Action desired and the direction of the Action. The
     * type must be a String that starts with "M", "S",
     * "T", or "P". Note: this function is case-insensitive
     * and will convert all types to a single capital letter.
     * Thus, move values such
     * as "Move", "s", or "pass this turn" would all work
     * as desired. Note that in the case where type is "pass"
     * or "shoot", the direction will be ignored.
     * @param type the type of Action desired
     * @param direction the direction (where applicable)
     */
    public Action(String type, int direction) {
        type = type.toUpperCase().substring(0, 1);
        if (!type.equals("M") && !type.equals("S")
                && !type.equals("T")
                && !type.equals("P")) {
            throw new IllegalArgumentException(
                    "Invalid move type: " + type);
        }
        this.type = type;
        this.direction = direction;
    }
    
    /**
     * Constructor function. Will assume a direction of 0
     * (north). This is most useful when shooting or passing.
     * @param type 
     */
    public Action(String type) {
        this(type, 0);
    }
    
    /**
     * Getter function for the Action type.
     * @return the type of Action desired
     */
    public String getType() {
        return type;
    }

    /**
     * Getter function for the Action direction.
     * @return the direction of the Action
     */
    public int getDirection() {
        return direction;
    }
    
}
