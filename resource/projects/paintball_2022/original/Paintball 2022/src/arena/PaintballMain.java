/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arena;

import w_brains.*;
import brains.*; //put your brain in this package. gives an error until you do so
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;
import javax.swing.Timer;
//import private_brains.*;
//import w_brains.RandoBot;

/**
 *
 * @author tweis0306
 */
public class PaintballMain extends javax.swing.JFrame {
    
    private Board board;
    private Player[][] team;
    private int turn;
    private Random randGen;
    private Timer timer;
    
    /**
     * Creates new form PaintballMain
     */
    public PaintballMain() {
        initComponents();
        randGen = new Random();
        reset();
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                step();
            }
        };
        timer = new Timer(1001 - speedSlider.getValue(), taskPerformer);
    }
    
    private void oneVOne() {
        team = new Player[3][];
        team[1] = new Player[10];
        team[2] = new Player[10];
        for (int i = 0; i < team[1].length; i++)
        {
            team[1][i] = new Player(1, new DumbBaseCharger());
            team[2][i] = new Player(2, new DumbBaseCharger());
        }
        
    }
    
    private void tournament()
    {
        Brain[] players = {new DumbBaseCharger(), new DumbBaseCharger(), new DumbBaseCharger(),
        new DumbBaseCharger(), new DumbBaseCharger(), new DumbBaseCharger(), new DumbBaseCharger(),
        new DumbBaseCharger(), new DumbBaseCharger(), new DumbBaseCharger()};
        PaintballMatch[] matches = new PaintballMatch[players.length*(players.length - 1) / 2];
        for (int p1 = 0, i = 0; p1 < players.length-1; p1++)
            for (int p2 = p1 + 1; p2 < players.length; p2++, i++)
                matches[i] = new PaintballMatch(players[p1], players[p2]);
        for (int i = matches.length-1; i > 0; i--) {
            int swap = (int) (Math.random() * (i+1));
            PaintballMatch temp = matches[i];
            matches[i] = matches[swap];
            matches[swap] = temp;
        }
    }
    
    //roster-filling methods
    
    //each team has a pair of each bot
    private void fillNormal2Teams() {
        team = new Player[3][];
        team[1] = new Player[10];
        team[2] = new Player[10];
        team[1][0] = new Player(1, new DumbBaseCharger());
        team[1][1] = new Player(1, new DumbBaseCharger());
        team[1][2] = new Player(1, new DumbBaseCharger());
        team[1][3] = new Player(1, new DumbBaseCharger());
        team[1][4] = new Player(1, new DumbBaseCharger());
        team[1][5] = new Player(1, new DumbBaseCharger());
        team[1][6] = new Player(1, new DumbBaseCharger());
        team[1][7] = new Player(1, new DumbBaseCharger());
        team[1][8] = new Player(1, new DumbBaseCharger());
        team[1][9] = new Player(1, new DumbBaseCharger());
        
        team[2][0] = new Player(2, new DumbBaseCharger());
        team[2][1] = new Player(2, new DumbBaseCharger());
        team[2][2] = new Player(2, new DumbBaseCharger());
        team[2][3] = new Player(2, new DumbBaseCharger());
        team[2][4] = new Player(2, new DumbBaseCharger());
        team[2][5] = new Player(2, new DumbBaseCharger());
        team[2][6] = new Player(2, new DumbBaseCharger());
        team[2][7] = new Player(2, new DumbBaseCharger());
        team[2][8] = new Player(2, new DumbBaseCharger());
        team[2][9] = new Player(2, new DumbBaseCharger());
    }
    
    //each team contains one of each bot
    private void fill2V2()
    {
        team = new Player[3][];
        team[1] = new Player[10];
        team[2] = new Player[10];
        for (int i = 0; i < team[1].length - 1; i+=2)
        {
            team[1][i] = new Player(1, new DumbBaseCharger());
            team[2][i] = new Player(2, new DumbBaseCharger());
            team[1][i+1] = new Player(1, new DumbBaseCharger());
            team[2][i+1] = new Player(2, new DumbBaseCharger());
        }
    }
    
    //makes 2 random teams
    private void fillRandom2Teams() {
        team = new Player[3][];
        team[1] = new Player[12];
        team[2] = new Player[10];
        java.util.List players = new ArrayList<Player>();
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        players.add(new DumbBaseCharger());
        team[1][0] = new Player(1, (Brain)players.remove((int)(Math.random()*players.size())));
        team[2][0] = new Player(2, (Brain)players.remove((int)(Math.random()*players.size())));
        team[1][1] = new Player(1, (Brain)players.remove((int)(Math.random()*players.size())));
        team[2][1] = new Player(2, (Brain)players.remove((int)(Math.random()*players.size())));
        team[1][2] = new Player(1, (Brain)players.remove((int)(Math.random()*players.size())));
        team[2][2] = new Player(2, (Brain)players.remove((int)(Math.random()*players.size())));
        team[1][3] = new Player(1, (Brain)players.remove((int)(Math.random()*players.size())));
        team[2][3] = new Player(2, (Brain)players.remove((int)(Math.random()*players.size())));
        team[1][4] = new Player(1, (Brain)players.remove((int)(Math.random()*players.size())));
        team[2][4] = new Player(2, (Brain)players.remove((int)(Math.random()*players.size())));
        team[1][5] = new Player(1, (Brain)players.remove((int)(Math.random()*players.size())));
        //team[2][5] = new Player(2, (Brain)players.remove((int)(Math.random()*players.size())));
        //team[1][12] = new Player(1, (Brain)players.remove((int)(Math.random()*players.size())));
        team[1][6] = new Player(1, team[1][0].getController());
        team[2][5] = new Player(2, team[2][0].getController());
        team[1][7] = new Player(1, team[1][1].getController());
        team[2][6] = new Player(2, team[2][1].getController());
        team[1][8] = new Player(1, team[1][2].getController());
        team[2][7] = new Player(2, team[2][2].getController());
        team[1][9] = new Player(1, team[1][3].getController());
        team[2][8] = new Player(2, team[2][3].getController());
        team[1][10] = new Player(1, team[1][4].getController());
        team[2][9] = new Player(2, team[2][4].getController());
        team[1][11] = new Player(1, team[1][5].getController());
        //team[2][11] = new Player(2, team[2][5].getController());
        //team[1][13] = new Player(1, team[1][12].getController());
    }
    
    
    //pick the fill method you want to use by uncommenting it
    private void reset() {
        //fill team rosters
        //fillSammyVsWeiting();
        //fillNormal2Teams();
        //fillRandom2Teams();
        oneVOne();
        //fill2V2();
        //create game board
        board = new Board(33, 50);
        fieldPanel.setMyBoard(board);

        //reset scores
        board.resetScores();

        
        //place bases
        Base blackBase = new Base(1);
        blackBase.addSelfToBoard(board, 16, 0);
        Base redBase = new Base(2);
        redBase.addSelfToBoard(board, 16, 49);
        
        //place blocks
        new Blocker().addSelfToBoard(board, 12, 0);
        new Blocker().addSelfToBoard(board, 20, 0);
        new Blocker().addSelfToBoard(board, 16, 4);
        new Blocker().addSelfToBoard(board, 12, 4);
        new Blocker().addSelfToBoard(board, 20, 4);
        new Blocker().addSelfToBoard(board, 12, 49);
        new Blocker().addSelfToBoard(board, 20, 49);
        new Blocker().addSelfToBoard(board, 16, 45);
        new Blocker().addSelfToBoard(board, 12, 45);
        new Blocker().addSelfToBoard(board, 20, 45);
        final int NUM_BLOCKS_PER_SIDE = 50;
        for (int i = 0; i < NUM_BLOCKS_PER_SIDE; ) {
            int row = randGen.nextInt(33);
            int col = randGen.nextInt(25);
            if (board.isEmpty(row, col) &&
                    (col > 4 || row < 12 || row > 20)) {
                new Blocker().addSelfToBoard(board, row, col);
                new Blocker().addSelfToBoard(board, 32-row, 49-col);
                i++;
            }
        }
        
        //put players on board
        for (int t = 1; t <= 2; t++) {
            for (int p = 0; p < team[t].length; p++) {
                team[t][p].respawn(board);
            }
        }
        
        repaint();
        
        //reset turn counter
        turn = 0;
    }
    
    private void step() {
        team1Label.setText("Black team: " + board.getScore(1));
        team2Label.setText("Red team: " + board.getScore(2));
        if (turn % 3 == 0) { //players act
            //Randomize players
            ArrayList<Player> pList1 = new ArrayList<>();
            for (Player p: team[1])
                pList1.add(p);
            for (Player p: team[2])
                pList1.add(p);
            ArrayList<Player> pList = new ArrayList<>();
            while (!pList1.isEmpty())
                pList.add(pList1.remove(randGen.nextInt(pList1.size())));
            //players act in turn
            for (Player p: pList) {
                if (p.getMyBoard() == null)
                    p.respawn(board);
                else
                    p.act();
            }
        } else { //shots act
            ArrayList<Shot> shotList = new ArrayList<>();
            for (int r = 0; r < 33; r++) {
                for (int c = 0; c < 50; c++) {
                    if (board.get(r, c) instanceof Shot)
                        shotList.add((Shot) board.get(r, c));
                }
            }
            while (!shotList.isEmpty())
                shotList.remove(randGen.nextInt(shotList.size())).move();
        }
        turn++;
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fieldPanel = new arena.FieldPanel();
        team2Label = new javax.swing.JLabel();
        team1Label = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();
        statsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout fieldPanelLayout = new javax.swing.GroupLayout(fieldPanel);
        fieldPanel.setLayout(fieldPanelLayout);
        fieldPanelLayout.setHorizontalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1001, Short.MAX_VALUE)
        );
        fieldPanelLayout.setVerticalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );

        team2Label.setText("jLabel1");

        team1Label.setText("jLabel1");

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        speedSlider.setMaximum(1000);
        speedSlider.setValue(500);
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });
        speedSlider.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                speedSliderPropertyChange(evt);
            }
        });

        statsButton.setText("Stats");
        statsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(team1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(team2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(team2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(team1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(resetButton)
                        .addComponent(stepButton)
                        .addComponent(runButton)
                        .addComponent(statsButton))
                    .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        reset();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        step();
    }//GEN-LAST:event_stepButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        if (runButton.getText().equals("Run")) {
            runButton.setText("Pause");
            timer.start();
        } else {
            runButton.setText("Run");
            timer.stop();
        }
    }//GEN-LAST:event_runButtonActionPerformed

    private void speedSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_speedSliderPropertyChange
        if (timer != null)
            timer.setDelay(1001 - speedSlider.getValue());
    }//GEN-LAST:event_speedSliderPropertyChange

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        if (timer != null)
            timer.setDelay(1001 - speedSlider.getValue());
    }//GEN-LAST:event_speedSliderStateChanged

    private void statsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsButtonActionPerformed
        int nameFieldWidth = 0, coderFieldWidth = 0;
        for (int t = 1; t <= 2; t++) {
            for (int i = 0; i < team[t].length; i++) {
                if (team[t][i].getController().getName().length() > nameFieldWidth)
                    nameFieldWidth = team[t][i].getController().getName().length();
                if (team[t][i].getController().getCoder().length() > coderFieldWidth)
                    coderFieldWidth = team[t][i].getController().getCoder().length();
            }   
        }
        for (int t = 1; t <= 2; t++) {
            System.out.println("\nTeam " + t + ":");
            String formatStr = "%" + nameFieldWidth +
                    "s %" + coderFieldWidth +
                    "s %5s %5s %6s %6s %4s %5s\n";
            System.out.printf(formatStr, "Name", "Coder",
                    "kills", "frags", "deaths", "enemyB", "ownB",
                    "score");
            for (int i = 0; i < team[t].length; i++) {
                Player p = team[t][i];
                System.out.printf(formatStr, 
                        p.getController().getName(),
                        p.getController().getCoder(),
                        p.getKills(), p.getFrags(),
                        p.getDeaths(), p.getEnemyBaseHits(),
                        p.getSelfBaseHits(), p.getScore());
            }
        }
    }//GEN-LAST:event_statsButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaintballMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaintballMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaintballMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaintballMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaintballMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private arena.FieldPanel fieldPanel;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton runButton;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton statsButton;
    private javax.swing.JButton stepButton;
    private javax.swing.JLabel team1Label;
    private javax.swing.JLabel team2Label;
    // End of variables declaration//GEN-END:variables
}
