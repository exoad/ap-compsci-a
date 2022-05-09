import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.Dimension;

import javax.swing.JTextArea;

public class DebugConsole extends JTextArea {
  public DebugConsole() {
    setColumns(25);
    setEditable(false);
    setPreferredSize(new Dimension(Config.WINDOW_SIZE.width - Config.PANE_WINDOW_SIZE.width, Config.PANE_WINDOW_SIZE.height / 2));
    setOpaque(true);
    setForeground(Config.CONSOLE_FOREGROUND);
    setBackground(Config.CONSOLE_BACKGROUND);
    System.setOut(new PrintStream(new CaretedConsole(this)));
  }

  public static class CaretedConsole extends OutputStream {
    private DebugConsole console;
    public CaretedConsole(DebugConsole console) {
      this.console = console;
    }
    @Override
    public synchronized void write(int v) throws IOException {
      console.append(String.valueOf((char) v));
      console.setCaretPosition(console.getDocument().getLength());
    }
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
      console.append(new String(b, off, len));
      console.setCaretPosition(console.getDocument().getLength());
    }
  }


  
}
