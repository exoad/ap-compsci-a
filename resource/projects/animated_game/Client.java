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
  public static void main(String[] args) {
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
      /**
       * Here we will constantly ask the user for a valid answer (n>=0 || n != number)
       * This is prompted by the usage of a confirm dialog in order to ask if the
       * number is what they actually want
       * This is the part of the program where the program will ideally do the
       * "usercontrolled-exit"
       */

      String toEnter = null;
      do {
        try {
          toEnter = JOptionPane.showInputDialog(null, "Enter the amount of steps (n>=0): ");
        } catch (NullPointerException e) {
          toEnter = JOptionPane.showInputDialog(null, "Enter the amount of steps (n>=0): ");
        }
        int toCancel = JOptionPane.showConfirmDialog(null, "Do you want to enter this number?", "",
            JOptionPane.YES_NO_OPTION);
        if (toCancel == JOptionPane.YES_OPTION && Integer.parseInt(toEnter) >= 0) {
          new Solver(Integer.parseInt(toEnter));
        } else if (toCancel == JOptionPane.NO_OPTION) {
          break;
        }
      } while (toEnter == null || Integer.parseInt(toEnter) < 0);
      /**
       * We init the GUI class that will both solve and display the final fractal
       */
      do {
        try {
          exit = JOptionPane.showInputDialog(new JFrame(), "Do you want to exit (y/n)");
        } catch (NullPointerException e) {
          exit = JOptionPane.showInputDialog(new JFrame(), "Do you want to exit (y/n)");
        }
      } while (exit == null);
    } while (exit.equals("n"));
    System.exit(0);
  }
}
