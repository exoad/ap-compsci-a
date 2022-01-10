package resource.projects.sorting_animation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;

import java.util.Arrays;

/** This import is static so it could be used without Math.* & also the standard print */
import static java.lang.Math.*;
import static java.lang.System.*;

/**
 * <h1>SortingAnimation</h1>
 * <p>
 * This class uses a GUI to display a sorting animation.
 * Sorting Animations:
 * <ul>
 * <li>Bubble Sort</li>
 * <li>Selection Sort</li>
 * <li>Insertion Sort</li>
 * </ul>
 * 
 * JavaDocs is used for easy documentation viewing
 * in IDE's and for JavaDoc generation
 * </p>
 * 
 * @author Jack Meng
 * @version 1.0
 * @since 2022-02-06
 */

public class SortingAnimation {
  public static final int MAX_ELEMENT = 100;

  /**
   * <p>
   * Here are the methods that our nested class,
   * where the magic happens, will use.
   * 
   * Technically it is just a Runnable {@link java.lang.Runnable}
   * </p>
   */
  public interface Runner {
    public void run();

    public void write(int[] list);
  }

  /**
   * The main method that will run our program
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    new Magic().run();
  }

  /**
   * <h1>Magic</h1>
   * <p>
   * This nested class will provide the GUI
   * while also providing the sorting algorithms
   * </p>
   * 
   * @author Jack Meng
   * @version 1.0
   * @since 2022-02-06
   * 
   * @see #SortingAnimation.Magic.bubbleSort()
   * @see #SortingAnimation.Magic.selectionSort()
   * @see #SortingAnimation.Magic.insertionSort()
   * @see #SortingAnimation.Magic.pressuredRunner(Runnable)
   */
  private static class Magic extends JPanel implements SortingAnimation.Runner, ActionListener, ChangeListener {
    private int[] list;
    private int current, concurrent;
    private long speed = 50;
    private JButton[] buttons = new JButton[5];
    private JSlider speedSlider = new JSlider(1, 200, 50);
    private javax.swing.JLabel status = new javax.swing.JLabel(), speedLabel = new javax.swing.JLabel();
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Bad Sorting");
    private Thread worker;
    private final int WIDTH = 900;

    /**
     * <p>
     * Our constructor will be called to initialize our components
     * in our GUI, and also generate the proper values for our list
     * </p>
     * 
     * @see #SortingAnimation.Magic.generate()
     */
    public Magic() {
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setSize(new Dimension(WIDTH, 700));

      buttons[0] = new JButton("Bubble Sort");

      buttons[1] = new JButton("Selection Sort");

      buttons[2] = new JButton("Insertion Sort");

      buttons[3] = new JButton("Bogo Sort");

      buttons[4] = new JButton("Stop");

      status.setText("Stopped.");
      status.setOpaque(true);
      status.setForeground(Color.RED);
      status.setBackground(Color.BLACK);

      Arrays.asList(buttons).forEach(button -> {
        button.addActionListener(this);
        panel.add(button);
      });

      speedSlider.setMajorTickSpacing(10);
      speedSlider.setMinorTickSpacing(1);
      speedSlider.setPaintTicks(true);
      speedSlider.addChangeListener(this);
      speedSlider.setOpaque(true);
      speedSlider.setBackground(Color.GRAY);

      speedLabel.setText("@" + speed + "ms");
      speedLabel.setOpaque(true);
      speedLabel.setBackground(Color.BLACK);
      speedLabel.setForeground(Color.YELLOW);

      panel.add(status);
      panel.add(speedLabel);
      panel.add(speedSlider);
      panel.setPreferredSize(new Dimension(WIDTH, 40));
      panel.setOpaque(true);
      panel.setBackground(Color.GRAY);

      setLayout(new BorderLayout());
      setPreferredSize(new Dimension(WIDTH, 600));
      add(panel, BorderLayout.NORTH);
      setOpaque(true);
      setBackground(Color.GRAY);
      frame.add(this);

      list = generate();
    }

    /**
     * <p>
     * This method generates random numbers for a primitive array of integers.
     * </p>
     * 
     * @return The int[] array with random numbers from 0 to 100
     */
    public int[] generate() {
      int[] temp = new int[SortingAnimation.MAX_ELEMENT];
      for (int i = 0; i < temp.length; i++) {
        temp[i] = (int) floor(random() * SortingAnimation.MAX_ELEMENT);
      }
      return temp;
    }

