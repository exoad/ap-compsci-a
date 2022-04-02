
/**
 * @author Jack Meng
 * Class: APCS
 * 
 * Side util class for the project
 * 
 * Project: Game of Life
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Bernard {
  private Bernard() {
  }

  
  /** 
   * @param n
   * @return boolean
   */
  public static synchronized boolean rule(int n) {
    return !(n < 2 || n > 3);
  }

  
  /** 
   * @param s Writes an image based on the present cells
   */
  public static synchronized void writeImage(JPanel[][] s) {
    BufferedImage img = new BufferedImage(s.length, s[0].length, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < s.length; i++) {
      for (int j = 0; j < s[0].length; j++) {
        img.setRGB(i, j, s[i][j].getBackground().getRGB());
      }
    }

    try {
      ImageIO.write(img, "png", new File("conway_" + System.currentTimeMillis() + ".png"));
    } catch (IOException e) {
      System.err.println("Error writing image");
    }

  }
}
