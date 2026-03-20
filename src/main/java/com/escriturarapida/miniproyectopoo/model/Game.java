package com.escriturarapida.miniproyectopoo.model;

/**
 * Represents the core game logic and state for the Fast Typing Game.
 * <p>
 * This class is responsible for managing the internal state of the game,
 * including the current level, the number of completed levels, the time
 * limit for each round, and whether the game has finished.
 * <p>
 * The difficulty of the game increases progressively. Every five completed
 * levels the time limit is reduced, making the game more challenging for the
 * player.
 * <p>
 * This class does not interact with the user interface directly.
 * Instead, it is used by the controller layer to manage the game rules.
 *
 * @author Abrahan Ybañez 2418114-3743
 * @version 1.0
 */
public class Game {

    /**
     * Current level the player is attempting.
     */
    private int currentLevel;

    /**
     * Total number of successfully completed levels.
     */
    private int completedLevels;

    /**
     * Time limit (in seconds) allowed to complete the current level.
     */
    private int currentTimeLimit;

    /**
     * Text that the player must type during the current round.
     */
    private String currentText;

    /**
     * Indicates whether the game has ended.
     */
    private boolean gameOver;

    /**
     * Creates a new game with default initial values.
     */
    public Game() {
        reset();
    }

    /**
     * Resets the whole game to its initial state.
     * <p>
     * This method restores all attributes to their default values:
     * level 1, zero completed levels, the initial time limit,
     * an empty target text, and the game marked as active.
     */
    public void reset() {
        this.currentLevel = 1;
        this.completedLevels = 0;
        this.currentTimeLimit = 20;
        this.currentText = "";
        this.gameOver = false;
    }

    /**
     * Sets the target text that the player must type in the current round.
     *
     * @param currentText the text that must be typed by the player
     */
    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    /**
     * Advances the game to the next level after a successful attempt.
     * <p>
     * This method increments the number of completed levels and the
     * current level. Additionally, every five completed levels the
     * available time is reduced to increase the difficulty of the game.
     * <p>
     * The minimum allowed time limit is 2 seconds.
     */
    public void advanceLevel() {
        completedLevels++;
        currentLevel++;

        if (completedLevels % 5 == 0 && currentTimeLimit > 2) {
            currentTimeLimit = Math.max(2, currentTimeLimit - 2);
        }
    }

    /**
     * Marks the game as finished.
     */
    public void endGame() {
        this.gameOver = true;
    }

    /**
     * Returns the current level of the game.
     *
     * @return current level number
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Returns the amount of completed levels by the player.
     *
     * @return completed levels
     */
    public int getCompletedLevels() {
        return completedLevels;
    }

    /**
     * Returns the current time limit for the level.
     *
     * @return current time limit in seconds
     */
    public int getCurrentTimeLimit() {
        return currentTimeLimit;
    }

    /**
     * Returns the target text for the current round.
     *
     * @return the text the player must type
     */
    public String getCurrentText() {
        return currentText;
    }

    /**
     * Indicates whether the game is over.
     *
     * @return true if the game is over; false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }
}