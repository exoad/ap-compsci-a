import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class ClientClass implements ActionListener, Runnable {
  private JFrame frame;
  private JLabel label;
  private JTextField textArea;
  private JPanel panel;
  private JButton confirm;
  private String output;

  public ClientClass(String text) {
    frame = new JFrame("Input");
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    label = new JLabel(text);
    textArea = new JTextField(72);
    output = "3";
    confirm = new JButton("Ok");
    confirm.addActionListener(this);
    panel = new JPanel();
    panel.setPreferredSize(new Dimension(300, 100));
    panel.add(label);
    panel.add(textArea);
    frame.add(panel);
    frame.setSize(300, 100);
  }

  public String getOutput() {
    return output;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == confirm) {
      output = textArea.getText().equals("") || !textArea.getText().matches("[0-9]+") ? "3" : textArea.getText();
    }
  }

  @Override
  public void run() {
    frame.pack();
    frame.setVisible(true);
  }
}