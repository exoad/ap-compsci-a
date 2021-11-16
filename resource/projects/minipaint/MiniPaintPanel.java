/**
 * Clas: MiniPaintPanel
 * Purpose: This class handles all of the drawing and everything in the main
 * window Author: Jack Meng
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MiniPaintPanel
    extends JPanel implements MouseListener, ActionListener {
  /**
   * Purpose: This class creates a Line object which is then utilized by the
   * Dynanmic Class to create and store lines in order to display them correctly
   * when window is resized Class Name: Line Author: Jack Meng
   */

  public static class Line {
    // necessary properties of a line object
    private int startx = 0;
    private int starty = 0;
    private int endx = 0;
    private int endy = 0;
    Color color = Color.BLACK;

    // move properties to the local variables
    Line(Point start, Point end, Color cc) {
      startx = start.x;
      starty = start.y;
      endx = end.x;
      endy = end.y;
      color = cc;
    }

    // getter method for the color
    public Color getColor() { return color; }

    // to string in order to print to the console to make sure the line was
    // drawn correctly
    public String toString() {
      return "Line: (" + startx + ", " + starty + ") to (" + endx + ", " +
          endy + ")\nColor: " + color.toString() + "\n";
    }
  }

  private String shape = "line";
  private final JButton[] buttons = new JButton[7];
  private final JLabel[] labels = new JLabel[2];
  private Color currColor = Color.BLACK;
  private int startx;
  private int starty;
  private int endx;
  private int endy;
  private int iterator = 0;
  private int size = 1;
  private boolean line = true;
  private transient Line[] entities =
      new Line[1]; // start out as size of one but will increase as times goes
                   // on
  private Graphics g;
  MiniPaintPanel(Dimension dim) {
    labels[0] =
        new JLabel("<html>Current Shape: " + shape +
                   "<br>Current Color:<html>"); // use html in order to space
                                                // and format text properly
    labels[0].setForeground(Color.BLACK);
    labels[0].setOpaque(true);
    labels[0].setAlignmentX(Component.LEFT_ALIGNMENT);

    labels[1] = new JLabel("Hello There"); // place holder text
    labels[1].setBackground(currColor);
    labels[1].setForeground(currColor);
    labels[1].setOpaque(true);
    labels[1].setAlignmentX(Component.LEFT_ALIGNMENT);

    String placeHolder = "           ";
    // separately initialize all of the buttons
    buttons[0] = new JButton(placeHolder);
    buttons[0].setBackground(Color.GREEN);

    buttons[1] = new JButton(placeHolder);
    buttons[1].setBackground(Color.BLUE);

    buttons[2] = new JButton(placeHolder);
    buttons[2].setBackground(Color.RED);

    buttons[3] = new JButton(placeHolder);
    buttons[3].setBackground(Color.BLACK);

    buttons[4] = new JButton(placeHolder);
    buttons[4].setBackground(new Color((int)(Math.random() * 256),
                                       (int)(Math.random() * 256),
                                       (int)(Math.random() * 256)));

    buttons[5] = new JButton(placeHolder);
    buttons[5].setBackground(Color.ORANGE);

    buttons[6] = new JButton("Change Shape");
    buttons[6].setBackground(Color.BLACK);

    // initialize of all necessary properties
    JPanel menu1 = new JPanel();
    menu1.setLayout(new BoxLayout(menu1, BoxLayout.Y_AXIS));
    menu1.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
    menu1.setPreferredSize(dim);
    menu1.add(labels[0]);
    menu1.add(labels[1]);

    // for repetitive initialization
    for (JButton jb : buttons) {
      jb.setForeground(Color.WHITE);
      jb.addActionListener(this);
      jb.setAlignmentX(Component.LEFT_ALIGNMENT);
      jb.setAlignmentY(Component.TOP_ALIGNMENT);
      jb.setBorderPainted(false);
      menu1.add(jb);
    }

    setPreferredSize(new Dimension(800, 800));
    addMouseListener(this);
    setLayout(new BorderLayout());
    add(menu1, BorderLayout.WEST);
  }

  /**
   * @param g1 The graphics value Component
   * This mehtod will draw the desired shape based on the variable line
   */
  protected void askDraw(Graphics g1) {
    g1 = getGraphics();
    g1.setColor(currColor);
    if (line) {
      g1.drawLine(startx, starty, endx, endy);
      add(new Line(new Point(startx, starty), new Point(endx, endy),
                   currColor));
    } else {
      g1.drawLine(startx, starty, endx, starty);
      g1.drawLine(startx, starty, startx, endy);
      g1.drawLine(endx, endy, endx, starty);
      g1.drawLine(endx, endy, startx, endy);
      add(new Line(new Point(startx, starty), new Point(endx, starty),
                   currColor));
      add(new Line(new Point(startx, starty), new Point(startx, endy),
                   currColor));
      add(new Line(new Point(endx, endy), new Point(endx, starty), currColor));
      add(new Line(new Point(endx, endy), new Point(startx, endy), currColor));
    }
  }

  /**
   * This overriden method will redrawn everything when the window is resized
   * and keep everything in view
   */
  @Override
  public void paintComponent(Graphics gr) {
    super.paintComponent(gr);
    g = gr;
    for (int i = 0; i < iterator; i++) {
      System.out.println(get(i).toString());
      g.setColor(get(i).color);
      g.drawLine(get(i).startx, get(i).starty, get(i).endx, get(i).endy);
      System.out.println("Paint");
    }
  }

  /**
   * @param cc is the color of the chosen color to be updated to
   */
  public void updateText(Color cc) {
    currColor = cc;
    labels[1].setBackground(cc);
    labels[1].setForeground(cc);
    g = getGraphics();
    g.setColor(cc);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // butons listeners and will perform the necessary color swap or shape swap
    // depending on the button being pressed
    if (e.getSource() == buttons[0])
      updateText(Color.GREEN);
    else if (e.getSource() == buttons[1])
      updateText(Color.BLUE);
    else if (e.getSource() == buttons[2])
      updateText(Color.RED);
    else if (e.getSource() == buttons[3])
      updateText(Color.BLACK);
    else if (e.getSource() == buttons[4]) {
      Color rar =
          new Color((int)(Math.random() * 256), (int)(Math.random() * 256),
                    (int)(Math.random() * 256));
      updateText(rar);
      buttons[4].setBackground(rar);
    } else if (e.getSource() == buttons[5])
      updateText(Color.ORANGE);
    else if (e.getSource() == buttons[6]) {
      if (line) {
        shape = "rectangle";
        labels[0].setText("<html>Current Shape: " + shape +
                          "<br>Current Color:<html>");
        line = false;
      } else {
        shape = "line";
        labels[0].setText("<html>Current Shape: " + shape +
                          "<br>Current Color:<html>");
        line = true;
      }
    }
  }

  public Line get(int index) { return entities[index]; }

  /**
   * @param tag This is the object that will be added to the dynanmic array
   */
  public void add(Line tag) {
    // check if we have not reached the limit if we did, we will expand the
    // array
    if (iterator == size)
      expand();
    System.out.println(tag.toString());
    entities[iterator] = tag;
    iterator++;
  }

  /**
   * @param tag   This is the object that will be added to the dynanmic array
   * @param index If the passed property should be stored and overwritten at a
   *              specific index
   */
  public void add(Line tag, int index) {
    // check if we have not reached the limit if we did, we will expand the
    // array
    if (iterator == size)
      expand();
    // loop through the array to find said index
    if (iterator - index >= 0)
      System.arraycopy(entities, index, entities, index + 1, iterator - index);
    System.out.println(tag.toString());
    // append the object at the index
    entities[index] = tag;
    // move to the next index
    iterator++;
  }

  /**
   * This method will expand the array double the size of the original array
   * size. This method shall not be used outside of this class itself
   */
  protected void expand() {
    System.out.println("Expanding...");
    // create a temporary array
    Line[] entityTransfer = new Line[size == iterator ? size * 2 : size + 1];
    // transfer everything over to this temporary array
    if (size >= 0)
      System.arraycopy(entities, 0, entityTransfer, 0, size);
    // assign this new array to the old array
    entities = entityTransfer;
    // update the size of the array
    size *= 2;
  }

  // action listeners (used)
  @Override
  public void mousePressed(MouseEvent e) {
    startx = e.getX();
    starty = e.getY();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    endx = e.getX();
    endy = e.getY();
    askDraw(g);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // UNUSED LISTENER
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // UNUSED LISTENER
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // UNUSED LISTENER
  }
}
