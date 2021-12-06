package resource.projects.rpsls;

import java.util.*;
import javax.swing.*;

public class RPSLSArena {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(
          UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    JFrame frame = new JFrame("ROCK PAPER SCISSORS LIZARD SPOCK");
    // schedule tournament
    RPSLSStrategy[] players = {new CopyBot(),          new CycleBot(),
                               new SheldonCooperBot(), new RockBotTom(),
                               new Botzilla(),         new OrigamiBot(),
                               new EdwardScissorBot(), new RandoCalrissian()};
    RPSLSMatch[] matches =
        new RPSLSMatch[players.length * (players.length - 1) / 2];
    for (int p1 = 0, i = 0; p1 < players.length - 1; p1++)
      for (int p2 = p1 + 1; p2 < players.length; p2++, i++)
        matches[i] = new RPSLSMatch(players[p1], players[p2]);
    for (int i = matches.length - 1; i > 0; i--) {
      int swap = (int)(Math.random() * (i + 1));
      RPSLSMatch temp = matches[i];
      matches[i] = matches[swap];
      matches[swap] = temp;
    }

    RPSLSGUI gui = new RPSLSGUI(matches);
    frame.getContentPane().add(gui);
    frame.setSize(600, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.pack();
    frame.setVisible(true);
    gui.setFontSizes();
  }
}
