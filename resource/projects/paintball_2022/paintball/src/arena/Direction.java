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
public class Direction {
    public static final int NORTH = 0;
    public static final int NORTHEAST = 45;
    public static final int EAST = 90;
    public static final int SOUTHEAST = 135;
    public static final int SOUTH = 180;
    public static final int SOUTHWEST = 225;
    public static final int WEST = 270;
    public static final int NORTHWEST = 315;
    
    /**
     * Rounds any angle off to the nearest multiple of 45
     * degrees, thus pointing it in one of the 8 cardinal
     * directions.
     * @param dir the angle to be rounded
     * @return the nearest of the 8 cardinal directions.
     */
    public static int roundTo8(int dir) {
        dir %= 360;
        if (dir < 0) dir += 360;
        dir = 45 * ((dir + 22) / 45);
        return dir % 360;
    }
    
    /**
     * Rounds any angle off to the nearest multiple of 90
     * degrees, thus pointing it in one of the 4 cardinal
     * directions.
     * @param dir the angle to be rounded
     * @return the nearest of the 4 cardinal directions.
     */
    public static int roundTo4(int dir) {
        dir %= 360;
        if (dir < 0) dir += 360;
        dir = 90 * ((dir + 45) / 90);
        return dir % 360;
    }  
 
    /**
     * Returns the nearest adjacent space, relative to a given
     * location, in a given direction. Directions will be rounded
     * off to the nearest of 8 cardinal directions.
     * @param row The row of the original space
     * @param col The column of the original space
     * @param dir The direction from the original space to the
     * final space
     * @return a 2-d array (row, col) containing the coordinates
     * of the final space
     */
    public static int[] getLocInDirection(int row, int col, 
            int dir) {
        int[] result = new int[2];
        dir = roundTo8(dir);
        if (dir == NORTH || dir == NORTHEAST ||
                dir == NORTHWEST)
            result[0] = row - 1;
        else if (dir == SOUTH || dir == SOUTHEAST ||
                dir == SOUTHWEST)
            result[0] = row + 1;
        else
            result[0] = row;
        if (dir == WEST || dir == SOUTHWEST ||
                dir == NORTHWEST)
            result[1] = col - 1;
        else if (dir == EAST || dir == SOUTHEAST ||
                dir == NORTHEAST)
            result[1] = col + 1;
        else
            result[1] = col;
        return result;
    }
    
    /**
     * Calculates the direction from one space to another. The code basically 
     * draws a straight line from the origin space to the destination space and
     * computes the closest of the 8 cardinal directions to that line.
     * @param row1 The row of the origin space
     * @param col1 The column of the origin space
     * @param row2 The row of the destination space
     * @param col2 The column of the destination space
     * @return the closest direction from (row1, col1) to (row2, col2).
     */
    public static int getDirectionTowards(int row1, int col1, int row2, int col2) {
        int deltaX = row1 - row2;   //yes, I know this isn't quite right. I'm switching x and y
        int deltaY = col2 - col1;   //so as to be able to use normal trigonometry.
        if (deltaX == 0) {
            if (deltaY < 0)
                return WEST;
            else if (deltaY > 0)
                return EAST;
            else
                throw new IllegalArgumentException("Origin and destination match.");
        }
        //note: deltaX != 0 here
        double dir = Math.atan((double) deltaY / deltaX);
        if (deltaX < 0)
            dir += Math.PI;
        return roundTo8((int) Math.round(dir * 180 / Math.PI));
    }
    
    /**
     * Computes the minimum number of moves required to get
     * from location (row1, col1) to location (row2, col2).
     * Diagonal moves are permitted.
     * @param row1 The row of the origin space
     * @param col1 The column of the origin space
     * @param row2 The row of the destination space
     * @param col2 The column of the destination space
     * @return the minimum number of (8-direction) moves required
     * to get from the origin space to the destination space.
     */
    public static int moveDistance(int row1, int col1, int row2, int col2) {
        return Math.max(Math.abs(row2-row1), Math.abs(col2-col1));
    }
}
