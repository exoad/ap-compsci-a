import java.awt.Dimension;
import java.awt.Color;

public class Config {
  public static final Dimension PANE_WINDOW_SIZE = new Dimension(800, 900);
  public static final Dimension WINDOW_SIZE = new Dimension(1000, 900);
  public static final int CELL_SIZE_MODIFIER = 10;
  public static final Color ALIVE_COLOR = Color.BLACK;
  public static final Color DEAD_COLOR = new Color(200, 200, 200);
  public static final Color BORDER_COLOR = Color.WHITE;
  public static final Color CONSOLE_FOREGROUND = new Color(152, 195, 121);
  public static final Color CONSOLE_BACKGROUND = new Color(29, 32, 38);
  public static final Color FRAME_BG_COLOR = new Color(40, 44, 52);
  public static final Color CONTROL_PANEL_BUTTON_BG = new Color(229, 192, 123);
  public static final Color CONTROL_PANEL_BUTTON_FG = new Color(10, 12, 16);
  public static final Color TOOLTIP_BG = new Color(224, 108, 118);
  public static final Color TOOLTIP_FG = new Color(10, 12, 16);
  public static final Color DEBUG_NEIGHBOR = Color.RED;
  public static final long MAX_DELAY = 300L;
  public static final long REFRESH_GENERATION_TIME_MS = 200L;
  public static final int GRID_MODIFIER = 85;
  public static final int VERT_STRUCT = 10;
  public static final String GAME_MANUAL_URL = "http://pi.math.cornell.edu/~lipa/mec/lesson6.html"; 
  public static final String START_BTN_TEXT = "Start";
  public static final String STOP_BTN_TEXT = "Stop";
  public static final boolean DEBUG = false;
  private Config() {}
}
