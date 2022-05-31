/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brains;

import arena.*;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jmeng0814
 */
public class Amogus implements Brain {
    private static class Pair<T, E> {
        private T first;
        private E second;

        public T getFirst() {
            return first;
        }

        public E getSecond() {
            return second;
        }

        @SuppressWarnings("unchecked")
        public <N> void setFirst(N first) {
            this.first = (T) first;
        }

        @SuppressWarnings("unchecked")
        public <K> void setSecond(K second) {
            this.second = (E) second;
        }

        public String toString() {
            return "{ " + first.toString() + " | "  + second.toString() + " }";
        }
    }
    private int row, col, myTeam, baseRow, baseCol;
    private Player p;

    public Amogus() {
        this.row = 0;
        this.col = 0;
        this.myTeam = -1;
        this.baseRow = 16;
        this.baseCol = -1;
    }

    @Override
    public String getCoder() {
        return "JackM";
    }

    @Override
    public String getName() {
        return "Amongoose";
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }

    /**
     * To search for enemies in the same row on either directions
     * with a range from the center, this center being the brain itself.
     *
     * This method does not take into account any other game piece that is not
     * on the same team or is a blocker; anything on the other team.
     *
     * @param b The Board to look at
     * @param maxRangeFromMe The range to search from. This variable is inclusive (i - 1)
     * @return A pair of lists with one side being the right and the other being the right.
     */
    private Pair<List<Boolean>, List<Boolean>>rowOfEnemies(Board b, int maxRangeFromMe) {
        List<Boolean> right = new ArrayList<>();
        List<Boolean> left = new ArrayList<>();
        Pair<List<Boolean>, List<Boolean>> enemies = new Pair<>();

        int myRow = p.getRow(), myCol = p.getCol();

        // find right side (+)
        for(int i = myCol + 1; i <= maxRangeFromMe - 1; i++) {
            right.add(!(b.get(myRow, i).equals(p.getTeam()) || !(b.get(myRow, i) instanceof Blocker)));
        }

        // find left side (-)
        for(int i = myCol + 1; i >= maxRangeFromMe - 1; i--) {
            right.add(!(b.get(myRow, i).equals(p.getTeam()) || !(b.get(myRow, i) instanceof Blocker)));
        }

        enemies.setFirst(left);
        enemies.setSecond(right);

        return enemies;
    }

    /*
     * T -> turn
     * M -> Move
     * S -> Shoot
     * P -> Pass
     * The basic approach here is that our bot will have
     */
    @Override
    public Action getMove(Player p, Board b) {
        this.p = p;
        if (myTeam == -1) {
            myTeam = p.getTeam();
            if (myTeam == 1)
                baseCol = 49;
            else
                baseCol = 0;
        }
        Pair<List<Boolean>, List<Boolean>> rowEne = rowOfEnemies(b, 5);
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