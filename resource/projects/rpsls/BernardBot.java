package resource.projects.rpsls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * <h1>BernardBot</h1>
 * 
 * <a>
 * This is a class that contains a bot that
 * will try to predict the move of the
 * opponent bot
 * Class: BernardBot
 * Strategy: BernardBot
 * </a>
 * 
 * @author Jack Meng
 * @version 1.1
 * @since 2021-12-22
 * @brief This is a bot that requires a special game board in order to be
 *          played:
 *          {@link RPSLSMatch},
 *          {@link RPSLSGUI},
 *          {@link RPSLSStrategy},
 *          {@link RPSLSMatch},
 *          {@link RPSLSArena}
 * @see #BernardBot()
 */

public class BernardBot implements RPSLSStrategy {
  /**
   * Here are the structures that we
   * need in order to completely store
   * all the necessary informations to run
   * our bot
   */
  ArrayList<ArrayList<Integer>> history = new ArrayList<>();
  ArrayList<Status> status = new ArrayList<>();
  ArrayList<Integer> myHistyList = new ArrayList<>();
  double avgWin = 0;

  /**
   * First init constructor
   * {@code new BernardBot()}
   */
  public BernardBot() {
    init();
  }

  /**
   * <p>
   * This method initalizes everything
   * and can be called separately in order
   * to reset everything
   * </p>
   */
  private void init() {
    for (int i = 0; i < Throws.values().length; i++) {
      history.add(new ArrayList<>());
    }
  }

  static ArrayList<Integer> lastThrows = new ArrayList<>();

  /**
   * {@code Throws}
   * <p>
   * This enum stores all of the valid
   * throws and the rules of the bot/game
   */
  private enum Throws {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK;

    ArrayList<Throws> listItems = new ArrayList<>();

    public boolean rule(Throws t) {
      return listItems.contains(t);
    }

    /**
     * Here we set the rules
     * for each of the valid throws
     */
    static {
      SCISSORS.listItems = new ArrayList<>(Arrays.asList(PAPER, LIZARD));
      PAPER.listItems = new ArrayList<>(Arrays.asList(ROCK, SPOCK));
      ROCK.listItems = new ArrayList<>(Arrays.asList(SCISSORS, LIZARD));
      LIZARD.listItems = new ArrayList<>(Arrays.asList(SPOCK, PAPER));
      SPOCK.listItems = new ArrayList<>(Arrays.asList(ROCK, SCISSORS));
    }
  }

  /**
   * {@code Config(String)}
   * <p>
   * This enum represents
   * the configuration of this
   * bot, such as the bot name
   * and the coder name
   */
  private enum Config {
    CODER_NAME("Jack Meng"),
    STRAT_NAME("Bernard");

    private final String value;

    Config(String value) {
      this.value = value;
    }

    public String v() {
      return value;
    }
  }

  /**
   * {@code Status}
   * This enum is just to represent
   * the states of the game
   */
  private enum Status {
    WIN, LOSE, TIE, UNKNOWN;
  }

  /**
   * @see BernardBot.Config.STRAT_NAME
   * @return The name of my strategy
   */
  @Override
  public String strategyName() {
    return Config.STRAT_NAME.v();
  }

  /**
   * @see BernardBot.Config.CODER_NAME
   * @return The Coder Name which is Jack Meng in this case
   */
  @Override
  public String coderName() {
    return Config.CODER_NAME.v();
  }

  /** 
   * <p>
   * This method is a separate method like getThrow() that will
   * use another method to get the throw and then return it
   * 
   * This method shall never be used to be called directly
   * to get the throw.
   * </p>
   * @see #getThrow()
   * @see #calc()
   * @return int A number representing the throw
   */
  private int predictNextThrow() {
    int[] count = new int[Throws.values().length];
    for (int i = 0; i < history.size(); i++) {
      for (int j = 0; j < history.get(i).size(); j++) {
        count[history.get(i).get(j)]++;
      }
    }
    int max = 0;
    int index = 0;
    for (int i = 0; i < count.length; i++) {
      if (count[i] > max) {
        max = count[i];
        index = i;
      }
    }
    return index;

  }

