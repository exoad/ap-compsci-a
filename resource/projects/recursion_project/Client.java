
import javax.swing.*;

/**
 * <p>
 * This is the client class where input is taken into the program
 * This is done via the usage of JOptionPanes which help us get input from the
 * user graphically
 * </p>
 * 
 * @author Jack Meng
 * @see Solver
 */
public class Client {
  public static void main(String... args) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    String exit = "y";
    /**
     * <p>
     * We get information from the user in a do-while loop to allow the user to exit
     * after an operation.
     * All numbers are checked to make sure they are valid before sending them off
     * for processing (like
     * the amount of steps etc.)
     * </p>
     */
    do {
      int steps = 0;
      do {
        /**
         * Here we will constantly ask the user for a valid answer (n>=0 || n != number)
         */
        try {
          steps = Integer.parseInt(JOptionPane.showInputDialog(null,
              steps < 0 ? "Enter an INTEGER GREATER OR EQUAL TO 0: "
                  : "Enter the amount of steps (n >= 0) A number larger than 10 can result in a long time to process: "));
        } catch (NumberFormatException | NullPointerException e) {
          steps = Integer.parseInt(JOptionPane.showInputDialog(null,
              steps < 0 ? "Enter an INTEGER GREATER OR EQUAL TO 0: "
                  : "Enter the amount of steps (n >= 0) A number larger than 10 can result in a long time to process: "));
        }
        /**
         * We init the GUI class that will both solve and display the final fractal
         */
        new Solver(steps);
      } while (steps < 0);
      do {
        try {
          exit = JOptionPane.showInputDialog(null, "Do you want to exit (y/n)");
        } catch (NullPointerException e) {
          exit = JOptionPane.showInputDialog(null, "Do you want to exit (y/n)");
        }
      } while (exit == null);
    } while (exit.equals("n"));
    System.exit(0);
  }
}
