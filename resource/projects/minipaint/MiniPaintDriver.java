
/**
 * Class: MiniPaintDriver
 * Purpose: The Driver class and will run all of the necessary classes
 * Author: Jack Meng
 */

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MiniPaintDriver {
    public static void main(String[] args) {
        // for handling exceptions within the program when thrown during execution
        try {
            // initializes everything in a try catch block with the attached handle
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //initialize the panel
        Dimension dim = new Dimension(800, 800);
        JFrame jf = new JFrame("Jack Meng - MiniPaint");
        MiniPaintPanel mpp = new MiniPaintPanel(dim);
        jf.setContentPane(mpp);
        jf.setSize(dim);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }
}