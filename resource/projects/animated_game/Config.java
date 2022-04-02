
/**
 * @author Jack Meng
 * Class: APCS
 * 
 * Class based configuration file for the game
 * 
 * Project: Game of Life
 */

import java.awt.Dimension;
import java.awt.Color;

public class Config {
  protected static final Dimension PANE_WINDOW_SIZE = new Dimension(800, 900);
  protected static final Dimension WINDOW_SIZE = new Dimension(1000, 900);
  protected static final int CELL_SIZE_MODIFIER = 10;
  protected static final Color ALIVE_COLOR = Color.BLACK;
  protected static final Color DEAD_COLOR = new Color(200, 200, 200);
  protected static final Color BORDER_COLOR = Color.WHITE;
  protected static final Color CONSOLE_FOREGROUND = new Color(152, 195, 121);
  protected static final Color CONSOLE_BACKGROUND = new Color(29, 32, 38);
  protected static final Color FRAME_BG_COLOR = new Color(40, 44, 52);
  protected static final Color CONTROL_PANEL_BUTTON_BG = new Color(229, 192, 123);
  protected static final Color CONTROL_PANEL_BUTTON_FG = new Color(10, 12, 16);
  protected static final Color TOOLTIP_BG = new Color(224, 108, 118);
  protected static final Color TOOLTIP_FG = new Color(10, 12, 16);
  protected static final Color DEBUG_NEIGHBOR = new Color(255, 41, 251);
  protected static final Color CONTROL_PANEL_LABEL_FG = Color.WHITE;
  protected static final long MAX_TLE = 300L;
  protected static final long MAX_GENERATION_TLE = 600L;
  protected static final int GRID_MODIFIER = 85;
  protected static final int VERT_STRUCT = 10;
  protected static final String GAME_MANUAL_URL = "http://pi.math.cornell.edu/~lipa/mec/lesson6.html"; 
  protected static final String START_BTN_TEXT = "Start";
  protected static final String STOP_BTN_TEXT = "Stop";
  protected static final boolean DEBUG = false;
  protected static final boolean RANDOM_DEBUG = false;
  private Config() {}
}