  /**
   * <h3>Throws</h3>
   * <p>
   * This method is called to get what this bot should throw.
   * It will use a standard probability algorithim to check which throw would
   * yield the highest win ratio
   * </p>
   * 
   * <p>
   * The biggest part is that this bot will also attempt to break ties
   * and confuse the opponent if there are too many wins
   * </p>
   * 
   * @return A integer representing what throw was thrown
   */
  @Override
  public int getThrow() {
    if (lastThrows.isEmpty()) {
      int throwBest = (int) (Math.random() * Throws.values().length);
      myHistyList.add(throwBest);
      return throwBest;
    }
    if (lastThrows.size() > 0) {
      for (int i = 0; i < Throws.values().length; i++) {
        if (Throws.values()[i].rule(Throws.values()[lastThrows.get(lastThrows.size() - 1)])) {
          myHistyList.add(i);
          return i;
        }
      }
    }
    if (avgWin > 0.9) {
      int[] matrix = { 0, 0, 0, 0, 0 };
      for (int i = 0; i < history.size(); i++) {
        matrix[i] = history.get(i).size();
      }
      int max = 0;
      int maxIndex = 0;
      for (int i = 0; i < matrix.length; i++) {
        if (matrix[i] > max) {
          max = matrix[i];
          maxIndex = i;
        }
      }
      myHistyList.add(maxIndex);
      return maxIndex;
    }
    int nextIndex = 0;

    for (int i = 0; i < Throws.values().length; i++) {
      if (Throws.values()[i].rule(Throws.values()[lastThrows.get(lastThrows.size() - 1)])) {
        nextIndex = i;
        break;
      }
    }

    if (history.get(nextIndex).isEmpty()) {
      myHistyList.add(nextIndex);
      return nextIndex;
    }

    int max = 0;
    int maxIndex = 0;
    for (int i = 0; i < history.get(nextIndex).size(); i++) {
      if (history.get(nextIndex).get(i) > max) {
        max = history.get(nextIndex).get(i);
        maxIndex = i;
      }
    }
    myHistyList.add(maxIndex);
    if (history.get(nextIndex).size() > 0) {
      if (history.get(nextIndex).get(maxIndex) > 0) {
        return maxIndex;
      }
    }

    if (avgWin < 0.1) {
      int[] matrix = { 0, 0, 0, 0, 0 };
      for (int i = 0; i < history.size(); i++) {
        matrix[i] = history.get(i).size();
      }
      int max2 = 0;
      int maxIndex2 = 0;
      for (int i = 0; i < matrix.length; i++) {
        if (matrix[i] > max2) {
          max2 = matrix[i];
          maxIndex2 = i;
        }
      }
      myHistyList.add(maxIndex2);
      return maxIndex2;
    }

    return calc(maxIndex, predictNextThrow());

  }

  
  /** 
   * <p>
   * This method attempts to compare the values
   * of two throws and make it's own based on 
   * these moves, and compare it. It then makes a
   * decision based on the results of which
   * move to use.
   * </p>
   * @param maxIndex The calculation for the first type throw
   * @param predictNextThrow The calculation for the second type throw
   * @return int A throw that was decided on
   */
  private int calc(int maxIndex, int predictNextThrow) {
    int max = 0;
    int maxIndex2 = 0;
    for (int i = 0; i < history.get(predictNextThrow).size(); i++) {
      if (history.get(predictNextThrow).get(i) > max) {
        max = history.get(predictNextThrow).get(i);
        maxIndex2 = i;
      }
    }
    
    // check which move is the best with MaxIndex2 and MaxIndex and predictNextThrow
    int max3 = 0;
    int maxIndex3 = 0;
    for (int i = 0; i < history.get(predictNextThrow).size(); i++) {
      if (history.get(predictNextThrow).get(i) > max3) {
        max3 = history.get(predictNextThrow).get(i);
        maxIndex3 = i;
      }
    }
    if (history.get(maxIndex3).size() > 0) {
      if (history.get(maxIndex3).get(maxIndex) > 0) {
        return maxIndex3;
      }
    }
    if (history.get(maxIndex).size() > 0) {
      if (history.get(maxIndex).get(maxIndex2) > 0) {
        return maxIndex;
      }
    }
    if (history.get(predictNextThrow).size() > 0) {
      if (history.get(predictNextThrow).get(maxIndex2) > 0) {
        return predictNextThrow;
      }
    }

    if (history.get(maxIndex2).size() > 0) {
      if (history.get(maxIndex2).get(maxIndex) > 0) {
        return maxIndex2;
      }
    }

    return maxIndex;

  }

  
  /** 
   * <p>
   * This method is used to interact with the game board.
   * We specifically here add the lastThrow to the history
   * 
   * We also have to update our winrate so that that we can
   * correlate everything so we have a good and accurate
   * chance of winning (maybe)
   * </p>
   * @param lastThrow This is the last throw that was thrown by the opponent
   */
  @Override
  public void opponentsLastThrow(int lastThrow) {
    lastThrows.add(lastThrow);
    // update the avgWin if we won that round or we lost that round
    if (lastThrows.size() > 1) {
      if (Objects.equals(lastThrows.get(lastThrows.size() - 1), lastThrows.get(lastThrows.size() - 2))) {
        avgWin = (avgWin + 0.5) / 2;
      } else {
        avgWin = (avgWin + 0) / 2;
      }
    }
  }

  @Override
  public void reset() {
    // Method not used
  }

}
