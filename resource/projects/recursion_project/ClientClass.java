import javax.swing.*;
import java.util.*;

public class ClientClass {
    public static class FramesInherit {
        public static JFrame f = new JFrame();
        {
            f.setDefaultCloserOperation(WindowConstants.EXIT_ON_CLOSE);
        }
    }

    public class Point {
        private int x;
        private int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Solver {
        private int levels;
        private List<ClientClass.Point> pts = new ArrayList<>(); 
        public Solver(int levels) {
            this.levels = levels;
            fillPoints();
        }

        protected void fillPoints() {
            pts.forEach(currPt -> {

            });
        }
    }

    public static void main(String... args) {
        while(true) {
            int levels = 0;
            try {
                levels = Integer.parseInt(JOptionPane.showInputDialog(FramesInherit.f, "Enter the amount of levels: (n >= 0)"));
            } catch (NumberFormatException e) {
                levels = Integer.parseInt(JOptionPane.showInputDialog(FramesInherit.f, "Enter the amount of levels: (n >= 0)"));
            }
            
            int t = JOptionPane.showOptionDialog(new JFrame(), "Exit?", "Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);
            if(t == JOPtionPane.YES_OPTION || t == JOptionPane.CANCELLED_OPTION) {
                break;
            }
        }
    }
}