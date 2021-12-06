package resource.projects.rpsls;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class RPSLSGUI extends JPanel {
  private final int NUM_GAMES = 10000;
  private final int UPDATE_STEP = 10;
  private final String OUTPUT_FILE = "RPSLSResults.csv";

  private JProgressBar gameProgress, playerWinProgress;
  private RPSLSStrategy playerA, playerB;
  private JLabel playerAStrategy, playerBStrategy;
  private JLabel playerAName, playerBName;
  private int currentGamesPlayed, scoreA, scoreB;
  private javax.swing.Timer t;
  private JButton startButton, stopButton, pauseButton, nextButton;
  private JLabel playerAScore, playerBScore, matchesRemaining;
  private boolean paused;
  private JLabel currentMatchCounter;
  private int[][] winTable = {{1, 0, 2, 2, 0},
                              {2, 1, 0, 0, 2},
                              {0, 2, 1, 2, 0},
                              {0, 2, 0, 1, 2},
                              {2, 0, 2, 0, 1}};

  private RPSLSMatch[] matches;
  private int currentMatch;

  public RPSLSGUI(RPSLSMatch[] matchArray) {
    matches = matchArray;
    currentMatch = 0;
    playerA = matches[currentMatch].getPlayer1();
    playerB = matches[currentMatch].getPlayer2();

    playerAStrategy = new JLabel(playerA.strategyName(), JLabel.CENTER);
    playerBStrategy = new JLabel(playerB.strategyName(), JLabel.CENTER);

    playerAName = new JLabel(playerA.coderName(), JLabel.CENTER);
    playerBName = new JLabel(playerB.coderName(), JLabel.CENTER);

    playerAScore = new JLabel("00000", JLabel.CENTER);
    playerBScore = new JLabel("00000", JLabel.CENTER);
    currentMatchCounter = new JLabel("Current match " + currentMatch + " of " +
                                         (matches.length - 1),
                                     JLabel.CENTER);
    matchesRemaining = new JLabel(
        "Games remaining: " + (NUM_GAMES - currentGamesPlayed), JLabel.CENTER);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(2, 1));
    centerPanel.add(currentMatchCounter);
    centerPanel.add(matchesRemaining);

    JPanel scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout(1, 3));
    scorePanel.add(playerAScore);
    scorePanel.add(centerPanel);
    scorePanel.add(playerBScore);

    JPanel playerAPanel = new JPanel();
    playerAPanel.setLayout(new GridLayout(2, 1));
    playerAPanel.add(playerAStrategy);
    playerAPanel.add(playerAName);

    JPanel playerBPanel = new JPanel();
    playerBPanel.setLayout(new GridLayout(2, 1));
    playerBPanel.add(playerBStrategy);
    playerBPanel.add(playerBName);

    startButton = new JButton("Start");
    startButton.addActionListener(new StartButtonListener());
    stopButton = new JButton("Stop");
    stopButton.addActionListener(new StopButtonListener());
    pauseButton = new JButton("Pause");
    pauseButton.addActionListener(new PauseButtonListener());
    nextButton = new JButton("Next");
    nextButton.addActionListener(new NextButtonListener());
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(4, 1));
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(nextButton);

    JPanel playersPanel = new JPanel();
    playersPanel.setLayout(new GridLayout(1, 3));
    playersPanel.add(playerAPanel);
    playersPanel.add(buttonPanel);
    playersPanel.add(playerBPanel);
    playersPanel.setSize(200, 600);

    setLayout(new GridLayout(3, 1));
    add(playersPanel);
    add(scorePanel);

    gameProgress = new JProgressBar(0, NUM_GAMES);
    playerWinProgress = new JProgressBar(0, 100); // testing percentages
    playerWinProgress.setForeground(Color.RED);
    playerWinProgress.setBackground(
        Color.BLUE); // trying background and foreground

    JPanel progressPanel = new JPanel();
    progressPanel.setLayout(new GridLayout(2, 1));
    progressPanel.add(playerWinProgress);
    progressPanel.add(gameProgress);

    add(progressPanel, BorderLayout.PAGE_END);

    t = new javax.swing.Timer(1, new TimerListener());

    currentGamesPlayed = 0;
    scoreA = 0;
    scoreB = 0;
    paused = false;

    playerAStrategy.setText(playerA.strategyName());
    playerBStrategy.setText(playerB.strategyName());

    playerAName.setText(playerA.coderName());
    playerBName.setText(playerB.coderName());

    playerAScore.setText("00000");
    playerBScore.setText("00000");

    matchesRemaining.setText("Games remaining: " + NUM_GAMES);
    currentMatchCounter.setText("Current match " + currentMatch + " of " +
                                (matches.length - 1));

    playerWinProgress.setValue(50);
    gameProgress.setValue(0);
  }

  public void reset(RPSLSStrategy strat1, RPSLSStrategy strat2) {
    t = new javax.swing.Timer(1, new TimerListener());

    currentGamesPlayed = 0;
    scoreA = 0;
    scoreB = 0;
    paused = false;

    playerA = strat1;
    playerB = strat2;

    playerAStrategy.setText(playerA.strategyName());
    playerBStrategy.setText(playerB.strategyName());

    playerAName.setText(playerA.coderName());
    playerBName.setText(playerB.coderName());

    playerAScore.setText("00000");
    playerBScore.setText("00000");

    matchesRemaining.setText("Games remaining: " + NUM_GAMES);
    currentMatchCounter.setText("Current match " + currentMatch + " of " +
                                (matches.length - 1));

    playerWinProgress.setValue(50);
    gameProgress.setValue(0);

    playerA.reset();
    playerB.reset();

    Container c = this.getParent();
    while (!(c instanceof JFrame)) {
      c = c.getParent();
    }
    c.setVisible(false);
    c.setVisible(true);
    setFontSizes();
  }

  public void outputResults() {
    try {
      FileWriter fw = new FileWriter(OUTPUT_FILE, true);
      fw.write("\"" + playerA.coderName() + "\",\"" + scoreA + "\",\"" +
               playerB.coderName() + "\",\"" + scoreB + "\"\n");
      fw.close();
    } catch (IOException ioe) {
      System.err.println("IOException: " + ioe.getMessage());
    }
  }

  public void setFontSizes() {
    createFont(playerAStrategy);
    createFont(playerBStrategy);
    createFont(playerAName);
    createFont(playerBName);
    createFont(playerAScore);
    createFont(playerBScore);
    createFont(matchesRemaining);
    createFont(currentMatchCounter);
  }

  private class StartButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      reset(playerA, playerB);
      t.start();
    }
  }

  private void createFont(JLabel label) {
    Font labelFont = label.getFont();
    String labelText = label.getText();

    int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
    int componentWidth = label.getWidth();

    // Find out how much the font can grow in width.
    double widthRatio = (double)componentWidth / (double)stringWidth;

    int newFontSize = (int)(labelFont.getSize() * widthRatio);
    int componentHeight = label.getHeight();

    // Pick a new font size so it will not be larger than the height of label.
    int fontSizeToUse = Math.min(newFontSize, componentHeight);

    // Set the label's font size to the newly determined size.
    label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
  }

  private class StopButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) { t.stop(); }
  }

  private class PauseButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (pauseButton.getText().equals("Pause")) {
        paused = true;
        pauseButton.setText("Resume");
      } else {
        paused = false;
        pauseButton.setText("Pause");
      }
    }
  }

  private class NextButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      currentMatch++;
      if (currentMatch >= matches.length)
        return;
      reset(matches[currentMatch].getPlayer1(),
            matches[currentMatch].getPlayer2());
    }
  }

  private class TimerListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (!paused) {
        for (int i = 0; i < UPDATE_STEP; i++) {
          currentGamesPlayed++;
          // throw rolls for A and B and store for next game
          boolean aError = false, bError = false;
          int rollA = 0, rollB = 0;
          try {
            rollA = playerA.getThrow();
          } catch (Exception ex) {
            aError = true;
          }
          if (rollA < 0 || rollA > 4)
            aError = true;
          try {
            rollB = playerB.getThrow();
          } catch (Exception ex) {
            bError = true;
          }
          if (rollB < 0 || rollB > 4)
            bError = true;
          try {
            playerA.opponentsLastThrow(rollB);
          } catch (Exception ex) {
          }
          try {
            playerB.opponentsLastThrow(rollA);
          } catch (Exception ex) {
          }

          // update win totals
          if (aError && bError) {
            scoreA += 1;
            scoreB += 1;
          } else if (aError) {
            scoreB += 2;
          } else if (bError) {
            scoreA += 2;
          } else {
            scoreA += winTable[rollA][rollB];
            scoreB += winTable[rollB][rollA];
          }
        }

        gameProgress.setValue(currentGamesPlayed);
        matchesRemaining.setText("Games remaining: " +
                                 (NUM_GAMES - currentGamesPlayed));
        playerWinProgress.setValue(
            (int)((double)scoreA / (2 * currentGamesPlayed) * 100));
        playerAScore.setText("" + scoreA);
        playerBScore.setText("" + scoreB);

        if (currentGamesPlayed >= NUM_GAMES) {
          t.stop();
          outputResults();
          // update the three progress bars and scores (using labels for score
          // makes this super slow) playerAScore.setText("" + scoreA);
          // playerBScore.setText("" + scoreB);
        }
      }
    }
  }
}
