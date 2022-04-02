import javax.swing.UIManager;

/**
 * @author Jack Meng
 * 
 * This is the main driver class which
 * calls the Game class to start the program.
 * 
 * It does not take any arguments with the 
 * java command during runtime.
 */

public class AnimatedDriver {
  
  /** 
   * @param args
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    new Game();
  }
}