    /**
     * <p>
     * This method performs the bubble sort algorithm.
     * Every time it is sorted, it will be redrawn, and
     * the animation will be paused for 100th of a second
     * 
     * After it is done, the status JLabel will be updated
     * 
     * Our bubbleSort's stats:
     * Time Complexity:
     * Best Case: O(n)
     * Average Case: O(n^2)
     * Worst Case: O(n^2)
     * </p>
     * 
     * @throws InterruptedException
     */
    public synchronized void bubbleSort() throws InterruptedException {
      int n = list.length;
      for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
          concurrent = j;
          if (list[j] > list[j + 1]) {
            int temp = list[j];
            list[j] = list[j + 1];
            list[j + 1] = temp;
          }
        }
        current = i;
        repaint();
        write(list);
        Thread.sleep(speed);
      }
      status.setForeground(Color.GREEN);
      status.setText("Sorted.");
    }

    /**
     * <p>
     * This method performs a bogo sort
     * 
     * In general it should NEVER be used for sorting.
     * Why?
     * It just does a random shuffle of the array
     * </p>
     * 
     * @throws InterruptedException
     */
    public synchronized void bogoSort() throws InterruptedException {
      int n = list.length;
      int[] sorted = new int[n];
      for (int i = 0; i < n; i++) {
        sorted[i] = list[i];
      }
      Arrays.sort(sorted);
      while (!Arrays.equals(sorted, list)) {
        for (int i = 0; i < n; i++) {
          int j = (int) floor(random() * n);
          int temp = list[i];
          list[i] = list[j];
          list[j] = temp;
          current = i;
          concurrent = j;
          repaint();
          write(list);
          Thread.sleep(speed);
        }
      }
      status.setForeground(Color.BLUE);
      status.setText("Wait how...");
    }

    /**
     * <p>
     * This method performs the selection sort algorithm.
     * Every time it is sorted, it will be redrawn, and
     * the animation will be paused for 100th of a second
     * 
     * Our selectionSort's stats:
     * Time Complexity:
     * Best Case: O(n^2)
     * Worst Case: O(n^2)
     * Average Case: O(n^2)
     * </p>
     * 
     * @throws InterruptedException
     */
    public synchronized void selectionSort() throws InterruptedException {
      for (int i = 0; i < list.length - 1; i++) {
        int mm = i;
        for (int j = i + 1; j < list.length; j++) {
          concurrent = mm;
          if (list[j] < list[mm]) {
            mm = j;
          }
        }
        int temp = list[i];
        list[i] = list[mm];
        list[mm] = temp;
        current = i;
        repaint();
        write(list);
        Thread.sleep(speed);

      }
      status.setForeground(Color.GREEN);
      status.setText("Sorted.");
    }

    /**
     * <p>
     * This method performs the insertion sort algorithm.
     * Every time it is sorted, it will be redrawn, and
     * the animation will be paused for 100th of a second
     * 
     * Our insertionSort's stats:
     * Time Complexity:
     * Best Case: O(n)
     * Average Case: O(n^2)
     * Worst Case: O(n^2)
     * </p>
     * 
     * @throws InterruptedException
     */
    public synchronized void insertionSort() throws InterruptedException {
      for (int i = 1; i < list.length; i++) {
        int curr = list[i];
        int k = i - 1;
        while (k >= 0 && list[k] >= curr) {
          list[k + 1] = list[k];
          k -= 1;
          concurrent = k;
        }
        list[k + 1] = curr;
        current = i;
        repaint();
        write(list);
        Thread.sleep(speed);
      }
      status.setForeground(Color.GREEN);
      status.setText("Sorted.");
    }

    /**
     * <p>
     * This method is overridden.
     * 
     * It will attempt to redraw the most current
     * state of the array as lines on the screen.
     * 
     * This will also make a temporary cursor and flag
     * for the current element being sorted.
     * 
     * Cursor Colors:
     * Orange: Current Element
     * Black: Default
     * Blue: Finding Element
     * </p>
     * 
     * @param g This is the graphics object that will be used to draw the array
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.BLACK);
      for (int i = 0, j = 200; j < WIDTH && i < list.length; i++, j += 5) {
        g.drawLine(j, 400 - list[i], j, 600);
        if (i == current) {
          g.setColor(Color.ORANGE);
          g.drawLine(j, 400 - list[i], j, 600);
          g.setColor(Color.BLACK);
        }else if (i == concurrent) {
          g.setColor(Color.BLUE);
          g.drawLine(j, 400 - list[i], j, 600);
          g.setColor(Color.BLACK);
        }
      }
    }

    /**
     * <p>
     * This method is overridden.
     * 
     * It is used to run the current GUI
     * </p>
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      frame.pack();
      frame.setVisible(true);
    }

    @Override
    public void write(int[] list) {
      for (int e : list)
        System.out.print(e + " ");
      out.print("\n========================================================\n\n\n");
    }

    /**
     * <p>
     * This method is a macro function, meaning it is used to make my life
     * easier when writing the code.
     * 
     * A thread is ran because this allows our GUI to not "broken" or
     * frozen when our algorithm is running. This also allows us to switch
     * from algorithm to algorithm without having to restart the program or wait
     * until it is done.
     * 
     * Since the algorithm only contains 100 integers, it will not use a lot of
     * memory, so leaving them in a thread is not a problem.
     * 
     * It will run the parameter as a callback. (Java bad no Function pointers)
     * </p>
     * 
     * @param func The callback to be run
     * @throws InterruptedException
     * @see #run()
     */
    private void pressuredRunner(Runnable func) throws InterruptedException {
      worker = new Thread(() -> {
        func.run();
      });
      Thread.sleep(2);
      worker.start();
    }

    /**
     * <p>
     * This method is overridden
     * 
     * This method acts as an actionListener for the JButtons in the GUI.
     * 
     * When the user clicks on a button, it will run the corresponding algorithm
     * inside a new thread {@link #pressuredRunner(Runnable)}
     * 
     * This action allows us to run multiple algorithms at once or at least
     * make sure the program doesn't freeze when running the algorithms.
     * 
     * Every time a button is pressed, the status JLabel will be updated
     * so the user can a see what the program is doing.
     * </p>
     * 
     * @param e The action event that is triggered
     * 
     * @see #SortingAnimation.Magic.pressuredRunner(Runnable)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (worker != null) {
        try {
          worker.interrupt();
        } catch (Exception ex) {
          // do nothing
        }
        list = generate();
        repaint();
      }
      if (e.getSource().equals(buttons[0])) {
        list = generate();
        repaint();
        status.setForeground(Color.RED);
        status.setText("Running Bubble");
        try {
          pressuredRunner(() -> {
            try {
              bubbleSort();
            } catch (InterruptedException e1) {

            }
          });
        } catch (InterruptedException e1) {

        }
      } else if (e.getSource().equals(buttons[1])) {
        list = generate();
        repaint();
        status.setForeground(Color.RED);
        status.setText("Running Selection");
        try {
          pressuredRunner(() -> {
            try {
              selectionSort();
            } catch (InterruptedException e1) {

            }
          });
        } catch (InterruptedException e1) {

        }
      } else if (e.getSource().equals(buttons[2])) {
        list = generate();
        repaint();
        status.setForeground(Color.RED);
        status.setText("Running Insertion");
        try {
          pressuredRunner(() -> {
            try {
              insertionSort();
            } catch (InterruptedException e1) {

            }
          });
        } catch (InterruptedException e1) {

        }
      } else if (e.getSource().equals(buttons[3])) {
        list = generate();
        repaint();
        status.setForeground(Color.RED);
        status.setText("Running Bogo");
        try {
          pressuredRunner(() -> {
            try {
              bogoSort();
            } catch (InterruptedException e1) {

            }
          });
        } catch (InterruptedException e1) {

        }
      } else if (e.getSource().equals(buttons[4])) {
        if (worker != null) {
          worker.interrupt();
          status.setText("Stopped");
          repaint();
        }
      }
    }

    /**
     * <p>
     * This method is overriden
     * 
     * It is used to update the speed
     * of the animation based on the
     * slider's position
     * </p>
     * 
     * @see #ChangeListener.stateChanged(ChangeEvent)
     */
    @Override
    public void stateChanged(ChangeEvent e) {
      speed = speedSlider.getValue() * 2;
      repaint();
      speedLabel.setText("@" + speed + "ms");
    }
  }
}
